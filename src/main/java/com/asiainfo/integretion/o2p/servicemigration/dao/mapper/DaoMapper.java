package com.asiainfo.integretion.o2p.servicemigration.dao.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.integretion.o2p.servicemigration.domain.Api;
import com.asiainfo.integretion.o2p.servicemigration.domain.ApiInvokeObject;
import com.asiainfo.integretion.o2p.servicemigration.domain.ApiInvokeObjectFlowControl;
import com.asiainfo.integretion.o2p.servicemigration.domain.App;
import com.asiainfo.integretion.o2p.servicemigration.domain.AuthenticationExpress;
import com.asiainfo.integretion.o2p.servicemigration.domain.Component;
import com.asiainfo.integretion.o2p.servicemigration.domain.Contract;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractAdapterEndpoint;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractDocument;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractFormat;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractVersion;
import com.asiainfo.integretion.o2p.servicemigration.domain.Endpoint;
import com.asiainfo.integretion.o2p.servicemigration.domain.FileShare;
import com.asiainfo.integretion.o2p.servicemigration.domain.GetValueExpr;
import com.asiainfo.integretion.o2p.servicemigration.domain.MessageFlow;
import com.asiainfo.integretion.o2p.servicemigration.domain.NodeDesc;
import com.asiainfo.integretion.o2p.servicemigration.domain.NodeDescMaper;
import com.asiainfo.integretion.o2p.servicemigration.domain.NodeValueAdapterReq;
import com.asiainfo.integretion.o2p.servicemigration.domain.Org;
import com.asiainfo.integretion.o2p.servicemigration.domain.RouteCondition;
import com.asiainfo.integretion.o2p.servicemigration.domain.RoutePolicy;
import com.asiainfo.integretion.o2p.servicemigration.domain.Service;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceRouteConfig;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceTechnologyImplement;
import com.asiainfo.integretion.o2p.servicemigration.domain.TechnologyImplement;
import com.asiainfo.integretion.o2p.servicemigration.domain.TechnologyImplementFlowControl;
import com.asiainfo.integretion.o2p.servicemigration.domain.TechnologyImplementNode;
import com.asiainfo.integretion.o2p.servicemigration.domain.Transformer;
import com.asiainfo.integretion.o2p.servicemigration.domain.VariableMap;

@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
@Repository(value = "daoMapper")
public interface DaoMapper {
	
	public void insertContract(Contract object);
	
	public void updateContract(Contract object);
	@Delete(value = "delete from contract where code = #{contractCode}")
	public void deleteContract(Contract object);

	//NodeDesc
	@Insert(value = "insert into node_desc(node_desc_id,parent_node_id, node_desc_code, tcp_ctr_f_id, node_name, node_code,node_path, node_type, node_length_cons, node_number_cons, node_type_cons, is_need_sign, is_need_check, nevl_cons_type, nevl_cons_value, java_field, description, state, create_time, lastest_time, tenant_id) "
			+ "values(#{id},#{attributes.parentNodeDescId},#{nodeDescCode},(select tcp_ctr_f_id from contract_format where contract_format_code = #{attributes.contractFormatCode} and tenant_id = #{tenantId}), #{name},#{code},#{nodePath},#{type},#{lengthCons},#{countCons},#{typeCons},#{isSignature},#{isCheck},#{nodeValueConsType},#{nodeValueConsExpressions},#{javaField},#{description},#{status},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{tenantId})")
	public void insertNodeDesc(NodeDesc object);
	@Update(value = "update node_desc set parent_node_id = #{attributes.parentNodeDescId}, tcp_ctr_f_id = (select tcp_ctr_f_id from contract_format where contract_format_code = #{attributes.contractFormatCode} and tenant_id = #{tenantId}), node_name = #{name}, node_code = #{code}, node_path = #{nodePath}, node_type = #{type}, node_length_cons = #{lengthCons}, node_number_cons = #{countCons}, node_type_cons = #{typeCons}, is_need_sign = #{isSignature}, is_need_check = #{isCheck}, nevl_cons_type = #{nodeValueConsType}, nevl_cons_value = #{nodeValueConsExpressions}, java_field = #{javaField}, description = #{description}, state = #{status} where node_desc_code = #{nodeDescCode} and tenant_id = #{tenantId}")
	public void updateNodeDesc(NodeDesc object);
	@Delete(value = "delete from node_desc where node_desc_code = #{nodeDescCode} and tenant_id = #{tenantId}")
	public void deleteNodeDesc(NodeDesc object);
	
	@ResultType(Integer.class)
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select node_desc_id from node_desc where node_desc_code = #{nodeDescCode} and tenant_id = #{tenantId}")
	public Integer queryNodeDescIdByCode(@Param("nodeDescCode") String nodeDescCode, @Param("tenantId") Integer tenantId);

	//ContractFormat
	@Insert(value = "insert into contract_format(tcp_ctr_f_id, contract_version_id, req_rsp, contract_format_code, con_type, descriptor, state, create_time, lastest_time, tenant_id) values(#{id},(select contract_version_id from contract_version where version = #{attributes.contractVersionCode} and tenant_id = #{tenantId}), #{attributes.reqRsp}, #{contractFormatCode},#{formatType},#{description},#{status},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{tenantId})")
	public void insertContractFormat(ContractFormat object);
	@Update(value = "update contract_format set contract_format_code = #{contractFormatCode}, con_type=#{formatType}, contract_version_id = (select contract_version_id from contract_version where version = #{attributes.contractVersionCode} and tenant_id = #{tenantId}), req_rsp=#{attributes.reqRsp}, contract_format_code = #{contractFormatCode}, con_type = #{formatType}, descriptor = #{description}, state = #{status} where (contract_format_code = #{contractFormatCode} or (req_rsp=#{attributes.reqRsp} and contract_version_id=(select contract_version_id from contract_version where version=#{attributes.contractVersionCode} and tenant_id = #{tenantId}))) and tenant_id = #{tenantId}")
	public void updateContractFormat(ContractFormat object);
	@Delete(value = "delete from contract_format where contract_format_code = #{contractFormatCode} and tenant_id = #{tenantId}")
	public void deleteContractFormat(ContractFormat object);

