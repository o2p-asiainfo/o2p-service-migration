package com.asiainfo.integretion.o2p.servicemigration.smo;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;

@Transactional
public interface IExportService {
	ServiceObject getServices(Map<String, Object> params);
}
