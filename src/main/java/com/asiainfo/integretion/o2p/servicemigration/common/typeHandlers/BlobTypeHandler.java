package com.asiainfo.integretion.o2p.servicemigration.common.typeHandlers;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;


public class BlobTypeHandler implements TypeHandler<String> {
	
	private static final Log LOG = LogFactory.getLog(BlobTypeHandler.class);

	@Override
	public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		if(parameter!=null){
			ps.setBytes(i, parameter.getBytes());
		}else{
			ps.setBytes(i, null);
		}
	}

	@Override
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		if(rs.getBytes(columnName)==null){
			return null;
		}
		try {
			return new String(rs.getBytes(columnName), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
		}
		return null;
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		if(rs.getBytes(columnIndex)==null){
			return null;
		}
		try {
			return new String(rs.getBytes(columnIndex), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
		}
		return null;
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		if(cs.getBytes(columnIndex)==null){
			return null;
		}
		try {
			return new String(cs.getBytes(columnIndex), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
		}
		return null;
	}


}
