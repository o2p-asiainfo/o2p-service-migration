package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XML_FILE_READER", propOrder = {
    "tracingFlag",
    "dataLogFlag",
    "fileDir",
    "rootElementName"
})
public class XMLFILEREADER extends BasedBean {

    @XmlElement(required = true)
    protected String tracingFlag;
    @XmlElement(required = true)
    protected String dataLogFlag;
    @XmlElement(required = true)
    protected String fileDir;
    @XmlElement(required = true)
    protected String rootElementName;

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
     * Gets the value of the rootElementName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRootElementName() {
        return rootElementName;
    }

    /**
     * Sets the value of the rootElementName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRootElementName(String value) {
        this.rootElementName = value;
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
