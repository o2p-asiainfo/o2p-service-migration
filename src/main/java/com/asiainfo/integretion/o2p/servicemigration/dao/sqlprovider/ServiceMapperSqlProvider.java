package com.asiainfo.integretion.o2p.servicemigration.dao.sqlprovider;

import java.util.Map;

public class ServiceMapperSqlProvider {
	public String queryServicesCount(Map<String,Object> params) {
		StringBuilder sb = new StringBuilder("select count(1) from service_bean where 1=1 ");
		if(params != null) {
			if(params.get("name") != null) {
				sb.append(" and service_bean_name like concat('%', concat(#{name}, '%'))");
			}
			if(params.get("style") != null) {
				sb.append(" and style = #{style}");
			}
			if(params.get("tenantId") != null) {
				sb.append(" and tenant_id = #{tenantId}");
			}
		}
		return sb.toString();
	}
	
	public String queryServiceBeanPage(Map<String,Object> params) {
		StringBuilder sb = new StringBuilder("select * from service_bean where 1=1 ");
		if(params != null) {
			if(params.get("name") != null) {
				sb.append(" and service_bean_name like concat('%', concat(#{name}, '%'))");
			}
			if(params.get("style") != null) {
				sb.append(" and style = #{style}");
			}
			if(params.get("tenantId") != null) {
				sb.append(" and tenant_id = #{tenantId}");
			}
		}
		sb.append(" order by modify_date desc");
		return sb.toString();
	}
	
	
	public String queryLogsCount(Map<String,Object> params) {
		StringBuilder sb = new StringBuilder("select count(1) from SERVICE_MIGRATION_LOG where 1=1 ");
		if(params != null) {
			if(params.get("fromTime") != null) {
				sb.append(" and OPERATE_TIME > {fromTime}");
			}
			if(params.get("toTime") != null) {
				sb.append(" and OPERATE_TIME < {toTime}");
			}
			if(params.get("operator") != null) {
				sb.append(" and operate_user = #{operator}");
			}
			if(params.get("tenantId") != null) {
				sb.append(" and tenant_id = #{tenantId}");
			}
		}
		return sb.toString();
	}
	
	public String queryLogsPage(Map<String,Object> params) {
		StringBuilder sb = new StringBuilder("select service_migration_log_id,operate_user,operate_time,ip,old_doc,new_doc,status from (select service_migration_log_id,operate_user,operate_time,ip,'1' as old_doc,'1' as new_doc, status" + (params.get("tenantId")!=null?", tenant_id":"") + " from SERVICE_MIGRATION_LOG where OLD_DOC is not null "
				+ " UNION " +
				"select service_migration_log_id,operate_user,operate_time,ip,null as old_doc,'1' as new_doc,status" + (params.get("tenantId")!=null?", tenant_id":"") + " from SERVICE_MIGRATION_LOG where OLD_DOC is null) sml where 1=1 ");
		if(params.get("fromTime") != null) {
			sb.append(" and OPERATE_TIME > #{fromTime}");
		}
		if(params.get("toTime") != null) {
			sb.append(" and OPERATE_TIME < #{toTime}");
		}
		if(params.get("operator") != null) {
			sb.append(" and operate_user = #{operator}");
		}
		if(params.get("tenantId") != null) {
			sb.append(" and tenant_id = #{tenantId}");
		}
		sb.append(" order by operate_time desc");
		return sb.toString();
	}
}
