package com.asiainfo.integretion.o2p.servicemigration.common.util;

import java.io.File;
import java.io.InputStream;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.asiainfo.integretion.o2p.servicemigration.domain.ServiceObject;

public class JaxbUtil {
	private static final String ROOT_DOMAIN_PACKAGE = "com.asiainfo.integretion.o2p.servicemigration.domain";
	private static final String DEFAULT_XSD_FILE = "O2P-BMO.xsd";
	private static JAXBContext jc = null;
	
	public static void marshal(ServiceObject rootObject, File xmlFile) {
		if(xmlFile == null) {
			throw new IllegalArgumentException("marshal exception: xmlFile can't be null");
		}
		if(rootObject == null) {
			rootObject = new ServiceObject();
		}
		try {
			jc = JAXBContext.newInstance(ROOT_DOMAIN_PACKAGE);
			Marshaller ms = jc.createMarshaller();
			ms.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, DEFAULT_XSD_FILE);
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			ms.marshal(rootObject, xmlFile);
		} catch (JAXBException e) {
			throw new IllegalStateException("marshal exception:", e);
		}
	}
	
	public static void marshal(ServiceObject rootObject, Writer os) {
		if(rootObject == null) {
			rootObject = new ServiceObject();
		}
		try {
			jc = JAXBContext.newInstance(ROOT_DOMAIN_PACKAGE);
			Marshaller ms = jc.createMarshaller();
			ms.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, DEFAULT_XSD_FILE);
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			ms.marshal(rootObject, os);
		} catch (JAXBException e) {
			throw new IllegalStateException("marshal exception:", e);
		}
	}
	
	public static void marshal(ServiceObject rootObject, String xmlPath) {
		File xmlFile = new File(xmlPath);
		marshal(rootObject, xmlFile);
	}
	
	public static ServiceObject unmarshal(File xmlFile) {
		if(xmlFile == null) {
			throw new IllegalArgumentException("unmarshal exception: xmlFile can't be null");
		}
		try {
			jc = JAXBContext.newInstance(ROOT_DOMAIN_PACKAGE);
			Unmarshaller ums = jc.createUnmarshaller();
			return (ServiceObject) ums.unmarshal(xmlFile);
		} catch (JAXBException e) {
			throw new IllegalStateException("marshal exception:", e);
		}
	}
	
	public static ServiceObject unmarshal(InputStream is) {
		if(is == null) {
			throw new IllegalArgumentException("unmarshal exception: InputStream can't be null");
		}
		try {
			jc = JAXBContext.newInstance(ROOT_DOMAIN_PACKAGE);
			Unmarshaller ums = jc.createUnmarshaller();
			return (ServiceObject) ums.unmarshal(is);
		} catch (JAXBException e) {
			throw new IllegalStateException("marshal exception:", e);
		}
	}
	
	public static ServiceObject unmarshal(String xmlPath) {
		File xmlFile = new File(xmlPath);
		return unmarshal(xmlFile);
	}

}
