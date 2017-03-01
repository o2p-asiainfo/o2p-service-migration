package com.asiainfo.integretion.o2p.servicemigration.comon.util;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.asiainfo.integretion.o2p.servicemigration.common.util.XMLValidateUtil;

public class XMLValidateUtilTest {
	
	@Test(expected=SAXException.class)
	public void validateXmlTest() throws SAXException, IOException {
		String xmlFile = XMLValidateUtilTest.class.getResource("/testSource/O2P-BMO-SAMPLE.xml").getFile();
		String xmlFileNovalid = XMLValidateUtilTest.class.getResource("/testSource/O2P-BMO-SAMPLE-NOVALID.xml").getFile();
		String xsdFile = XMLValidateUtilTest.class.getResource("/testSource/O2P-BMO.xsd").getFile();
		boolean validateResult = XMLValidateUtil.validateXml(xsdFile, xmlFile);
		Assert.assertEquals(true, validateResult);
		XMLValidateUtil.validateXml(xsdFile, xmlFileNovalid);
	}
	
	@Test
	public void validateXmlTest1() throws SAXException, IOException{
		String xmlFile = XMLValidateUtilTest.class.getResource("/testSource/O2P-BMO-SAMPLE.xml").getFile();
		String xsdFile = XMLValidateUtilTest.class.getResource("/testSource/O2P-BMO.xsd").getFile();
		boolean validateResult = XMLValidateUtil.validateXml(xsdFile, xmlFile);
		Assert.assertEquals(true, validateResult);
	}
	
}