	//FileShare
	@Insert(value = "insert into file_share(s_file_id, file_share_code, s_file_name, s_file_content, state,create_time, tenant_id) values(#{id},#{fileShareCode},#{name},#{content, typeHandler=BlobTypeHandler},#{status},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{tenantId})")
	public void insertFileShare(FileShare object);
	@Update(value = "update file_share set file_share_code = #{fileShareCode}, s_file_name = #{name}, s_file_content = #{content, typeHandler=BlobTypeHandler}, state = #{status} where file_share_code = #{fileShareCode} and tenant_id = #{tenantId}")
	public void updateFileShare(FileShare object);
	@Delete(value = "delete from file_share where file_share_code = #{fileShareCode} and tenant_id = #{tenantId}")
	public void deleteFileShare(FileShare object);

	//ContractDocument
	public void insertContractDocument(ContractDocument object);
	public void updateContractDocument(ContractDocument object);
	@Delete(value = "delete from contract_doc where resource_aliss = #{resourceAliss}")
	public void deleteContractDocument(ContractDocument object);

	//ContractVersion
	public void insertContractVersion(ContractVersion object);
	@Update(value = "update contract_version set version = #{version}, is_need_check = #{checkFlag}, descriptor = #{description}, state = #{status} where version = #{version}")
	public void updateContractVersion(ContractVersion object);
	@Delete(value = "delete from contract_version where version = #{version}")
	public void deleteContractVersion(ContractVersion object);

	//TechnologyImplementFlowControl
	@Insert(value = "insert into ctl_counterms_2_tech(tech_impl_id, flow_control_code, cc_cd, cutms_value, effect_state, cycle_type, cycle_value,confi_time, tenant_id) values((select tech_impl_id from tech_impl where tech_impl_code = #{attributes.technologyImplementCode} and tenant_id = #{tenantId}), #{flowControlCode},#{flowControlPolicyCode},#{cutmsValue},#{effectStatus},#{cycleType},#{cycleValue},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{tenantId})")
	public void insertTechnologyImplementFlowControl(TechnologyImplementFlowControl object);
	@Update(value = "update ctl_counterms_2_tech set tech_impl_id=(select tech_impl_id from tech_impl where tech_impl_code = #{attributes.technologyImplementCode} and tenant_id = #{tenantId}), cc_cd = #{flowControlPolicyCode}, cutms_value = #{cutmsValue}, effect_state = #{effectStatus}, cycle_type = #{cycleType}, cycle_value = #{cycleValue} where flow_control_code = #{flowControlCode} and tenant_id = #{tenantId}")
	public void updateTechnologyImplementFlowControl(TechnologyImplementFlowControl object);
	@Delete(value = "delete from ctl_counterms_2_tech where flow_control_code = #{flowControlCode} and tenant_id = #{tenantId}")
	public void deleteTechnologyImplementFlowControl(TechnologyImplementFlowControl object);
	
	//TechnologyImplementNode
		@Insert(value = "insert into tech_impl_node(tech_impl_node_id, tech_impl_id, node_host, node_ip, node_port, node_heart_add, node_sync_add, tech_route_expr, node_rge_time, node_lasetest_time, node_state, node_code, tenant_id) "
				+ "values(#{id}, (select tech_impl_id from tech_impl where tech_impl_code = #{attributes.technologyImplementCode} and tenant_id = #{tenantId}), #{host},#{ip},#{port},#{heartAdd},#{syncAdd},#{techRouteExpr},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{status}, #{nodeCode}, #{tenantId})")
		public void insertTechnologyImplementNode(TechnologyImplementNode object);
		@Update(value = "update tech_impl_node set tech_impl_id=(select tech_impl_id from tech_impl where tech_impl_code = #{attributes.technologyImplementCode} and tenant_id = #{tenantId}), node_host = #{host}, node_ip = #{ip}, node_port = #{port}, node_state = #{status}, node_heart_add = #{heartAdd}, node_sync_add = #{syncAdd}, tech_route_expr = #{techRouteExpr}, node_lasetest_time=#{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle} where node_code = #{nodeCode} and tenant_id = #{tenantId}")
		public void updateTechnologyImplementNode(TechnologyImplementNode object);
		@Delete(value = "delete from tech_impl_node where node_code = #{nodeCode} and tenant_id = #{tenantId}")
		public void deleteTechnologyImplementNode(TechnologyImplementNode object);

	//TechnologyImplement
	@Insert(value = "insert into tech_impl(tech_impl_id, tech_impl_code, component_id, tech_impl_name, comm_pro_cd, usealbe_state, reg_time,laest_time,tech_imp_con_po_id, tenant_id) values(#{id},#{technologyImplementCode},(select component_id from component where code = #{producerComponentCode} and tenant_id = #{tenantId}),#{name},(select comm_pro_cd from comm_protocal where comm_pro_name = #{communicationPotocolCode} and tenant_id = #{tenantId}),#{status},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle},1, #{tenantId})")
	public void insertTechnologyImplement(TechnologyImplement object);
	@Update(value = "update tech_impl set tech_impl_code = #{technologyImplementCode}, component_id = (select component_id from component where code = #{producerComponentCode} and tenant_id = #{tenantId}), tech_impl_name = #{name}, comm_pro_cd = (select comm_pro_cd from comm_protocal where comm_pro_name = #{communicationPotocolCode}), usealbe_state = #{status} where tech_impl_code = #{technologyImplementCode} and tenant_id = #{tenantId}")
	public void updateTechnologyImplement(TechnologyImplement object);
	@Delete(value = "delete from tech_impl where tech_impl_code = #{technologyImplementCode} and tenant_id = #{tenantId}")
	public void deleteTechnologyImplement(TechnologyImplement object);

