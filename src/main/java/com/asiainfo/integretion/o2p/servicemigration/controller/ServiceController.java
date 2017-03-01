package com.asiainfo.integretion.o2p.servicemigration.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.integration.o2p.session.web.http.CookieUtil;
import com.asiainfo.integretion.o2p.servicemigration.common.Constant;
import com.asiainfo.integretion.o2p.servicemigration.common.util.BusinessUtil;
import com.asiainfo.integretion.o2p.servicemigration.common.util.FileUtil;
import com.asiainfo.integretion.o2p.servicemigration.common.util.JaxbUtil;
import com.asiainfo.integretion.o2p.servicemigration.common.util.XMLValidateUtil;
import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBean;
import com.asiainfo.integretion.o2p.servicemigration.domain.CommonUtil;
import com.asiainfo.integretion.o2p.servicemigration.domain.EndpointSpec;
import com.asiainfo.integretion.o2p.servicemigration.domain.Service;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceMigrationLog;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;
import com.asiainfo.integretion.o2p.servicemigration.smo.IExportService;
import com.asiainfo.integretion.o2p.servicemigration.smo.IImportService;
import com.asiainfo.integretion.o2p.servicemigration.smo.IMigrationService;
import com.asiainfo.integretion.o2p.servicemigration.smo.IServiceSmo;

@Controller
@RequestMapping("/service")
public class ServiceController {

	private static final Log LOG = LogFactory.getLog(ServiceController.class);
	@Resource(name = "ServiceSmo")
	private IServiceSmo serviceSmo;
	@Resource(name = "migrationService")
	private IMigrationService migrationService;
	@Resource(name = "exportService")
	private IExportService exportService;
	@Resource(name="importService")
	private IImportService importService;

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@RequestMapping(value="/queryServicePage.do")
	@ResponseBody
	public Map<String, Object> queryServicePage(HttpServletRequest request) throws UnsupportedEncodingException {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			String tenantObj =CookieUtil.getTenantId(request);
			if(tenantObj==null || "".equals(tenantObj)){
				LOG.error("Can not get tenant id from session!");
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("error", "ERROR: Can not get tenant id from session!");
				return result;
			}
			Integer tenantId = Integer.valueOf(tenantObj);
			params.put("tenantId", tenantId);
			String serviceName = request.getParameter("name")==null?null:(new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8"));
			String style = request.getParameter("style");
			if(StringUtils.hasText(serviceName)) {
				params.put("name", serviceName);
			}
			if(StringUtils.hasText(style)) {
				params.put("style", style);
			}
			List<Service> services = serviceSmo.queryServicesPage(params);
	
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("data", services);
			return result;
		}catch(Exception e){
			LOG.error("runtime error!", e);
			Map<String, Object> result = new HashMap<String, Object>();
			if(e instanceof BusinessException){
				result.put("error", ((BusinessException)e).getResult());
			}
			result.put("error", "ERROR: code=10000000");
			return result;
		}
	}
	
