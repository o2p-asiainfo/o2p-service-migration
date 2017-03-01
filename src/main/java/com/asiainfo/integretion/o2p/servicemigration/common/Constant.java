package com.asiainfo.integretion.o2p.servicemigration.common;

import java.util.HashMap;
import java.util.Map;

public final class Constant {
	
	public static final String XML_SCHEMA_INVALID = "The xml schema is invalid!";
	public static final String SUCCESS = "success";
	
	public static final String STATUS = "STATUS";
	public static final String OBJECT_STATUS_EXIST = "E";
	public static final String OBJECT_STATUS_SAME = "S";
	public static final String OBJECT_STATUS_NON_EXIST = "N";
	public static final String OBJECT_NEW = "OBJECT_NEW";
	public static final String OBJECT_OLD = "OBJECT_OLD";

	public static final String OBJECT_NAME = "OBJECT_NAME"; 
	public static final String OBJECT_CODE = "OBJECT_CODE"; 
	
	public static final String ACTION_ADD = "A"; 
	public static final String ACTION_UPDATE = "U"; 
	public static final String ACTION_DELETE = "D";
	public static final String ACTION_IGNORE = "I";
	
	public static final String OBJECT_LIST = "OBJECT_LIST";
	
	public static final String ACTION_R = "REMOVE";
	public static final String ACTION_N = "NONE";
	public static final String ACTION_A = "ADD";
	public static final String ACTION_M = "MODIFY";
	
	public static final String TRANSFORMERv2 = "TRANSFORMER-v2";
	public static final String CALL = "CALL";
	public static final String JMSRECEIVE = "JMSRECEIVE";
	public static final String JMSCALL = "jmscall";
	public static final String SOCKETACCESS = "SOCKETACCESS";
	
	public static final String ATTR_SPEC_SERVICETECHID = "servicetechid";
	public static final String ATTR_SPEC_TRANSFORMERID = "transformer_rule_id";
	
	public static Map<String, String> tbMap = new HashMap<String, String>();
	static {
		tbMap.put("Contract", "contract");
		tbMap.put("NodeDesc", "node_desc");
		tbMap.put("ContractFormat", "contract_format");
		tbMap.put("ContractFormatChoice", "contract_format");
		tbMap.put("FileShare", "file_share");
		tbMap.put("ContractDocument", "contract_doc");
		tbMap.put("ContractVersion", "contract_version");
		tbMap.put("TechnologyImplement", "tech_impl");
		tbMap.put("AuthenticationExpress", "proof_effect");
		tbMap.put("NodeDescMaper", "node_desc_maper");
		tbMap.put("NodeValueAdapterReq", "node_val_adapter_req");
		tbMap.put("VariableMap", "variable_map");
		tbMap.put("ContractAdapterEndpoint", "contract_adapter_endpoint");
		tbMap.put("Transformer", "contract_adapter");
		tbMap.put("Endpoint", "endpoint");
		tbMap.put("GetValueExpr", "get_value_expr");
		tbMap.put("RouteCondition", "route_condition");
		tbMap.put("RoutePolicy", "route_policy");
		tbMap.put("ServiceRouteConfig", "service_route_config");
		tbMap.put("MessageFlow", "message_flow");
		tbMap.put("ApiInvokeObjectFlowControl", "ctl_counterms_2_comp");
		tbMap.put("Api", "service");
		tbMap.put("ApiInvokeObject", "ser_invoke_ins");
		tbMap.put("Service", "service_bean");
		tbMap.put("Org", "org");
		tbMap.put("App", "app");
		tbMap.put("Component", "component");
		tbMap.put("ServiceBean2Service", "service_bean_2_service");
		tbMap.put("Role", "org_role");
		tbMap.put("ProoefEffectMid", "prooef_effect_mid");
		tbMap.put("DocContract", "doc_contract");
		tbMap.put("TechImplAttrValue", "tech_imp_att");
		tbMap.put("AuthenticationExpressAttrValue", "proof_values");
		tbMap.put("ContractFormatAttrValue", "contract_2_attr_spec");
		tbMap.put("EndpointAttrValue", "endpoint_attr_val");
		tbMap.put("SerTechImpl", "ser_tech_impl");
		tbMap.put("ServiceTechnologyImplement", "ser_tech_impl");
		tbMap.put("ApiMethod", "api");
		tbMap.put("DirSerList", "dir_ser_list");
		tbMap.put("FileShare", "file_share");
		tbMap.put("TechnologyImplementNode", "tech_impl_node");
	}
	
}
