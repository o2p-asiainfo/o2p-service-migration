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
@XmlType(name = "variableMap", propOrder = {
    "variableMapCode",
    "constantMappingTypeCode",
    "dataSource",
    "keyExpression",
    "valueExpression",
    "version",
    "status"
})
public class VariableMap extends BasedBean {

    @XmlElement(required = true)
    protected String variableMapCode;
    @XmlElement(required = true)
    protected String constantMappingTypeCode;
    @XmlElement(required = true)
    protected String dataSource;
    @XmlElement(required = true)
    protected String keyExpression;
    @XmlElement(required = true)
    protected String valueExpression;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    protected XMLGregorianCalendar version;
    @XmlElement(required = true)
    protected String status;

    /**
     * Gets the value of the variableMapCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariableMapCode() {
        return variableMapCode;
    }

    /**
     * Sets the value of the variableMapCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariableMapCode(String value) {
        this.variableMapCode = value;
    }

    /**
     * Gets the value of the constantMappingTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstantMappingTypeCode() {
        return constantMappingTypeCode;
    }

    /**
     * Sets the value of the constantMappingTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstantMappingTypeCode(String value) {
        this.constantMappingTypeCode = value;
    }

    /**
     * Gets the value of the dataSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * Sets the value of the dataSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSource(String value) {
        this.dataSource = value;
    }

    /**
     * Gets the value of the keyExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyExpression() {
        return keyExpression;
    }

    /**
     * Sets the value of the keyExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyExpression(String value) {
        this.keyExpression = value;
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
    
    public XMLGregorianCalendar getVersion() {
		return version;
	}

	public void setVersion(XMLGregorianCalendar version) {
		this.version = version;
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
