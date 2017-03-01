package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import com.asiainfo.integretion.o2p.servicemigration.common.jaxbAdapter.JaxbDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "technicalImplementationControlStrategy", propOrder = {
    "technicalImplementationControlStrategyCode",
    "controlType",
    "controlStatus",
    "controlCycle",
    "controlCycleValue",
    "controlValue",
    "controlAction",
    "createTime",
    "usealbeStatus"
})
public class TechnicalImplementationControlStrategy extends BasedBean {

    @XmlElement(required = true)
    protected String technicalImplementationControlStrategyCode;
    @XmlElement(required = true)
    protected String controlType;
    @XmlElement(required = true)
    protected String controlStatus;
    @XmlElement(required = true)
    protected String controlCycle;
    protected long controlCycleValue;
    protected long controlValue;
    @XmlElement(required = true)
    protected String controlAction;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    protected XMLGregorianCalendar createTime;
    @XmlElement(required = true)
    protected String usealbeStatus;

    /**
     * Gets the value of the technicalImplementationControlStrategyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnicalImplementationControlStrategyCode() {
        return technicalImplementationControlStrategyCode;
    }

    /**
     * Sets the value of the technicalImplementationControlStrategyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnicalImplementationControlStrategyCode(String value) {
        this.technicalImplementationControlStrategyCode = value;
    }

    /**
     * Gets the value of the controlType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlType() {
        return controlType;
    }

    /**
     * Sets the value of the controlType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlType(String value) {
        this.controlType = value;
    }

    /**
     * Gets the value of the controlStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlStatus() {
        return controlStatus;
    }

    /**
     * Sets the value of the controlStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlStatus(String value) {
        this.controlStatus = value;
    }

    /**
     * Gets the value of the controlCycle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlCycle() {
        return controlCycle;
    }

    /**
     * Sets the value of the controlCycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlCycle(String value) {
        this.controlCycle = value;
    }

    /**
     * Gets the value of the controlCycleValue property.
     * 
     */
    public long getControlCycleValue() {
        return controlCycleValue;
    }

    /**
     * Sets the value of the controlCycleValue property.
     * 
     */
    public void setControlCycleValue(long value) {
        this.controlCycleValue = value;
    }

    /**
     * Gets the value of the controlValue property.
     * 
     */
    public long getControlValue() {
        return controlValue;
    }

    /**
     * Sets the value of the controlValue property.
     * 
     */
    public void setControlValue(long value) {
        this.controlValue = value;
    }

    /**
     * Gets the value of the controlAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlAction() {
        return controlAction;
    }

    /**
     * Sets the value of the controlAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlAction(String value) {
        this.controlAction = value;
    }

    /**
     * Gets the value of the createTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTime(XMLGregorianCalendar value) {
        this.createTime = value;
    }

    /**
     * Gets the value of the usealbeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsealbeStatus() {
        return usealbeStatus;
    }

    /**
     * Sets the value of the usealbeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsealbeStatus(String value) {
        this.usealbeStatus = value;
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
