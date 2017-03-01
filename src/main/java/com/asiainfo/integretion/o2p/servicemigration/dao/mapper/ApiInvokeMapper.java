package com.asiainfo.integretion.o2p.servicemigration.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.asiainfo.integretion.o2p.servicemigration.common.typeHandlers.XMLGregorianCalendarToDateHandle;
import com.asiainfo.integretion.o2p.servicemigration.domain.ApiInvokeObject;
import com.asiainfo.integretion.o2p.servicemigration.domain.ApiInvokeObjectFlowControl;
import com.asiainfo.integretion.o2p.servicemigration.domain.AttrValue;
import com.asiainfo.integretion.o2p.servicemigration.domain.AuthenticationExpress;
import com.asiainfo.integretion.o2p.servicemigration.domain.Endpoint;
import com.asiainfo.integretion.o2p.servicemigration.domain.GetValueExpr;
import com.asiainfo.integretion.o2p.servicemigration.domain.MessageFlow;
import com.asiainfo.integretion.o2p.servicemigration.domain.RouteCondition;
import com.asiainfo.integretion.o2p.servicemigration.domain.RoutePolicy;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceRouteConfig;

@Component(value="apiInvokeMapper")
public interface ApiInvokeMapper {
	
	@Results(value = {
			@Result(column = "ser_invoke_ins_code", property = "apiInvokeObjectCode"),
			@Result(column = "ser_invoke_ins_name", property = "name"),
			@Result(column = "code", property = "comsumerComponentCode"),
			@Result(column = "log_level", property = "logLevel"),
			@Result(column = "state", property = "status"),
			@Result(column = "auth_express", property = "expressLogicRelation"),
			@Result(column = "messageFlowId = message_flow_id, tenantId = tenant_id", property = "messageFlowOrCode", javaType=MessageFlow.class, one=@One(select="queryMessageFlowById")),
			@Result(column = "serInvokeInsId = ser_invoke_ins_id, tenantId = tenant_id", property = "authenticationExpresses", javaType=List.class, many=@Many(select="queryAuthenticationExpressionBySerInvokeInsId")),
			@Result(column = "serInvokeInsId = ser_invoke_ins_id, tenantId = tenant_id", property = "apiInvokeObjectFlowControls", javaType=List.class, many=@Many(select="queryApiInvokeObjectFlowControlsBySerInvokeInsId"))
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select(" select si.*, ct.code from ser_invoke_ins si left join component ct on si.component_id = ct.component_id where service_id = #{service_id} and si.tenant_id = #{tenantId}")
	public List<ApiInvokeObject> queryApiInvokeById(@Param("map") Map<String, String> param);
	
	@Results(value = {
			@Result(column = "proof_effect_code", property = "authenticationExpressCode"),
			@Result(column = "pt_code", property = "type"),
			@Result(column = "status", property = "status", jdbcType=JdbcType.CHAR),
			@Result(column = "proofId = proofe_id, tenantId = tenant_id", property = "attrValues", javaType=List.class, many=@Many(select="queryAttrValueByProofId"))
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select pe.*, pt.pt_code from proof_effect pe left join proof_type pt on pt.pt_cd = pe.pt_cd where pe.proofe_id in (select pem.proofe_id from prooef_effect_mid pem where pem.ser_invoke_ins_id = #{serInvokeInsId}) and petenant_id = #{tenantId}")
	public List<AuthenticationExpress> queryAuthenticationExpressionBySerInvokeInsId(@Param("params") Map<String, String> params);
	
	@Results(value = {
			@Result(column = "proof_effect_code", property = "authenticationExpressCode"),
			@Result(column = "pt_code", property = "type"),
			@Result(column = "status", property = "status", jdbcType=JdbcType.CHAR),
			@Result(column = "proofId = proofe_id, tenantId = tenant_id", property = "attrValues", javaType=List.class, many=@Many(select="queryAttrValueByProofId"))
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select pe.*, pt.pt_code from proof_effect pe left join proof_type pt on pt.pt_cd = pe.pt_cd where proof_effect_code = #{authenticationExpressionCode} and tenant_id = #{tenantId}")
	public AuthenticationExpress queryAuthenticationExpressionByCodeWithOne(@Param("authenticationExpressionCode") String authenticationExpressionCode, @Param("tenantId") Integer tenantId);
	
	@ResultMap("baseMapper.attrValue")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select pv.attr_value, pv.state, ps.pr_atr_spec_name as attr_value_name, ac.attr_spec_code as attr_value_code from proof_values pv"
			+ " left join proof_type_atr_spec ps on ps.pr_atr_spec_cd = pv.pr_atr_spec_cd"
			+ " left join attr_spec ac on ac.attr_spec_id = ps.attr_spec_id"
			+ " where pv.proofe_id = #{proofId} and pv.tenant_id = #{tenantId}")
	public List<AttrValue> queryAttrValueByProofId(@Param("params") Map<String, String> params);
	
	@Results(value = {
			@Result(column = "ctl_counterms_2_comp_code", property = "flowControlCode"),
			@Result(column = "policy_code", property = "flowControlPolicyCode"),
			@Result(column = "cutms_value", property = "cutmsValue"),
			@Result(column = "effect_state", property = "effectStatus"),
			@Result(column = "cycle_type", property = "cycleType"),
			@Result(column = "cycle_value", property = "cycleValue"),
			@Result(column = "config_time", property = "configTime", typeHandler=XMLGregorianCalendarToDateHandle.class),
			@Result(column = "lastest_time", property = "modifyTime", typeHandler=XMLGregorianCalendarToDateHandle.class),
			@Result(column = "cc_seq", property = "sequence"),
			@Result(column = "effect_state", property = "status")
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select csc.*, cc.name policy_code from ctl_counterms_2_comp csc left join control_counterms cc on cc.cc_cd = csc.cc_cd where csc.ser_invoke_ins_id = #{serInvokeInsId} and csc.tenant_id = #{tenantId}")
	public List<ApiInvokeObjectFlowControl> queryApiInvokeObjectFlowControlsBySerInvokeInsId(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.messageFlow")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select mf.*, et.endpoint_code as first_endpoint_code from message_flow mf left join endpoint et on et.endpoint_id = mf.first_endpoint_id where mf.message_flow_id = #{messageFlowId} and mf.tenant_id = #{tenantId}")
	public MessageFlow queryMessageFlowById(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.messageFlow")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select mf.*, et.endpoint_code as first_endpoint_code from message_flow mf left join endpoint et on et.endpoint_id = mf.first_endpoint_id where mf.message_flow_code = #{messageFlowCode} and mf.tenant_id = #{tenantId}")
	public MessageFlow queryMessageFlowByCodeWithOne(@Param("messageFlowCode") String messageFlowCode, @Param("tenantId") Integer tenantId);
	
	@ResultMap("baseMapper.endpoint")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select et.*, es.endpoint_spec_code, dt_in.data_type_code in_data_type_code, dt_out.data_type_code out_data_type_code from endpoint et left join endpoint_spec es on et.endpoint_spec_id = es.endpoint_spec_id "
			+ " left join data_type dt_in on dt_in.data_type_id = et.in_data_type_id"
			+ " left join data_type dt_out on dt_out.data_type_id = et.out_data_type_id"
			+ " where et.endpoint_id in ("
			+ "	select from_endpoint_id from service_route_config route where route.message_flow_id = #{messageFlowId}"
			+ " union"
			+ "	select to_endpoint_id from service_route_config route where route.message_flow_id = #{messageFlowId}"
			+ ") and et.tenant_id = #{tenantId}")
	public List<Endpoint> queryEndpointsByMessageFlowId(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.endpoint")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select et.*, es.endpoint_spec_code, dt_in.data_type_code in_data_type_code, dt_out.data_type_code out_data_type_code from endpoint et left join endpoint_spec es on et.endpoint_spec_id = es.endpoint_spec_id "
			+ " left join data_type dt_in on dt_in.data_type_id = et.in_data_type_id"
			+ " left join data_type dt_out on dt_out.data_type_id = et.out_data_type_id"
			+ " where et.endpoint_code = #{endpointCode} and et.tenant_id = #{tenantId}")
	public Endpoint queryEndpointByCodeWithOne(@Param("endpointCode") String endpointCode, @Param("tenantId") Integer tenantId);
	
	@ResultMap("baseMapper.attrValue")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select ac.attr_spec_code as attr_value_code,  ac.attr_spec_code as attr_value_name, 'A' as state, "
			+ " case when ac.attr_spec_code = 'servicetechid' then 'servicetechid' when ac.attr_spec_code = 'transformer_rule_id' then 'transformer_rule_id' else ev.attr_value end attr_value "
			+ "from endpoint_attr_value ev"
			+ " left join endpoint et on et.endpoint_id = ev.endpoint_id"
			+ " left join endpoint_spec_attr esa on esa.endpoint_spec_attr_id = ev.endpoint_spec_attr_id and esa.endpoint_spec_id = et.endpoint_spec_id"
			+ " left join attr_spec ac on ac.attr_spec_id = esa.attr_spec_id "
			+ " where ev.endpoint_id = #{endpointId} and ev.tenant_id = #{tenantId}")
	public List<AttrValue> queryAttrValueByEndpointId(@Param("params") Map<String, String> params);
	
	@Results(value = {
		@Result(column = "service_route_config_code", property = "serviceRouteConfigCode"),
		@Result(column = "routePolicyId = route_policy_id, tenantId = tenant_id", property = "routePolicy", javaType=RoutePolicy.class, one=@One(select="queryRoutePolicyById")),
		@Result(column = "syn_asyn", property = "synAsyn"),
		@Result(column = "from_endpoint_code", property = "srcEndpointCode", javaType=String.class),
		@Result(column = "to_endpoint_code", property = "tarEndpointCode", javaType=String.class),
		@Result(column = "map_code", property = "mapCode"),
		@Result(column = "state", property = "status")
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select sc.*, e1.endpoint_code from_endpoint_code, e2.endpoint_code to_endpoint_code from service_route_config sc "
			+ "left join endpoint e1 on sc.from_endpoint_id = e1.endpoint_id "
			+ "left join endpoint e2 on e2.endpoint_id = sc.to_endpoint_id "
			+ "where sc.message_flow_id = #{messageFlowId} and sc.tenant_id = #{tenantId}")
	public List<ServiceRouteConfig> queryRouteByMessageFlowId(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.routePolicy")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select distinct rp.*, rs.rule_strategy_code from route_policy rp left join rule_strategy rs on rs.rule_strategy_id = rp.rule_strategy_id where route_policy_id = #{routePolicyId} and rp.tenant_id = #{tenantId}")
	public RoutePolicy queryRoutePolicyById(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.routePolicy")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select rp.*, rs.rule_strategy_code from route_policy rp left join rule_strategy rs on rs.rule_strategy_id = rp.rule_strategy_id where route_policy_Code = #{routePolicyCode} and tenant_id = #{tenantId}")
	public RoutePolicy queryRoutePolicyByCodeWithOne(@Param("routePolicyCode") String routePolicyCode, @Param("tenantId") Integer tenantId);
	
	@ResultMap("baseMapper.routeCondition")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select rc.*, co.operator_code from route_condition rc left join comparison_operator co on co.operator_id = rc.operator_id where rc.route_cond_id = #{routeConditionId} and rc.tenant_id = #{tenantId}")
	public RouteCondition queryRouteConditionById(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.routeCondition")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select rc.*, co.operator_code from route_condition rc left join comparison_operator co on co.operator_id = rc.operator_id where rc.route_condition_code = #{routeConditionCode} and rc.tenant_id = #{tenantId}")
	public RouteCondition queryRouteConditionByCodeWithOne(@Param("routeConditionCode") String routeConditionCode, @Param("tenantId") Integer tenantId);
	
	@ResultMap("baseMapper.getValueExpr")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select ge.*, ce.cond_evaluator_code from get_value_expr ge left join cond_evaluator ce on ge.cond_evaluator_id = ce.cond_evaluator_id where expr_id = #{getValueExprId} and tenant_id = #{tenantId}")
	public GetValueExpr queryGetValueExprByExprId(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.getValueExpr")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select ge.*, ce.cond_evaluator_code from get_value_expr ge left join cond_evaluator ce on ge.cond_evaluator_id = ce.cond_evaluator_id where get_value_expr_code = #{getValueExprCode} and tenant_id = #{tenantId}")
	public GetValueExpr queryGetValueExprByCodeWithOne(@Param("getValueExprCode") String getValueExprCode, @Param("tenantId") Integer tenantId);

}
