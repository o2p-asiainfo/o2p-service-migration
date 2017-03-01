package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZIP_FILE", propOrder = {
    "tracingFlag",
    "dataLogFlag",
    "fileTargetPath",
    "zipTargetName",
    "zipType",
    "zipInvokedAction",
    "zipOrUnzip"
})
public class ZIPFILE extends BasedBean {

    @XmlElement(required = true)
    protected String tracingFlag;
    @XmlElement(required = true)
    protected String dataLogFlag;
    @XmlElement(required = true)
    protected String fileTargetPath;
    @XmlElement(required = true)
    protected String zipTargetName;
    @XmlElement(required = true)
    protected String zipType;
    @XmlElement(required = true)
    protected String zipInvokedAction;
    @XmlElement(required = true)
    protected String zipOrUnzip;

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
     * Gets the value of the fileTargetPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileTargetPath() {
        return fileTargetPath;
    }

    /**
     * Sets the value of the fileTargetPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileTargetPath(String value) {
        this.fileTargetPath = value;
    }

    /**
     * Gets the value of the zipTargetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipTargetName() {
        return zipTargetName;
    }

    /**
     * Sets the value of the zipTargetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipTargetName(String value) {
        this.zipTargetName = value;
    }

    /**
     * Gets the value of the zipType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipType() {
        return zipType;
    }

    /**
     * Sets the value of the zipType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipType(String value) {
        this.zipType = value;
    }

    /**
     * Gets the value of the zipInvokedAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipInvokedAction() {
        return zipInvokedAction;
    }

    /**
     * Sets the value of the zipInvokedAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipInvokedAction(String value) {
        this.zipInvokedAction = value;
    }

    /**
     * Gets the value of the zipOrUnzip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipOrUnzip() {
        return zipOrUnzip;
    }

    /**
     * Sets the value of the zipOrUnzip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipOrUnzip(String value) {
        this.zipOrUnzip = value;
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
