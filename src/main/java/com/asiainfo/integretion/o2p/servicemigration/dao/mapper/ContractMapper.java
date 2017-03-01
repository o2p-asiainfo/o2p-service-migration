package com.asiainfo.integretion.o2p.servicemigration.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.asiainfo.integretion.o2p.servicemigration.common.typeHandlers.XMLGregorianCalendarToDateHandle;
import com.asiainfo.integretion.o2p.servicemigration.domain.AttrValue;
import com.asiainfo.integretion.o2p.servicemigration.domain.Contract;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractAdapterEndpoint;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractDocument;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractFormat;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractVersion;
import com.asiainfo.integretion.o2p.servicemigration.domain.FileShare;
import com.asiainfo.integretion.o2p.servicemigration.domain.NodeDesc;
import com.asiainfo.integretion.o2p.servicemigration.domain.NodeDescMaper;
import com.asiainfo.integretion.o2p.servicemigration.domain.NodeValueAdapterReq;
import com.asiainfo.integretion.o2p.servicemigration.domain.VariableMap;

@Component(value="contractMapper")
public interface ContractMapper {
	
	@ResultMap("baseMapper.contractVersion")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select cv.* from contract_version cv where cv.contract_version_id in ("
			+ "select s.contract_version_id from service s "
			+ "	left join service_bean_2_service sn on sn.service_id = s.service_id "
			+ "	left join service_bean sbn on sbn.service_bean_id = sn.service_bean_id "
			+ "	where sbn.service_bean_code = #{serviceCode} and cv.tenant_id=#{tenantId}"
			+ ")")
	public List<ContractVersion> queryContractVersionByServiceCode(@Param("serviceCode") String serviceCode, @Param("tenantId") Integer tenantId);
	
	@ResultMap("baseMapper.contractVersion")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select cv.* from contract_version cv left join contract_format cf on cf.contract_version_id = cv.contract_version_id where cf.contract_format_code = #{code} and cv.tenant_id=#{tenantId}")
	public ContractVersion queryContractVersionByFormatCodeWithOne(@Param("code") String code, @Param("tenantId") Integer tenantId);
	
	@ResultMap("baseMapper.contractVersion")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from contract_version where version = #{contractVersionCode} and tenant_id=#{tenantId}")
	public ContractVersion queryContractVersionByCodeWithOne(@Param("contractVersionCode") String contractVersionCode, @Param("tenantId") Integer tenantId);
	
	@ResultMap("baseMapper.contract")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from contract where contract_id = #{contractId} and tenant_id = #{tenantId}")
	public Contract queryContractByContractId(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.contract")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from contract where code = #{contractCode} and tenant_id=#{tenantId}")
	public Contract queryContractByContractByCodeWithOne(@Param("contractCode") String contractCode, @Param("tenantId") Integer tenantId);
	
	@ResultType(Integer.class)
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select contract_id from contract where code = #{code} and tenant_id=#{tenantId}")
	public Integer queryContractIdByContractByCodeWithOne(String code, Integer tenantId);
	
