//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.21 at 03:27:25 com.asiainfo.integretion.o2p.serviceagent.cache.IMemcacheManageServ CST 
//


package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.asiainfo.integretion.o2p.servicemigration.common.annotation.BeforeMigration;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.Parent;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contractDocumentType", propOrder = {
    "baseContractDocument",
    "name",
    "resourceAliss",
    "version",
    "fileShareOrCode",
    "type",
    "status"
})
public class ContractDocument extends BasedBean {
	@Parent
    @XmlElement(required = true)
    protected ContractDocument baseContractDocument;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String resourceAliss;
    @XmlElement(required = true)
    protected String version;
    @XmlElements({
    	@XmlElement(name="fileShareCode",type=String.class),
    	@XmlElement(name="fileShare", type=FileShare.class)
    })
    @BeforeMigration
    protected Object fileShareOrCode;
    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected String status;
    
    @XmlTransient
    protected String contractVersion;
    @XmlTransient
    protected boolean compareContractVersion = true;
    
    public boolean isCompareContractVersion() {
		return compareContractVersion;
	}

	public void setCompareContractVersion(boolean compareContractVersion) {
		this.compareContractVersion = compareContractVersion;
	}

	public String getContractVersion() {
		return contractVersion;
	}

	public void setContractVersion(String contractVersion) {
		this.contractVersion = contractVersion;
	}

	/**
     * Gets the value of the baseContractDocument property.
     * 
     * @return
     *     possible object is
     *     {@link ContractDocument }
     *     
     */
    public ContractDocument getBaseContractDocument() {
        return baseContractDocument;
    }

    /**
     * Sets the value of the baseContractDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractDocument }
     *     
     */
    public void setBaseContractDocument(ContractDocument value) {
        this.baseContractDocument = value;
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
     * Gets the value of the resourceAliss property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public String getResourceAliss() {
        return resourceAliss;
    }

    /**
     * Sets the value of the resourceAliss property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setResourceAliss(String value) {
        this.resourceAliss = value;
    }
    
    

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }
    
	public Object getFileShareOrCode() {
		return fileShareOrCode;
	}

	public void setFileShareOrCode(Object fileShareOrCode) {
		this.fileShareOrCode = fileShareOrCode;
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
	public String getCode() {
		return "resourceAliss";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(compareContractVersion){
			return super.equals(obj) && this.contractVersion.equals(((ContractDocument)obj).getContractVersion());
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode(){
		return super. hashCode();
	}
}
