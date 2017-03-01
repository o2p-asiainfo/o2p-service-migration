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
@XmlType(name = "nodeValueAdapterReq", propOrder = {
    "nodeValueAdapterReqCode",
    "tarNodeDescCode",
    "nodeValueSrouceType",
    "valueExpression",
    "script",
    "triggerExpression",
    "status",
    "createDate"
})
public class NodeValueAdapterReq extends BasedBean {

    @XmlElement(required = true)
    protected String nodeValueAdapterReqCode;
    @XmlElement(required = true)
    @ReferenceType(value=NodeDesc.class)
    protected String tarNodeDescCode;
    @XmlElement(required = true)
    protected String nodeValueSrouceType;
    @XmlElement(required = true)
    protected String valueExpression;
    protected String script;
    protected String triggerExpression;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    protected XMLGregorianCalendar createDate;

    /**
     * Gets the value of the nodeValueAdapterReqCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeValueAdapterReqCode() {
        return nodeValueAdapterReqCode;
    }

    /**
     * Sets the value of the nodeValueAdapterReqCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeValueAdapterReqCode(String value) {
        this.nodeValueAdapterReqCode = value;
    }
    
    

    public String getTarNodeDescCode() {
		return tarNodeDescCode;
	}

	public void setTarNodeDescCode(String tarNodeDescCode) {
		this.tarNodeDescCode = tarNodeDescCode;
	}

	/**
     * Gets the value of the nodeValueSrouceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeValueSrouceType() {
        return nodeValueSrouceType;
    }

    /**
     * Sets the value of the nodeValueSrouceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeValueSrouceType(String value) {
        this.nodeValueSrouceType = value;
    }

    /**
     * Gets the value of the valueExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueExpression() {
        return valueExpression;
    }

    /**
     * Sets the value of the valueExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueExpression(String value) {
        this.valueExpression = value;
    }

    /**
     * Gets the value of the script property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScript() {
        return script;
    }

    /**
     * Sets the value of the script property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScript(String value) {
        this.script = value;
    }

    /**
     * Gets the value of the triggerExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTriggerExpression() {
        return triggerExpression;
    }

    /**
     * Sets the value of the triggerExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTriggerExpression(String value) {
        this.triggerExpression = value;
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
