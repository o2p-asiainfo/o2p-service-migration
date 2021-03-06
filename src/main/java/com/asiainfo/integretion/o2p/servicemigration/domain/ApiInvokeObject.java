//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.21 at 03:27:25 com.asiainfo.integretion.o2p.serviceagent.cache.IMemcacheManageServ CST 
//


package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import com.asiainfo.integretion.o2p.servicemigration.common.annotation.BeforeMigration;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.ReferenceType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "apiInvokeObject", propOrder = {
    "apiInvokeObjectCode",
    "name",
    "comsumerComponentCode",
    "authenticationExpresses",
    "expressLogicRelation",
    "messageFlowOrCode",
    "apiInvokeObjectFlowControls",
    "logLevel",
    "status"
})
public class ApiInvokeObject extends BasedBean {

    @XmlElement(required = true)
    protected String apiInvokeObjectCode;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    @ReferenceType(value=Component.class)
    protected String comsumerComponentCode;
    @XmlElementWrapper(name="authenticationExpresses")
    @XmlElement(name="authenticationExpress",required=true)
    protected List<AuthenticationExpress> authenticationExpresses;
    @XmlElement(required = true)
    protected String expressLogicRelation;
    @XmlElements({
    	@XmlElement(name="messageFlowCode",type=String.class),
    	@XmlElement(name="messageFlow",type=MessageFlow.class)
    })
    @BeforeMigration
    protected Object messageFlowOrCode;
    @XmlElementWrapper(name="apiInvokeObjectFlowControls")
    @XmlElement(name="apiInvokeObjectFlowControl",required=true)
    protected List<ApiInvokeObjectFlowControl> apiInvokeObjectFlowControls;
    protected String logLevel;
    @XmlElement(required = true)
    protected String status;

    /**
     * Gets the value of the apiInvokeObjectCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApiInvokeObjectCode() {
        return apiInvokeObjectCode;
    }

    /**
     * Sets the value of the apiInvokeObjectCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApiInvokeObjectCode(String value) {
        this.apiInvokeObjectCode = value;
    }
    
    

    public String getExpressLogicRelation() {
		return expressLogicRelation;
	}

	public void setExpressLogicRelation(String expressLogicRelation) {
		this.expressLogicRelation = expressLogicRelation;
	}

	public List<AuthenticationExpress> getAuthenticationExpresses() {
		return authenticationExpresses;
	}

	public void setAuthenticationExpresses(
			List<AuthenticationExpress> authenticationExpresses) {
		this.authenticationExpresses = authenticationExpresses;
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
     * Gets the value of the comsumerComponentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComsumerComponentCode() {
        return comsumerComponentCode;
    }

    /**
     * Sets the value of the comsumerComponentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComsumerComponentCode(String value) {
        this.comsumerComponentCode = value;
    }
    
    
    
    public Object getMessageFlowOrCode() {
		return messageFlowOrCode;
	}

	public void setMessageFlowOrCode(Object messageFlowOrCode) {
		this.messageFlowOrCode = messageFlowOrCode;
	}
	
    public List<ApiInvokeObjectFlowControl> getApiInvokeObjectFlowControls() {
		return apiInvokeObjectFlowControls;
	}

	public void setApiInvokeObjectFlowControls(
			List<ApiInvokeObjectFlowControl> apiInvokeObjectFlowControls) {
		this.apiInvokeObjectFlowControls = apiInvokeObjectFlowControls;
	}
	
	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
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
		if("logLevel".equals(attr)) {
			if("".equals(logLevel)) {
				logLevel = null;
			}
		}
	}

	@Override
	void valueUnTransform(String attr) {
		if("logLevel".equals(attr)) {
			if("".equals(logLevel)) {
				logLevel = null;
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
