package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import com.asiainfo.integretion.o2p.servicemigration.common.jaxbAdapter.JaxbDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contract2AttrSpec", propOrder = {
    "contract2AttrSpecCode",
    "attrValues",
    "value",
    "name",
    "createDate",
    "status"
})
public class Contract2AttrSpec extends BasedBean {

    @XmlElement(required = true)
    protected String contract2AttrSpecCode;
    @XmlElementWrapper(name="attrValues")
    @XmlElement(name="attrValue")
    protected List<AttrValue> attrValues;
    @XmlElement(required = true)
    protected String value;
    protected String name;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    protected XMLGregorianCalendar createDate;
    @XmlElement(required = true)
    protected String status;

    /**
     * Gets the value of the contract2AttrSpecCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContract2AttrSpecCode() {
        return contract2AttrSpecCode;
    }

    /**
     * Sets the value of the contract2AttrSpecCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContract2AttrSpecCode(String value) {
        this.contract2AttrSpecCode = value;
    }
    
    public List<AttrValue> getAttrValues() {
		return attrValues;
	}

	public void setAttrValues(List<AttrValue> attrValues) {
		this.attrValues = attrValues;
	}

	/**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
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
		return super. hashCode();
	}
}
