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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.asiainfo.integretion.o2p.servicemigration.common.annotation.BeforeMigration;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.ReferenceType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "apiType", propOrder = {
    "apiCode",
    "name",
    "version",
    "method",
    "type",
    "contractVersionCode",
    "technologyImplements",
    "apiInvokeObjects",
    "serviceCatalogCodes",
    "messageFlowOrCode",
    "status"
})
public class Api extends BasedBean {
	@XmlTransient
	protected int apiId;
    @XmlElement(required = true)
    protected String apiCode;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required=true)
    protected String version;
    protected String method;

	protected String type;
    @ReferenceType(ContractVersion.class)
    protected String contractVersionCode;
    @XmlElementWrapper(name="technologyImplements")
    @XmlElement(required = true,name="technologyImplement")
    protected List<TechnologyImplement> technologyImplements;
    @XmlElementWrapper(name="apiInvokeObjects")
    @XmlElement(required = true,name="apiInvokeObject")
    protected List<ApiInvokeObject> apiInvokeObjects;
    @XmlElementWrapper(name="serviceCatalogCodes")
    @XmlElement(name="serviceCatalogCode")
    protected List<String> serviceCatalogCodes;
    @XmlElements({
    	@XmlElement(name="defaultMessageFlowCode",type=String.class,required=true),
    	@XmlElement(name="defaultMessageFlow",type=MessageFlow.class,required=true)
    })
    @BeforeMigration
    protected Object messageFlowOrCode;
    @XmlElement(required = true)
    protected String status;
    
    public int getApiId() {
		return apiId;
	}

	public void setApiId(int apiId) {
		this.apiId = apiId;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

    public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	/**
     * Gets the value of the apiCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApiCode() {
        return apiCode;
    }

    /**
     * Sets the value of the apiCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApiCode(String value) {
        this.apiCode = value;
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
    
    public String getContractVersionCode() {
		return contractVersionCode;
	}

	public void setContractVersionCode(String contractVersionCode) {
		this.contractVersionCode = contractVersionCode;
	}

	public List<TechnologyImplement> getTechnologyImplements() {
		return technologyImplements;
	}

	public void setTechnologyImplements(
			List<TechnologyImplement> technologyImplements) {
		this.technologyImplements = technologyImplements;
	}

	public List<ApiInvokeObject> getApiInvokeObjects() {
		return apiInvokeObjects;
	}

	public void setApiInvokeObjects(List<ApiInvokeObject> apiInvokeObjects) {
		this.apiInvokeObjects = apiInvokeObjects;
	}

	public List<String> getServiceCatalogCodes() {
		return serviceCatalogCodes;
	}

	public void setServiceCatalogCodes(List<String> serviceCatalogCodes) {
		this.serviceCatalogCodes = serviceCatalogCodes;
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
    
    
    
    public Object getMessageFlowOrCode() {
		return messageFlowOrCode;
	}

	public void setMessageFlowOrCode(Object messageFlowOrCode) {
		this.messageFlowOrCode = messageFlowOrCode;
	}

	@Override
	void valueTransform(String attr) {
		if("type".equals(attr)) {
			if("0".equals(type)) {
				type = "atom";
			} else if("1".equals(type)) {
				type = "assemble";
			}
		}
	}

	@Override
	void valueUnTransform(String attr) {
		if("type".equals(attr)) {
			if("atom".equals(type)) {
				type = "0";
			} else if("assemble".equals(type)) {
				type = "1";
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
