package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transScript2Param", propOrder = {
    "transScript2ParamCode",
    "variableMap"
})
public class TransScript2Param extends BasedBean {

    @XmlElement(required = true)
    protected String transScript2ParamCode;
    @XmlElement(required = true)
    protected VariableMap variableMap;

    /**
     * Gets the value of the transScript2ParamCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransScript2ParamCode() {
        return transScript2ParamCode;
    }

    /**
     * Sets the value of the transScript2ParamCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransScript2ParamCode(String value) {
        this.transScript2ParamCode = value;
    }

    /**
     * Gets the value of the variableMap property.
     * 
     * @return
     *     possible object is
     *     {@link VariableMap }
     *     
     */
    public VariableMap getVariableMap() {
        return variableMap;
    }

    /**
     * Sets the value of the variableMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link VariableMap }
     *     
     */
    public void setVariableMap(VariableMap value) {
        this.variableMap = value;
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
