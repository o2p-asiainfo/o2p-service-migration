package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import javax.jms.IllegalStateException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.asiainfo.integretion.o2p.servicemigration.common.Constant;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.BeforeMigration;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.Parent;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.ReferenceType;
import com.asiainfo.integretion.o2p.servicemigration.common.util.ReflectionUtil;
import com.asiainfo.integretion.o2p.servicemigration.dao.mapper.CheckMapper;

public class BasedBeanHelper {
	
//	private static final Logger logger = LoggerFactory.getLogger(BasedBeanHelper.class);
	private BasedBean rootSrcBean;
	private BasedBean rootTarBean;
	private CheckMapper checkMapper;
	
	public BasedBeanHelper(CheckMapper checkMapper) {
		this.checkMapper = checkMapper;
	}
	
	public BasedBeanHelper(CheckMapper checkMapper, BasedBean rootSrcBean, BasedBean rootTarBean) {
		this.checkMapper = checkMapper;
		this.rootSrcBean = rootSrcBean;
		this.rootTarBean = rootTarBean;
	}
	
	/**
	 * 寻找basedBean本身的唯一编码的值
	 * @param bean
	 * @return
	 */
	public String findCodeValue(Object bean) {
		String value = null;
		if(bean instanceof BasedBean) {
			value = ((BasedBean) bean).codeValue();
		} else if(bean instanceof String){
			value = (String) bean;
		}
		return value;
	}
	
	public Object findObjectInDB(BasedBean basedBean){
		if(basedBean.getTableName()==null || basedBean instanceof ServiceTechnologyImplement){
			return null;
		}
		return checkMapper.queryDynamicObject(basedBean.getTableName(), basedBean.getCodeColumnName() , basedBean.codeValue(), basedBean.tenantId);
	}
	
	/**
	 * 比较Object对象属性
	 * @param srcObj 属性所在对象
	 * @param currentValue 当前属性值
	 * @param targetValue 要对比的目标属性值
	 * @return
	 * @throws IllegalStateException 如果类注解有问题或者没找到传进来的code
	 */
	public boolean compareObject(BasedBean srcObj, Object currentValue, Object targetValue, String attrName) throws IllegalStateException {
		Object bean = currentValue;
		Object tbean = targetValue;
		//判断两个bean的code是否相同，不相同可能需要upate父类
		boolean codeEquals = true;
		//判断是否为删除
		if(bean ==null && tbean instanceof BasedBean){
			((BasedBean)targetValue).setAction(Constant.ACTION_R);
			ReflectionUtil.setAttrValue(attrName, targetValue, srcObj);
			return false;
		}
		if(bean == null && tbean == null) {
			codeEquals =  true;
		} else if(bean instanceof BasedBean && tbean instanceof BasedBean) {
			codeEquals = CommonUtil.compareOrdinaryAttr(((BasedBean)bean).codeValue(), ((BasedBean)tbean).codeValue());
			((BasedBean)bean).compare((BasedBean)tbean, this);
		} else if(bean instanceof java.lang.String) {
			BasedBean result = null;
			BasedBean result1 = null;
			result = queryBasedBeanByCode(srcObj, attrName, (String)bean, null);
			if(result != null && !StringUtils.hasText(result.getAction())) {
				if(tbean instanceof java.lang.String) {
					codeEquals = bean.equals(tbean);
					result1 = queryBasedBeanByCode(srcObj, attrName, (String)tbean, rootTarBean);
				} else {
					codeEquals = bean.equals(((BasedBean)tbean).codeValue());
					result1 = (BasedBean)tbean;
				}
				result.compare(result1, this);
			} else if(result == null) {
				String tcode = findCodeValue(tbean);
				if(!bean.equals(tcode)) {
					codeEquals = false;
				}
			}
		} else if(tbean instanceof java.lang.String){
			BasedBean result1 = queryBasedBeanByCode(srcObj, attrName, (String)tbean, rootTarBean);
			if(bean == null) {
				codeEquals = false;
			} else {
				BasedBean result = (BasedBean)bean;
				result.compare(result1, this);
				codeEquals = CommonUtil.compareOrdinaryAttr(result.codeValue(), result1.codeValue());
			}
		} else {
			if(bean != null && tbean == null) {
				if(((BasedBean)bean).getAction() == null) {
					((BasedBean)bean).changeAllAction(Constant.ACTION_A, this);
				}
			}
			codeEquals = false;
		}
		if((ReflectionUtil.fieldExistAnnotation(BeforeMigration.class, srcObj, attrName) 
				|| ReflectionUtil.fieldExistAnnotation(Parent.class, srcObj, attrName))
				|| ReflectionUtil.fieldExistAnnotation(ReferenceType.class, srcObj, attrName)) {
			return codeEquals;
		}
		return true;
	}
	
