package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "technologyImplementFlowControlType", propOrder = {
    "flowControlCode",
    "flowControlPolicyCode",
    "cutmsValue",
    "effectStatus",
    "cycleType",
    "cycleValue",
    "status"
})
public class TechnologyImplementFlowControl extends BasedBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(required = true)
    protected String flowControlCode;
    @XmlElement(required = true)
    protected String flowControlPolicyCode;
    @XmlElement(required = true)
    protected String cutmsValue;
    @XmlElement(required = true)
    protected String effectStatus;
    @XmlElement(required = true)
    protected String cycleType;
    @XmlElement(required = true)
    protected String cycleValue;
    @XmlElement(required = true)
    protected String status;

    /**
     * Gets the value of the flowControlCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlowControlCode() {
        return flowControlCode;
    }

    /**
     * Sets the value of the flowControlCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlowControlCode(String value) {
        this.flowControlCode = value;
    }

    /**
     * Gets the value of the flowControlPolicyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlowControlPolicyCode() {
        return flowControlPolicyCode;
    }

    /**
     * Sets the value of the flowControlPolicyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlowControlPolicyCode(String value) {
        this.flowControlPolicyCode = value;
    }

    /**
     * Gets the value of the cutmsValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCutmsValue() {
        return cutmsValue;
    }

    /**
     * Sets the value of the cutmsValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCutmsValue(String value) {
        this.cutmsValue = value;
    }

    /**
     * Gets the value of the effectStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectStatus() {
        return effectStatus;
    }

    /**
     * Sets the value of the effectStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectStatus(String value) {
        this.effectStatus = value;
    }

    /**
     * Gets the value of the cycleType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCycleType() {
        return cycleType;
    }

    /**
     * Sets the value of the cycleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCycleType(String value) {
        this.cycleType = value;
    }

    /**
     * Gets the value of the cycleValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCycleValue() {
        return cycleValue;
    }

    /**
     * Sets the value of the cycleValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCycleValue(String value) {
        this.cycleValue = value;
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
		return "flowControlCode";
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
