package com.asiainfo.integretion.o2p.servicemigration.smo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.jms.IllegalStateException;
import javax.xml.bind.annotation.XmlElements;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.integretion.o2p.servicemigration.common.Constant;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.BeforeMigration;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.Parent;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.ReferenceType;
import com.asiainfo.integretion.o2p.servicemigration.common.util.ReflectionUtil;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ApiInvokeMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.BaseMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.CheckMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ContractMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.DaoMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ServiceMapper;
import com.asiainfo.integretion.o2p.servicemigration.domain.Api;
import com.asiainfo.integretion.o2p.servicemigration.domain.AttrValue;
import com.asiainfo.integretion.o2p.servicemigration.domain.AuthenticationExpress;
import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBean;
import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBeanHelper;
import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBeanIdHelper;
import com.asiainfo.integretion.o2p.servicemigration.domain.CommonUtil;
import com.asiainfo.integretion.o2p.servicemigration.domain.Component;
import com.asiainfo.integretion.o2p.servicemigration.domain.Contract;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractDocument;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractFormat;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractFormatChoice;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractVersion;
import com.asiainfo.integretion.o2p.servicemigration.domain.Endpoint;
import com.asiainfo.integretion.o2p.servicemigration.domain.EndpointSpec;
import com.asiainfo.integretion.o2p.servicemigration.domain.NodeDesc;
import com.asiainfo.integretion.o2p.servicemigration.domain.Org;
import com.asiainfo.integretion.o2p.servicemigration.domain.Service;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceTechnologyImplement;
import com.asiainfo.integretion.o2p.servicemigration.domain.TechnologyImplement;
import com.asiainfo.integretion.o2p.servicemigration.domain.Transformer;
import com.asiainfo.integretion.o2p.servicemigration.domain.VariableMap;
import com.asiainfo.integretion.o2p.servicemigration.smo.ICheckExistService;
import com.asiainfo.integretion.o2p.servicemigration.smo.IImportService;

@org.springframework.stereotype.Service(value="importService")
@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
public class ImportServiceImpl implements IImportService{
	private static final Log log = LogFactory.getLog(ImportServiceImpl.class);
	
	@Resource(name="serviceMapper")
	private ServiceMapper serviceMapper;
	@Resource(name="baseMapper")
	private BaseMapper baseMapper;
	@Resource(name="checkMapper")
	private CheckMapper checkMapper;
	@Resource(name="daoMapper")
	private DaoMapper daoMapper;
	@Resource(name="basedBeanIdHelper")
	private BasedBeanIdHelper idHelper;
	@Resource(name="contractMapper")
	private ContractMapper contractMapper;
	@Resource(name="apiInvokeMapper")
	private ApiInvokeMapper apiInvokeMapper;
	@Resource(name="checkExistService")
	private ICheckExistService checkExistService;
	
	
//	@SuppressWarnings("unchecked")
//	public void checkObjectForRemove(BasedBean bb, Object newObject, String sql, Set<Object> set){
//		List<String> oldList = baseMapper.queryObjectCodeList(sql);
//		if(oldList==null || oldList.isEmpty()){
//			return;
//		}
//		if(newObject==null){//remove all old object
//			for(String code : oldList){
//				bb.codeValue(code);
//				set.add(bb);
//			}
//		}else{
//			if(newObject instanceof List){
//				for(Object no : (List<Object>)newObject){
//					String newCode = null;
//					if(no instanceof BasedBean){
//						newCode = ((BasedBean)no).codeValue();
//					}else{
//						newCode = no.toString();
//					}
//					for(String code : oldList){
//						if(!code.equals(newCode)){
//							bb.codeValue(code);
//							set.add(bb);
//						}
//					}
//				}
//			}else{
//				String newCode = null;
//				if(newObject instanceof BasedBean){
//					newCode = ((BasedBean)newObject).codeValue();
//				}else{
//					newCode = newObject.toString();
//				}
//				for(String code : oldList){
//					if(!code.equals(newCode)){
//						bb.codeValue(code);
//						set.add(bb);
//					}
//				}
//			}
//		}
//	}

//	@Override
//	public Map<String, Object> queryObjectStatus(BasedBean newObj, String tbName, String cloName, String code, Integer tenantId) {
//		Map<String, Object> queryMap = new HashMap<String, Object>();
//		Object oldObj = baseMapper.queryDynamicObject(tbName, cloName, code, tenantId);
//		if(oldObj==null){
//			newObj.setAction(Constant.ACTION_ADD);
//			newObj.setId(idHelper.getId(newObj));
//			Map<String, Object> attributes = newObj.getAttributes();
//			attributes.put("createTime", CommonUtil.dateToXmlGregorianCalendar(new Date()));
//			attributes.put("modifyTime", CommonUtil.dateToXmlGregorianCalendar(new Date()));
//			newObj.setAttributes(attributes);
//		}else{
//			if(newObj.equals(oldObj)){
//				newObj.setAction(Constant.ACTION_IGNORE);
//			}else{ 
//				newObj.setAction(Constant.ACTION_UPDATE);
//				Map<String, Object> attributes = newObj.getAttributes();
//				attributes.put("modifyTime", CommonUtil.dateToXmlGregorianCalendar(new Date()));
//				newObj.setAttributes(attributes);
//			}
//		}
//		queryMap.put(Constant.OBJECT_NEW, newObj);
//		log.debug("tbName:"+tbName);
//		log.debug("cloName:"+cloName);
//		log.debug("code:"+code);
//		log.debug(queryMap);
//		return queryMap;
//	}