	//AuthenticationExpress
	@Insert(value = "insert into proof_effect(proofe_id, proof_effect_code, pt_cd, status, ser_invoke_ins_id,create_time,datetime, tenant_id) values(#{id},#{authenticationExpressCode},"
			+ "(select pt_cd from proof_type where pt_code = #{type} and tenant_id = #{tenantId}),#{status}, (select ser_invoke_ins_id from ser_invoke_ins where ser_invoke_ins_code = #{attributes.apiInvokeObjectCode} and tenant_id = #{tenantId}),#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{tenantId})")
	public void insertAuthenticationExpress(AuthenticationExpress object);
	@Update(value = "update proof_effect set proof_effect_code = #{authenticationExpressCode}, pt_cd = (select pt_cd from proof_type where pt_code = #{type} and tenant_id = #{tenantId}), status = #{status}, ser_invoke_ins_id=(select ser_invoke_ins_id from ser_invoke_ins where ser_invoke_ins_code = #{attributes.apiInvokeObjectCode} and tenant_id = #{tenantId}) where proof_effect_code = #{authenticationExpressCode} and tenant_id = #{tenantId}")
	public void updateAuthenticationExpress(AuthenticationExpress object);
	@Delete(value = "delete from proof_effect where proof_effect_code = #{authenticationExpressCode} and tenant_id = #{tenantId}")
	public void deleteAuthenticationExpress(AuthenticationExpress object);

	//NodeDescMaper
	@Insert(value = "insert into node_desc_maper(node_desc_maper_id, node_desc_maper_code, src_node_desc_id, tar_node_desc_id, contract_adapter_id, action_type_cd, create_dt, tenant_id) values(#{id},#{nodeDescMaperCode},(select node_desc_id from node_desc where node_desc_code = #{sourceNodeDescCode} and tenant_id = #{tenantId}),(select node_desc_id from node_desc where node_desc_code = #{targetNodeDescCode} and tenant_id = #{tenantId}),(select contract_adapter_id from contract_adapter where contract_adapter_code = #{attributes.transformerCode} and tenant_id = #{tenantId}),#{actionType},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{tenantId})")
	public void insertNodeDescMaper(NodeDescMaper object);
	@Update(value = "update node_desc_maper set node_desc_maper_code = #{nodeDescMaperCode}, src_node_desc_id = (select node_desc_id from node_desc where node_desc_code = #{sourceNodeDescCode} and tenant_id = #{tenantId}), tar_node_desc_id = (select node_desc_id from node_desc where node_desc_code = #{targetNodeDescCode} and tenant_id = #{tenantId}), contract_adapter_id = (select contract_adapter_id from contract_adapter where contract_adapter_code = #{attributes.transformerCode} and tenant_id = #{tenantId}), action_type_cd = #{actionType}, create_dt = #{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle} where node_desc_maper_code = #{nodeDescMaperCode} and tenant_id = #{tenantId}")
	public void updateNodeDescMaper(NodeDescMaper object);
	@Delete(value = "delete from node_desc_maper where node_desc_maper_code = #{nodeDescMaperCode} and tenant_id = #{tenantId}")
	public void deleteNodeDescMaper(NodeDescMaper object);

	//NodeValueAdapterReq
	@Insert(value = "insert into node_val_adapter_req(node_val_adapter_req_id, node_desc_id, contract_adapter_id, node_val_adapter_req_code, node_value_source_cd, value_express, script, trigger_express, state, version, tenant_id) "
			+ " values(#{id},(select node_desc_id from node_desc where node_desc_code = #{tarNodeDescCode} and tenant_id = #{tenantId}), (select contract_adapter_id from contract_adapter where contract_adapter_code = #{attributes.transformerCode} and tenant_id = #{tenantId}), #{nodeValueAdapterReqCode},#{nodeValueSrouceType},#{valueExpression},#{script},#{triggerExpression},#{status},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{tenantId})")
	public void insertNodeValueAdapterReq(NodeValueAdapterReq object);
	@Update(value = "update node_val_adapter_req set node_desc_id = (select node_desc_id from node_desc where node_desc_code = #{tarNodeDescCode} and tenant_id = #{tenantId}), contract_adapter_id = (select contract_adapter_id from contract_adapter where contract_adapter_code = #{attributes.transformerCode} and tenant_id = #{tenantId}), node_val_adapter_req_code = #{nodeValueAdapterReqCode}, node_value_source_cd = #{nodeValueSrouceType}, value_express = #{valueExpression}, script = #{script}, trigger_express = #{triggerExpression}, state = #{status} where node_val_adapter_req_code = #{nodeValueAdapterReqCode} and tenant_id = #{tenantId}")
	public void updateNodeValueAdapterReq(NodeValueAdapterReq object);
	@Delete(value = "delete from node_val_adapter_req where node_val_adapter_req_code = #{nodeValueAdapterReqCode} and tenant_id = #{tenantId}")
	public void deleteNodeValueAdapterReq(NodeValueAdapterReq object);

	//VariableMap
	@Insert(value = "insert into variable_map(var_maping_id, variable_map_code, cons_map_cd, data_source, key_express, val_express, version, state, tenant_id) values(#{id},#{variableMapCode},#{constantMappingTypeCode},#{dataSource},#{keyExpression},#{valueExpression},#{version,typeHandler=XMLGregorianCalendarToDateHandle},#{status}, #{tenantId})")
	public void insertVariableMap(VariableMap object);
	@Update(value = "update variable_map set cons_map_cd = #{constantMappingTypeCode}, data_source = #{dataSource}, key_express = #{keyExpression}, val_express = #{valueExpression}, version = #{version,typeHandler=XMLGregorianCalendarToDateHandle}, state = #{status} where variable_map_code = #{variableMapCode} and tenant_id = #{tenantId}")
	public void updateVariableMap(VariableMap object);
	@Delete(value = "delete from variable_map where variable_map_code = #{variableMapCode} and tenant_id = #{tenantId}")
	public void deleteVariableMap(VariableMap object);
	
