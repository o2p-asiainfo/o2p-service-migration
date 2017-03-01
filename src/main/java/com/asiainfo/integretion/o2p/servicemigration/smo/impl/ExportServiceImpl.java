package com.asiainfo.integretion.o2p.servicemigration.smo.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.BaseMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ContractMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.DaoMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ServiceMapper;
import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBeanHelper;
import com.asiainfo.integretion.o2p.servicemigration.domain.Component;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractVersion;
import com.asiainfo.integretion.o2p.servicemigration.domain.Service;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;
import com.asiainfo.integretion.o2p.servicemigration.smo.IExportService;

@org.springframework.stereotype.Service(value="exportService")
public class ExportServiceImpl implements IExportService{
	@Resource(name="serviceMapper")
	private ServiceMapper serviceMapper;
	@Resource(name="baseMapper")
	private BaseMapper baseMapper;
	@Resource(name="contractMapper")
	private ContractMapper contractMapper;
	private static final Logger log = Logger.getLog(ExportServiceImpl.class);

	@Override
	public ServiceObject getServices(Map<String, Object> params) {
		Integer tenantId = Integer.valueOf(params.get("tenantId").toString());
		log.info("params = " + params);
		if(tenantId==null){
			log.warn("can not get tenantId!");
			return null;
		}
		String serviceCodes = (String) params.get("serviceCodes");
		ServiceObject serviceObject = new ServiceObject();
		if(serviceCodes != null) {
			String[] serviceCodeArray = serviceCodes.split(",");
			Map<String, String> exportError = new HashMap<String, String>();
			Set<Component> comSet = new HashSet<Component>();
			Set<ContractVersion> cvSet = new HashSet<ContractVersion>();
			//获取所有contractFormat对应的contractVersion
			Set<String> contractFormatCodeList = new HashSet<String>();
			for(String serviceCode: serviceCodeArray) {
				try {
					Service service = serviceMapper.queryServiceBeanByCode(serviceCode, tenantId);
					if(service == null) {
						continue;
					}
					List<Component> components = baseMapper.queryComponentByServiceBeanCode(serviceCode, tenantId);
					
					//获取所有service下面的contractVersion
					List<ContractVersion> cvList = contractMapper.queryContractVersionByServiceCode(service.codeValue(), tenantId);
					cvSet.addAll(cvList);
					BasedBeanHelper.findAllContractFormatCode(contractFormatCodeList, service);
					comSet.addAll(components);
					serviceObject.getServices().add(service);
				} catch(Exception e) {
					log.error("serviceCode:"+serviceCode+" export error, cause: ", e);
					exportError.put(serviceCode, e.getMessage());
				}
			}
			ContractVersion cv = null;
			for(String code: contractFormatCodeList) {
				cv = contractMapper.queryContractVersionByFormatCodeWithOne(code, tenantId);
				cvSet.add(cv);
			}
			serviceObject.getComponents().addAll(comSet);
			serviceObject.getContractVersions().addAll(cvSet);
			if(exportError.size() != 0) {
				params.put("exportError", exportError);
				return null;
			}
		}
		//值转换
		serviceObject.attrTransform(true);
		return serviceObject;
	}
}
