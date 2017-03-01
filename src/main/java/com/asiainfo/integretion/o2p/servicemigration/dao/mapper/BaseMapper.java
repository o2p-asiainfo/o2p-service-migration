package com.asiainfo.integretion.o2p.servicemigration.dao.mapper;

import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import com.asiainfo.integretion.o2p.servicemigration.common.typeHandlers.XMLGregorianCalendarToDateHandle;
import com.asiainfo.integretion.o2p.servicemigration.domain.App;
import com.asiainfo.integretion.o2p.servicemigration.domain.Component;
import com.asiainfo.integretion.o2p.servicemigration.domain.FileShare;
import com.asiainfo.integretion.o2p.servicemigration.domain.Org;
import com.asiainfo.integretion.o2p.servicemigration.domain.RoutePolicy;
import com.asiainfo.integretion.o2p.servicemigration.domain.TechnologyImplementNode;

@Repository(value = "baseMapper")
public interface BaseMapper {
	
	@Select("update service_bean set service_address = #{serviceAddress} where service_bean_code = #{code} and tenant_id=#{tenantId}")
	@ResultType(Integer.class)
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public void setServiceAddress(@Param("code") String code, @Param("serviceAddress") String serviceAddress, @Param("tenantId") Integer tenantId);
	
	@Select("select ${seqName}.nextval from dual")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public Integer getOracleSeqByTbName(@Param("seqName") String seqName);
	@ResultType(Integer.class)
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select nextval('${seqName}')")
	public Integer getMysqlSeqByTbName(@Param("seqName") String seqName);
	
	@Select("select max(id)+1 from ${seqName}")
	@ResultType(Integer.class)
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public Integer getMaxId(@Param("seqName") String seqName);
	
	@Select("${sqlStr}")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public List<String> queryObjectCodeList(@Param("sqlStr") String sqlStr);
	
	@Update(value = "${sqlStr}")
	public void excuteDynamicSql(@Param("sqlStr") String sqlStr);

	@ResultMap(value="baseMapper.org")
	@Select("select a.*, c.ZONE_NAME city from org a"
			+ " left join area c on a.area_id = c.area_id "
			+ " where a.org_id = #{orgId} and a.tenant_id = #{tenantId}")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public Org queryOrgByOrgId(@Param("params") Map<String, String> params);
	
	@ResultMap(value="baseMapper.org")
	@Select("select a.*, c.ZONE_NAME city from org a"
			+ " left join area c on a.area_id = c.area_id "
			+ " where a.org_code = #{orgCode} and a.tenant_id=#{tenantId}")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public Org queryOrgByCodeWithOne(@Param("orgCode") String orgCode, @Param("tenantId") Integer tenantId);
	
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@ResultType(String.class)
	@Select("select role_code from org_role where org_id = #{orgId} and tenant_id = #{tenantId}")
	public List<String> queryOrgRoleByOrgId(@Param("params") Map<String, String> params);

	@Results(value = {
			@Result(column = "code", property = "componentCode"),
			@Result(column = "name", property = "name"),
			@Result(column = "orgId = org_id, tenantId = tenant_id", property = "org", javaType=Org.class, one=@One(select="queryOrgByOrgId")),
			@Result(column = "id = component_id, tenantId = tenant_id", property = "apps", javaType=List.class, many=@Many(select="queryAppByComponentId")),
			@Result(column = "type_code", property = "type"),
			@Result(column = "state", property = "status",jdbcType=JdbcType.CHAR)})
	@Select("select c.*, ct.name type_code from component c left join component_type ct on c.component_type_id = ct.component_type_id where c.component_id in ("
			+ " select distinct component_id from ("
			+ "		select si.component_id from ser_invoke_ins si left join service s on si.service_id = s.service_id left join service_bean_2_service sa on sa.service_id = s.service_id where sa.service_bean_id = (select service_bean_id from service_bean where service_bean_code = #{serviceBeanCode} and tenant_id=#{tenantId}) "
			+ "		union "
			+ "		select ti.component_id from ser_tech_impl si left join tech_impl ti on si.tech_impl_id = ti.tech_impl_id left join service_bean_2_service sa on sa.service_id = si.service_id where sa.service_bean_id =(select service_bean_id from service_bean where service_bean_code = #{serviceBeanCode} and tenant_id=#{tenantId})"
			+ " ) x"
			+ ")")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public List<Component> queryComponentByServiceBeanCode(@Param("serviceBeanCode") String serviceBeanCode, @Param("tenantId") Integer tenantId);
	
