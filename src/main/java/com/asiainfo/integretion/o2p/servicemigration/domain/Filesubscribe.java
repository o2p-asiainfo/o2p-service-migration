package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filesubscribe", propOrder = {
    "tracingFlag",
    "dataLogFlag",
    "fileNamePattern",
    "remoteCallUrlId"
})
public class Filesubscribe extends BasedBean {

    @XmlElement(required = true)
    protected String tracingFlag;
    @XmlElement(required = true)
    protected String dataLogFlag;
    @XmlElement(required = true)
    protected String fileNamePattern;
    @XmlElement(required = true)
    protected String remoteCallUrlId;

    /**
     * Gets the value of the tracingFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTracingFlag() {
        return tracingFlag;
    }

    /**
     * Sets the value of the tracingFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTracingFlag(String value) {
        this.tracingFlag = value;
    }

    /**
     * Gets the value of the dataLogFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataLogFlag() {
        return dataLogFlag;
    }

    /**
     * Sets the value of the dataLogFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataLogFlag(String value) {
        this.dataLogFlag = value;
    }

    /**
     * Gets the value of the fileNamePattern property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileNamePattern() {
        return fileNamePattern;
    }

    /**
     * Sets the value of the fileNamePattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileNamePattern(String value) {
        this.fileNamePattern = value;
    }

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
