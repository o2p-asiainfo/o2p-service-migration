package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import com.asiainfo.integretion.o2p.servicemigration.common.annotation.ReferenceType;
import com.asiainfo.integretion.o2p.servicemigration.common.jaxbAdapter.JaxbDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nodeDescMaper", propOrder = {
    "nodeDescMaperCode",
    "sourceNodeDescCode",
    "targetNodeDescCode",
    "actionType",
    "createDate"
})
public class NodeDescMaper extends BasedBean {

    @XmlElement(required = true)
    protected String nodeDescMaperCode;
    @XmlElement(required = true,name="sourceNodeDescCode")
    @ReferenceType(value=NodeDesc.class)
    protected String sourceNodeDescCode;
    @XmlElement(required = true)
    @ReferenceType(value=NodeDesc.class)
    protected String targetNodeDescCode;
    @XmlElement(required = true)
    protected String actionType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    protected XMLGregorianCalendar createDate;

    /**
     * Gets the value of the nodeDescMaperCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeDescMaperCode() {
        return nodeDescMaperCode;
    }

    /**
     * Sets the value of the nodeDescMaperCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeDescMaperCode(String value) {
        this.nodeDescMaperCode = value;
    }
    
	public String getSourceNodeDescCode() {
		return sourceNodeDescCode;
	}

	public void setSourceNodeDescCode(String sourceNodeDescCode) {
		this.sourceNodeDescCode = sourceNodeDescCode;
	}

	/**
     * Gets the value of the targetNodeDescCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetNodeDescCode() {
        return targetNodeDescCode;
    }

    /**
     * Sets the value of the targetNodeDescCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetNodeDescCode(String value) {
        this.targetNodeDescCode = value;
    }

    /**
     * Gets the value of the actionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Sets the value of the actionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionType(String value) {
        this.actionType = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
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
