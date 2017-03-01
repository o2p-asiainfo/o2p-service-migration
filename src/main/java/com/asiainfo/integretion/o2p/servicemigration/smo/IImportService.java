package com.asiainfo.integretion.o2p.servicemigration.smo;

import java.util.Map;

import javax.jms.IllegalStateException;

import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBean;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;

@Transactional
public interface IImportService {

//	Map<String, Object> queryObjectStatus(BasedBean newObj, String tbName, String cloName, String code, Integer tenantId);
	
	String importService(ServiceObject serviceObject) throws IllegalStateException;
	
	Map<String, ServiceObject> compareServiceObject(ServiceObject so) throws IllegalStateException;
}
