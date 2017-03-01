package com.asiainfo.integretion.o2p.servicemigration.domain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.BaseMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.DaoMapper;

@Component("basedBeanIdHelper")
public class BasedBeanIdHelper {
	@Resource(name="baseMapper")
	private BaseMapper baseMapper;
	@Value("${databaseType}")
	private String dbType;
	@Resource(name="daoMapper")
	private DaoMapper daoMapper;
	private static Map<String, String> dbMap = new HashMap<String, String>();
	private static List<String> maxList = new ArrayList<String>();
	static {
		dbMap.put("Contract", "contract");
		dbMap.put("NodeDesc", "node_desc");
		dbMap.put("ContractFormat", "contract_format");
		dbMap.put("FileShare", "file_share");
		dbMap.put("ContractDocument", "contract_doc");
		dbMap.put("ContractVersion", "contract_version");
		dbMap.put("TechnologyImplement", "tech_impl");
		dbMap.put("AuthenticationExpress", "proof_effect");
		dbMap.put("NodeDescMaper", "node_desc_maper");
		dbMap.put("NodeValueAdapterReq", "node_val_adapter_req");
		dbMap.put("VariableMap", "variable_map");
		dbMap.put("ContractAdapterEndpoint", "contract_adapter_endpoint");
		dbMap.put("Transformer", "contract_adapter");
		dbMap.put("Endpoint", "endpoint");
		dbMap.put("GetValueExpr", "get_value_expr");
		dbMap.put("RouteCondition", "route_condit");
		dbMap.put("RoutePolicy", "route_poli");
		dbMap.put("ServiceRouteConfig", "service_route_conf");
		dbMap.put("MessageFlow", "message_flow");
		dbMap.put("ApiInvokeObjectFlowControl", "ctl_counterms_2_c");
		dbMap.put("Api", "service");
		dbMap.put("ApiInvokeObject", "ser_invoke_ins");
		dbMap.put("Service", "service_bean");
		dbMap.put("Org", "org");
		dbMap.put("App", "org");
		dbMap.put("Component", "component");
		dbMap.put("ServiceBean2Service", "service_bean_2_service");
		dbMap.put("Role", "org_role");
		dbMap.put("ProoefEffectMid", "prooef_effect_mid");
		dbMap.put("DocContract", "doc_contract");
		dbMap.put("TechImplAttrValue", "tech_imp_att");
		dbMap.put("AuthenticationExpressAttrValue", "proof_values");
		dbMap.put("ContractFormatAttrValue", "contract_2_attr_spec");
		dbMap.put("EndpointAttrValue", "endpoint_attr_val");
		dbMap.put("SerTechImpl", "ser_tech_impl");
		dbMap.put("ServiceTechnologyImplement", "ser_tech_impl");
		dbMap.put("ApiMethod", "api");
		dbMap.put("DirSerList", "dir_ser_list");
		dbMap.put("FileShare", "file_share");
		dbMap.put("TechnologyImplementNode", "tech_impl_node");
		maxList.add("prooef_effect_mid");
	}
	
	public Integer getId(Object inst) {
		String seq = null;
		if(inst instanceof String) {
			seq = dbMap.get(inst);
		} else if(inst instanceof BasedBean){
			String name = inst.getClass().getName();
			seq = dbMap.get(name.substring(name.lastIndexOf(".")+1));
			if(seq == null) {
				return 0;
			}
		}
		if(seq != null) {
			Integer id = getIdSequence("seq_" + seq);
			if(id == null && maxList.contains(seq)) {
				id = getMaxId(seq);
			}
			return id;
		} else {
			return 0;
		}
	}
	
	
	public Integer getMaxId(String seq) {
		return baseMapper.getMaxId(seq);
	}


	public Integer getIdSequence(String seqName) {
		if(dbType.equals("oracle")){
			return baseMapper.getOracleSeqByTbName(seqName);
		}else if(dbType.equals("mysql")){
			return baseMapper.getMysqlSeqByTbName(seqName);
		}
		return 0;
	}
	
	public String getDbType() {
		return dbType;
	}


	public void setDbType(String dbType) {
		this.dbType = dbType;
	}


	public BaseMapper getBaseMapper() {
		return baseMapper;
	}


	public void setBaseMapper(BaseMapper baseMapper) {
		this.baseMapper = baseMapper;
	}
}
