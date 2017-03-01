package com.asiainfo.integretion.o2p.servicemigration.dao.mapper;

import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.asiainfo.integretion.o2p.servicemigration.common.typeHandlers.XMLGregorianCalendarToDateHandle;
import com.asiainfo.integretion.o2p.servicemigration.dao.sqlprovider.ServiceMapperSqlProvider;
import com.asiainfo.integretion.o2p.servicemigration.domain.Api;
import com.asiainfo.integretion.o2p.servicemigration.domain.AttrValue;
import com.asiainfo.integretion.o2p.servicemigration.domain.MessageFlow;
import com.asiainfo.integretion.o2p.servicemigration.domain.Service;
import com.asiainfo.integretion.o2p.servicemigration.domain.TechnologyImplement;
import com.asiainfo.integretion.o2p.servicemigration.domain.TechnologyImplementFlowControl;
import com.asiainfo.integretion.o2p.servicemigration.domain.TechnologyImplementNode;

@Component(value = "serviceMapper")
public interface ServiceMapper {

	@Results(value = {
			@Result(column = "service_bean_id", property = "serviceId"),
			@Result(column = "service_bean_code", property = "serviceCode"),
			@Result(column = "style", property = "style", javaType = String.class, jdbcType = JdbcType.CHAR),
			@Result(column = "service_bean_name", property = "name"),
			@Result(column = "create_date", property = "createDate", javaType = XMLGregorianCalendar.class, jdbcType = JdbcType.DATE, typeHandler = XMLGregorianCalendarToDateHandle.class),
			@Result(column = "modify_date", property = "modifyDate", javaType = XMLGregorianCalendar.class, jdbcType = JdbcType.DATE, typeHandler = XMLGregorianCalendarToDateHandle.class),
			@Result(column = "description", property = "description"),
			@Result(column = "state", property = "status", javaType = String.class, jdbcType = JdbcType.CHAR),
			@Result(column = "{serviceBeanId=service_bean_id, tenantId=tenant_id}", property="apis", many=@Many(select="queryApiByServiceBeanId"), javaType=List.class)
	})
	@Select("select * from service_bean where service_bean_code = #{code} and tenant_id = #{tenantId}")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public Service queryServiceBeanByCode(@Param("code") String code, @Param("tenantId") Integer tenantId);

	@Insert(value = "insert into service_bean(service_bean_id, service_bean_code, style, service_bean_name, create_date, modify_date, description, state, tenant_id) values(NULL, #{serviceCode}, #{style}, #{name}, #{createDate,typeHandler=XMLGregorianCalendarToDateHandle},#{modifyDate,typeHandler=XMLGregorianCalendarToDateHandle}, #{description}, #{status}, #{tenantId})")
	public void insertServiceBean(Service service);
	
	@Update(value = "update service_bean set style=#{style}, service_bean_name=#{name}, modify_date=#{modifyDate,typeHandler=XMLGregorianCalendarToDateHandle}, description=#{description}, state=#{status} where code=#{serviceCode} and tenant_id=#{tenantId}")
	public void updateServiceBean(Service service);

