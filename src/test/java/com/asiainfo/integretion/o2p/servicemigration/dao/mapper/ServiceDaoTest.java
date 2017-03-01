package com.asiainfo.integretion.o2p.servicemigration.dao.mapper;


import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asiainfo.integretion.o2p.servicemigration.domain.Service;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/o2p-service-migration-base.xml","classpath:spring/o2p-service-migration-db.xml"})
public class ServiceDaoTest {
	@Resource(name="serviceMapper")
	ServiceMapper serviceMapper;
	@Resource(name="contractMapper")
	ContractMapper contractMapper;
	@Resource(name="baseMapper")
	BaseMapper baseMapper;
	@Resource(name="apiInvokeMapper")
	ApiInvokeMapper apiInvokeMapper;
	
	@Test
	public void queryServiceBeanByCodeTest() {
		String code = "test_yyp";
		Service service = serviceMapper.queryServiceBeanByCode(code, 22);
		Assert.assertEquals("test_yyp", service.getName());
	}
	
	@Test
	public void insertServiceBeanTest() {
		String code = "test_yyp";
		Service service = serviceMapper.queryServiceBeanByCode(code, 22);
		service.setServiceCode("test_yyp_copy3");
		serviceMapper.insertServiceBean(service);
	}
	
	
}