	//ContractAdapterEndpoint
	@Insert(value = "insert into contract_adapter_endpoint(contract_adapter_endpoint_id, contract_adapter_endpoint_code, contract_adapter_id, endpoint_id, contract_formate_id, action_type, create_date, tenant_id) values(#{id},#{contractAdapterEndpointCode},(select contract_adapter_id from contract_adapter where contract_adapter_code = #{attributes.transformerCode} and tenant_id = #{tenantId}),(select endpoint_id from endpoint where endpoint_code = #{endpointCode} and tenant_id = #{tenantId}),(select tcp_ctr_f_id from contract_format where contract_format_code = #{contractFormatCode} and tenant_id = #{tenantId}),#{actionType},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{tenantId})")
	public void insertContractAdapterEndpoint(ContractAdapterEndpoint object);
	@Update(value = "update contract_adapter_endpoint set contract_adapter_id=(select contract_adapter_id from contract_adapter where contract_adapter_code = #{attributes.transformerCode} and tenant_id = #{tenantId}), endpoint_id = (select endpoint_id from endpoint where endpoint_code = #{endpointCode} and tenant_id = #{tenantId}), contract_formate_id = (select tcp_ctr_f_id from contract_format where contract_format_code = #{contractFormatCode} and tenant_id = #{tenantId}), action_type = #{actionType} where contract_adapter_endpoint_code = #{contractAdapterEndpointCode} and tenant_id = #{tenantId}")
	public void updateContractAdapterEndpoint(ContractAdapterEndpoint object);
	@Delete(value = "delete from contract_adapter_endpoint where contract_adapter_endpoint_code = #{contractAdapterEndpointCode} and tenant_id = #{tenantId}")
	public void deleteContractAdapterEndpoint(ContractAdapterEndpoint object);

	//Transformer
	public void insertTransformer(Transformer object);
	public void updateTransformer(Transformer object);
	@Delete(value = "delete from contract_adapter where contract_adapter_code = #{transformerCode} and tenant_id = #{tenantId}")
	public void deleteTransformer(Transformer object);

	//Endpoint
	@Insert(value = "insert into endpoint(endpoint_id, endpoint_spec_id, endpoint_code, endpoint_name,in_data_type_id,out_data_type_id, enable_in_trace, enable_out_trace, enable_in_log, enable_out_log, endpoint_desc, state,create_date, lastest_date,map_code, tenant_id) values(#{id},(select endpoint_spec_id from endpoint_spec where endpoint_spec_code=#{endpointSpec.endpointSpecCode}),#{endpointCode},#{endpointName},(select data_type_id from data_type where data_type_code = #{inDataTypeCode}),(select data_type_id from data_type where data_type_code = #{outDataTypeCode}),#{inTrace},#{outTrace},#{inLog},#{outLog},#{endpointDesc},#{status},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, replace(#{mapCode}, #{endpointCode}, #{id}), #{tenantId})")
	public void insertEndpoint(Endpoint object);
	@Update(value = "update endpoint set endpoint_spec_id = (select endpoint_spec_id from endpoint_spec where endpoint_spec_code=#{endpointSpec.endpointSpecCode}),endpoint_code = #{endpointCode}, endpoint_name = #{endpointName}, in_data_type_id=(select data_type_id from data_type where data_type_code = #{inDataTypeCode}),out_data_type_id=(select data_type_id from data_type where data_type_code = #{outDataTypeCode}),enable_in_trace = #{inTrace},enable_out_trace = #{outTrace}, enable_in_log = #{inLog}, enable_out_log = #{outLog}, endpoint_desc = #{endpointDesc}, state = #{status},map_code=#{mapCode} where endpoint_code = #{endpointCode} and tenant_id = #{tenantId}")
	public void updateEndpoint(Endpoint object);
	@Delete(value = "delete from endpoint where endpoint_code = #{endpointCode} and tenant_id = #{tenantId}")
	public void deleteEndpoint(Endpoint object);

	//GetValueExpr
	@Insert(value = "insert into get_value_expr(expr_id, get_value_expr_code, cond_evaluator_id, up_expr_id, expr, req_rsp, expr_type, tenant_id) values(#{id},#{getValueExprCode},(select cond_evaluator_id from cond_evaluator where cond_evaluator_code = #{condEvaluatorCode} and tenant_id = #{tenantId}),(select expr_id from get_value_expr where get_value_expr_code = #{upGetValueExpr.getValueExprCode} and tenant_id = #{tenantId}),#{expr},#{reqRsp},#{exprType}, #{tenantId})")
	public void insertGetValueExpr(GetValueExpr object);
	@Update(value = "update get_value_expr set cond_evaluator_id = (select cond_evaluator_id from cond_evaluator where cond_evaluator_code = #{condEvaluatorCode} and tenant_id = #{tenantId}), expr = #{expr}, up_expr_id = (select expr_id from get_value_expr where get_value_expr_code = #{upGetValueExpr.getValueExprCode} and tenant_id = #{tenantId}),req_rsp = #{reqRsp}, expr_type = #{exprType} where get_value_expr_code = #{getValueExprCode} and tenant_id = #{tenantId}")
	public void updateGetValueExpr(GetValueExpr object);
	@Delete(value = "delete from get_value_expr where get_value_expr_code = #{getValueExprCode} and tenant_id = #{tenantId}")
	public void deleteGetValueExpr(GetValueExpr object);

	//RouteCondition
	public void insertRouteCondition(RouteCondition object);
	public void updateRouteCondition(RouteCondition object);
	@Delete(value = "delete from route_condition where route_condition_code = #{routeConditionCode} and tenant_id = #{tenantId}")
	public void deleteRouteCondition(RouteCondition object);

	//RoutePolicy
	@Insert(value = "insert into route_policy(route_policy_id, route_policy_code, route_cond_id, rule_strategy_id, tenant_id) values(#{id},#{routePolicyCode}, #{routeCondition.id},(select rule_strategy_id from rule_strategy where rule_strategy_code = #{ruleStrategyCode}), #{tenantId})")
	public void insertRoutePolicy(RoutePolicy object);
	@Update(value = "update route_policy set rule_strategy_id = (select rule_strategy_id from rule_strategy where rule_strategy_code = #{ruleStrategyCode} and tenant_id = #{tenantId}) where route_policy_code = #{routePolicyCode} and tenant_id = #{tenantId}")
	public void updateRoutePolicy(RoutePolicy object);
	@Delete(value = "delete from route_policy where route_policy_code = #{routePolicyCode} and tenant_id = #{tenantId}")
	public void deleteRoutePolicy(RoutePolicy object);

