package com.asiainfo.integretion.o2p.servicemigration.comon.util;
import org.junit.Assert;
import org.junit.Test;

import com.asiainfo.integretion.o2p.servicemigration.common.util.JaxbUtil;
import com.asiainfo.integretion.o2p.servicemigration.common.util.XMLValidateUtil;
import com.asiainfo.integretion.o2p.servicemigration.domain.ObjectFactory;
import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;

public class JaxbUtilTest {
	@Test
	public void marshalTest() throws Exception{
		ObjectFactory obj = new ObjectFactory();
		ServiceObject so = new ServiceObject();
		String xmlFilePath = JaxbUtilTest.class.getResource("/").getPath()
		+ "ServiceObject.xml";
		JaxbUtil.marshal(so, xmlFilePath);
	}
	
	@Test
	public void unmarshalTest() throws Exception{
		String xmlPath = JaxbUtilTest.class.getResource("/testSource/O2P-BMO-SAMPLE.xml").getFile();
		ServiceObject sos = JaxbUtil.unmarshal(xmlPath);
		
		String xmlFilePath = JaxbUtilTest.class.getResource("/").getPath()
				+ "ServiceObject.xml";
		JaxbUtil.marshal(sos, xmlFilePath);
		String xsdFile = XMLValidateUtilTest.class.getResource("/testSource/O2P-BMO.xsd").getFile();
		boolean isValid = XMLValidateUtil.validateXml(xsdFile, xmlFilePath);
		Assert.assertSame(true, isValid);
	}
}
