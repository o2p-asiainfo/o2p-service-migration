package com.asiainfo.integretion.o2p.servicemigration.common.typeHandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class XMLGregorianCalendarToDateHandle implements TypeHandler<XMLGregorianCalendar> {
	private static final Log LOG = LogFactory.getLog(XMLGregorianCalendarToDateHandle.class);

	@Override
	public XMLGregorianCalendar getResult(ResultSet rs, String columnName)
			throws SQLException {
		Timestamp date = rs.getTimestamp(columnName);
		if (null == date) {
			return null;
		}
		XMLGregorianCalendar date2 = null;
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		try {
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			LOG.error("Datatype configuration exception!", e);
		}
		return date2;
	}

	@Override
	public XMLGregorianCalendar getResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp date = rs.getTimestamp(columnIndex);
		if (null == date) {
			return null;
		}
		XMLGregorianCalendar date2 = null;
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		try {
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			LOG.error("Datatype configuration exception!", e);
		}
		return date2;
	}

	@Override
	public XMLGregorianCalendar getResult(CallableStatement arg0, int arg1)
			throws SQLException {
		return null;
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, XMLGregorianCalendar parameter,
			JdbcType jdbcType) throws SQLException {
		if (null != parameter) {
			long time = parameter.toGregorianCalendar().getTimeInMillis();
			Timestamp date = new Timestamp(time);
			ps.setTimestamp(i, date);
		} else {
			ps.setTimestamp(i, null);
		}
	}

}