	//ServiceRouteConfig
	@Insert(value = "insert into service_route_config(route_id, service_route_config_code, route_policy_id, message_flow_id, from_endpoint_id, to_endpoint_id, syn_asyn, state,create_date, lastest_date,map_code, tenant_id) values(#{id},#{serviceRouteConfigCode},(select route_policy_id from route_policy where route_policy_code = #{routePolicy.routePolicyCode} and tenant_id = #{tenantId}),(select message_flow_id from message_flow where message_flow_code = #{attributes.messageFlowCode} and tenant_id = #{tenantId}), (select endpoint_id from endpoint where endpoint_code = #{srcEndpointCode} and tenant_id = #{tenantId}),(select endpoint_id from endpoint where endpoint_code = #{tarEndpointCode} and tenant_id = #{tenantId}), #{synAsyn},#{status},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle},#{mapCode}, #{tenantId})")
	public void insertServiceRouteConfig(ServiceRouteConfig object);
	@Update(value = "update service_route_config set route_policy_id = (select route_policy_id from route_policy where route_policy_code = #{routePolicy.routePolicyCode} and tenant_id = #{tenantId}), message_flow_id = (select message_flow_id from message_flow where message_flow_code = #{attributes.messageFlowCode} and tenant_id = #{tenantId}), from_endpoint_id=(select endpoint_id from endpoint where endpoint_code = #{srcEndpointCode} and tenant_id = #{tenantId}), to_endpoint_id=(select endpoint_id from endpoint where endpoint_code = #{tarEndpointCode} and tenant_id = #{tenantId}), syn_asyn = #{synAsyn}, state = #{status}, map_code=#{mapCode} where service_route_config_code = #{serviceRouteConfigCode} and tenant_id = #{tenantId}")
	public void updateServiceRouteConfig(ServiceRouteConfig object);
	@Delete(value = "delete from service_route_config where service_route_config_code = #{serviceRouteConfigCode} and tenant_id = #{tenantId}")
	public void deleteServiceRouteConfig(ServiceRouteConfig object);

	//MessageFlow
	@Insert(value = "insert into message_flow(message_flow_id, first_endpoint_id, message_flow_code, message_flow_name, create_time, lastest_time, descriptor, state, tenant_id) "
			+ " values(#{id},(select endpoint_id from endpoint where endpoint_code = #{startEndpointCode} and tenant_id = #{tenantId}), #{messageFlowCode},#{messageFlowName},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle},#{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle},#{description},#{status}, #{tenantId})")
	public void insertMessageFlow(MessageFlow object);
	@Update(value = "update message_flow set first_endpoint_id = (select endpoint_id from endpoint where endpoint_code = #{startEndpointCode} and tenant_id = #{tenantId}),message_flow_code = #{messageFlowCode}, message_flow_name = #{messageFlowName}, lastest_time = #{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, descriptor = #{description}, state = #{status} where message_flow_code = #{messageFlowCode} and tenant_id = #{tenantId}")
	public void updateMessageFlow(MessageFlow object);
	@Delete(value = "delete from message_flow where message_flow_code = #{messageFlowCode} and tenant_id = #{tenantId}")
	public void deleteMessageFlow(MessageFlow object);

	//ApiInvokeObjectFlowControl
	public void insertApiInvokeObjectFlowControl(ApiInvokeObjectFlowControl object);
	@Update(value = "update ctl_counterms_2_comp set cc_cd = (select cc_cd from control_counterms where name = #{flowControlPolicyCode} and tenant_id = #{tenantId}), cutms_value = #{cutmsValue}, effect_state = #{effectStatus}, ser_invoke_ins_id = (select ser_invoke_ins_id from ser_invoke_ins where ser_invoke_ins_code = #{attributes.apiInvokeObjectCode} and tenant_id = #{tenantId}),cycle_type = #{cycleType}, cycle_value = #{cycleValue}, lastest_time = #{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, cc_seq = #{sequence}, effect_state = #{status} where ctl_counterms_2_comp_code = #{flowControlCode} and tenant_id = #{tenantId}")
	public void updateApiInvokeObjectFlowControl(ApiInvokeObjectFlowControl object);
	@Delete(value = "delete from ctl_counterms_2_comp where ctl_counterms_2_comp_code = #{flowControlCode} and tenant_id = #{tenantId}")
	public void deleteApiInvokeObjectFlowControl(ApiInvokeObjectFlowControl object);

	//api对应service表
	public void insertApi(Api object);
	@Update(value = "update service set contract_version_id=(select contract_version_id from contract_version where version = #{contractVersionCode} and tenant_id =  #{tenantId}), service_version = #{version},service_cn_name=#{name}, service_en_name=#{name}, service_type=#{type}, state=#{status},default_msg_flow=(select message_flow_id from message_flow where message_flow_code=#{attributes.messageFlowOrCode} and tenant_id = #{tenantId}) where service_code=#{apiCode} and tenant_id = #{tenantId}")
	public void updateApi(Api object);
	@Delete(value = "delete from service  where service_code=#{apiCode} and tenant_id = #{tenantId}")
	public void deleteApi(Api object);
	
	public void insertApiMethod(Map<String, Object> object);
	@Update(value = "update api set api_name = #{name},api_version=#{version}, api_method=#{method} where service_id=#{serviceId}")
	public void updateApiMethod(Map<String, Object> object);
	@Delete(value = "delete from api  where api_method=#{method}")
	public void deleteApiMethod(Map<String, Object> object);
	
	@Insert(value = "insert into DIR_SER_LIST(DIR_SER_LIST_ID, SERVICE_ID, DIR_ID, STATE, CREATE_TIME, LASTEST_TIME, tenant_id) values"
			+ "(#{id}, (select service_id from service where service_code = #{apiCode} and tenant_id = #{tenantId}), (select dir_id from DIRECTORY where dir_code = #{dirCode} and tenant_id = #{tenantId}), #{status}, #{createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{tenantId})")
	public void insertDirSerList(Map<String, Object> object);
	@Delete(value = "delete from DIR_SER_LIST  where service_id=(select service_id from service where service_code = #{apiCode} and tenant_id = #{tenantId})")
	public void deleteDirSerList(Map<String, Object> object);
	
