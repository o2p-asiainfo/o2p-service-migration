package com.asiainfo.integretion.o2p.servicemigration.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLValidateUtil {
	private static final Log log = LogFactory.getLog(XMLValidateUtil.class);
	public static boolean validateXml(String xsdpath, String xmlpath)
			throws SAXException, IOException {
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		File schemaFile = new File(xsdpath);
		Schema schema = schemaFactory.newSchema(schemaFile);
		Validator validator = schema.newValidator();
		Source source = new StreamSource(xmlpath);
		validator.validate(source);
		return true;
	}
	
	public static String validateXml(String xsdPath, InputStream is) {
		try{
			SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = null;
			if(xsdPath==null){
				InputStream xsdInputStream = XMLValidateUtil.class.getResourceAsStream("/O2P-BMO.xsd");
				schema = schemaFactory.newSchema(new SAXSource(new InputSource(xsdInputStream)));
			}else{
				schema = schemaFactory.newSchema(new File(xsdPath));
			}
			Validator validator = schema.newValidator();
			Source source = new StreamSource(is);
			validator.validate(source);
		}catch(Exception e){
			log.error(e);
			return String.valueOf(e);
		}
		return "success";
	}
}