	@Results(value = {
			@Result(column = "code", property = "componentCode"),
			@Result(column = "name", property = "name"),
			@Result(column = "type_code", property = "type"),
			@Result(column = "state", property = "status",jdbcType=JdbcType.CHAR)})
	@Select("select c.*, ct.name type_code from component c left join component_type ct on c.component_type_id = ct.component_type_id where c.code = #{componentCode} and c.tenant_id=#{tenantId}")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public Component queryComponentByCodeWithOne(@Param("componentCode") String componentCode, @Param("tenantId") Integer tenantId);
	
	@Results(value = {
			@Result(column = "node_code", property = "nodeCode"),
			@Result(column = "node_host", property = "host"),
			@Result(column = "node_ip", property = "ip"),
			@Result(column = "node_port", property = "port"),
			@Result(column = "node_rge_time", property = "createTime", javaType = XMLGregorianCalendar.class, jdbcType = JdbcType.DATE, typeHandler = XMLGregorianCalendarToDateHandle.class),
			@Result(column = "node_lasetest_time", property = "modifyTime", javaType = XMLGregorianCalendar.class, jdbcType = JdbcType.DATE, typeHandler = XMLGregorianCalendarToDateHandle.class),
			@Result(column = "node_heart_add", property = "heartAdd"),
			@Result(column = "node_sync_add", property = "syncAdd"),
			@Result(column = "tech_route_expr", property = "techRouteExpr"),
			@Result(column = "node_state", property = "status")
	})
	@Select("select * from tech_impl_node where node_code = #{nodeCode} and tenant_id=#{tenantId}")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public TechnologyImplementNode queryTechnologyImplementNodeByCodeWithOne(@Param("nodeCode") String nodeCode, @Param("tenantId") Integer tenantId);

	@ResultMap("baseMapper.app")
	@Select("select a.*, at.app_type_name from app a left join app_type at on at.app_type_cd = a.app_type where component_id = #{id} and a.tenant_id = #{tenantId}")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public List<App> queryAppByComponentId(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.app")
	@Select("select a.*, at.app_type_name from app a left join app_type at on at.app_type_cd = a.app_type where appkey = #{appKey} and a.tenant_id=#{tenantId}")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public App queryAppByCodeWithOne(@Param("appKey") String appKey, @Param("tenantId") Integer tenantId);
	
	@ResultMap("baseMapper.routePolicy")
	@Select("select distinct rp.*, rs.rule_strategy_code from route_policy rp left join rule_strategy rs on rs.rule_strategy_id = rp.rule_strategy_id where route_policy_code = #{code} and rp.tenant_id = #{tenantId}")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public RoutePolicy queryRoutePolicyByCodeWithOne(@Param("code") String code, @Param("tenantId") Integer tenantId);
	
	@ResultMap("baseMapper.fileShare")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from file_share where file_share_code = #{fileShareCode} and tenant_id=#{tenantId}")
	public FileShare queryFileShareByCode(@Param("fileShareCode") String fileShareCode, @Param("tenantId") Integer tenantId);
	
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Update("update o2p_cache_version set version = #{cacheVersion} where tenant_id=#{tenantId}")
	public void updateCacheVersion(@Param("cacheVersion") String cacheVersion, @Param("tenantId") Integer tenantId);
}
