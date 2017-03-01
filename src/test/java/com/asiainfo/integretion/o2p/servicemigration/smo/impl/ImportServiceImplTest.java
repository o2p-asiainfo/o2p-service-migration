package com.asiainfo.integretion.o2p.servicemigration.smo.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.jms.IllegalStateException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asiainfo.integretion.o2p.servicemigration.common.Constant;
import com.asiainfo.integretion.o2p.servicemigration.common.util.JaxbUtil;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.BaseMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.CheckMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ContractMapper;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ServiceMapper;
import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBeanHelper;
import com.asiainfo.integretion.o2p.servicemigration.domain.Component;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractVersion;
import com.asiainfo.integretion.o2p.servicemigration.domain.Service;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;
import com.asiainfo.integretion.o2p.servicemigration.smo.IExportService;
import com.asiainfo.integretion.o2p.servicemigration.smo.IImportService;

/**
 * The class <code>ExportServiceImplTest</code> contains tests for the class <code>{@link ExportServiceImpl}</code>.
 *
 * @generatedBy CodePro at 15-8-31 上午11:12
 * @author windy
 * @version $Revision: 1.0 $
 */
@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/o2p-service-migration-base.xml","classpath:spring/o2p-service-migration-db.xml"})
public class ImportServiceImplTest {
	@Autowired
	IImportService importService;
	@Autowired
	IExportService exportService;
	@Resource(name="checkMapper")
	CheckMapper checkMapper;
	@Resource(name="serviceMapper")
	ServiceMapper serviceMapper;
	@Resource(name="baseMapper")
	BaseMapper baseMapper;
	@Resource(name="contractMapper")
	ContractMapper contractMapper;
	
	@Test
	public void importTest() throws IllegalStateException {
		String xmlFile = "D:\\in\\ServiceObject.xml";
		ServiceObject so = JaxbUtil.unmarshal(xmlFile);
		so.attrTransform(false);
		ServiceObject so1 = new ServiceObject();
		Set set = new HashSet<Component>();
		Set<ContractVersion> cvSet = new HashSet<ContractVersion>();
		Set<String> contractFormatCodeList = new HashSet<String>();
		for(Service s: so.getServices()) {
			Service s1 = serviceMapper.queryServiceBeanByCode(s.codeValue(), 22);
			if(s1 == null) {
				continue;
			}
			set.addAll(baseMapper.queryComponentByServiceBeanCode(s.codeValue(), 22));
			so1.getServices().add(s1);
			//获取所有service下面的contractVersion
			List<ContractVersion> cvList = contractMapper.queryContractVersionByServiceCode(s1.codeValue(), 22);
			cvSet.addAll(cvList);
			BasedBeanHelper.findAllContractFormatCode(contractFormatCodeList, s1);
		}
		ContractVersion cv = null;
		for(String code: contractFormatCodeList) {
			cv = contractMapper.queryContractVersionByFormatCodeWithOne(code, 22);
			cvSet.add(cv);
		}
		so1.getComponents().addAll(set);
		so1.getContractVersions().addAll(cvSet);
		so.validateCode(new BasedBeanHelper(checkMapper));
		so.compare(so1, new BasedBeanHelper(checkMapper));
//		System.out.println(so);
		importService.importService(so);
	}
	
	@Test
	public void importTest3() throws IllegalStateException {
		String xmlFile = "D:\\in\\ServiceObject.xml";
		ServiceObject so = JaxbUtil.unmarshal(xmlFile);
		so.attrTransform(false);
		so.changeAllAction(Constant.ACTION_R, null);
		importService.importService(so);
	}
	
	@Test
	public void importTest1() throws IllegalStateException {
		String xmlFile = "D:\\test_yyp.xml";
		ServiceObject so = JaxbUtil.unmarshal(xmlFile);
//		so.attrTransform(false);
//		so.compare(new ServiceObject(), new BasedBeanCompareHelper(checkMapper));
//		importService.importService(so);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serviceCodes", "test_yyp");
		ServiceObject so1 = exportService.getServices(params);
		so.compare(so1, new BasedBeanHelper(checkMapper));
		
		System.out.println(so);
	}
}