	//ApiInvokeObject
	@Insert(value = "insert into ser_invoke_ins(ser_invoke_ins_id, ser_invoke_ins_code, message_flow_id, component_id, service_id, ser_invoke_ins_name, create_date, state, lastest_date, log_level,auth_express, tenant_id) "
			+ " values(#{id}, #{apiInvokeObjectCode},(select message_flow_id from message_flow where message_flow_code = #{attributes.messageFlowOrCode} and tenant_id = #{tenantId}), (select component_id from component where code = #{comsumerComponentCode} and tenant_id = #{tenantId}), (select service_id from service where service_code = #{attributes.apiCode} and tenant_id = #{tenantId}), #{name}, #{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle},#{status}, #{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{logLevel},#{expressLogicRelation}, #{tenantId})")
	public void insertApiInvokeObject(ApiInvokeObject object);
	@Update(value = "update ser_invoke_ins set message_flow_id=(select message_flow_id from message_flow where message_flow_code = #{attributes.messageFlowOrCode} and tenant_id = #{tenantId}), component_id=(select component_id from component where code = #{comsumerComponentCode} and tenant_id = #{tenantId}), service_id=(select service_id from service where service_code = #{attributes.apiCode} and tenant_id = #{tenantId}), ser_invoke_ins_name=#{name}, "
			+ " create_date=#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle},state=#{status}, lastest_date=#{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, log_level=#{logLevel} ,auth_express=#{expressLogicRelation} where ser_invoke_ins_code= #{apiInvokeObjectCode} and tenant_id = #{tenantId}")
	public void updateApiInvokeObject(ApiInvokeObject object);
	@Delete(value = "delete from ser_invoke_ins  where ser_invoke_ins_code=#{apiInvokeObjectCode} and tenant_id = #{tenantId}")
	public void deleteApiInvokeObject(ApiInvokeObject object);

	//service
	public void insertService(Service object);
	@Update(value = "update service_bean set style=#{style}, service_bean_name=#{name}, modify_date=#{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, description=#{description, jdbcType=VARCHAR}, state=#{status}, service_address=#{serviceAddress} where service_bean_code=#{serviceCode} and tenant_id = #{tenantId}")
	public void updateService(Service object);
	@Delete(value = "delete from service_bean  where service_bean_code=#{serviceCode} and tenant_id = #{tenantId}")
	public void deleteService(Service object);

	//Org
	public void insertOrg(Org object);
	@Update(value = "update org set org_code = #{orgCode}, name = #{name}, org_type_code = #{type}, area_id = (select area_id from area where zone_name =#{area} and tenant_id = #{tenantId}), org_username = #{username}, org_pwd = #{password}, cert_type_code = #{idType}, cert_number = #{idNumber}, email = #{email}, telephone = #{contractNumber}, s_file_id = #{icon}, fil_s_file_id = #{idScannedCopy}, descriptor = #{description}, state = #{status} where org_code = #{orgCode} and tenant_id = #{tenantId}")
	public void updateOrg(Org object);
	@Delete(value = "delete from org where org_code = #{orgCode} and tenant_id = #{tenantId}")
	public void deleteOrg(Org object);
	
	//App
	@Insert(value = "insert into app(app_id, component_id, app_summa, app_url, app_type, app_oauth_type, token_enable_time, appkey, app_desc, app_create_time, tenant_id) values(#{id},(select component_id from component where code = #{attributes.componentCode} and tenant_id = #{tenantId}), #{summary},#{url},(select app_type_cd from app_type where app_type_name = #{type}),#{oauthType},#{tokenEnabledTime},#{appKey},#{description},#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{tenantId})")
	public void insertApp(App object);
	@Update(value = "update app set component_id=(select component_id from component where code = #{attributes.componentCode} and tenant_id = #{tenantId}),  app_summa = #{summary}, app_url = #{url}, app_type = (select app_type_cd from app_type where app_type_name = #{type}), app_oauth_type = #{oauthType}, token_enable_time = #{tokenEnabledTime}, appkey = #{appKey}, app_desc = #{description} where appKey = #{appKey} and tenant_id = #{tenantId}")
	public void updateApp(App object);
	@Delete(value = "delete from app where appKey = #{appKey} and tenant_id = #{tenantId}")
	public void deleteApp(App object);

	//Component
	public void insertComponent(Component object);
	@Update(value = "update component set org_id = (select org_id from org where org_code = #{attributes.org} and tenant_id = #{tenantId}), code = #{componentCode}, name = #{name}, component_type_id = (select component_type_id from component_type where name = #{type}), state = #{status} where code = #{componentCode} and tenant_id = #{tenantId}")
	public void updateComponent(Component object);
	@Delete(value = "delete from component where code = #{componentCode} and tenant_id = #{tenantId}")
	public void deleteComponent(Component object);
	
	//TechImplAttrValue
	@Insert(value = "insert into tech_imp_att(tech_imp_att_id, attr_spec_id, tech_impl_id, attr_spec_value,state, create_time, lastest_time, tenant_id)"
			+ "	values(#{id}, (select attr_spec_id from attr_spec where attr_spec_code = #{attrValueCode} and tenant_id = #{tenantId}), (select tech_impl_id from tech_impl where tech_impl_code = #{techImplCode} and tenant_id = #{tenantId}), #{value}, #{status}, #{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{tenantId})")
	public void insertTechImplAttrValue(Map<String, Object> object);
	@Update(value = "update tech_imp_att set attr_spec_value=#{value}, state=#{status}, lastest_time=#{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle} where attr_spec_id = (select attr_spec_id from attr_spec where attr_spec_code = #{attrValueCode} and tenant_id = #{tenantId}) and tech_impl_id = (select tech_impl_id from tech_impl where tech_impl_code = #{techImplCode} and tenant_id = #{tenantId})")
	public void updateTechImplAttrValue(Map<String, Object> object);
	@Delete(value = "delete from tech_imp_att where  attr_spec_id = (select attr_spec_id from attr_spec where attr_spec_code = #{attrValueCode} and tenant_id = #{tenantId}) and tech_impl_id = (select tech_impl_id from tech_impl where tech_impl_code = #{techImplCode} and tenant_id = #{tenantId})")
	public void deleteTechImplAttrValue(Map<String, Object> object);
	@Delete(value = "delete from tech_imp_att where  tech_impl_id = (select tech_impl_id from tech_impl where tech_impl_code = #{techImplCode} and tenant_id = #{tenantId})")
	public void deleteAllTechImplAttrValue(Map<String, Object> object);
	