	@RequestMapping("recovery")
	@ResponseBody
	public String recovery(HttpServletRequest request, HttpServletResponse response) {
		try{
			String tenantObj =CookieUtil.getTenantId(request);
			if(tenantObj==null){
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("sign", "old");
			String oldDoc = migrationService.queryLogDoc(params);
			ServiceObject so = JaxbUtil.unmarshal(new ByteArrayInputStream(oldDoc.getBytes("UTF-8")));
			so.setTenantId(Integer.valueOf(tenantObj));
			ServiceObject newSo = (ServiceObject)JSONObject.toBean(JSONObject.fromObject(importService.compareServiceObject(so).get("new")));
			
			newSo.setTenantId(Integer.valueOf(tenantObj));
			String result = importService.importService(newSo);
			String id = request.getParameter("id");
			params.put("id", id);
			if(Constant.SUCCESS.equals(result)){
				migrationService.setLogStatus(id, "R");
			}
			return result;
		}catch(Exception e){
			LOG.error("runtime error!", e);
			if(e instanceof BusinessException){
				return "errorCode=" + ((BusinessException)e).getResult().getCode();
			}
			return "10XXXXXXX";
		}
	}
	
	@RequestMapping("download.do")
	public String export(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		String serviceCodes = request.getParameter("serviceCodes");
		if(serviceCodes==null || "".equals(serviceCodes)){
			return "export";
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serviceCodes", serviceCodes);
		params.put("tenantId", CookieUtil.getTenantId(request));
		ServiceObject so = exportService.getServices(params);
		if(params.get("exportError") != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> errorResult = (Map<String, Object>) params.get("exportError");
			request.setAttribute("exportError", errorResult);
			return "export";
		} else {
			response.setContentType("application/octet-stream");  
	        response.setHeader("Content-disposition", "attachment; filename=ServiceObject.xml");
			JaxbUtil.marshal(so, response.getWriter());
			return null;
		}
	}
	
	@RequestMapping("downloadLogDoc.do")
	public String exportLogDoc(HttpServletRequest request,  
            HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String sign = request.getParameter("sign");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("sign", sign);
		String doc = migrationService.queryLogDoc(params);
		response.setContentType("application/octet-stream");  
        response.setHeader("Content-disposition", "attachment; filename=service_obj_doc_"+sign+".xml");
        response.getWriter().write(doc);
		return null;
	}

	@RequestMapping("import")
	public String serviceImport(Map<String, Object> map) {
		return "import";
	}

	@RequestMapping("export")
	public String serviceExport(Map<String, Object> map) {
		return "export";
	}
	
	@RequestMapping("logs")
	public String serviceLogs(Map<String, Object> map) {
		return "logs";
	}
	
	@RequestMapping(value="/queryLogsPage.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> queryLogsPage(HttpServletRequest request) {
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			Object tenantObj = CookieUtil.getTenantId(request);
			if(tenantObj==null){
				return null;
			}
			Integer tenantId = Integer.valueOf(tenantObj.toString());
			params.put("tenantId", tenantId);

			String operator = request.getParameter("operator")==null?null:(new String(request.getParameter("operator").getBytes("ISO-8859-1"),"UTF-8"));;
			String fromTime = request.getParameter("fromTime");
			String toTime = request.getParameter("toTime");
			if(StringUtils.hasText(operator)) {
				params.put("operator", operator);
			}
			if(StringUtils.hasText(fromTime)) {
				params.put("fromTime", CommonUtil.strToDate(fromTime, "yyyy-MM-dd"));
			}
			if(StringUtils.hasText(toTime)) {
				params.put("toTime", CommonUtil.strToDate(toTime, "yyyy-MM-dd"));
			}
			List<ServiceMigrationLog> logs = migrationService.queryLogsPage(params);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("data", logs);
			return result;
		}catch(Exception e){
			LOG.error("runtime error!", e);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("error", "ERROR: " + e.toString());
			return result;
		}
	}
	
	@RequestMapping(value="/xsdCheck", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String xsdCheck(@PathVariable("file") MultipartFile file ,Map<String,Object> map, HttpServletRequest request) {
		try{
			String tenantObj = CookieUtil.getTenantId(request);
			if(tenantObj==null){
				LOG.error("session time out!");
				return "The session is time out, please login and try again!";
			}
			if(file.getSize()==0 || file.getName()==null){
				return null;
			}
			if(!FileUtil.getFileExtension(file.getOriginalFilename()).equalsIgnoreCase("xml")){
				map.put("checkResult", "failed");
				map.put("info", "Invald file! Please choose a xml file!");
				return JSONObject.fromObject(map).toString();
			}
			//check size
			if(file.getSize()>10485760){
				map.put("checkResult", "failed");
				map.put("info", "the request was rejected, because its size (" + file.getSize() + ") exceeds the configured maximum (10485760)");
				return JSONObject.fromObject(map).toString();
			}
			InputStream is = file.getInputStream();
			String rp = request.getSession().getServletContext().getRealPath("/");
			String realPath = (rp==null?null:(rp + "/WEB-INF/classes/O2P-BMO.xsd"));
			String xsdValidateResult = XMLValidateUtil.validateXml(realPath, is);
			if(!Constant.SUCCESS.equals(xsdValidateResult)){
				map.put("checkResult", "failed");
				map.put("info", xsdValidateResult.replace("org.xml.sax.SAXParseException", file.getOriginalFilename()).replace("cvc-complex-type.2.4.a: ", ""));
				return JSONObject.fromObject(map).toString();
			}
			if(!BusinessUtil.businessValidate()){
				map.put("checkResult", "invalid");
				return JSONObject.fromObject(map).toString();
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        try {
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\r\n");
	            }
	        } catch (IOException e) {   
	           LOG.error("IOException", e);
	        } finally {   
	            try {   
	            	reader.close();
	                is.close();   
	            } catch (IOException e) {   
	            	LOG.error("IOException", e);
	            }   
	        }
			//检查对象是否冲突并重构
			ServiceObject so = JaxbUtil.unmarshal(file.getInputStream());
			so.setTenantId(Integer.valueOf(tenantObj));
			request.getSession().setAttribute("serviceObjectMap", importService.compareServiceObject(so));
			so.attrTransform(false);
			List<String> serviceCodes = new ArrayList<String>();
			for(Service service : so.getServices()){
				serviceCodes.add(service.codeValue());
			}
			
//			map.put("addList", getObjectListByAction(so, Constant.ACTION_A));
//			map.put("updateList", getObjectListByAction(so, Constant.ACTION_M));
//			map.put("removeList", getObjectListByAction(so, Constant.ACTION_R));
			pickUpByActionType(so, map);
			map.put("checkResult", "success");
			map.put("serviceCodes", serviceCodes);
			return JSONObject.fromObject(map).toString();
		}catch(Exception e){
			LOG.error("runtime error!", e);
			map.put("checkResult", "failed");
			map.put("info", e.getMessage());
			return JSONObject.fromObject(map).toString();
		}
	}
	
	public void pickUpByActionType(ServiceObject so, Map<String,Object> map){
		Map<String, BasedBean> objectMap = new HashMap<String, BasedBean>();
		so.getObjectMap(objectMap);
		if(objectMap==null || objectMap.isEmpty()){
			return;
		}
		List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> updateList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> removeList = new ArrayList<Map<String, Object>>();
		for(Map.Entry<String, BasedBean> kv :  objectMap.entrySet()){
			BasedBean basedBean = kv.getValue();
			if(basedBean.codeValue()==null || "".equals(basedBean.codeValue()) || basedBean instanceof EndpointSpec){
				continue;
			}
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("name", basedBean.getClass().toString().substring(basedBean.getClass().toString().lastIndexOf(".")+1));
			temp.put("code", basedBean.codeValue());
			if(basedBean.getAttributes()==null){
				basedBean.setAttributes(new HashMap<String, Object>());
			}
			if(basedBean.getAttributes().get("oldObject")==null){
				temp.put("detail", basedBean.toString());
			}else{
				Map<String, Object> updateMap = new HashMap<String, Object>();
				updateMap.put("newObj", basedBean.toString());
				updateMap.put("oldObj", basedBean.getAttributes().get("oldObject").toString());
				temp.put("detail", updateMap);
			}
			if(Constant.ACTION_A.equals(basedBean.getAction())){
				addList.add(temp);
			}else if(Constant.ACTION_M.equals(basedBean.getAction())){
				updateList.add(temp);
			}else if(Constant.ACTION_R.equals(basedBean.getAction())){
				removeList.add(temp);
			}
		}
		map.put("addList", addList);
		map.put("updateList", updateList);
		map.put("removeList", removeList);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("importAction")
	@ResponseBody
	@Before("execution(* org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.handle(..))")
	public String importAction(HttpServletRequest request) {
		try{
			Object obj = request.getSession().getAttribute("serviceObjectMap");
			if(obj==null){
				return "No service for import, please check! ";
			}
			ServiceObject serviceObject = ((Map<String, ServiceObject>)obj).get("new");
			
			serviceObject.setTenantId(Integer.valueOf(CookieUtil.getTenantId(request)));

			return importService.importService(serviceObject);
		}catch(Exception e){
			LOG.error("runtime error!", e);
			if(e instanceof BusinessException){
				return String.valueOf("Import Failed! errorCode=" + ((BusinessException)e).getResult().getCode());
			}else{
				return String.valueOf("Import Failed! errorCode=10010000");
			}
		}finally{
			request.getSession().removeAttribute("serviceObjectMap");
		}
	}
}
