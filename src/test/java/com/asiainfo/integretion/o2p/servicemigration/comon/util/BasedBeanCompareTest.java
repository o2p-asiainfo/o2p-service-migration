package com.asiainfo.integretion.o2p.servicemigration.comon.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.jms.IllegalStateException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asiainfo.integretion.o2p.servicemigration.common.Constant;
import com.asiainfo.integretion.o2p.servicemigration.common.util.ReflectionUtil;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.CheckMapper;
import com.asiainfo.integretion.o2p.servicemigration.domain.Api;
import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBean;
import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBeanHelper;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractFormat;
import com.asiainfo.integretion.o2p.servicemigration.domain.Endpoint;
import com.asiainfo.integretion.o2p.servicemigration.domain.EndpointSpec;
import com.asiainfo.integretion.o2p.servicemigration.domain.NodeDesc;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;
import com.asiainfo.integretion.o2p.servicemigration.domain.Transformer;
import com.asiainfo.integretion.o2p.servicemigration.smo.IExportService;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/o2p-service-migration-base.xml","classpath:spring/o2p-service-migration-db.xml"})
public class BasedBeanCompareTest {
	@Autowired
	IExportService exportService;
	@Resource(name="checkMapper")
	CheckMapper checkMapper;
	
	@Test
	public void compareTest1() throws IllegalStateException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serviceCodes", "test_yyp");
		ServiceObject result = exportService.getServices(params);
		result.attrTransform(true);
		ServiceObject result1 = (ServiceObject) result.clone();
		ReflectionUtil.setAttrValue("services[0].status", "S", result1);
		Transformer tr = (Transformer) result1.queryBeanByCodeAndType(Transformer.class, "transformer2604");
		tr.setStatus("S");
		EndpointSpec str = (EndpointSpec) result.queryBeanByCodeAndType(EndpointSpec.class, "TRANSFORMER-v2");
		str.setEndpointSpecDetail("transformer2605");
		try {
			BasedBeanHelper helper = new BasedBeanHelper(checkMapper);
			result.compare(result1, helper);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw e;
		}
		Assert.assertSame(Constant.ACTION_M, ReflectionUtil.getAttrValue("services[0].action", result));
		Assert.assertSame(Constant.ACTION_M, str.getAction());
	}
	
	@Test
	public void compareTest3() throws IllegalStateException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serviceCodes", "test_yyp");
		ServiceObject result = exportService.getServices(params);
		result.attrTransform(true);
		ServiceObject result1 = (ServiceObject) result.clone();
		ReflectionUtil.setAttrValue("services[0].status", "S", result1);
		EndpointSpec str = (EndpointSpec) result.queryBeanByCodeAndType(EndpointSpec.class, "TRANSFORMER-v2");
		str.setEndpointSpecDetail(null);
		try {
			BasedBeanHelper helper = new BasedBeanHelper(checkMapper);
			result.compare(result1, helper);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw e;
		}
		Assert.assertSame(Constant.ACTION_R, ((BasedBean)(str.getEndpointSpecDetail())).getAction());
		((BasedBean)(str.getEndpointSpecDetail())).setAction(null);
		EndpointSpec str1 = (EndpointSpec) result1.queryBeanByCodeAndType(EndpointSpec.class, "TRANSFORMER-v2");
		str1.setEndpointSpecDetail(null);
		try {
			BasedBeanHelper helper = new BasedBeanHelper(checkMapper);
			result.compare(result1, helper);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw e;
		}
		Assert.assertSame(Constant.ACTION_A, ((BasedBean)(str.getEndpointSpecDetail())).getAction());
	}
	
	@Test
	public void equalsTest() {
		NodeDesc nd = new NodeDesc();
		nd.setDescription("aa");
		NodeDesc nd1 = new NodeDesc();
		nd1.setDescription("bb");
		Assert.assertSame(false, nd.equals(nd1));
		
		nd1.setDescription("aa");
		Assert.assertSame(true, nd.equals(nd1));
		
		nd.setId(333);
		nd1.setId(444);
		Assert.assertSame(true, nd.equals(nd1));
		
		nd.setCountCons("aaa");
		nd1.setCountCons(null);
		Assert.assertSame(false, nd.equals(nd1));
		
		ContractFormat cf = new ContractFormat();
		Assert.assertSame(false, nd.equals(cf));
	}
	
	@Test
	public void compareTest2() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serviceCodes", "test_yyp");
		ServiceObject result = exportService.getServices(params);
		result.attrTransform(true);
		params.put("serviceCodes", "PBSService.paymentRequestInfo");
		ServiceObject result1 = (ServiceObject) exportService.getServices(params);
		try {
			BasedBeanHelper helper = new BasedBeanHelper(checkMapper);
			result.compare(result1, helper);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		Assert.assertSame("ADD", ReflectionUtil.getAttrValue("services[0].action", result));
	}
	
	@Test
	public void compareTest4() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serviceCodes", "test_yyp");
		ServiceObject result = exportService.getServices(params);
		result.attrTransform(true);
		ServiceObject result1 = (ServiceObject) result.clone();
		Api api = (Api) result.queryBeanByCodeAndType(Api.class, "ColumbinApi.TDC-showBroadband");
		api.setMessageFlowOrCode("messageFlow221");
		try {
			BasedBeanHelper helper = new BasedBeanHelper(checkMapper);
			result.compare(result1, helper);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		Assert.assertSame(Constant.ACTION_M, api.getAction());
	}
}
