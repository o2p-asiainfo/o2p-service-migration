package com.asiainfo.integretion.o2p.servicemigration.dao.logmapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.asiainfo.integretion.o2p.servicemigration.dao.sqlprovider.ServiceMapperSqlProvider;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceMigrationLog;

@Component(value="logMapper")
public interface LogMapper {
	
	@Insert("insert into service_migration_log(service_migration_log_id, operate_user, operate_time, ip, old_doc, new_doc, tenant_id) values("
			+ " #{id}, #{operateUser}, #{operateDate}, #{ip}, #{old_doc}, #{new_doc}, #{tenantId})")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public void insertLog(ServiceMigrationLog log);
	
	
	@Results(value = {
			@Result(column = "service_migration_log_id", property = "id"),
			@Result(column = "operate_user", property = "operateUser"),
			@Result(column = "ip", property = "ip"),
			@Result(column = "operate_time", property = "operateDate", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "old_doc", property = "old_doc"),
			@Result(column = "new_doc", property = "new_doc"),
			@Result(column = "tenant_id", property = "tenantId")
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@SelectProvider(type=ServiceMapperSqlProvider.class, method="queryLogsPage")
	public List<ServiceMigrationLog> queryLogsPage(Map<String, Object> params);
	
	
	@ResultType(Integer.class)
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@SelectProvider(type=ServiceMapperSqlProvider.class, method="queryLogsCount")
	public int queryLogsCount(Map<String, Object> params);

	@ResultType(Integer.class)
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select ${sign}_doc from service_migration_log where service_migration_log_id = #{id}")
	public String queryLogDoc(Map<String, Object> params);
	
	@ResultType(Integer.class)
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select SEQ_SERVICE_MIGRATION_LOG.nextval from dual")
	public Integer getOrcleIdSequence();
	
	@ResultType(Integer.class)
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select nextval('SEQ_SERVICE_MIGRATION_LOG')")
	public Integer getMysqlIdSequence();
	
	@Select("update SERVICE_MIGRATION_LOG set status = #{status} where service_migration_log_id = #{id}")
	@ResultType(Integer.class)
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public void setLogStatus(@Param("id") String id, @Param("status") String status);
}
