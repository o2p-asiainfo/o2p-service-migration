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
@XmlType(name = "FLAT_FILE_READER", propOrder = {
    "tracingFlag",
    "dataLogFlag",
    "fileDir",
    "threadNumLimit"
})
public class FLATFILEREADER extends BasedBean {

    @XmlElement(required = true)
    protected String tracingFlag;
    @XmlElement(required = true)
    protected String dataLogFlag;
    @XmlElement(required = true)
    protected String fileDir;
    @XmlElement(required = true)
    protected String threadNumLimit;

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
     * Gets the value of the fileDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileDir() {
        return fileDir;
    }

    /**
     * Sets the value of the fileDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileDir(String value) {
        this.fileDir = value;
    }

    /**
     * Gets the value of the threadNumLimit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThreadNumLimit() {
        return threadNumLimit;
    }

    /**
     * Sets the value of the threadNumLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThreadNumLimit(String value) {
        this.threadNumLimit = value;
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
