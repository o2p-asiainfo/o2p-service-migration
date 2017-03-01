package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filepublish", propOrder = {
    "remoteCallUrlId"
})
public class Filepublish extends BasedBean {

    @XmlElement(required = true)
    protected String remoteCallUrlId;

    /**
     * Gets the value of the remoteCallUrlId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoteCallUrlId() {
        return remoteCallUrlId;
    }

    /**
     * Sets the value of the remoteCallUrlId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoteCallUrlId(String value) {
        this.remoteCallUrlId = value;
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