	@Results(value = {
			@Result(column = "service_code", property = "apiCode"),
			@Result(column = "service_en_name", property = "name"),
			@Result(column = "service_version", property = "version"),
			@Result(column = "api_method", property = "method"),
			@Result(column = "service_type", property = "type"),
			@Result(column = "state", property = "status", javaType = String.class, jdbcType = JdbcType.CHAR),
			@Result(column = "contract_version_code", property = "contractVersionCode"),
			@Result(column = "apiId = service_id, tenantId = tenant_id", property = "apiInvokeObjects", javaType = List.class ,many=@Many(select="com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ApiInvokeMapper.queryApiInvokeById")),
			@Result(column = "apiId = service_id, tenantId = tenant_id", property = "technologyImplements" , javaType = List.class, many = @Many(select="queryTechImplByApiId")),
			@Result(column = "apiId = service_id, tenantId = tenant_id", property = "serviceCatalogCodes" , javaType = List.class, many = @Many(select="queryServiceCatalogCodeByApiId")),
			@Result(column = "messageFlowId = default_msg_flow, tenantId = tenant_id", property = "messageFlowOrCode", javaType=MessageFlow.class, one=@One(select="com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ApiInvokeMapper.queryMessageFlowById"))
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select se.*, cv.version contract_version_code from (select s.*,a.api_method from service s left join api a on s.SERVICE_ID=a.SERVICE_ID) se left join contract_version cv on cv.contract_version_id = se.contract_version_id where se.service_id in ("
			+ "select service_id from service_bean_2_service where service_bean_id = #{serviceBeanId} and tenant_id = #{tenantId}"
			+ ")")
	public List<Api> queryApiByServiceBeanId(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.techImpl")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select ti.*, cp.comm_pro_name, ct.code from tech_impl ti "
			+ "left join comm_protocal cp on ti.comm_pro_cd = cp.comm_pro_cd "
			+ "left join component ct on ti.component_id = ct.component_id where ti.tech_impl_id in"
			+ " (select tech_impl_id from ser_tech_impl sti where sti.service_id = #{apiId} and tenant_id = #{tenantId})")
	public List<TechnologyImplement> queryTechImplByApiId(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.techImpl")
	@Select("select ti.*, cp.comm_pro_name, ct.code from tech_impl ti "
			+ "left join comm_protocal cp on ti.comm_pro_cd = cp.comm_pro_cd "
			+ "left join component ct on ti.component_id = ct.component_id where ti.tech_impl_code = #{techCode} and ti.tenant_id = #{tenantId}")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	public TechnologyImplement queryTechImplByCodeWithOne(@Param("techCode") String techCode, @Param("tenantId") Integer tenantId);
	
	@ResultMap("baseMapper.attrValue")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select ac.attr_spec_code as attr_value_code, ac.attr_spec_code as attr_value_name, tia.attr_spec_value attr_value, tia.state  from tech_imp_att tia "
			+ " left join attr_spec ac on ac.attr_spec_id = tia.attr_spec_id"
			+ " where tia.tech_impl_id = #{techImplId} and tech_impl_id = #{techImplId} and tia.tenant_id = #{tenantId}")
	public List<AttrValue> queryAttrValuesByTechImplId(@Param("params") Map<String, String> params);
	
	@Results(value = {
			@Result(column = "flow_control_code", property = "flowControlCode"),
			@Result(column = "cc_cd", property = "flowControlPolicyCode"),
			@Result(column = "cutms_value", property = "cutmsValue"),
			@Result(column = "effect_state", property = "effectStatus"),
			@Result(column = "effect_state", property = "status"),
			@Result(column = "cycle_type", property = "cycleType"),
			@Result(column = "cycle_value", property = "cycleValue")
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select(" select ct.* from ctl_counterms_2_tech ct where ct.tech_impl_id = #{techImplId} and tenant_id = #{tenantId}")
	public List<TechnologyImplementFlowControl> queryTechnologyImplementFlowControlsByTechImplId(@Param("params") Map<String, String> params);
	
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
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select(" select * from tech_impl_node where tech_impl_id = #{techImplId} and tenant_id = #{tenantId}")
	public List<TechnologyImplementNode> queryTechnologyImplementNodesByTechImplId(@Param("params") Map<String, String> params);
	
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select(" select dir_code from directory where dir_id in (select dir_id from dir_ser_list where service_id = #{aipId} and tenant_id = #{tenantId})")
	public List<String> queryServiceCatalogCodeByApiId(@Param("params") Map<String, String> params);

	@Results(value = {
			@Result(column = "service_bean_id", property = "serviceId"),
			@Result(column = "service_bean_code", property = "serviceCode"),
			@Result(column = "style", property = "style", javaType = String.class, jdbcType = JdbcType.CHAR),
			@Result(column = "service_bean_name", property = "name"),
			@Result(column = "create_date", property = "createDate", javaType = XMLGregorianCalendar.class, jdbcType = JdbcType.DATE, typeHandler = XMLGregorianCalendarToDateHandle.class),
			@Result(column = "modify_date", property = "modifyDate", javaType = XMLGregorianCalendar.class, jdbcType = JdbcType.DATE, typeHandler = XMLGregorianCalendarToDateHandle.class),
			@Result(column = "service_address", property = "serviceAddress"),
			@Result(column = "description", property = "description"),
			@Result(column = "state", property = "status", javaType = String.class, jdbcType = JdbcType.CHAR)
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@SelectProvider(type=ServiceMapperSqlProvider.class, method="queryServiceBeanPage")
	public List<Service> queryServiceBeanPage(Map<String, Object> params);

	@ResultType(Integer.class)
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@SelectProvider(type=ServiceMapperSqlProvider.class, method="queryServicesCount")
	public int queryServicesCount(Map<String, Object> params);
}
