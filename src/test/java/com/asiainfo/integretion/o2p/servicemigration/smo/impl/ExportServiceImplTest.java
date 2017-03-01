package com.asiainfo.integretion.o2p.servicemigration.smo.impl;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asiainfo.integretion.o2p.servicemigration.common.util.JaxbUtil;
import com.asiainfo.integretion.o2p.servicemigration.common.util.ReflectionUtil;
import com.asiainfo.integretion.o2p.servicemigration.common.util.XMLValidateUtil;
import com.asiainfo.integretion.o2p.servicemigration.comon.util.XMLValidateUtilTest;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.ContractMapper;
import com.asiainfo.integretion.o2p.servicemigration.domain.BasedBean;
import com.asiainfo.integretion.o2p.servicemigration.domain.CommonUtil;
import com.asiainfo.integretion.o2p.servicemigration.domain.ContractVersion;
import com.asiainfo.integretion.o2p.servicemigration.domain.MessageFlow;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;
import com.asiainfo.integretion.o2p.servicemigration.smo.IExportService;

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
public class ExportServiceImplTest {
	@Autowired
	IExportService exportService;
	@Resource(name="contractMapper")
	public ContractMapper contractMapper;
	public List<String> listName = new ArrayList<String>();
	/**
	 * Run the ServiceObject getServices(Map<String,Object>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-8-31 上午11:12
	 */
	@Test
	public void testGetServices_1()
		throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		ServiceObject result = exportService.getServices(params);

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the ServiceObject getServices(Map<String,Object>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-8-31 上午11:12
	 */
	@Test
	public void testGetServices_2()
		throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serviceCodes", "aa");
		ServiceObject result = exportService.getServices(params);

		// add additional test code here
		Assert.assertNotNull(result);
	}

	/**
	 * Run the ServiceObject getServices(Map<String,Object>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-8-31 上午11:12
	 */
	@Test
	public void testGetServices_3()
		throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serviceCodes", "test_yyp");
		ServiceObject result = exportService.getServices(params);
//		MessageFlow mf = (MessageFlow) result.queryBeanByCodeAndType(MessageFlow.class, "messageFlow451");
		result.attrTransform(true);
		File file = new File("D:/test_yyp.xml");
		JaxbUtil.marshal(result, file);
		String xsdFile = XMLValidateUtilTest.class.getResource("/testSource/O2P-BMO.xsd").getFile();
		boolean valResult = XMLValidateUtil.validateXml(xsdFile, file.getAbsolutePath());
		// add additional test code here
		Assert.assertNotNull(result);
		
		assertEquals(1, result.getServices().size());
		
		assertEquals(true, valResult);
	}
	
	@Test
	public void testGetServices_4()
		throws Exception {
		File file = new File("D:/test_yyp.xml");
		ServiceObject result = JaxbUtil.unmarshal(file);
		String content = (String) ReflectionUtil.getAttrValue("serviceObject[0].services[0].apis[0].contractVersion.contractDocument.fileShareOrCode.content", result);
		BufferedReader br = new BufferedReader(new FileReader(new File("D:/service_in_out.sql")));
		String st = null;
		StringBuffer sb = new StringBuffer("");
		while((st = br.readLine()) != null) {
			sb.append(st + "\r\n");
		}
		System.out.println(content.equals(sb.substring(0, sb.length()-2)));
	}

	/**
	 * Run the ServiceObject queryServicesPage(Map<String,Object>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-8-31 上午11:12
	 */
	public void testQueryServicesPage_1(Class so)
		throws Exception {
		if(listName.contains(so.getName())) return;
		listName.add(so.getName());
		Field[] attrs = so.getDeclaredFields();
		String sql = "@Update(value = \"update xx set xx = #{id},";
		String sqlInsert = "@Insert(value = \"insert into xx() values(#{id},";
		for(Field attrf: attrs) {
			String attr = attrf.getName();
			if(attrf.getAnnotation(XmlTransient.class) != null) {
				continue;
			}
			if(String.class.isAssignableFrom(attrf.getType()) || XMLGregorianCalendar.class.isAssignableFrom(attrf.getType())) {
				sql += " xx = #{"+attr+"},";
				sqlInsert += "#{"+attr+"},";
				continue;
			} else if(List.class.isAssignableFrom(attrf.getType())) {
				Class type = null;
				if(attrf.getGenericType() instanceof ParameterizedType) {
					ParameterizedType t = (ParameterizedType) attrf.getGenericType();
					type = (Class) t.getActualTypeArguments()[0];
				}
				if(BasedBean.class.isAssignableFrom(type)) {
					testQueryServicesPage_1(type);
				} else if(String.class.isAssignableFrom(type)){
					sql += " xx = #{"+attr+"},";
					sqlInsert += "#{"+attr+"},";
				} else {
					XmlElements x = (XmlElements) attrf.getAnnotation(XmlElements.class);
					if(x != null) {
						for(XmlElement xe :x.value()) {
							if(xe.type() != String.class)
								testQueryServicesPage_1(xe.type());
						}
					}
				}
				continue;
			} else if(BasedBean.class.isAssignableFrom(attrf.getType())) {
				if(so != attrf.getType())
					testQueryServicesPage_1(attrf.getType());
				continue;
			} else {
				XmlElements x = (XmlElements) attrf.getAnnotation(XmlElements.class);
				if(x != null) {
					for(XmlElement xe :x.value()) {
						if(xe.type() != String.class)
							testQueryServicesPage_1(xe.type());
					}
					continue;
				}else {
					sql += " xx = #{"+attr+"},";
					sqlInsert += "#{"+attr+"},";
				}
			}
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where code = xx\")";
		sqlInsert = sqlInsert.substring(0, sqlInsert.length()-1);
		sqlInsert += ")\")";
		if(!sql.contains("values(#{id});")) {
			String className = so.getName().substring(so.getName().lastIndexOf(".")+1);
			System.out.println("//"+className);
			System.out.println(sqlInsert);
			System.out.println("public void insert"+className+"("+className+" object);");
			System.out.println(sql);
			System.out.println("public void update"+className+"("+className+" object);");
			System.out.println("@Delete(value = \"delete from xx where code = xx\")");
			System.out.println("public void delete"+className+"("+className+" object);");
			System.out.println();
		}
	}
	
	@Test
	public void test2() throws Exception {
		new ExportServiceImplTest().testQueryServicesPage_1(ServiceObject.class);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 15-8-31 上午11:12
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 15-8-31 上午11:12
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}
}