package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.asiainfo.integretion.o2p.servicemigration.common.annotation.ReferenceType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "technologyImplementType", propOrder = {
    "technologyImplementCode",
    "producerComponentCode",
    "name",
    "attrValues",
    "communicationPotocolCode",
    "technologyImplementFlowControls",
    "technologyImplementNodes",
    "status"
})
public class TechnologyImplement extends BasedBean{

    @XmlElement(required = true)
    protected String technologyImplementCode;
    @XmlElement(required = true)
    @ReferenceType(value=Component.class)
    protected String producerComponentCode;
    @XmlElement(required = true)
    protected String name;
    @XmlElementWrapper(name="attrValues")
    @XmlElement(name="attrValue")
    protected List<AttrValue> attrValues;
    @XmlElement(required = true)
    protected String communicationPotocolCode;
    @XmlElement(required=true,name="technologyImplementFlowControl")
    @XmlElementWrapper(name="technologyImplementFlowControls")
    protected List<TechnologyImplementFlowControl> technologyImplementFlowControls;
    @XmlElement(required=true,name="technologyImplementNode")
    @XmlElementWrapper(name="technologyImplementNodes")
    protected List<TechnologyImplementNode> technologyImplementNodes;
    @XmlElement(required = true)
    protected String status;

    /**
     * Gets the value of the technologyImplementCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnologyImplementCode() {
        return technologyImplementCode;
    }

    /**
     * Sets the value of the technologyImplementCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnologyImplementCode(String value) {
        this.technologyImplementCode = value;
    }
    
    public List<TechnologyImplementFlowControl> getTechnologyImplementFlowControls() {
		return technologyImplementFlowControls;
	}

	public void setTechnologyImplementFlowControls(
			List<TechnologyImplementFlowControl> technologyImplementFlowControls) {
		this.technologyImplementFlowControls = technologyImplementFlowControls;
	}
	
    public List<TechnologyImplementNode> getTechnologyImplementNodes() {
		return technologyImplementNodes;
	}

	public void setTechnologyImplementNodes(
			List<TechnologyImplementNode> technologyImplementNodes) {
		this.technologyImplementNodes = technologyImplementNodes;
	}

	/**
     * Gets the value of the producerComponentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducerComponentCode() {
        return producerComponentCode;
    }

    /**
     * Sets the value of the producerComponentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducerComponentCode(String value) {
        this.producerComponentCode = value;
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

    public List<AttrValue> getAttrValues() {
		return attrValues;
	}

	public void setAttrValues(List<AttrValue> attrValues) {
		this.attrValues = attrValues;
	}

	/**
     * Gets the value of the communicationPotocolCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommunicationPotocolCode() {
        return communicationPotocolCode;
    }

    /**
     * Sets the value of the communicationPotocolCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommunicationPotocolCode(String value) {
        this.communicationPotocolCode = value;
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
	public boolean equals(Object obj){
		return super.equals(obj);
	}

	@Override
	public int hashCode(){
		return super.hashCode();
	}
}