	/**
	 * 比较List属性
	 * @param srcObj 属性所在对象
	 * @param currentValue 当前属性值
	 * @param targetValue 要对比的目标属性值
	 * @param attr 属性名
	 * @return
	 * @throws IllegalStateException 如果类注解有问题或者没找到传进来的code
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean compareListAttr(BasedBean srcObj, Object currentValue, Object targetValue, String attr) throws IllegalStateException {
		List cvalue = (List)currentValue;
		List tvalue = (List)targetValue;
		String ccode = null;
		String tcode = null;
		if(cvalue!=null && !cvalue.isEmpty()){
			for(Object bean: cvalue) {
				if(bean == null) {
					continue;
				}
				ccode = findCodeValue(bean);
				boolean isExist = false;
				for(Object tbean: tvalue) {
					if(tbean == null) {
						continue;
					}
					tcode = findCodeValue(tbean);
					if(CommonUtil.compareOrdinaryAttr(ccode, tcode)) {
						isExist = true;
						compareObject(srcObj, bean, tbean, attr);
						break;
					}
				}
				if(!isExist) {
					BasedBean result = null;
					if(!(bean instanceof java.lang.String)) {
						result = (BasedBean)bean;
					}
					if(result!=null && result.getAction() == null) {
						result.changeAllAction(Constant.ACTION_A, this);
					}
				}
			}
		}
		if(tvalue!=null && !tvalue.isEmpty()){
			for(Object bean: tvalue) {
				if(bean == null) {
					continue;
				}
				tcode = findCodeValue(bean);
				boolean isExist = false;
				for(Object cbean: cvalue) {
					if(cbean == null) {
						continue;
					}
					ccode = findCodeValue(cbean);
					if(CommonUtil.compareOrdinaryAttr(ccode, tcode)) {
						isExist = true;
					}
				}
				if(!isExist) {
					BasedBean result = null;
					if(!(bean instanceof java.lang.String)) {
						result = (BasedBean)bean;
						result.setAction(Constant.ACTION_R);
						cvalue.add(result);
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 根据code获取对象
	 * @param srcObj
	 * @param srcObj 
	 * @param attr
	 * @param code
	 * @return
	 * @throws IllegalStateException
	 */
	@SuppressWarnings("rawtypes")
	public BasedBean queryBasedBeanByCode(BasedBean srcObj, String attr, String code, BasedBean rootBean) throws IllegalStateException {
		Class type = srcObj.findAttrRealType(attr);
		if(type != null) {
			if(rootBean == null) {
				return rootSrcBean.queryBeanByCodeAndType(type, code);
			} else {
				return rootBean.queryBeanByCodeAndType(type, code);
			}
		}
		throw new IllegalStateException("the class type:" + srcObj.getClass() + " attr:" + attr + " can't be resolved, check the annotatioin of the attr");
	}
	
	/**
	 * 校验传入的code是否存在
	 * @param result 
	 * @param type
	 * @param code
	 * @return
	 * @throws IllegalStateException 
	 */
	public BasedBean validateCode(@SuppressWarnings("rawtypes") Class type, String code, Integer tenantId) throws IllegalStateException {
		BasedBean result = null;
		result = rootSrcBean.queryBeanByCodeAndType(type, code);
		if(result == null) {
			String className = type.getName().substring(type.getName().lastIndexOf(".")+1);
			String methodName = "check"+className+"ExistByCode";
			Method m = ReflectionUtil.findMethod(checkMapper.getClass(), methodName, String.class, Integer.class);
			if(m == null) {
				throw new IllegalStateException("the methodName:" + methodName + " can't be find in the class:" + checkMapper.getClass());
			}
			Integer count = (Integer) ReflectionUtil.invokeMethod(m, checkMapper, code, tenantId);
			if(count == null || count == 0) {
				throw new IllegalStateException("the code :" + code + " of the class type:" + type + " can't found in the improt doc and the database");
			}
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public static void findAllContractFormatCode(Set<String> contractFormatCodeList, BasedBean bean) {
		List<String> attrs = ReflectionUtil.getAllAttrs(bean);
		for(String attr: attrs) {
			Object value = ReflectionUtil.getAttrValue(attr, bean);
			if(value instanceof BasedBean) {
				findAllContractFormatCode(contractFormatCodeList,(BasedBean)value);
			} else if(value instanceof List) {
				for(Object subValue: (List)value) {
					if(subValue instanceof BasedBean) {
						findAllContractFormatCode(contractFormatCodeList,(BasedBean)subValue);
					}
				}
			} else if((value instanceof String) && ReflectionUtil.fieldExistAnnotation(ReferenceType.class, bean, attr)) {
				ReferenceType type = (ReferenceType) ReflectionUtil.findFieldAnnotation(ReferenceType.class, bean, attr);
				if(type.value() == ContractFormat.class) {
					contractFormatCodeList.add((String)value);
				}
			}
		}
	}
	
	public BasedBean getRootSrcBean() {
		return rootSrcBean;
	}
	public void setRootSrcBean(BasedBean rootSrcBean) {
		this.rootSrcBean = rootSrcBean;
	}
	public BasedBean getRootTarBean() {
		return rootTarBean;
	}
	public void setRootTarBean(BasedBean rootTarBean) {
		this.rootTarBean = rootTarBean;
	}
}
