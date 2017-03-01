package com.asiainfo.integretion.o2p.servicemigration.smo.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.integretion.o2p.servicemigration.common.Constant;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.DaoMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ServiceMapper;
import com.asiainfo.integretion.o2p.servicemigration.domain.Service;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;
import com.asiainfo.integretion.o2p.servicemigration.smo.IServiceSmo;

@org.springframework.stereotype.Service(value="ServiceSmo")
public class ServiceSmoImpl implements IServiceSmo{
	@Resource(name="serviceMapper")
	private ServiceMapper serviceMapper;
	@Resource(name="daoMapper")
	private DaoMapper daoMapper;
	private static final Log LOG = LogFactory.getLog(ServiceSmoImpl.class);

	@Override
	public List<Service> getServices(Map<String, Object> params) {
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Override
	public List<Service> queryServicesPage(Map<String, Object> params) {
		try{
			daoMapper.codeInit();
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("now", new Date());
//			daoMapper.insertServiceBean(temp);
		}catch(Exception e){
			LOG.error("Init migration data error!", e);
		}
		return serviceMapper.queryServiceBeanPage(params);
	}

//	@Override
//	public Map<String, Object> queryObjectStatus(Object newObj) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		if(newObj instanceof Service){
//			Service service = serviceMapper.queryServiceBeanByCode(((Service)newObj).getServiceCode(), ((Service)newObj).getTenantId());
//			if(service==null){
//				map.put(Constant.STATUS, Constant.OBJECT_STATUS_NON_EXIST);
//			}else{
//				if(newObj.equals(service)){
//					map.put(Constant.STATUS, Constant.OBJECT_STATUS_SAME);
//				}else{
//					map.put(Constant.STATUS, Constant.OBJECT_STATUS_EXIST);
//					map.put(Constant.OBJECT_NEW, newObj);
//					map.put(Constant.OBJECT_OLD, service);
//				}
//			}
//		}
//		return map;
//	}

	@Override
	public String importService(ServiceObject so) {
		for(Service service : so.getServices()){
			if(service.getCreateDate()==null){
				try {
					service.setCreateDate(DatatypeFactory.newInstance().newXMLGregorianCalendar());
				} catch (DatatypeConfigurationException e) {
					
				}
			}
			if(service.getModifyDate()==null){
				try {
					service.setModifyDate(DatatypeFactory.newInstance().newXMLGregorianCalendar());
				} catch (DatatypeConfigurationException e) {
					
				}
			}
			serviceMapper.updateServiceBean(service);
		}
		return Constant.SUCCESS;
	}

	@Override
	public int queryServicesCount(Map<String, Object> params) {
		return serviceMapper.queryServicesCount(params);
	}

}
