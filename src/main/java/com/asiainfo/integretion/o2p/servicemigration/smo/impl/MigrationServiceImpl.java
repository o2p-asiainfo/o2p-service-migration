package com.asiainfo.integretion.o2p.servicemigration.smo.impl;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;

import com.asiainfo.integration.o2p.web.bo.UserRoleInfo;
import com.asiainfo.integretion.o2p.servicemigration.common.util.JaxbUtil;
import com.asiainfo.integretion.o2p.servicemigration.dao.logmapper.LogMapper;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceMigrationLog;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;
import com.asiainfo.integretion.o2p.servicemigration.smo.IMigrationService;

@org.springframework.stereotype.Service("migrationService")
public class MigrationServiceImpl implements IMigrationService{
	private static final Log LOG = LogFactory.getLog(MigrationServiceImpl.class);
	@Resource(name="logMapper")
	private LogMapper logMapper;
	@Value("${databaseType}")
	private String dbType;

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	@Override
	public void assembLogAndLog(Map<String, ServiceObject> map, String ip, UserRoleInfo user) {
		if(LOG.isDebugEnabled()){
			LOG.debug("use info:" + user);
		}
		try{
			ServiceMigrationLog log = new ServiceMigrationLog();
			log.setIp(ip);
			log.setOperateUser(user==null?"admin":user.getName());
			log.setOperateDate(new Date());
			log.setTenantId((user==null || user.getTenantId()==null)?Integer.valueOf("0"):user.getTenantId());
			ServiceObject newSO = map.get("new");
			ServiceObject oldSO = map.get("old");
			StringWriter sw = new StringWriter();
			JaxbUtil.marshal(newSO, sw);
			log.setNew_doc(sw.toString());
			sw.close();
			sw = null;
			if(oldSO != null) {
				sw = new StringWriter();
				JaxbUtil.marshal(oldSO, sw);
				log.setOld_doc(sw.toString());
				sw.close();
				sw = null;
			}
			log.setId("oracle".equals(dbType)?logMapper.getOrcleIdSequence():logMapper.getMysqlIdSequence());
			logMapper.insertLog(log);
		}catch(Exception e){
			LOG.error(e);
		}
	}
	
	@Override
	public List<ServiceMigrationLog> queryLogsPage(Map<String, Object> params) {
		return logMapper.queryLogsPage(params);
	}
	
	@Override
	public int queryLogsCount(Map<String, Object> params) {
		return logMapper.queryLogsCount(params);
	}

	@Override
	public String queryLogDoc(Map<String, Object> params) {
		return logMapper.queryLogDoc(params);
	}

	@Override
	public void setLogStatus(String id, String status) {
		logMapper.setLogStatus(id, status);
	}
	
}
