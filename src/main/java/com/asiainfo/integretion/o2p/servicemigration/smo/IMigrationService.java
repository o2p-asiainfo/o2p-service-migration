package com.asiainfo.integretion.o2p.servicemigration.smo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceMigrationLog;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;

public interface IMigrationService {
	public void assembLogAndLog(Map<String, ServiceObject> map, String ip, UserRoleInfo user) throws IOException;
	
	public List<ServiceMigrationLog> queryLogsPage(Map<String, Object> params);
	
	public int queryLogsCount(Map<String, Object> params);

	public String queryLogDoc(Map<String, Object> params);
	
	public void setLogStatus(String id, String status);
}
