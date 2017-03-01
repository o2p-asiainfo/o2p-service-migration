package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class CommonUtil {
	public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static XMLGregorianCalendar dateToXmlGregorianCalendar(Date date) {
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {
			throw new IllegalStateException("dateToXmlGregorianCalendar error",
					e);
		}
	}

	public static boolean isObjInCollection(Object obj, Collection collection) {
		if (collection == null) {
			return false;
		}
		Iterator iterator = collection.iterator();
		Object item = null;
		while (iterator.hasNext()) {
			item = iterator.next();
			if (item == obj) {
				return true;
			}
		}
		return false;
	}

	public static Date xmlGregorianCalendarToDate(XMLGregorianCalendar date) {
		if (date == null) {
			return null;
		}
		return date.toGregorianCalendar().getTime();
	}

	public static String xmlGregorianCalendarToDateFormat(
			XMLGregorianCalendar date) {
		if (date == null) {
			return null;
		}
		Date time = xmlGregorianCalendarToDate(date);
		return dateFormat(time);
	}

	public static String dateFormat(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(DEFAULT_TIME_FORMAT).format(date);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean collectionEquals(Object list1, Object list2) {
		if (list1 == null && list2 == null) {
			return true;
		}
		if (list1 instanceof Collection && list2 instanceof Collection) {
			Collection l1 = (Collection) list1;
			Collection l2 = (Collection) list2;
			return l1.containsAll(l2) && l2.containsAll(l1);
		} else {
			return false;
		}
	}

	public static boolean compareOrdinaryAttr(Object currentValue,
			Object targetValue) {
		if ((currentValue == null || "".equals(currentValue)) && targetValue == null) {
			return true;
		} else if (currentValue != null && targetValue != null) {
			return currentValue.equals(targetValue);
		} else {
			return false;
		}
	}

	public static Date strToDate(String timeStr, String format) {
		try {
			if(format == null) {
				return new SimpleDateFormat(DEFAULT_TIME_FORMAT).parse(timeStr);
			} else {
				return new SimpleDateFormat(format).parse(timeStr);
			}
		} catch (ParseException e) {
			return null;
		}
	}
}
