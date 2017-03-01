package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nodeDescType", propOrder = {
    "nodeDescCode",
    "childNodeDescCodeOrNodeDesc",
    "name",
    "code",
    "nodePath",
    "type",
    "lengthCons",
    "countCons",
    "typeCons",
    "isSignature",
    "isCheck",
    "nodeValueConsType",
    "nodeValueConsExpressions",
    "javaField",
    "fuzzy",
    "description",
    "status"
})
public class NodeDesc extends BasedBean {

    @XmlElement(required = true)
    protected String nodeDescCode;
    @XmlElementWrapper(name="childNodeDesces")
    @XmlElements({
        @XmlElement(name = "childNodeDescCode", type = String.class),
        @XmlElement(name = "nodeDesc", type = NodeDesc.class)
    })
    protected List<Object> childNodeDescCodeOrNodeDesc;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String code;
    protected String nodePath;
    @XmlElement(required = true)
    protected String type;
    protected String lengthCons;
    @XmlElement(required = true)
    protected String countCons;
    @XmlElement(required = true)
    protected String typeCons;
    protected String isSignature;
    protected String isCheck;
    @XmlElement(required = true)
    protected String nodeValueConsType;
    @XmlElement(required = true)
    protected String nodeValueConsExpressions;
    protected String javaField;
    protected String fuzzy;
    protected String description;
    @XmlElement(required = true)
    protected String status;
    
    @XmlTransient
    protected static Map<String, String> valueMap = new HashMap<String, String>();
    static{
    	valueMap.put("1", "header");
    	valueMap.put("2", "body");
    	valueMap.put("3", "tail");
    	valueMap.put("4", "url");
    	valueMap.put("6", "xml namespace");
    	valueMap.put("7", "property");
    	valueMap.put("8", "child nodes namespace");
    	
    	valueMap.put("typeCons1", "String");
    	valueMap.put("typeCons2", "Number");
    	valueMap.put("typeCons3", "Object");
    	valueMap.put("typeCons4", "Date");
    	valueMap.put("typeCons5", "DateTime");
    	valueMap.put("typeCons6", "Time");
    	valueMap.put("typeCons8", "JSON");
    	valueMap.put("typeCons9", "XML");
    }

    /**
     * Gets the value of the nodeDescCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeDescCode() {
        return nodeDescCode;
    }

    /**
     * Sets the value of the nodeDescCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeDescCode(String value) {
        this.nodeDescCode = value;
    }

    /**
     * Gets the value of the childNodeDescCodeOrNodeDesc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the childNodeDescCodeOrNodeDesc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildNodeDescCodeOrNodeDesc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * {@link NodeDesc }
     * 
     * 
     */
    public List<Object> getChildNodeDescCodeOrNodeDesc() {
    	if(childNodeDescCodeOrNodeDesc == null) {
    		childNodeDescCodeOrNodeDesc = new ArrayList<Object>();
    	}
        return this.childNodeDescCodeOrNodeDesc;
    }
    
    public void setChildNodeDescCodeOrNodeDesc(
			List<Object> childNodeDescCodeOrNodeDesc) {
		this.childNodeDescCodeOrNodeDesc = childNodeDescCodeOrNodeDesc;
	}

	/**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the lengthCons property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLengthCons() {
        return lengthCons;
    }

    /**
     * Sets the value of the lengthCons property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLengthCons(String value) {
        this.lengthCons = value;
    }

    /**
     * Gets the value of the countCons property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountCons() {
        return countCons;
    }

    /**
     * Sets the value of the countCons property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountCons(String value) {
        this.countCons = value;
    }

    /**
     * Gets the value of the typeCons property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeCons() {
        return typeCons;
    }

    /**
     * Sets the value of the typeCons property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeCons(String value) {
        this.typeCons = value;
    }
    
    public String getNodePath() {
		return nodePath;
	}

	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}

	public String getIsSignature() {
		return isSignature;
	}

	public void setIsSignature(String isSignature) {
		this.isSignature = isSignature;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	/**
     * Gets the value of the nodeValueConsType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeValueConsType() {
        return nodeValueConsType;
    }

    /**
     * Sets the value of the nodeValueConsType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeValueConsType(String value) {
        this.nodeValueConsType = value;
    }

    /**
     * Gets the value of the nodeValueConsExpressions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeValueConsExpressions() {
        return nodeValueConsExpressions;
    }

    /**
     * Sets the value of the nodeValueConsExpressions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeValueConsExpressions(String value) {
        this.nodeValueConsExpressions = value;
    }

    /**
     * Gets the value of the javaField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJavaField() {
        return javaField;
    }

    /**
     * Sets the value of the javaField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJavaField(String value) {
        this.javaField = value;
    }

    /**
     * Gets the value of the fuzzy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuzzy() {
        return fuzzy;
    }

    /**
     * Sets the value of the fuzzy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuzzy(String value) {
        this.fuzzy = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }
    
    @Override
	void valueTransform(String attr) {
    	if("type".equals(attr)) {
    		if(type != null) {
    			if(valueMap.containsKey(type)) {
    				type = valueMap.get(type);
    			}
    			if("".equals(type)) {
    				type = null;
    			}
    		}
    	} else if("javaField".equals(attr)) {
    		if(javaField != null) {
    			if("".equals(javaField)) {
    				javaField = null;
    			}
    		}
    	} else if("typeCons".equals(attr)) {
    		if(typeCons != null) {
    			if(valueMap.containsKey("typeCons"+typeCons)) {
    				typeCons = valueMap.get("typeCons"+typeCons);
    			}
    			if("".equals(typeCons)) {
    				typeCons = null;
    			}
    		}
    	}
	}

	@Override
	void valueUnTransform(String attr) {
		if("type".equals(attr)) {
    		if(type != null) {
    			for(String key: valueMap.keySet()) {
    				if(valueMap.get(key) != null && valueMap.get(key).equals(type)) {
    					type = key;
    				}
    			}
    			if("".equals(type)) {
    				type = null;
    			}
    		}
    	} else if("javaField".equals(attr)) {
    		if(javaField != null) {
    			if("".equals(javaField)) {
    				javaField = null;
    			}
    		}
    	} else if("typeCons".equals(attr)) {
    		if(typeCons != null) {
    			for(String key: valueMap.keySet()) {
    				if(valueMap.get(key) != null && valueMap.get(key).equals(typeCons)) {
    					typeCons = key.replaceAll("typeCons", "");
    				}
    			}
    			if("".equals(typeCons)) {
    				typeCons = null;
    			}
    		}
    	}
	}

	@Override
	public boolean equals(Object obj){
		return super.equals(obj);
	}
	
	@Override
	public int hashCode(){
		return super. hashCode();
	}
}
