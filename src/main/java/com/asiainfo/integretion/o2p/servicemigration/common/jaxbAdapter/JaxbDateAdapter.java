package com.asiainfo.integretion.o2p.servicemigration.common.jaxbAdapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class JaxbDateAdapter extends XmlAdapter<String, XMLGregorianCalendar>{
	static final String STANDARM_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Override
	public XMLGregorianCalendar unmarshal(String v) throws Exception {
//		if (v == null) {
//			return null;
//		}
//		DateFormat format = new SimpleDateFormat(STANDARM_DATE_FORMAT);
//		Date date =  format.parse(v);
//		return CommonUtil.dateToXmlGregorianCalendar(date);
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(v);
	}

	@Override
	public String marshal(XMLGregorianCalendar v) throws Exception {
//		if(v == null) {
//			return null;
//		}
//		DateFormat format = new SimpleDateFormat(STANDARM_DATE_FORMAT);
//		return format.format(CommonUtil.xmlGregorianCalendarToDate(v));
		return v.toString();
	}
}
