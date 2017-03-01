package com.asiainfo.integretion.o2p.servicemigration.dao.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asiainfo.integretion.o2p.servicemigration.domain.CommonUtil;
import com.asiainfo.integretion.o2p.servicemigration.domain.Contract;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractFormat;
import com.asiainfo.integretion.o2p.servicemigration.domain.Endpoint;
import com.asiainfo.integretion.o2p.servicemigration.domain.EndpointSpec;
import com.asiainfo.integretion.o2p.servicemigration.domain.NodeDesc;
import com.asiainfo.integretion.o2p.servicemigration.domain.TechnologyImplementFlowControl;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/o2p-service-migration-base.xml","classpath:spring/o2p-service-migration-db.xml"})
public class DaoMapperTest {
	@Resource(name="daoMapper")
	public DaoMapper daoMapper;
	
	@Test
	public void insertContractTest() {
		Contract contract = new Contract();
		contract.setName("aaa");
		contract.setStatus("A");
		contract.setContractCode("555");
		contract.setDescription(null);
		Contract baseContract = new Contract();
		baseContract.setId(800001518);
		daoMapper.insertContract(contract);
	}
	
	@Test
	public void insertTechController() {
		TechnologyImplementFlowControl tfc = new TechnologyImplementFlowControl();
		tfc.setFlowControlCode("aaaa");
		tfc.setCycleValue("333");
		daoMapper.insertTechnologyImplementFlowControl(tfc);
	}
	
	@Test
	public void insertContractFormat() {
		ContractFormat cf = new ContractFormat();
		Map<String, Object> attribute = new HashMap<String, Object>();
		attribute.put("reqRsp", "req");
		attribute.put("contractVersionId", "111");
		cf.setAttributes(attribute);
		cf.setStatus("A");
		cf.setContractFormatCode("aaaaaaaaa");
		cf.setDescription("aaaaa");
		daoMapper.insertContractFormat(cf);
	}
	
	@Test
	public void insertRolesTest() {
		List<String> roles = new ArrayList<String>();
		roles.add("1");
		roles.add("2");
		roles.add("3");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roles", roles);
		params.put("orgId", 77777);
	}
	
	@Test
	public void insertEndpoint() {
		Endpoint e = new Endpoint();
		e.setEndpointCode("aaa");
		e.setEndpointDesc("aaa");
		e.setEndpointName("aaa");
		EndpointSpec es = new EndpointSpec();
		es.setEndpointSpecCode("PROF");
		e.setEndpointSpec(es);
		e.setInDataTypeCode("String");
		e.setOutDataTypeCode("String");
		e.setStatus("A");
		daoMapper.insertEndpoint(e);
	}
	
	@Test
	public void insertNodeDesc() {
		NodeDesc nd = new NodeDesc();
		nd.setNodeDescCode("bb");
		nd.setIsCheck("Y");
		nd.setIsSignature("N");
		daoMapper.insertNodeDesc(nd);
	}
}