	@ResultMap("baseMapper.contractVersion")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from contract_version cv where cv.contract_id = (select base_contract_id from contract where contract_id = #{contractId}) and tenant_id = #{tenantId}")
	public ContractVersion queryBaseContractVersionWithOne(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.contractFormat")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from contract_format where contract_version_id = #{contractVersionId} and req_rsp = 'REQ' and tenant_id = #{tenantId}")
	public ContractFormat queryReqContractFormatByContractVersionIdWithOne(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.contractFormat")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from contract_format where contract_version_id = #{contractVersionId} and req_rsp = 'RSP' and tenant_id = #{tenantId}")
	public ContractFormat queryRspContractFormatByContractVersionIdWithOne(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.contractFormat")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from contract_format where tcp_ctr_f_id = #{contractFormatId} and tenant_id = #{tenantId}")
	public ContractFormat queryContractFormatById(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.nodeDesc")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from node_desc nd where tcp_ctr_f_id = #{contractFormatId} and parent_node_id is null and tenant_id = #{tenantId}")
	public List<NodeDesc> queryNodeDescByContractFormatId(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.nodeDesc")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from node_desc where parent_node_id = #{parentNodeId} and tenant_id = #{tenantId}")
	public List<NodeDesc> queryChildNodeDesces(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.attrValue")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("SELECT ac.attr_spec_code as attr_value_code,cs.value as attr_value,cs.state state,ac.attr_spec_code attr_value_name FROM contract_2_attr_spec cs"
			+" LEFT JOIN attr_spec ac ON cs.attr_spec_id = ac.attr_spec_id WHERE tcp_ctr_f_id = #{contractFormatId} and cs.tenant_id = #{tenantId}")
	public List<AttrValue> queryAttrValueByContractFormatId(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.contractDocument")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from contract_doc where contract_doc_id in (select contract_doc_id from doc_contract where contract_version_id = #{contractVersionId}) and tenant_id = #{tenantId}")
	public ContractDocument queryContractDocumentByContractVersionIdWithOne(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.contractDocument")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from contract_doc where resource_aliss = #{code} and tenant_id=#{tenantId}")
	public ContractDocument queryContractDocumentByCodeWithOne(@Param("code") String code, @Param("tenantId") Integer tenantId);
	
	@ResultMap("baseMapper.fileShare")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from file_share where s_file_id = #{fileShareId} and tenant_id = #{tenantId}")
	public FileShare queryFileShareById(@Param("params") Map<String, String> params);
	
	@ResultMap("baseMapper.contractDocument")
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from contract_doc where contract_doc_id = #{baseContractDocId} and tenant_id = #{tenantId}")
	public List<ContractDocument> queryBaseDocumentById(@Param("params") Map<String, String> params);
	
	@Results(value = {
			@Result(column = "node_desc_maper_code", property = "nodeDescMaperCode"),
			@Result(column = "src_node_desc_code", property = "sourceNodeDescCode"),
			@Result(column = "tar_node_desc_code", property = "targetNodeDescCode"),
			@Result(column = "action_type_cd", property = "actionType"),
			@Result(column = "create_dt", property = "createDate", typeHandler=XMLGregorianCalendarToDateHandle.class)
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select nm.*, nd1.node_desc_code src_node_desc_code, nd2.node_desc_code tar_node_desc_code from node_desc_maper  nm"
			+ " left join node_desc nd1 on nm.src_node_desc_id = nd1.node_desc_id"
			+ " left join node_desc nd2 on nm.tar_node_desc_id = nd2.node_desc_id"
			+ " where nm.contract_adapter_id = #{contractAdapterId} and nm.tenant_id = #{tenantId}")
	public List<NodeDescMaper> queryNodeDescMappersByTransfromId(@Param("params") Map<String, String> params);
	
	@Results(value = {
			@Result(column = "node_val_adapter_req_code", property = "nodeValueAdapterReqCode"),
			@Result(column = "tar_node_desc_code", property = "tarNodeDescCode"),
			@Result(column = "node_value_source_cd", property = "nodeValueSrouceType"),
			@Result(column = "value_express", property = "valueExpression"),
			@Result(column = "script", property = "script"),
			@Result(column = "trigger_express", property = "triggerExpression"),
			@Result(column = "state", property = "status"),
			@Result(column = "version", property = "createDate", typeHandler=XMLGregorianCalendarToDateHandle.class)
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select nr.*, nd.node_desc_code tar_node_desc_code from node_val_adapter_req nr left join node_desc nd on nd.node_desc_id = nr.node_desc_id where nr.contract_adapter_id = #{contractAdapterId} and nr.tenant_id = #{tenantId}")
	public List<NodeValueAdapterReq> queryNodeValuesByTransfromId(@Param("params") Map<String, String> params);
	
	@Results(value = {
			@Result(column = "variable_map_code", property = "variableMapCode"),
			@Result(column = "cons_map_cd", property = "constantMappingTypeCode"),
			@Result(column = "data_source", property = "dataSource"),
			@Result(column = "key_express", property = "keyExpression"),
			@Result(column = "val_express", property = "valueExpression"),
			@Result(column = "version", property = "version", typeHandler=XMLGregorianCalendarToDateHandle.class),
			@Result(column = "state", property = "status")
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from variable_map vm where vm.var_maping_id in (select var_maping_id from trans_script_2_param tp where tp.contract_adapter_id = #{contractAdapterId}) and vm.tenant_id = #{tenantId}")
	public List<VariableMap> queryVariableMapsByContractAdapterId(@Param("params") Map<String, String> params);
	
	@Results(value = {
			@Result(column = "variable_map_code", property = "variableMapCode"),
			@Result(column = "cons_map_cd", property = "constantMappingTypeCode"),
			@Result(column = "data_source", property = "dataSource"),
			@Result(column = "key_express", property = "keyExpression"),
			@Result(column = "val_express", property = "valueExpression"),
			@Result(column = "version", property = "version", typeHandler=XMLGregorianCalendarToDateHandle.class),
			@Result(column = "state", property = "status")
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select * from variable_map vm where variable_map_code = #{code} and tenant_id=#{tenantId}")
	public VariableMap queryVariableMapByCodeWithOne(@Param("code") String code, @Param("tenantId") Integer tenantId);
	
	@Results(value = {
			@Result(column = "contract_adapter_endpoint_code", property = "contractAdapterEndpointCode"),
			@Result(column = "endpoint_code", property = "endpointCode"),
			@Result(column = "contract_format_code", property = "contractFormatCode"),
			@Result(column = "action_type", property = "actionType"),
			@Result(column = "create_date", property = "createDate", typeHandler=XMLGregorianCalendarToDateHandle.class)
	})
	@Options(flushCache = true, useCache = true, timeout = 10000)
	@Select("select ce.*, et.endpoint_code, cf.contract_format_code from contract_adapter_endpoint ce"
			+ " left join endpoint et on et.endpoint_id = ce.endpoint_id"
			+ "	left join contract_format cf on cf.tcp_ctr_f_id = ce.contract_formate_id"
			+ " where contract_adapter_id = #{contractAdapterId} and ce.tenant_id = #{tenantId}")
	public List<ContractAdapterEndpoint> queryContractAdapterEndpointByContractAdapterId(@Param("params") Map<String, String> params);

}
