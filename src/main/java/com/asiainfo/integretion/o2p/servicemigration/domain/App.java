//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.21 at 03:27:25 com.asiainfo.integretion.o2p.serviceagent.cache.IMemcacheManageServ CST 
//


package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "app", propOrder = {
    "summary",
    "url",
    "type",
    "oauthType",
    "tokenEnabledTime",
    "appKey",
    "description"
})
public class App extends BasedBean {

    protected String summary;
    protected String url;
    @XmlElement(required = true)
    protected String type;
    protected String oauthType;
    protected Integer tokenEnabledTime;
    protected String appKey;
    protected String description;

    /**
     * Gets the value of the summary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummary(String value) {
        this.summary = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the oauthType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOauthType() {
        return oauthType;
    }

    /**
     * Sets the value of the oauthType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOauthType(String value) {
        this.oauthType = value;
    }

    /**
     * Gets the value of the tokenEnabledTime property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTokenEnabledTime() {
        return tokenEnabledTime;
    }

    /**
     * Sets the value of the tokenEnabledTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTokenEnabledTime(Integer value) {
        this.tokenEnabledTime = value;
    }

    /**
     * Gets the value of the appKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * Sets the value of the appKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppKey(String value) {
        this.appKey = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

	@Override
	public String getCode() {
		return "appKey";
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