	/**
	 * 数据持久化
	 * @throws IllegalStateException 
	 */
	@Override
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public String importService(ServiceObject serviceObject) throws IllegalStateException {
		Integer tenangId = serviceObject.getTenantId();
		Set<BasedBean> allImportBean = new LinkedHashSet<BasedBean>();
		//遍历初始化并入库
		recursionImport(serviceObject, null,null,allImportBean, tenangId);
		//剔除相同code删除与新增的重复，并变更为更新
		Map<String, BasedBean> map = new HashMap<String, BasedBean>();
		for(BasedBean bean: allImportBean) {
			if(!map.containsKey(bean.getObjectKey())){
				map.put(bean.getObjectKey(), bean);
			}else{
				if(Constant.ACTION_R.equals(bean.getAction())){
					map.get(bean.getObjectKey()).setAction(Constant.ACTION_M);
				}else{
					bean.setAction(Constant.ACTION_M);
					map.put(bean.getObjectKey(), bean);
				}
			}
		}
		for(Map.Entry<String, BasedBean> kv: map.entrySet()) {
			if(kv.getKey().startsWith("AttrValue")){
				continue;
			}
			BasedBean bean = kv.getValue();
			bean.setTenantId(tenangId);
			basedBeanImport(bean, serviceObject);
		}
		for(Map.Entry<String, BasedBean> kv: map.entrySet()) {
			if(!kv.getKey().startsWith("AttrValue")){
				continue;
			}
			BasedBean bean = kv.getValue();
			bean.setTenantId(tenangId);
			basedBeanImport(bean, serviceObject);
		}
		//刷新缓存版本
		baseMapper.updateCacheVersion((new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS")).format(new Date()), tenangId);
		return Constant.SUCCESS;
	}
	
	/**
	 * 初始化basedbean，赋值时间和id
	 * @param bean
	 */
	public void initBasedBean(BasedBean bean) {
		if(bean != null) {
			if(bean.getAttributes() == null) {
				bean.setAttributes(new HashMap<String, Object>());
			}
			if(!bean.getAttributes().containsKey("createTime")) {
				bean.getAttributes().put("createTime", CommonUtil.dateToXmlGregorianCalendar(new Date()));
				bean.getAttributes().put("modifyTime", CommonUtil.dateToXmlGregorianCalendar(new Date()));
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void recursionImport(BasedBean basedBean, BasedBean parent, BasedBean rootBean, Set<BasedBean> allImportBean, Integer tenantId) throws IllegalStateException {
		if(basedBean == null) {
			return;
		}
		if(rootBean == null) {
			rootBean = basedBean;
		}
		if(log.isDebugEnabled()) {
			log.debug("recursionImport code = " + basedBean.codeValue() + " bean type=" + ReflectionUtil.getClassLowerName(basedBean) + " action = " + basedBean.getAction());
		}
		//父对象可能还没导入，先检查父对象是否导入
		if(basedBean.getAttributes() != null && basedBean.getAttributes().get("parentBean") != null) {
			if(checkParentImport(basedBean, (BasedBean)basedBean.getAttributes().get("parentBean"), allImportBean)) {
				recursionImport((BasedBean)basedBean.getAttributes().get("parentBean"), null, rootBean, allImportBean, tenantId);
			}
		}
		//防止重复执行造成死循环
		if(!CommonUtil.isObjInCollection(basedBean, allImportBean)) {
			//检查是否在数据库中存在
			if(Constant.ACTION_A.equals(basedBean.getAction())) {
				boolean isExist = checkExistService.checkExist(basedBean, rootBean, tenantId);
				if(isExist) {
					//防止其他用code引用baseBean子对象，造成重复导入报错
					basedBean.changeAllAction(Constant.ACTION_N, null);
					//baseBean底下子对象都不需要导入
					return;
				}
			}
			initBasedBean(basedBean);
			//先把属性中属于父对象导入,父对象属性身上标记有@Parent注解
			for(String attr: ReflectionUtil.getAllAttrs(basedBean)) {
				Object value = ReflectionUtil.getAttrValue(attr, basedBean);
				if(ReflectionUtil.fieldExistAnnotation(Parent.class, basedBean, attr)) {
					if(value instanceof BasedBean) {
						recursionImport((BasedBean) value, basedBean,rootBean,allImportBean, tenantId);
						//将父类对象的code存到自身attributes
	 					basedBean.getAttributes().put(attr, ((BasedBean)value).codeValue());
					}
				}
			}
			
			//先把属性中属于优先对象导入,父对象属性身上标记有@BeforeMigration注解
			for(String attr: ReflectionUtil.getAllAttrs(basedBean)) {
				Object value = ReflectionUtil.getAttrValue(attr, basedBean);
				if(ReflectionUtil.fieldExistAnnotation(BeforeMigration.class, basedBean, attr) 
						|| ReflectionUtil.fieldExistAnnotation(ReferenceType.class, basedBean, attr) ) {
					if(value instanceof BasedBean) {
						recursionImport((BasedBean) value, basedBean,rootBean,allImportBean, tenantId);
						//将父类对象的code存到自身attributes
	 					basedBean.getAttributes().put(attr, ((BasedBean)value).codeValue());
					} else if(ReflectionUtil.isFieldType(Object.class, basedBean, attr) 
							&& value instanceof String 
							&& ReflectionUtil.fieldExistAnnotation(XmlElements.class, basedBean, attr)) {//如果是code引用，并且定义是object类型
		 				//检查code属于哪种类型
		 				Class type = basedBean.findAttrRealType(attr);
		 				BasedBean bean = rootBean.queryBeanByCodeAndType(type, (String)value);
		 				if(bean != null && bean.getId() == null) {
		 					recursionImport(bean, basedBean,rootBean,allImportBean, tenantId);
		 					//将父类对象的code存到自身attributes
		 					basedBean.getAttributes().put(attr, bean.codeValue());
		 				}
		 			} else if(ReflectionUtil.fieldExistAnnotation(ReferenceType.class, basedBean, attr) 
		 					&& value instanceof String) {//如果是code引用，并且定义是String类型
		 				//检查code属于哪种类型
		 				ReferenceType type = (ReferenceType) ReflectionUtil.findFieldAnnotation(ReferenceType.class, basedBean, attr);
		 				Class typeClass = type.value();
		 				//找到bean，并将这个bean的parent设置到bean的attributes
		 				BasedBean bean = rootBean.queryBeanByCodeAndType(typeClass, (String)value,true);
		 				if(bean != null && bean.getId() == null && !allImportBean.contains(bean)) {
		 					recursionImport(bean, basedBean,rootBean,allImportBean, tenantId);
		 					if(basedBean.getAttributes().get("parentBean") != null) {
				 				//将父类对象的code存到自身attributes
			 					basedBean.getAttributes().put(attr, ((BasedBean)basedBean.getAttributes().get("parentBean")).codeValue());
		 					}
		 				}
		 			}
				}
			}
			
			if(basedBean.getAttributes().get("parentBean") == null) {
				basedBean.getAttributes().put("parentBean", parent);
			}
			//放到set中，避免重复导入
			allImportBean.add(basedBean);
			
			//把属性中属于子对象的属性更新到数据库中
			for(String attr: ReflectionUtil.getAllAttrs(basedBean)) {
				Object value = ReflectionUtil.getAttrValue(attr, basedBean);
				if(!ReflectionUtil.fieldExistAnnotation(BeforeMigration.class, basedBean, attr) && !ReflectionUtil.fieldExistAnnotation(Parent.class, basedBean, attr)) {
					if(value instanceof BasedBean) {//如果当前属性是子对象
						//将自身code存到子对象attributes
						if(((BasedBean) value).getAttributes() == null) {
							((BasedBean) value).setAttributes(new HashMap<String, Object>());
						}
						((BasedBean) value).getAttributes().put(ReflectionUtil.getClassLowerName(basedBean) + "Code", basedBean.codeValue());
						recursionImport((BasedBean) value, basedBean,rootBean,allImportBean, tenantId);
					}  else if(ReflectionUtil.isFieldType(Object.class, basedBean, attr) 
							&& value instanceof String 
							&& ReflectionUtil.fieldExistAnnotation(XmlElements.class, basedBean, attr)) {//如果是code引用，并且定义是object类型
		 				//检查code属于哪种类型
		 				Class type = basedBean.findAttrRealType(attr);
		 				BasedBean bean = rootBean.queryBeanByCodeAndType(type, (String)value);
		 				if(bean != null && bean.getId() == null) {
		 					//将自身code存到子对象attributes
							if(((BasedBean) bean).getAttributes() == null) {
								((BasedBean) bean).setAttributes(new HashMap<String, Object>());
							}
							((BasedBean) bean).getAttributes().put(ReflectionUtil.getClassLowerName(basedBean) + "Code", basedBean.codeValue());
		 					recursionImport(bean, basedBean,rootBean,allImportBean, tenantId);
		 				}
		 			} else if(value instanceof java.util.List) {//如果是List类型
						for(Object subValue: (List)value) {
							//通过code找到真正对象
							if(subValue instanceof String && ReflectionUtil.fieldExistAnnotation(XmlElements.class, basedBean, attr)) {
								subValue = rootBean.queryBeanByCodeAndType(basedBean.findAttrRealType(attr), (String)subValue);
							}
							if(subValue instanceof BasedBean) {
								//将自身code存到子对象attributes
								if(((BasedBean) subValue).getAttributes() == null) {
									((BasedBean) subValue).setAttributes(new HashMap<String, Object>());
								}
								((BasedBean) subValue).getAttributes().put(ReflectionUtil.getClassLowerName(basedBean) + "Code", basedBean.codeValue());
								recursionImport((BasedBean) subValue, basedBean,rootBean,allImportBean, tenantId);
							}
						}
		 			}
				}
			}
		}
	}
	
	private boolean checkParentImport(BasedBean basedBean, BasedBean parentBean, Set<BasedBean> allImportBean) {
		if(CommonUtil.isObjInCollection(parentBean, allImportBean)) {
			return false;
		}
		for(String attr: ReflectionUtil.getAllAttrs(parentBean)) {
			Object value = ReflectionUtil.getAttrValue(attr, parentBean);
			if(value instanceof BasedBean) {
				if(value.equals(basedBean)) {
					if(ReflectionUtil.fieldExistAnnotation(Parent.class, parentBean, attr)
							|| ReflectionUtil.fieldExistAnnotation(BeforeMigration.class, parentBean, attr)) {
						return false;
					} else {
						return true;
					}
				} else if(((BasedBean)value).getCode() == null) {
					boolean insub = checkParentImport(basedBean,(BasedBean)value,allImportBean);
					if(insub) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void basedBeanImport(BasedBean targetObj, BasedBean rootBean) throws IllegalStateException {
		targetObj.attrTransform(false);
		BasedBean parentObj = null;
		if(targetObj.getAttributes() != null) {
			parentObj = (BasedBean) targetObj.getAttributes().get("parentBean");
		} else {
			return;
		}
		if(targetObj.getAction() == null || Constant.ACTION_N.equals(targetObj.getAction())) {
			return;
		}
		if(Constant.ACTION_A.equals(targetObj.getAction()) && targetObj.getId() == null) {
			int id = idHelper.getId(targetObj);
			targetObj.setId(id);
		}
		if(targetObj instanceof Api){
			Api entity = (Api)targetObj;
			Map<String, Object> serviceBean2Service = new HashMap<String, Object>();
			serviceBean2Service.put("id", idHelper.getId("ServiceBean2Service"));
			serviceBean2Service.put("serviceCode", targetObj.codeValue());
			serviceBean2Service.put("serviceBeanCode", parentObj.codeValue());
			serviceBean2Service.put("state", "A");
			serviceBean2Service.put("tenantId", entity.getTenantId());
			
			Map<String, Object> apiMethod = new HashMap<String, Object>();
			apiMethod.put("id", idHelper.getId("ApiMethod"));
			apiMethod.put("serviceCode", targetObj.codeValue());
			apiMethod.put("name", entity.getName());
			apiMethod.put("version", entity.getVersion());
			apiMethod.put("method", entity.getMethod());
			apiMethod.put("status", "D");
			apiMethod.put("tenantId", entity.getTenantId());
			
			Map<String, Object> dirSerList = new HashMap<String, Object>();
			dirSerList.put("apiCode", entity.getApiCode());
			dirSerList.put("tenantId", entity.getTenantId());
			importHandler(dirSerList,"DirSerList", Constant.ACTION_R);
			//如果是删除，先删除中间表
			if(Constant.ACTION_R.equals(entity.getAction())) {
				if("2".equals(((Service)parentObj).getStyle())){
					importHandler(apiMethod,"ApiMethod", entity.getAction());
				}
				importHandler(serviceBean2Service,"ServiceBean2Service", entity.getAction());
			}
			setServiceAddress((Service)parentObj, entity, parentObj.codeValue());
			importHandler(entity,null, entity.getAction());
			if(Constant.ACTION_A.equals(entity.getAction())) {
				importHandler(serviceBean2Service,"ServiceBean2Service", entity.getAction());
			}
			if(Constant.ACTION_M.equals(entity.getAction())) {
				importHandler(serviceBean2Service,"ServiceBean2Service", Constant.ACTION_R);
				importHandler(serviceBean2Service,"ServiceBean2Service", Constant.ACTION_A);
			}
			if(!Constant.ACTION_R.equals(entity.getAction())){
				if("2".equals(((Service)parentObj).getStyle())){
					importHandler(apiMethod,"ApiMethod", Constant.ACTION_R);
					importHandler(apiMethod,"ApiMethod", Constant.ACTION_A);
				}
				if(entity.getServiceCatalogCodes()!=null && !entity.getContractVersionCode().isEmpty()){
					for(String dirCode : entity.getServiceCatalogCodes()){
						dirSerList.put("id", idHelper.getId("DirSerList"));
						dirSerList.put("dirCode", dirCode);
						dirSerList.put("status", "A");
						dirSerList.put("createTime", CommonUtil.dateToXmlGregorianCalendar(new Date()));
						dirSerList.put("modifyTime", CommonUtil.dateToXmlGregorianCalendar(new Date()));
						importHandler(dirSerList,"DirSerList", Constant.ACTION_A);
					}
				}
			}
		} else if(targetObj instanceof Endpoint) {
			Endpoint entity = (Endpoint)targetObj;
			Map<String, Object> endpointMap = new HashMap<String, Object>();
			endpointMap.put("tenantId", entity.getTenantId());
			//删除所有端点属性值
			if(Constant.ACTION_R.equals(targetObj.getAction())) {
				endpointMap.put("endpointCode", entity.codeValue());
				importHandler(endpointMap,"AllEndpointAttrValue", entity.getAction());
			}
			importHandler(entity,null, entity.getAction());
		} else if(targetObj instanceof AttrValue) {
			Map<String, Object> attrs = ReflectionUtil.objToMap(targetObj);
			attrs.put("tenantId", targetObj.getTenantId());
			if(parentObj instanceof TechnologyImplement) {
				attrs.put("techImplCode", parentObj.codeValue());
				attrs.put("id", idHelper.getId("TechImplAttrValue"));
				importHandler(attrs,"TechImplAttrValue", targetObj.getAction());
			} else if(parentObj instanceof AuthenticationExpress) {
				attrs.put("authenticationExpressCode", parentObj.codeValue());
				attrs.put("id", idHelper.getId("AuthenticationExpressAttrValue"));
				importHandler(attrs,"AuthenticationExpressAttrValue", targetObj.getAction());
			} else if(parentObj instanceof ContractFormat) {
				attrs.put("contractFormatCode", parentObj.codeValue());
				attrs.put("id", idHelper.getId("ContractFormatAttrValue"));
				importHandler(attrs,"ContractFormatAttrValue", targetObj.getAction());
			} else if(parentObj instanceof Endpoint) {
				Endpoint parent = (Endpoint)parentObj;
				AttrValue entity = (AttrValue)targetObj;
				if(Constant.ATTR_SPEC_SERVICETECHID.equals(entity.getAttrValueCode()) && !Constant.ACTION_R.equals(entity.getAction())) {
					if(parent.getEndpointSpec() != null && parent.getEndpointSpec().getEndpointSpecDetail() instanceof ServiceTechnologyImplement) {
						if("download".equals(parent.getEndpointSpec().getEndpointSpecCode())){
							return;
						}
						ServiceTechnologyImplement sti = (ServiceTechnologyImplement) parent.getEndpointSpec().getEndpointSpecDetail();
						String serviceCode = sti.getApiCode();
						String techCode = sti.getTechnologyImplementCode();
						Integer id = daoMapper.querySerTechImplIdByServiceAndTech(serviceCode, techCode, entity.getTenantId());
						if(id == null) {
							throw new IllegalStateException("import endpoint_attr_value error, service_code:"+serviceCode + " tech_impl_code:" + techCode + " no found! endpoint_code = " + parent.codeValue());
						}
						attrs.put("value", id);
					} else {
						if("download".equals(parent.getEndpointSpec().getEndpointSpecCode())){
							return;
						}
						throw new IllegalStateException("endpoint_attr_value refer the ser_tech_impl no found! endpoint_code = " + parent.codeValue());
					}
				} else if(Constant.ATTR_SPEC_TRANSFORMERID.equals(entity.getAttrValueCode()) && !Constant.ACTION_R.equals(entity.getAction())) {
					if(parent.getEndpointSpec() != null && parent.getEndpointSpec().getEndpointSpecDetail() instanceof Transformer) {
						Transformer transformer = (Transformer)parent.getEndpointSpec().getEndpointSpecDetail();
						Integer id = daoMapper.queryTransformerIdByCode(transformer.codeValue(), entity.getTenantId());
						if(id == null) {
							throw new IllegalStateException("endpoint_attr_value refer the contract_adapter, code="+transformer.codeValue()+" no found! endpoint_code = " + parent.codeValue());
						}
						attrs.put("value", id);
					} else {
						throw new IllegalStateException("endpoint_attr_value refer the contract_adapter no found! endpoint_code = " + parent.codeValue());
					}
				}
				attrs.put("endpointCode", parentObj.codeValue());
				attrs.put("endpointSpecCode", ReflectionUtil.getAttrValue("endpointSpec.endpointSpecCode", parentObj));
				attrs.put("id", idHelper.getId("EndpointAttrValue"));
				importHandler(attrs,"EndpointAttrValue", targetObj.getAction());
			}
		} else if(targetObj instanceof VariableMap) {
			VariableMap entity = (VariableMap)targetObj;
			Map<String, Object> transScript2Param = new HashMap<String, Object>();
			transScript2Param.put("tenantId", targetObj.getTenantId());
			transScript2Param.put("contractAdapterCode", parentObj.codeValue());
			transScript2Param.put("varMapingCode", targetObj.codeValue());
			transScript2Param.put("state", "A");
			if(Constant.ACTION_R.equals(entity.getAction())) {
				importHandler(transScript2Param,"TransScript2Param", entity.getAction());
			}
			if(Constant.ACTION_A.equals(entity.getAction())) {
				//检查code是否存在于数据库中
				VariableMap entityOld = contractMapper.queryVariableMapByCodeWithOne(entity.codeValue(), rootBean.getTenantId());
				if(entityOld != null && !entityOld.equals(entity)) {
					if(!entityOld.equals(entity)) {
						throw new IllegalArgumentException("VariableMap code:" + entity.getCode() + " exist in the database, and not equals with the import one");
					}
				} else {
					importHandler(entity,null, entity.getAction());
				}
				if(checkMapper.checkTransScript2ParamExist(transScript2Param) == null || checkMapper.checkTransScript2ParamExist(transScript2Param) == 0) {
					importHandler(transScript2Param,"TransScript2Param", entity.getAction());
				}
			} else {
				importHandler(entity,null, entity.getAction());
			}
		} else if(targetObj instanceof AuthenticationExpress) {
			AuthenticationExpress entity = (AuthenticationExpress)targetObj;
			Map<String, Object> prooefEffectMid = new HashMap<String, Object>();
			prooefEffectMid.put("tenantId", targetObj.getTenantId());
			prooefEffectMid.put("prooefEffectMidId", idHelper.getId("ProoefEffectMid"));
			prooefEffectMid.put("authenticationExpressCode", targetObj.codeValue());
			prooefEffectMid.put("ApiInvokeObjectCode", parentObj.codeValue());
			prooefEffectMid.put("state", "A");
			if(Constant.ACTION_R.equals(entity.getAction())) {
				//删除中间表
				importHandler(prooefEffectMid,"ProoefEffectMid", entity.getAction());
				//删除所有属性值
				importHandler(prooefEffectMid,"AllAuthenticationExpressAttrValue", entity.getAction());
			}
			if(Constant.ACTION_A.equals(entity.getAction())) {
				//检查code是否存在于数据库中
				AuthenticationExpress entityOld = apiInvokeMapper.queryAuthenticationExpressionByCodeWithOne(entity.codeValue(), entity.getTenantId());
				if(entityOld != null && !entityOld.equals(entity)) {
					if(!entity.fullEquals(entityOld, rootBean)) {
						throw new IllegalArgumentException("AuthenticationExpress code:" + entity.getCode() + " exist in the database, and not equals with the import one");
					}
				} else if(entityOld==null) {
					importHandler(entity,null, entity.getAction());
				}
				if(checkMapper.checkProoefEffectMidExist(prooefEffectMid) == null || checkMapper.checkProoefEffectMidExist(prooefEffectMid) == 0) {
					importHandler(prooefEffectMid,"ProoefEffectMid", entity.getAction());
				}
			} else {
				importHandler(entity,null, entity.getAction());
			}
		} else if(targetObj instanceof ContractDocument) {
			ContractDocument entity = (ContractDocument)targetObj;
			Map<String, Object> docContract = new HashMap<String, Object>();
			docContract.put("tenantId", targetObj.getTenantId());
			docContract.put("docContractId", idHelper.getId("DocContract"));
			docContract.put("contractDocCode", targetObj.codeValue());
			docContract.put("contractVersionCode", ((ContractDocument) targetObj).getContractVersion());
			if(Constant.ACTION_R.equals(entity.getAction())) {
				importHandler(docContract,"DocContract", entity.getAction());
			}
			if(Constant.ACTION_A.equals(entity.getAction())) {
				//检查code是否存在于数据库中
				ContractDocument entityOld = contractMapper.queryContractDocumentByCodeWithOne(entity.codeValue(), rootBean.getTenantId());
				if(entityOld != null) {
					entity.setCompareContractVersion(false);
					if(!entity.fullEquals(entityOld, rootBean)) {
						throw new IllegalArgumentException("ContractDocument code:" + entity.codeValue() + " exist in the database, and not equals with the import one");
					}
				} else {
					importHandler(entity,null, entity.getAction());
				}
				if(checkMapper.checkDocContractExist(docContract) == null || checkMapper.checkDocContractExist(docContract) == 0) {
					importHandler(docContract,"DocContract", entity.getAction());
				}
			} else {
				importHandler(entity,null, entity.getAction());
			}
		} else if(targetObj instanceof TechnologyImplement) {
			TechnologyImplement entity = (TechnologyImplement)targetObj;
//			Map<String, Object> techMap = new HashMap<String, Object>();
//			techMap.put("serTechImplId", idHelper.getId("SerTechImpl"));
//			techMap.put("apiCode", parentObj.codeValue());
//			techMap.put("techImplCode", targetObj.codeValue());
//			techMap.put("modifyTime", targetObj.getAttributes().get("modifyTime"));
//			if(Constant.ACTION_R.equals(entity.getAction())) {
//				//删除服务技术实现
//				importHandler(techMap,"SerTechImpl", entity.getAction());
//				//删除技术实现属性值
//				importHandler(techMap,"AllTechImplAttrValue", entity.getAction());
//			}
			if(Constant.ACTION_A.equals(entity.getAction())) {
//				techMap.put("createTime", targetObj.getAttributes().get("createTime"));
				//检查code是否存在于数据库中
				TechnologyImplement entityOld = serviceMapper.queryTechImplByCodeWithOne(entity.codeValue(), rootBean.getTenantId());
				if(entityOld != null) {
					if(!entity.fullEquals(entityOld, rootBean)) {
						throw new IllegalArgumentException("TechnologyImplement code:" + entity.codeValue() + " exist in the database, and not equals with the import one");
					}
				} else {
					importHandler(entity,null, entity.getAction());
				}
//				if(checkMapper.checkSerTechExist(techMap) == null || checkMapper.checkSerTechExist(techMap) == 0) {
//					importHandler(techMap,"SerTechImpl", entity.getAction());
//				}
			} else {
				importHandler(entity,null, entity.getAction());
			}
		} else if(targetObj instanceof Org) {
			Org entity = (Org)targetObj;
			if(Constant.ACTION_R.equals(entity.getAction())) {
				Map<String, Object> roleMap = new HashMap<String, Object>();
				roleMap.put("tenantId", targetObj.getTenantId());
				roleMap.put("orgCode", entity.codeValue());
				importHandler(roleMap, "Role", "delete");
			}
			importHandler(entity,null, entity.getAction());
			List<String> roles = entity.getRoles();
			if(roles != null && entity.getAction() != null) {
				if(!Constant.ACTION_N.equals(entity.getAction()) && !Constant.ACTION_R.equals(entity.getAction())) {
					Map<String, Object> roleMap = new HashMap<String, Object>();
					roleMap.put("tenantId", targetObj.getTenantId());
					roleMap.put("orgCode", entity.codeValue());
					importHandler(roleMap, "Role", "delete");
					for(String role: roles) {
						roleMap.put("roleCode", role);
						roleMap.put("createTime", ReflectionUtil.getAttrValue("attributes.createTime", targetObj));
						importHandler(roleMap, "Role", null);
					}
				}
			}
		} else if(targetObj instanceof EndpointSpec) {
			return;
		} else if(targetObj instanceof NodeDesc) {
			NodeDesc entity = (NodeDesc)targetObj;
			if(parentObj instanceof NodeDesc) {
				Integer id = daoMapper.queryNodeDescIdByCode(parentObj.codeValue(), parentObj.getTenantId());
				entity.getAttributes().put("parentNodeDescId", id);
			}
			if(!entity.getAttributes().containsKey("contractFormatCode")) {
				entity.getAttributes().put("contractFormatCode", parentObj.getAttributes().get("contractFormatCode"));
			}
			importHandler(targetObj,null, targetObj.getAction());
		} else if(targetObj instanceof ContractFormat) {
			ContractFormat entity = (ContractFormat)targetObj;
			if((entity.getAttributes().get("parentBean") instanceof ContractFormatChoice)
					||(entity.getAttributes().get("parentBean") instanceof ContractVersion)) {
				BasedBean bean = null;
				if(entity.getAttributes().get("parentBean") instanceof ContractFormatChoice) {
					bean = (BasedBean)(ReflectionUtil.getAttrValue("attributes.parentBean.attributes.parentBean", entity));
				} else {
					bean = (BasedBean)(ReflectionUtil.getAttrValue("attributes.parentBean", entity));
				}
				if(bean instanceof ContractVersion) {
					ContractVersion cv = (ContractVersion)bean;
					if(cv.getRequestContractFormat() != null && entity == cv.getRequestContractFormat().getContractFormatOrCode()) {
						entity.getAttributes().put("reqRsp", "REQ");
					}
					if(cv.getResponceContractFormat() != null && entity == cv.getResponceContractFormat().getContractFormatOrCode()) {
						entity.getAttributes().put("reqRsp", "RSP");
					}
					entity.getAttributes().put("contractVersionCode", cv.codeValue());
				}
			}
			Map<String, Object> contractFormatMap = new HashMap<String, Object>();
			contractFormatMap.put("contractFormatCode", targetObj.codeValue());
			if(Constant.ACTION_R.equals(targetObj.getAction())) {
				importHandler(contractFormatMap,"AllContractFormatAttrValue", targetObj.getAction());
			}
			importHandler(targetObj,null, targetObj.getAction());
		} else if(targetObj instanceof Contract) {
			if(!Constant.ACTION_R.equals(targetObj.getAction())) {
				Contract entity = (Contract)targetObj;
				if(entity.getAttributes().get("parentBean") instanceof ContractVersion) {
					ContractVersion cv = (ContractVersion)entity.getAttributes().get("parentBean");
					if(cv.getBaseContractVersion() != null && cv.getBaseContractVersion().getContract() != null) {
						entity.getAttributes().put("baseContractId", contractMapper.queryContractIdByContractByCodeWithOne(cv.getBaseContractVersion().getContract().codeValue(), rootBean.getTenantId()));
					}
				}
			}
			importHandler(targetObj,null, targetObj.getAction());
		} else {
			importHandler(targetObj,null, targetObj.getAction());
		}
	}

	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	private void importHandler(Object obj, String methodName, String action){
		if(obj == null) {
			throw new IllegalArgumentException("import obj can't be null!");
		}
		//没有code的对象不需要导入
		if(obj instanceof BasedBean && ((BasedBean)obj).getCode() == null && !(obj instanceof ServiceTechnologyImplement)) {
			return;
		}
		String objName = obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".")+1);
		if(log.isDebugEnabled()) {
			log.debug("objName:" + objName + " action:" + action + " obj:[" + obj.toString() + "]");
		}
		if(obj instanceof BasedBean) {
			if(Constant.ACTION_A.equals(action)){
				ReflectionUtil.invokeMethod(ReflectionUtil.findMethod(daoMapper.getClass(), "insert"+objName, obj.getClass()), daoMapper, obj);
			} else if(Constant.ACTION_M.equals(action)){
				ReflectionUtil.invokeMethod(ReflectionUtil.findMethod(daoMapper.getClass(), "update"+objName, obj.getClass()), daoMapper, obj);
			} else if(Constant.ACTION_R.equals(action)){
				ReflectionUtil.invokeMethod(ReflectionUtil.findMethod(daoMapper.getClass(), "delete"+objName, obj.getClass()), daoMapper, obj);
			}
		} else if(action == null) {
			Integer count = (Integer) ReflectionUtil.invokeMethod(ReflectionUtil.findMethod(daoMapper.getClass(), "checkExist"+methodName, Map.class), daoMapper, obj);
			if(count == 0) {
				ReflectionUtil.invokeMethod(ReflectionUtil.findMethod(daoMapper.getClass(), "add"+methodName, Map.class), daoMapper, obj);
			}
		} else if(obj instanceof Map) {
			if(Constant.ACTION_A.equals(action)){
				ReflectionUtil.invokeMethod(ReflectionUtil.findMethod(daoMapper.getClass(), "insert"+methodName, Map.class), daoMapper, obj);
			} else if(Constant.ACTION_M.equals(action)){
				ReflectionUtil.invokeMethod(ReflectionUtil.findMethod(daoMapper.getClass(), "update"+methodName, Map.class), daoMapper, obj);
			} else if(Constant.ACTION_R.equals(action)){
				ReflectionUtil.invokeMethod(ReflectionUtil.findMethod(daoMapper.getClass(), "delete"+methodName, Map.class), daoMapper, obj);
			}
		}
		
	}

	@Override
	public Map<String, ServiceObject> compareServiceObject(ServiceObject so) throws IllegalStateException {
		//校验
		BasedBeanHelper helper = new BasedBeanHelper(checkMapper);
		so.validateCode(helper);
		
		ServiceObject old = new ServiceObject();
		List<Service> list = new ArrayList<Service>();
		Set<Component> comSet = new HashSet<Component>();
		Set<ContractVersion> cvSet = new HashSet<ContractVersion>();
		//获取所有contractFormat对应的contractVersion
		Set<String> contractFormatCodeList = new HashSet<String>();
		for(Service service : so.getServices()){
			service = serviceMapper.queryServiceBeanByCode(service.getServiceCode(), so.getTenantId());
			if(service==null){
				continue;
			}
			List<Component> components = baseMapper.queryComponentByServiceBeanCode(service.getServiceCode(), so.getTenantId());
			//获取所有service下面的contractVersion
			List<ContractVersion> cvList = contractMapper.queryContractVersionByServiceCode(service.codeValue(), so.getTenantId());
			cvSet.addAll(cvList);
			BasedBeanHelper.findAllContractFormatCode(contractFormatCodeList, service);
			comSet.addAll(components);
			list.add(service);
		}
		ContractVersion cv = null;
		for(String code: contractFormatCodeList) {
			cv = contractMapper.queryContractVersionByFormatCodeWithOne(code, so.getTenantId());
			cvSet.add(cv);
		}
		old.getComponents().addAll(comSet);
		old.getContractVersions().addAll(cvSet);
		old.setServices(list);
		Map<String, ServiceObject> soMap = new HashMap<String, ServiceObject>();
		try {
			old.attrTransform(true);
			so.compare(old, helper);
		} catch (Exception e) {
			log.error("Compare service object error!", e);
		}
		old.attrTransform(false);
		if(!list.isEmpty()){
			soMap.put("old", old);
		}
		soMap.put("new", so);
		return soMap;
	}
	
	public void setServiceAddress(Service serivce, Api api, String serviceBeanCode){
		StringBuffer serviceAddress = new StringBuffer("http://{ip}:{port}/");
		String style = serivce.getStyle();
		if("1".equals(style)){//rest
			serviceAddress.append("rest");
			List<AttrValue> attrValues = api.getTechnologyImplements().get(0).getAttrValues();
			for(AttrValue attrValue : attrValues){
				if("restResource".equals(attrValue.codeValue())){
					serviceAddress.append(attrValue.getValue());
				}
			}
		}else if("2".equals(style)){//webservice
			serviceAddress.append("webservice");
			ContractVersion contractVersion = contractMapper.queryContractVersionByCodeWithOne(api.getContractVersionCode(), serivce.getTenantId());
			if(contractVersion.getBaseContractVersion()!=null){
				serviceAddress.append("/").append(contractVersion.getBaseContractVersion().getVersion());
			}
			if(contractVersion.getContractDocument()!=null){
				serviceAddress.append("/").append(contractVersion.getContractDocument().getResourceAliss());
			}
		}else if("4".equals(style)){//http
			serviceAddress.append("http");
			ContractVersion contractVersion = contractMapper.queryContractVersionByCodeWithOne(api.getContractVersionCode(), serivce.getTenantId());
			if(contractVersion.getBaseContractVersion()!=null){
				serviceAddress.append("/").append(contractVersion.getBaseContractVersion().getVersion());
			}
			serviceAddress.append("/").append(api.getMethod());
		}
		baseMapper.setServiceAddress(serviceBeanCode, serviceAddress.toString(), serivce.getTenantId());
	}
}
