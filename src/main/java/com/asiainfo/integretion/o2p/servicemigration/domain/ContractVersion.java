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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.asiainfo.integretion.o2p.servicemigration.common.annotation.BeforeMigration;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.Parent;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contractVersionType", propOrder = {
    "version",
    "checkFlag",
    "baseContractVersion",
    "contract",
    "requestContractFormat",
    "responceContractFormat",
    "contractDocument",
    "description",
    "status"
})
public class ContractVersion extends BasedBean {

	@XmlTransient
	protected int contractVersionId;
    @XmlElement(required = true)
    protected String version;
    @XmlElement(required = true)
    protected String checkFlag;
    @Parent
    protected ContractVersion baseContractVersion;
	@XmlElement(required = true)
	@BeforeMigration
    protected Contract contract;
    @XmlElement(required=true)
    protected ContractFormatChoice requestContractFormat;
    @XmlElement(required=true)
    protected ContractFormatChoice responceContractFormat;
    protected ContractDocument contractDocument;
    public ContractDocument getContractDocument() {
    	if(contractDocument!=null){
			contractDocument.setContractVersion(version);
		}
		return contractDocument;
	}

	protected String description;
    @XmlElement(required = true)
    protected String status;

    

    public int getContractVersionId() {
		return contractVersionId;
	}

	public void setContractVersionId(int contractVersionId) {
		this.contractVersionId = contractVersionId;
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

    /**
     * Gets the value of the checkFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckFlag() {
        return checkFlag;
    }

    /**
     * Sets the value of the checkFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckFlag(String value) {
        this.checkFlag = value;
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
     * Gets the value of the contract property.
     * 
     * @return
     *     possible object is
     *     {@link Contract }
     *     
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * Sets the value of the contract property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contract }
     *     
     */
    public void setContract(Contract value) {
        this.contract = value;
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
    
    public ContractFormatChoice getRequestContractFormat() {
		return requestContractFormat;
	}

	public void setRequestContractFormat(ContractFormatChoice requestContractFormat) {
		this.requestContractFormat = requestContractFormat;
	}

	public ContractFormatChoice getResponceContractFormat() {
		return responceContractFormat;
	}

	public void setResponceContractFormat(
			ContractFormatChoice responceContractFormat) {
		this.responceContractFormat = responceContractFormat;
	}

	public ContractVersion getBaseContractVersion() {
		return baseContractVersion;
	}

	public void setBaseContractVersion(ContractVersion baseContractVersion) {
		this.baseContractVersion = baseContractVersion;
	}

	public void setContractDocument(ContractDocument contractDocument) {
		if(contractDocument!=null){
			contractDocument.setContractVersion(version);
		}
		this.contractDocument = contractDocument;
	}
    
    @Override
	void valueTransform(String attr) {
    	if("checkFlag".equals(attr)) {
			if(!"Y".equals(checkFlag) && !"N".equals(checkFlag)) {
				checkFlag = null;
			}
    	}
	}

	@Override
	public String getCode() {
		return "version";
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