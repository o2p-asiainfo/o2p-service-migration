package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VALIDATE", propOrder = {
    "requestOrResponse"
})
public class VALIDATE  extends BasedBean{

    @XmlElement(required = true)
    protected String requestOrResponse;

    /**
     * Gets the value of the requestOrResponse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestOrResponse() {
        return requestOrResponse;
    }

    /**
     * Sets the value of the requestOrResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestOrResponse(String value) {
        this.requestOrResponse = value;
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
