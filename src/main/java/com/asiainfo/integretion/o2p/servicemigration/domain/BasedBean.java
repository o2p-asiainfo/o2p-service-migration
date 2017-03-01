package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.IllegalStateException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.datatype.XMLGregorianCalendar;

import net.sf.json.JSONObject;

import com.asiainfo.integretion.o2p.servicemigration.common.Constant;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.ReferenceType;
import com.asiainfo.integretion.o2p.servicemigration.common.util.ReflectionUtil;

public class BasedBean implements Serializable, Cloneable {
	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 1L;
	@XmlTransient
	protected Integer id = null;

	@XmlTransient
	protected String action;
	
	@XmlTransient
	protected Map<String, Object> attributes;
	
	@XmlTransient
	protected Integer tenantId = null;

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 将底下所有BasedBean对象的action都改变
	 * @param action
	 */
	@SuppressWarnings("rawtypes")
	public void changeAllAction(String action, BasedBeanHelper helper) {
		//如果已经存在于数据库中，则更新该对象
		if(Constant.ACTION_A.equals(action)){
			Object oldObj = helper.findObjectInDB(this);
			if(oldObj!=null){
				this.action = Constant.ACTION_M;
				if(this.attributes==null){
					this.attributes = new HashMap<String, Object>();
				}
				this.attributes.put("oldObject", oldObj.toString());
			}
		}else{
			this.setAction(action);
		}
		List<String> currentAttrs = ReflectionUtil.getAllAttrs(this);
		for(String attr: currentAttrs) {
			Object currentValue = ReflectionUtil.getAttrValue(attr, this);
			if(currentValue instanceof List) {
				for(Object obj : ((List)currentValue)) {
					if(obj instanceof BasedBean) {
						((BasedBean)obj).changeAllAction(action, helper);
					}
				}
			} else if(currentValue instanceof BasedBean) {
				((BasedBean)currentValue).changeAllAction(action, helper);
			}
		}
	}
	
	/**
	 * 如果对象中某一个属性是String值，那么有可能是指向某个对象code，此方法可根据属性名找到具体对应属性类型，子类可重写,参考EndpointSpec
	 * @param attr
	 * @return 
	 */
	@SuppressWarnings("rawtypes")
	public Class findAttrRealType(String attr) {
		XmlElements xe = (XmlElements) ReflectionUtil.findFieldAnnotation(XmlElements.class, this, attr);
		if(xe != null) {
			for(XmlElement xml:xe.value()) {
				if(!String.class.equals(xml.type())) {
					return xml.type();
				}
			}
		} else {
			ReferenceType rt = (ReferenceType)ReflectionUtil.findFieldAnnotation(ReferenceType.class, this, attr);
			if(rt != null) {
				return rt.value();
			}
		}
		return null;
	}
	
	//校验引用的code是否已经在文档中或者在目标数据库中
	@SuppressWarnings("rawtypes")
	public void validateCode(BasedBeanHelper helper) throws IllegalStateException {
		if(helper != null && helper.getRootSrcBean() == null && helper.getRootTarBean() == null) {
			helper.setRootSrcBean(this);
		}
		List<String> currentAttrs = ReflectionUtil.getAllAttrs(this);
		for(String attr: currentAttrs) {
			Object value = ReflectionUtil.getAttrValue(attr, this);
			if(value instanceof BasedBean) {
				((BasedBean)value).setTenantId(tenantId);
				((BasedBean)value).validateCode(helper);
			} else if(value instanceof List) {
				for(Object subValue: (List)value) {
					if(subValue instanceof BasedBean) {
						((BasedBean)subValue).setTenantId(tenantId);
						((BasedBean)subValue).validateCode(helper);
					} else if(subValue instanceof String && ReflectionUtil.fieldExistAnnotation(XmlElements.class, this, attr)) {
						Class type = this.findAttrRealType(attr);
						helper.validateCode(type, (String)subValue, this.tenantId);
					}
				}
			} else if(value instanceof String) {
				if(ReflectionUtil.fieldExistAnnotation(ReferenceType.class, this, attr)) {
					Class type = this.findAttrRealType(attr);
					helper.validateCode(type, (String)value, this.tenantId);
				}
			}
		}
	}
	