	//TechImplAttrValue
	@Insert(value = "insert into proof_values(pv_id, proofe_id, pr_atr_spec_cd, attr_value, create_time, lastest_time,state, tenant_id) values(#{id},(select proofe_id from proof_effect where proof_effect_code = #{authenticationExpressCode} and tenant_id = #{tenantId}), (select pr_atr_spec_cd from proof_type_atr_spec where pr_atr_spec_name = #{name} and attr_spec_id = (select attr_spec_id from attr_spec where attr_spec_code =#{attrValueCode} and tenant_id = #{tenantId}) and tenant_id = #{tenantId}), #{value}, #{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle},#{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{status}, #{tenantId})")
	public void insertAuthenticationExpressAttrValue(Map<String, Object> object);
	@Update(value = "update proof_values set attr_value=#{value},lastest_time=#{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle},state=#{status} where  pr_atr_spec_cd= (select pr_atr_spec_cd from proof_type_atr_spec where attr_spec_id = (select attr_spec_id from attr_spec where attr_spec_code =#{attrValueCode} and tenant_id = #{tenantId}) and tenant_id = #{tenantId}) and (select proofe_id from proof_effect where proof_effect_code = #{authenticationExpressCode} and tenant_id = #{tenantId})")
	public void updateAuthenticationExpressAttrValue(Map<String, Object> object);
	@Delete(value = "delete from proof_values where pr_atr_spec_cd=(select pr_atr_spec_cd from proof_type_atr_spec where attr_spec_id = (select attr_spec_id from attr_spec where attr_spec_code =#{attrValueCode} and tenant_id = #{tenantId})) and proofe_id=(select proofe_id from proof_effect where proof_effect_code = #{authenticationExpressCode} and tenant_id = #{tenantId})")
	public void deleteAuthenticationExpressAttrValue(Map<String, Object> object);
	@Delete(value = "delete from proof_values where proofe_id=(select proofe_id from proof_effect where proof_effect_code = #{authenticationExpressCode} and tenant_id = #{tenantId})")
	public void deleteAllAuthenticationExpressAttrValue(Map<String, Object> object);
	
	//TechImplAttrValue
	@Insert(value = "insert into contract_2_attr_spec(contract_2_attr_spec_id, tcp_ctr_f_id, attr_spec_id, value, state, tenant_id) values(#{id},(select tcp_ctr_f_id from contract_format where contract_format_code =  #{contractFormatCode} and tenant_id = #{tenantId}), (select attr_spec_id from attr_spec where attr_spec_code = #{attrValueCode} and tenant_id = #{tenantId}),#{value},#{status}, #{tenantId})")
	public void insertContractFormatAttrValue(Map<String, Object> object);
	@Update(value = "update contract_2_attr_spec set value =#{value} , state=#{status} where  tcp_ctr_f_id =(select tcp_ctr_f_id from contract_format where contract_format_code =  #{contractFormatCode} and tenant_id = #{tenantId}) and attr_spec_id=(select attr_spec_id from attr_spec where attr_spec_code = #{attrValueCode} and tenant_id = #{tenantId})")
	public void updateContractFormatAttrValue(Map<String, Object> object);
	@Delete(value = "delete from contract_2_attr_spec where tcp_ctr_f_id =(select tcp_ctr_f_id from contract_format where contract_format_code =  #{contractFormatCode} and tenant_id = #{tenantId}) and attr_spec_id=(select attr_spec_id from attr_spec where attr_spec_code = #{attrValueCode} and tenant_id = #{tenantId})")
	public void deleteContractFormatAttrValue(Map<String, Object> object);
	@Delete(value = "delete from contract_2_attr_spec where tcp_ctr_f_id =(select tcp_ctr_f_id from contract_format where contract_format_code =  #{contractFormatCode} and tenant_id = #{tenantId})")
	public void deleteAllContractFormatAttrValue(Map<String, Object> object);
	
	//TechImplAttrValue
	@Insert(value = "insert into endpoint_attr_value(endpoint_attr_value_id, endpoint_id, endpoint_spec_attr_id, attr_value, tenant_id) values(#{id}, (select endpoint_id from endpoint where endpoint_code = #{endpointCode} and tenant_id = #{tenantId}), (select endpoint_spec_attr_id from endpoint_spec_attr where attr_spec_id = (select attr_spec_id from attr_spec where attr_spec_code = #{attrValueCode} and tenant_id = #{tenantId}) and endpoint_spec_id = (select endpoint_spec_id from endpoint_spec where endpoint_spec_code = #{endpointSpecCode})),#{value}, #{tenantId})")
	public void insertEndpointAttrValue(Map<String, Object> object);
	@Update(value = "update endpoint_attr_value set attr_value=#{value} where  endpoint_id=(select endpoint_id from endpoint where endpoint_code = #{endpointCode} and tenant_id = #{tenantId}) and endpoint_spec_attr_id=(select endpoint_spec_attr_id from endpoint_spec_attr where attr_spec_id = (select attr_spec_id from attr_spec where attr_spec_code = #{attrValueCode} and tenant_id = #{tenantId}) and endpoint_spec_id = (select endpoint_spec_id from endpoint_spec where endpoint_spec_code = #{endpointSpecCode}))")
	public void updateEndpointAttrValue(Map<String, Object> object);
	@Delete(value = "delete from endpoint_attr_value where endpoint_id = (select endpoint_id from endpoint where endpoint_code = #{endpointCode}) and endpoint_spec_attr_id=(select endpoint_spec_attr_id from endpoint_spec_attr where attr_spec_id = (select attr_spec_id from attr_spec where attr_spec_code = #{attrValueCode} and tenant_id = #{tenantId}) and endpoint_spec_id = (select endpoint_spec_id from endpoint_spec where endpoint_spec_code = #{endpointSpecCode}))")
	public void deleteEndpointAttrValue(Map<String, Object> object);
	@Delete(value = "delete from endpoint_attr_value where endpoint_id = (select endpoint_id from endpoint where endpoint_code = #{endpointCode} and tenant_id = #{tenantId})")
	public void deleteAllEndpointAttrValue(Map<String, Object> object);
	
	
	
