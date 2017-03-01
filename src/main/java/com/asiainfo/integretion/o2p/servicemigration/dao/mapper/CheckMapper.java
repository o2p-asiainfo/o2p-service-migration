package com.asiainfo.integretion.o2p.servicemigration.dao.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.asiainfo.integretion.o2p.servicemigration.domain.Org;

@Repository(value = "checkMapper")
public interface CheckMapper {
	

	@Select("select * from ${tbName} where ${cloName} = #{code} and tenant_id = #{tenantId}")
	@ResultType(Map.class)
	public Object queryDynamicObject(@Param("tbName") String tbName, @Param("cloName") String cloName, @Param("code") String code, @Param("tenantId") Integer tenantId);
		
	@Select("select 1 from tech_impl where tech_impl_code = #{code} and tenant_id=#{tenantId}")
	@ResultType(Integer.class)
	public Integer checkTechnologyImplementExistByCode(@Param("code") String code, @Param("tenantId") Integer tenantId);
	
	@Select("select 1 from service where service_code = #{code} and tenant_id=#{tenantId}")
	@ResultType(Integer.class)
	public Integer checkApiExistByCode(@Param("code") String code, @Param("tenantId") Integer tenantId);
	
	@Select("select 1 from contract_adapter where contract_adapter_code = #{code} and tenant_id=#{tenantId}")
	@ResultType(Integer.class)
	public Integer checkTransformerExistByCode(@Param("code") String code, @Param("tenantId") Integer tenantId);
	
	@Select("select 1 from message_flow where message_flow_code = #{code} and tenant_id=#{tenantId}")
	@ResultType(Integer.class)
	public Integer checkMessageFlowExistByCode(@Param("code") String code, @Param("tenantId") Integer tenantId);
	
	@Select("select 1 from org where org_code = #{code} and tenant_id=#{tenantId}")
	@ResultType(Integer.class)
	public Integer checkOrgExistByCode(@Param("code") String code, @Param("tenantId") Integer tenantId);
	
	@Select("select 1 from node_desc where node_desc_code = #{code} and tenant_id=#{tenantId}")
	@ResultType(Integer.class)
	public Integer checkNodeDescExistByCode(@Param("code") String code, @Param("tenantId") Integer tenantId);
	
	@Select("select 1 from contract_format where contract_format_code = #{code} and tenant_id=#{tenantId}")
	@ResultType(Integer.class)
	public Integer checkContractFormatExistByCode(@Param("code") String code, @Param("tenantId") Integer tenantId);
	
	@Select("select 1 from endpoint where endpoint_code = #{code} and tenant_id=#{tenantId}")
	@ResultType(Integer.class)
	public Integer checkEndpointExistByCode(@Param("code") String code, @Param("tenantId") Integer tenantId);

	@Select("select 1 from trans_script_2_param where contract_adapter_id =  (select contract_adapter_id from contract_adapter where contract_adapter_code = #{contractAdapterCode} and tenant_id=#{tenantId}) and var_maping_id = (select var_maping_id from variable_map where variable_map_code = #{varMapingCode} and tenant_id=#{tenantId})")
	@ResultType(Integer.class)
	public Integer checkTransScript2ParamExist(Map<String, Object> transScript2Param);

	@Select("select 1 from prooef_effect_mid where proofe_id = (select proofe_id from proof_effect where proof_effect_code = #{authenticationExpressCode} and tenant_id=#{tenantId}) and ser_invoke_ins_id = (select ser_invoke_ins_id from ser_invoke_ins where ser_invoke_ins_code = #{ApiInvokeObjectCode} and tenant_id=#{tenantId})")
	@ResultType(Integer.class)
	public Integer checkProoefEffectMidExist(Map<String, Object> prooefEffectMid);

	@Select("select 1 from doc_contract where contract_doc_id = (select contract_doc_id from contract_doc where resource_aliss = #{contractDocCode} and tenant_id=#{tenantId}) and contract_version_id = (select contract_version_id from contract_version where version = #{contractVersionCode} and tenant_id=#{tenantId})")
	@ResultType(Integer.class)
	public Integer checkDocContractExist(Map<String, Object> docContract);

	@Select("select 1 from ser_tech_impl where service_id = (select service_id from service where service_code = #{apiCode} and tenant_id=#{tenantId}) and tech_impl_id = (select tech_impl_id from tech_impl where tech_impl_code = #{techImplCode} and tenant_id=#{tenantId})")
	@ResultType(Integer.class)
	public Integer checkSerTechExist(Map<String, Object> techMap);
}
