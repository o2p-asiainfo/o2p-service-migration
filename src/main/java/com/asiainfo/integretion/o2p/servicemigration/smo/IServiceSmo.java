package com.asiainfo.integretion.o2p.servicemigration.smo;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.integretion.o2p.servicemigration.domain.Service;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;

@Transactional
public interface IServiceSmo {
	List<Service> getServices(Map<String, Object> params);

	List<Service> queryServicesPage(Map<String, Object> params);
	
	
	String importService(ServiceObject sos);

	int queryServicesCount(Map<String, Object> params);
}