	//**********************中间表区域*************************//
	@Insert(value = "insert into service_bean_2_service(service_bean_2_service_id, service_id, service_bean_id, state, tenant_id) values(#{id}, (select service_id from service where service_code = #{serviceCode} and tenant_id = #{tenantId}), (select service_bean_id from service_bean where service_bean_code = #{serviceBeanCode} and tenant_id = #{tenantId}), #{state}, #{tenantId})")
	public void insertServiceBean2Service(Map<String, Object> serviceBean2Service);
	@Delete(value = "delete from service_bean_2_service where service_id in (select service_id from service where service_code = #{serviceCode} and tenant_id = #{tenantId})")
	public void deleteServiceBean2Service(Map<String, Object> serviceBean2Service);
	
	@Insert(value = "insert into trans_script_2_param(contract_adapter_id, var_maping_id, state, tenant_id) values((select contract_adapter_id from contract_adapter where contract_adapter_code = #{contractAdapterCode} and tenant_id = #{tenantId}), (select var_maping_id from variable_map where variable_map_code = #{varMapingCode} and tenant_id = #{tenantId}), 'A', #{tenantId})")
	public void insertTransScript2Param(Map<String,Object> map);
	@Delete(value = "delete from trans_script_2_param where var_maping_id = (select var_maping_id from variable_map where variable_map_code = #{varMapingCode} and tenant_id = #{tenantId})")
	public void deleteTransScript2Param(Map<String,Object> map);
	
	@Insert(value = "insert into prooef_effect_mid(id, proofe_id, ser_invoke_ins_id, tenant_id) values(#{prooefEffectMidId}, (select proofe_id from proof_effect where proof_effect_code = #{authenticationExpressCode} and tenant_id = #{tenantId}), (select ser_invoke_ins_id from ser_invoke_ins where ser_invoke_ins_code = #{ApiInvokeObjectCode} and tenant_id = #{tenantId}), #{tenantId})")
	public void insertProoefEffectMid(Map<String, Object> map);
	@Delete(value = "delete from prooef_effect_mid where proofe_id = (select proofe_id from proof_effect where proof_effect_code = #{authenticationExpressCode} and tenant_id = #{tenantId})")
	public void deleteProoefEffectMid(Map<String, Object> map);
	
	@Insert(value = "insert into doc_contract(doc_contr_id, contract_doc_id, contract_version_id, tenant_id) values(#{docContractId},(select contract_doc_id from contract_doc where resource_aliss = #{contractDocCode} and tenant_id = #{tenantId}),(select contract_version_id from contract_version where version = #{contractVersionCode} and tenant_id = #{tenantId}), #{tenantId})")
	public void insertDocContract(Map<String, Object> map);
	@Delete(value = "delete from doc_contract where contract_doc_id=(select contract_doc_id from contract_doc where resource_aliss = #{contractDocCode} and tenant_id = #{tenantId})")
	public void deleteDocContract(Map<String, Object> map);
	
	@Insert(value = "insert into ser_tech_impl(ser_tech_impl_id, service_id, tech_impl_id, create_time, state, lastest_time, tenant_id) values(#{id},(select service_id from service where service_code = #{apiCode} and tenant_id = #{tenantId}),(select tech_impl_id from tech_impl where tech_impl_code = #{technologyImplementCode} and tenant_id = #{tenantId}),#{attributes.createTime,typeHandler=XMLGregorianCalendarToDateHandle},'D',#{attributes.modifyTime,typeHandler=XMLGregorianCalendarToDateHandle}, #{tenantId})")
	public void insertServiceTechnologyImplement(ServiceTechnologyImplement obj);
	@Delete(value = "delete from ser_tech_impl where tech_impl_id=(select tech_impl_id from tech_impl where tech_impl_code = #{technologyImplementCode} and tenant_id = #{tenantId})")	
	public void deleteServiceTechnologyImplement(ServiceTechnologyImplement obj);
	
	@Insert(value = "insert into org_role(org_role_id, role_code, org_id, create_time, tenant_id) values(#{id}, #{roleCode}, #{orgId}, #{createTime}, #{tenantId}")
	public void insertRole(Map<String, Object> params);
	@Delete(value = "delete from org_role where orgId = (select org_id from org where org_code = #{orgCode} and tenant_id = #{tenantId})")
	public void deleteRole(Map<String, Object> params);
	@Select(value = "select count(1) where role_code = #{roleCode} and orgId = (select org_id from org where org_code = #{orgCode} and tenant_id = #{tenantId})")
	@ResultType(Integer.class)
	public Integer checkExistRole(Map<String, Object> params);
	
	@Select("select ser_tech_impl_id from ser_tech_impl where service_id = (select service_id from service where service_code = #{serviceCode} and tenant_id = #{tenantId}) and tech_impl_id = (select tech_impl_id from tech_impl where tech_impl_code = #{techCode} and tenant_id = #{tenantId})")
	@ResultType(Integer.class)
	public Integer querySerTechImplIdByServiceAndTech(@Param("serviceCode") String serviceCode, @Param("techCode") String techCode, @Param("tenantId") Integer tenantId);

	@Select("select contract_adapter_id from contract_adapter where contract_adapter_code = #{transformerCode} and tenant_id = #{tenantId}")
	@ResultType(Integer.class)
	public Integer queryTransformerIdByCode(@Param("transformerCode") String transformerCode, @Param("tenantId") Integer tenantId);
	
	
	public void codeInit();
	public void insertServiceBean(Map<String, Object> params);
}
