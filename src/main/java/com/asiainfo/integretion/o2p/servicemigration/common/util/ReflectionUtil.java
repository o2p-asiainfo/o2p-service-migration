package com.asiainfo.integretion.o2p.servicemigration.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.springframework.util.ReflectionUtils;

public class ReflectionUtil {
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final Log LOG = LogFactory.getLog(ReflectionUtil.class);
	
	public static Object getAttrValue(String attrName, Object target) {
		if(target == null || attrName == null) {
			return null;
		}
		MetaObject metaStatementHandler = MetaObject.forObject(target,  
			     DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		if(metaStatementHandler.hasGetter(attrName)) {
			return metaStatementHandler.getValue(attrName);
		} else {
			throw new IllegalArgumentException("the object:" + target.getClass().getName() + " no find the getter method of attr : " + attrName);
		}
	}
	
	public static String getClassLowerName(Object target) {
		if(target == null) {
			return null;
		}
		String name =  target.getClass().getName().substring(target.getClass().getName().lastIndexOf(".")+1);
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}
	
	public static boolean hasAttr(String attrName, Object target) {
		if(target == null) {
			return false;
		}
		MetaObject metaStatementHandler = MetaObject.forObject(target,  
			     DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		return metaStatementHandler.hasGetter(attrName);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object findFieldAnnotation(Class annotationType, Object target, String fieldName) {
		if(target == null) {
			return null;
		}
		Field f = ReflectionUtils.findField(target.getClass(), fieldName);
		if(f == null) {
			throw new IllegalArgumentException("the object:" + target.getClass().getName() + " no find the field : " + fieldName);
		}
		return f.getAnnotation(annotationType);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean fieldExistAnnotation(Class annotationType, Object target, String fieldName) {
		if(target == null) {
			return false;
		}
		Field f = ReflectionUtils.findField(target.getClass(), fieldName);
		if(f == null) {
			throw new IllegalArgumentException("the object:" + target.getClass().getName() + " no find the field : " + fieldName);
		}
		return (f.getAnnotation(annotationType) != null);
	}
	
	@SuppressWarnings({"rawtypes" })
	public static boolean isFieldType(Class fieldType, Object target, String fieldName) {
		if(target == null) {
			return false;
		}
		Field f = ReflectionUtils.findField(target.getClass(), fieldName);
		if(f == null) {
			throw new IllegalArgumentException("the object:" + target.getClass().getName() + " no find the field : " + fieldName);
		}
		return f.getGenericType().equals(fieldType);
	}
	
	public static boolean isFieldType(String keyword, Object target, String fieldName) {
		if(target == null) {
			return false;
		}
		Field f = ReflectionUtils.findField(target.getClass(), fieldName);
		if(f == null) {
			throw new IllegalArgumentException("the object:" + target.getClass().getName() + " no find the field : " + fieldName);
		}
		return f.getGenericType().toString().contains(keyword);
	}
	
	public static void setAttrValue(String attrName, Object value, Object target) {
		if(target == null) {
			return;
		}
		MetaObject metaStatementHandler = MetaObject.forObject(target,  
			     DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		if(metaStatementHandler.hasSetter(attrName)) {
			metaStatementHandler.setValue(attrName, value);
		} else {
			throw new IllegalArgumentException("the object:" + target.getClass().getName() + " no find the setter method of attr : " + attrName);
		}
	}
	
	public static List<String> getAllAttrs(Object target) {
		if(target == null) {
			return null;
		}
		MetaObject metaStatementHandler = MetaObject.forObject(target,  
			     DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		return Arrays.asList(metaStatementHandler.getSetterNames());
	}
	
	public static boolean isSameInstance(Object src, Object target) {
		if(src == null || target == null) {
			return false;
		}
		if(src.getClass().getName().equals(target.getClass().getName())) {
			return true;
		}
		return false;
	}
	
	public static Object invokeMethod(Method m, Object obj, Object... args) {
		if(obj == null || m == null) {
			throw new IllegalArgumentException("can't invoke method, the object or the method is null");
		}
		if(args.length > 0) {
			if(args.length==1){
				return ReflectionUtils.invokeMethod(m, obj, args);
			}else{
				return ReflectionUtils.invokeMethod(m, obj, args[0], args[1]);
			}
		} else {
			return ReflectionUtils.invokeMethod(m, obj);
		}
	}
	
	public static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramType) {
		if(paramType.length > 0) {
			return ReflectionUtils.findMethod(clazz, methodName, paramType);
		} else {
			return ReflectionUtils.findMethod(clazz, methodName);
		}
		
	}
	
	public static Map<String, Object> objToMap(Object obj) {
		if(obj == null) {
			return null;
		}
		List<String> attrs = ReflectionUtil.getAllAttrs(obj);
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		for(String attr: attrs) {
			result.put(attr, getAttrValue(attr,obj));
		}
		return result;
	}
}