	public String getTableName(){
		return Constant.tbMap.get(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".")+1));
	}
	
	public String getCodeColumnName(){
		if(this instanceof ContractFormat){
			return "contract_format_code";
		}
		if(this instanceof ContractVersion){
			return "version";
		}
		if(this instanceof App){
			return "appkey";
		}
		if(this instanceof Contract || this instanceof Component){
			return "code";
		}
		return getTableName() + "_code";
	}
	
	/**
	 * 比较两个BasedBean对象
	 * @param target 比较目标
	 * @param helper basedBean比较的辅助类
	 * @return
	 * @throws IllegalStateException
	 */
	public boolean compare(BasedBean target, BasedBeanHelper helper) throws IllegalStateException {
		if(helper != null && helper.getRootSrcBean() == null && helper.getRootTarBean() == null) {
			helper.setRootSrcBean(this);
			helper.setRootTarBean(target);
		}
		if(this.getAction() != null) {
			return true;
		}
		if(target == null) {
			this.changeAllAction(Constant.ACTION_A, helper);
			return false;
		}
//		if(this.codeValue()!=null && target.codeValue()!=null && !this.codeValue().equals(target.codeValue())){
//			this.changeAllAction(Constant.ACTION_A);
//			return false;
//		}
		boolean isSame = true;
		List<String> currentAttrs = ReflectionUtil.getAllAttrs(this);
		for(String attr: currentAttrs) {
			//不需要比较字段
			if(ReflectionUtil.fieldExistAnnotation(XmlTransient.class, this, attr) 
					|| ReflectionUtil.isFieldType(XMLGregorianCalendar.class, this, attr) 
					|| (transientFields() != null && transientFields().contains(attr))) {
				continue;
			} 
			Object currentValue = ReflectionUtil.getAttrValue(attr, this);
			Object targetValue = ReflectionUtil.getAttrValue(attr, target);
			if(compareFields() != null && compareFields().contains(attr)) {
				//自定义比较字段
				isSame = this.fieldCompare(attr, currentValue, targetValue, helper);
			} else if(currentValue instanceof BasedBean || targetValue instanceof BasedBean) {
				isSame = helper.compareObject(this, currentValue, targetValue, attr);
			} else if(currentValue instanceof List || targetValue instanceof List) {
				if(ReflectionUtil.isFieldType("java.util.List<java.lang.String>", this, attr)){
					//如果是其他普通属性
					if(!CommonUtil.collectionEquals(currentValue, targetValue)) {
						isSame = false;
					}
				//如果是List<Object>类型，但是只是普通的属性
				} else if(ReflectionUtil.isFieldType("java.util.List<java.lang.Object>", this, attr) && !ReflectionUtil.fieldExistAnnotation(XmlElements.class, this, attr)) {
					if(!CommonUtil.collectionEquals(currentValue, targetValue)) {
						isSame = false;
					}
				} else {
					//如果List里面是BasedBean对象或者编码
					helper.compareListAttr(this, currentValue, targetValue,attr);
				}
			} else if(ReflectionUtil.isFieldType(Object.class, this, attr)) {
				//是否有XmlElements注解，如果有，代表不是普通的Object属性
				if(!ReflectionUtil.fieldExistAnnotation(XmlElements.class, this, attr)) {
					if(!CommonUtil.compareOrdinaryAttr(currentValue, targetValue)) {
						isSame = false;
					}
				} else {
					isSame = helper.compareObject(this, currentValue, targetValue, attr);
				}
			} else {
				//如果是普通属性
				if(!CommonUtil.compareOrdinaryAttr(currentValue, targetValue)) {
					isSame = false;
				}
			}
		}
		if(isSame) {
			this.action = Constant.ACTION_N;
		} else {
			this.action = Constant.ACTION_M;
			if(this.attributes==null){
				this.attributes = new HashMap<String, Object>();
			}
			this.attributes.put("oldObject", target.toString());
		}
		return isSame;
	}
	
	/**
	 * 获取每个对象的唯一编码。参考ContractVersion getcode
	 * @return
	 */
	public String getCode() {
		if(this instanceof ServiceTechnologyImplement){
			return null;
		}
		String code = null;
		String className = this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".")+1);
		if(className.length() > 1) {
			code = (char)(className.charAt(0)+32) + className.substring(1) + "Code";
		} else {
			code = (char)(className.charAt(0)+32) + "Code";
		}
		return code;
	}
	
	public String codeValue() {
		return (String) ReflectionUtil.getAttrValue(getCode(), this);
	}
	
	public void codeValue(String codeValue) {
		ReflectionUtil.setAttrValue(getCode(), codeValue, this);
	}
	
	/**
	 * 根据bean的类型和编码，从bean底下递归获取对象
	 * @param type
	 * @param code
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public BasedBean queryBeanByCodeAndType(Class type, String code) {
		return queryBeanByCodeAndType(type, code, false);
	}
	
	/**
	 * 根据bean的类型和编码，从bean底下递归获取对象, 并且将父类对象设置到子类属性中
	 * @param type
	 * @param code
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public BasedBean queryBeanByCodeAndType(Class type, String code, boolean setParent) {
		if(code == null) return null;
		if(type == null) return null;
		if(this.getCode() != null) {
			if(code.equals(ReflectionUtil.getAttrValue(this.getCode(), this)) && this.getClass().getName().equals(type.getName())) {
				return this;
			}
		}
		BasedBean bean = null;;
		List<String> currentAttrs = ReflectionUtil.getAllAttrs(this);
		for(String attr: currentAttrs) {
			Object currentValue = ReflectionUtil.getAttrValue(attr, this);
			if(currentValue instanceof List) {
				for(Object obj : ((List)currentValue)) {
					if(obj instanceof BasedBean) {
						bean = ((BasedBean)obj).queryBeanByCodeAndType(type, code);
						if(bean != null) {
							if(setParent) {
								if(bean.getAttributes() == null) {
									bean.setAttributes(new HashMap<String, Object>());
								}
								if(bean.getAttributes().get("parentBean") == null) {
									bean.getAttributes().put("parentBean", obj);
								}
							}
							return bean;
						}
					}
				}
			} else if(currentValue instanceof BasedBean) {
				bean = ((BasedBean)currentValue).queryBeanByCodeAndType(type, code);
				if(bean != null) {
					if(setParent) {
						if(bean.getAttributes() == null) {
							bean.setAttributes(new HashMap<String, Object>());
						}
						if(bean.getAttributes().get("parentBean") != null) {
							bean.getAttributes().put("parentBean", currentValue);
						}
					}
					return bean;
				}
			} 
		}
		return null;
	}
	
	/**
	 * 获取不需要比较的字段，子类可重写返回不需要比较的字段
	 * @return
	 */
	public List<String> transientFields() {
		return null;
	}
	
	/**
	 * 获取自定义比较的字段，子类可重写返回自定义比较的字段
	 */
	public List<String> compareFields() {
		return null;
	}
	
	/**
	 * 具体自定义属性比较方法，子类可重写自定义比较相应的字段
	 * @return
	 */
	public boolean fieldCompare(String attr, Object currentValue, Object targetValue, BasedBeanHelper helper) {
		return true;
	}
	
	/**
	 * 字段值转换，比如字段 1 对应 xml，可在子类继承valueTransform（代表导出）或者 valueUnTransform（代表导入）作具体值转换
	 * @param isForward
	 */
	@SuppressWarnings("rawtypes")
	public void attrTransform(boolean isForward) {                
		List<String> currentAttrs = ReflectionUtil.getAllAttrs(this);
		for(String attr: currentAttrs) {
			Object currentValue = ReflectionUtil.getAttrValue(attr, this);
			if(currentValue instanceof List) {
				for(Object obj : ((List)currentValue)) {
					if(obj instanceof BasedBean) {
						((BasedBean)obj).attrTransform(isForward);
					}
				}
			} else if(currentValue instanceof BasedBean) {
				((BasedBean)currentValue).attrTransform(isForward);
			} else {
				if(isForward) {
					this.valueTransform(attr);
					//对空串转换为空值
//					if(currentValue != null && currentValue.equals("")) {
//						ReflectionUtil.setAttrValue(attr, null, this);
//					}
				} else {
					this.valueUnTransform(attr);
				}
			}
		}
	}
	
	public Object clone() {
		try {
			ByteArrayOutputStream bo=new ByteArrayOutputStream();//1行  
	        ObjectOutputStream os = new ObjectOutputStream(bo);  
	        os.writeObject(this);//3行  
	          
	        ByteArrayInputStream bo1 = new ByteArrayInputStream(bo.toByteArray());//1行  
	        ObjectInputStream os1 = new ObjectInputStream(bo1);  
	        return os1.readObject();
		} catch(IOException e) {
			throw new IllegalArgumentException("clone error, cause:",  e);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("clone error, cause:",  e);
		}
	}

	/**
	 * 导出值转换可重写方法，参考Org valueTransform方法
	 * @param attr
	 */
	void valueTransform(String attr) {
		// TODO Auto-generated method stub
		return;
	}
	
	/**
	 * 导入值转换可重写方法
	 * @param attr
	 */
	void valueUnTransform(String attr) {
		// TODO Auto-generated method stub
			return;
	}
	
	

	@Override
	public int hashCode() {
		return this.getClass().getName().hashCode() + (this.codeValue()==null?0:this.codeValue().hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		super.equals(obj);
		if(obj == null) return false;
		if(!ReflectionUtil.isSameInstance(this, obj)) return false;
		List<String> attrs = ReflectionUtil.getAllAttrs(this);
		for(String attr:attrs) {
			//不需要比较字段
			if((ReflectionUtil.fieldExistAnnotation(XmlTransient.class, this, attr))
					|| ReflectionUtil.isFieldType(XMLGregorianCalendar.class, this, attr)
					|| ReflectionUtil.isFieldType("java.util.List", this, attr)
					|| ReflectionUtil.isFieldType(Object.class, this, attr)
					|| "action".equals(attr) || "attributes".equals(attr) || "id".equals(attr)) {
				continue;
			}
			Object thisValue = ReflectionUtil.getAttrValue(attr, this);
			Object tarValue = ReflectionUtil.getAttrValue(attr, obj);
			if(thisValue instanceof BasedBean || tarValue instanceof BasedBean) {
				continue;
			}
			if(thisValue instanceof List && !ReflectionUtil.fieldExistAnnotation(XmlElements.class, this, attr)) {
				boolean eq = CommonUtil.collectionEquals(thisValue, tarValue);
				if(!eq) {
					return false;
				}
			}
			boolean eq = CommonUtil.compareOrdinaryAttr(thisValue, tarValue);
			if(!eq) {
				return false;
			}
		}
		return true;
	}

	public String getObjectKey(){//key=className + codeValue
		return (this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".")+1) + this.codeValue());
	}
	
	/**
	 * 拆解对象并合并重复对象
	 * @param actionType
	 * @return
	 */
	public Map<String, BasedBean> getObjectMap(Map<String, BasedBean> map) {
		this.attrTransform(true);
		List<String> currentAttrs = ReflectionUtil.getAllAttrs(this);
		if(this.action==null){
			return null;
		}
		String key = this.getObjectKey();
		if(map.containsKey(key) && !this.action.equals(map.get(key).action)){
			if(Constant.ACTION_R.equals(this.action)){
				this.action = Constant.ACTION_N;
				map.get(key).action = Constant.ACTION_M;
			}else{
				this.action = Constant.ACTION_M;
				map.get(key).action = Constant.ACTION_N;
				map.put(key, this);
			}
		}else{
			map.put(key, this);
		}
		for(String attr: currentAttrs) {
			Object currentValue = ReflectionUtil.getAttrValue(attr, this);
			if(currentValue instanceof List) {
				for(Object obj : ((List)currentValue)) {
					if(obj instanceof BasedBean) {
						Map<String, BasedBean> temp = ((BasedBean)obj).getObjectMap(map);
						if(temp!=null && !temp.isEmpty()){
							map.putAll(temp);
						}
					}
				}
			} else if(currentValue instanceof BasedBean) {
				Map<String, BasedBean> temp = ((BasedBean)currentValue).getObjectMap(map);
				if(temp!=null && !temp.isEmpty()){
					map.putAll(temp);
				}
			} 
		}
		return map;
	}
	
	@Override
	public String toString() {
		List<String> attrs = ReflectionUtil.getAllAttrs(this);
		Map<String, Object> map = new HashMap<String, Object>();
		for(String attr:attrs) {
			Object value = ReflectionUtil.getAttrValue(attr, this);
			//不需要比较字段
			if(ReflectionUtil.fieldExistAnnotation(XmlTransient.class, this, attr) 
					|| ReflectionUtil.isFieldType(XMLGregorianCalendar.class, this, attr)
					|| ReflectionUtil.isFieldType("java.util.List", this, attr)
					|| value instanceof BasedBean) {
				continue;
			} 
			
			if(value != null && !"".equals(value)) {
				value = value.toString().replaceAll(">", "&gt;");
				value = value.toString().replaceAll("<", "&lt;");
				value = value.toString().replaceAll("&", "&amp");
				value = value.toString().replaceAll("&", "&amp");
				map.put(attr, value);
			}
		}
		return JSONObject.fromObject(map).toString();
	}

	public boolean fullEquals(Object bean,BasedBean rootBean) throws IllegalStateException {
		if(!this.equals(bean) || !ReflectionUtil.isSameInstance(this, bean)) {
			return false;
		}
		if(bean == null) {
			return false;
		}
		for(String attr:ReflectionUtil.getAllAttrs(this)) {
			Object currentValue = ReflectionUtil.getAttrValue(attr, this);
			Object targetValue = ReflectionUtil.getAttrValue(attr, bean);
			if(currentValue instanceof BasedBean) {
				boolean re = ((BasedBean)currentValue).fullEquals(targetValue,rootBean);
				if(!re) {
					return false;
				}
			} else if(currentValue instanceof List) {
				if(!(targetValue instanceof List)) {
					return false;
				}
				if(!CommonUtil.collectionEquals(currentValue, targetValue)) {
					return false;
				}
				for(Object subValue: (List)currentValue) {
					for(Object subtValue: (List)targetValue) {
						if(subValue.equals(subtValue)) {
							if(subValue instanceof BasedBean) {
								boolean re =  ((BasedBean)subValue).fullEquals(subtValue,rootBean);
								if(!re) {
									return false;
								}
							} else if(subValue instanceof String && ReflectionUtil.fieldExistAnnotation(XmlElements.class, this, attr)) {
								BasedBean relBean = rootBean.queryBeanByCodeAndType(this.findAttrRealType(attr), (String)subValue);
								boolean re =  relBean.fullEquals(subtValue,rootBean);
								if(!re) {
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
}
