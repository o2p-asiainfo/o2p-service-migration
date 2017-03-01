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
@XmlType(name = "download", propOrder = {
    "call",
    "fileDir",
    "fileDirType",
    "fileSortType",
    "fileDownloadLimitNum",
    "messageRule",
    "fileTempDir"
})
public class Download extends BasedBean {

    @XmlElement(required = true)
    protected Call call;
    @XmlElement(required = true)
    protected String fileDir;
    @XmlElement(required = true)
    protected String fileDirType;
    protected String fileSortType;
    protected String fileDownloadLimitNum;
    protected String messageRule;
    protected String fileTempDir;

    /**
     * Gets the value of the call property.
     * 
     * @return
     *     possible object is
     *     {@link Call }
     *     
     */
    public Call getCall() {
        return call;
    }

    /**
     * Sets the value of the call property.
     * 
     * @param value
     *     allowed object is
     *     {@link Call }
     *     
     */
    public void setCall(Call value) {
        this.call = value;
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
     * Gets the value of the fileDirType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileDirType() {
        return fileDirType;
    }

    /**
     * Sets the value of the fileDirType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileDirType(String value) {
        this.fileDirType = value;
    }

    /**
     * Gets the value of the fileSortType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileSortType() {
        return fileSortType;
    }

    /**
     * Sets the value of the fileSortType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileSortType(String value) {
        this.fileSortType = value;
    }

    /**
     * Gets the value of the fileDownloadLimitNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileDownloadLimitNum() {
        return fileDownloadLimitNum;
    }

    /**
     * Sets the value of the fileDownloadLimitNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileDownloadLimitNum(String value) {
        this.fileDownloadLimitNum = value;
    }

    /**
     * Gets the value of the messageRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageRule() {
        return messageRule;
    }

    /**
     * Sets the value of the messageRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageRule(String value) {
        this.messageRule = value;
    }

    /**
     * Gets the value of the fileTempDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileTempDir() {
        return fileTempDir;
    }

    /**
     * Sets the value of the fileTempDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileTempDir(String value) {
        this.fileTempDir = value;
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
