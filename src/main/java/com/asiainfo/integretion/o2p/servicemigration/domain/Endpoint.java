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
import javax.xml.bind.annotation.XmlType;

import org.springframework.util.StringUtils;

import com.asiainfo.integretion.o2p.servicemigration.common.annotation.BeforeMigration;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "endpointType", propOrder = {
    "endpointCode",
    "endpointSpec",
    "endpointName",
    "inDataTypeCode",
    "outDataTypeCode",
    "inTrace",
    "outTrace",
    "inLog",
    "outLog",
    "attrValues",
    "mapCode",
    "endpointDesc",
    "status"
})
public class Endpoint extends BasedBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(required = true)
    protected String endpointCode;
    @XmlElement(required = true)
    @BeforeMigration
    protected EndpointSpec endpointSpec;
    @XmlElement(required = true)
    protected String endpointName;
    protected String inDataTypeCode;
    protected String outDataTypeCode;
    protected String inTrace;
    protected String outTrace;
    protected String inLog;
    protected String outLog;
    @XmlElementWrapper(name="attrValues")
    @XmlElement(name="attrValue")
    protected List<AttrValue> attrValues;
    protected String mapCode;
    protected String endpointDesc;
    @XmlElement(required = true)
    protected String status;

    /**
     * Gets the value of the endpointCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndpointCode() {
        return endpointCode;
    }

    /**
     * Sets the value of the endpointCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndpointCode(String value) {
        this.endpointCode = value;
    }
    
    

    public String getInDataTypeCode() {
		return inDataTypeCode;
	}

	public void setInDataTypeCode(String inDataTypeCode) {
		this.inDataTypeCode = inDataTypeCode;
	}

	public String getOutDataTypeCode() {
		return outDataTypeCode;
	}

	public void setOutDataTypeCode(String outDataTypeCode) {
		this.outDataTypeCode = outDataTypeCode;
	}

	/**
     * Gets the value of the endpointSpec property.
     * 
     * @return
     *     possible object is
     *     {@link EndpointSpec }
     *     
     */
    public EndpointSpec getEndpointSpec() {
        return endpointSpec;
    }

    /**
     * Sets the value of the endpointSpec property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndpointSpec }
     *     
     */
    public void setEndpointSpec(EndpointSpec value) {
        this.endpointSpec = value;
    }

    /**
     * Gets the value of the endpointName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndpointName() {
        return endpointName;
    }

    /**
     * Sets the value of the endpointName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndpointName(String value) {
        this.endpointName = value;
    }

    /**
     * Gets the value of the inTrace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInTrace() {
        return inTrace;
    }

    /**
     * Sets the value of the inTrace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInTrace(String value) {
        this.inTrace = value;
    }
    
    /**
     * Gets the value of the outTrace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutTrace() {
        return outTrace;
    }

    /**
     * Sets the value of the outTrace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutTrace(String value) {
        this.outTrace = value;
    }

    /**
     * Gets the value of the inLog property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInLog() {
        return inLog;
    }

    /**
     * Sets the value of the inLog property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInLog(String value) {
        this.inLog = value;
    }

    /**
     * Gets the value of the outLog property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutLog() {
        return outLog;
    }

    /**
     * Sets the value of the outLog property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutLog(String value) {
        this.outLog = value;
    }

    public List<AttrValue> getAttrValues() {
		return attrValues;
	}

	public void setAttrValues(List<AttrValue> attrValues) {
		this.attrValues = attrValues;
	}

	/**
     * Gets the value of the endpointDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndpointDesc() {
        return endpointDesc;
    }

    /**
     * Sets the value of the endpointDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndpointDesc(String value) {
        this.endpointDesc = value;
    }
    
    public String getMapCode() {
		return mapCode;
	}

	public void setMapCode(String mapCode) {
		this.mapCode = mapCode;
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
		if("status".equals(attr)) {
			if(!StringUtils.hasText(status)) {
				status = "A";
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