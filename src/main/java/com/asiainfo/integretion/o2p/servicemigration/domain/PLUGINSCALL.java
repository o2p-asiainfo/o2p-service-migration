package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PLUGINS_CALL", propOrder = {
    "pluginsCallUrl"
})
public class PLUGINSCALL extends BasedBean {

    @XmlElement(required = true)
    protected String pluginsCallUrl;

    /**
     * Gets the value of the pluginsCallUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPluginsCallUrl() {
        return pluginsCallUrl;
    }

    /**
     * Sets the value of the pluginsCallUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPluginsCallUrl(String value) {
        this.pluginsCallUrl = value;
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
