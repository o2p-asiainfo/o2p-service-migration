package com.asiainfo.integretion.o2p.servicemigration.dao.common;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;

import com.asiainfo.foundation.log.Logger;

@Intercepts({@Signature(type =StatementHandler.class, method = "prepare", args ={Connection.class})}) 
public class PageInterceptor implements Interceptor{
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final Logger logger = Logger.getLog(PageInterceptor.class);
    private static final String DEFAULT_PAGE_SQL_ID = ".*(Page|PAGE)$";
    private static final String DEFAULT_FETCH_ONE_SQL_ID = ".*(WithOne|WITHONE)$";
    private static final String DEFAULT_DIALECT = "mysql";
    private String dialect = null;
    private String pageSqlId = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();  
	     MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,  
	     DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
	     // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环  
	     // 可以分离出最原始的的目标类)  
	     while (metaStatementHandler.hasGetter("h")) {  
	         Object object = metaStatementHandler.getValue("h");  
	         metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,   
	         DEFAULT_OBJECT_WRAPPER_FACTORY);  
	     }  
	     // 分离最后一个代理对象的目标类  
	     while (metaStatementHandler.hasGetter("target")) {  
	         Object object = metaStatementHandler.getValue("target");  
	         metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,   
	         DEFAULT_OBJECT_WRAPPER_FACTORY);  
	     }
	     if (null == dialect || "".equals(dialect)) {  
	         logger.warn("Property dialect is not setted,use default 'mysql' ");  
	         dialect = DEFAULT_DIALECT;  
	     }  
	     if (null == pageSqlId || "".equals(pageSqlId)) {  
	         logger.warn("Property pageSqlId is not setted,use default '.*(Page|PAGE)$' ");  
	         pageSqlId = DEFAULT_PAGE_SQL_ID;
	     }  
	     MappedStatement mappedStatement = (MappedStatement)   
	     metaStatementHandler.getValue("delegate.mappedStatement");
	     
	     // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的  
	     //  MappedStatement的sql  
	     if (mappedStatement.getId().matches(pageSqlId) || mappedStatement.getId().matches(DEFAULT_FETCH_ONE_SQL_ID)) {  
	    	 BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
		     Object parameterObject = boundSql.getParameterObject();
		     if(mappedStatement.getId().matches(DEFAULT_FETCH_ONE_SQL_ID)) {
		    	 if(!(parameterObject instanceof java.util.Map)) {
		    		 parameterObject = new HashMap<String, Object>();
		    	 }
		    	 if(!((Map)parameterObject).containsKey("startPage_mysql") && !((Map)parameterObject).containsKey("endPage_mysql")) {
			    	 ((Map)parameterObject).put("startPage_mysql", 0);
			    	 ((Map)parameterObject).put("endPage_mysql", 1);
		    	 }
		     }
	    	 //往sql中注入当前的数据库类型
             String sql = boundSql.getSql();  
             // 重写sql  
             String pageSql = buildPageSql(sql, parameterObject);
             if(logger.isDebugEnabled()) {
            	 logger.debug("format page sql {0}", sql);
             }
             metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);  
             // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数  
             metaStatementHandler.setValue("delegate.rowBounds.offset",   
             RowBounds.NO_ROW_OFFSET);  
             metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);  
	     }  
	     // 将执行权交给下一个拦截器  
	     return invocation.proceed();
	}

	@SuppressWarnings("rawtypes")
	private String buildPageSql(String sql, Object parameterObject) {
        Integer start_row = null;
        Integer limit = null;
        if(parameterObject instanceof java.util.Map) {
        	start_row = (Integer) ((Map)parameterObject).get("startPage_mysql");
        	limit = (Integer)((Map)parameterObject).get("endPage_mysql");
        }
        if ("mysql".equals(dialect)) {  
            return buildPageSqlForMysql(sql, start_row, limit).toString();  
        } else if ("oracle".equals(dialect)) {  
        	return buildPageSqlForOracle(sql, start_row, limit).toString();  
        } else {  
            return sql;  
        }
	}
	
	public StringBuilder buildPageSqlForMysql(String sql, Integer start_row, Integer limit) {  
	    StringBuilder pageSql = new StringBuilder(100);  
	    pageSql.append(sql);  
	    if(start_row != null) {
	    	pageSql.append(" limit " + start_row + ", ");
	    }
	    if(limit != null) {
	    	pageSql.append(""+limit);
	    }
	    return pageSql;  
	}
	
	public StringBuilder buildPageSqlForOracle(String sql, Integer start_row, Integer limit) {  
	    StringBuilder pageSql = new StringBuilder(100);
	    pageSql.append("select * from ( select temp.*, rownum row_id from ( ");  
	    pageSql.append(sql);
	    pageSql.append(" ) temp");
	    if(limit != null) {
	    	if(start_row != null) {
	    		limit = limit + start_row;
	    	}
	    	pageSql.append(" where rownum <= ").append(limit);
	    }
	    pageSql.append(")");
	    if(start_row != null) {
	    	pageSql.append(" where row_id > ").append(start_row);
	    }
	    return pageSql;  
	}

	@Override
	public Object plugin(Object target) {
		// 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的  
	    // 次数  
	    if (target instanceof StatementHandler) {  
	        return Plugin.wrap(target, this);  
	    } else {  
	        return target;  
	    } 
	}

	@Override
	public void setProperties(Properties properties) {
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public void setPageSqlId(String pageSqlId) {
		this.pageSqlId = pageSqlId;
	}
}
