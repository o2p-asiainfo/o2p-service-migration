package com.asiainfo.integretion.o2p.servicemigration.smo.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import com.asiainfo.integretion.o2p.servicemigration.domain.Api;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;
import com.asiainfo.integretion.o2p.servicemigration.smo.IExportService;
import com.asiainfo.integretion.o2p.servicemigration.smo.IMigrationService;

/**
 * The class <code>MigrationServiceImplTest</code> contains tests for the class <code>{@link MigrationServiceImpl}</code>.
 *
 * @generatedBy CodePro at 15-9-8 下午5:23
 * @author windy
 * @version $Revision: 1.0 $
 */
@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/o2p-service-migration-base.xml","classpath:spring/o2p-service-migration-db.xml"})
public class MigrationServiceImplTest {
	@Autowired
	IMigrationService migrationService;
	@Autowired
	IExportService exportService;
	/**
	 * Run the void assembLogAndLog(ServiceObject,ServiceObject,String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 15-9-8 下午5:23
	 */
	@Test
	public void testAssembLogAndLog_1()
		throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("serviceCodes", "test_yyp");
		ServiceObject result = exportService.getServices(params);
		result.attrTransform(true);
		ServiceObject result1 = (ServiceObject) result.clone();
		Api api = (Api) result.queryBeanByCodeAndType(Api.class, "ColumbinApi.TDC-showBroadband");
		api.setMessageFlowOrCode("messageFlow221");
		String ip = "192.168.1.1";
		String user = "test_yyp";

//		migrationService.assembLogAndLog(result, ip, user);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 15-9-8 下午5:23
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
	 * @generatedBy CodePro at 15-9-8 下午5:23
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 15-9-8 下午5:23
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MigrationServiceImplTest.class);
	}
}