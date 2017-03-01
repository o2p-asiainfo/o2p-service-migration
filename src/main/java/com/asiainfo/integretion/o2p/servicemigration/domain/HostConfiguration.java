package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hostConfiguration", propOrder = {
    "hostConfigurationCode",
    "hostName",
    "hostType",
    "hostIp",
    "fileSystemType",
    "ftpPort",
    "ftpType",
    "hostDescription",
    "fileHome"
})
public class HostConfiguration extends BasedBean {

    @XmlElement(required = true)
    protected String hostConfigurationCode;
    @XmlElement(required = true)
    protected String hostName;
    @XmlElement(required = true)
    protected String hostType;
    @XmlElement(required = true)
    protected String hostIp;
    protected String fileSystemType;
    @XmlElement(required = true)
    protected String ftpPort;
    @XmlElement(required = true)
    protected String ftpType;
    @XmlElement(required = true)
    protected String hostDescription;
    protected String fileHome;

    /**
     * Gets the value of the hostConfigurationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostConfigurationCode() {
        return hostConfigurationCode;
    }

    /**
     * Sets the value of the hostConfigurationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostConfigurationCode(String value) {
        this.hostConfigurationCode = value;
    }

    /**
     * Gets the value of the hostName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Sets the value of the hostName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostName(String value) {
        this.hostName = value;
    }

    /**
     * Gets the value of the hostType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostType() {
        return hostType;
    }

    /**
     * Sets the value of the hostType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostType(String value) {
        this.hostType = value;
    }

    /**
     * Gets the value of the hostIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostIp() {
        return hostIp;
    }

    /**
     * Sets the value of the hostIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostIp(String value) {
        this.hostIp = value;
    }

    /**
     * Gets the value of the fileSystemType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileSystemType() {
        return fileSystemType;
    }

    /**
     * Sets the value of the fileSystemType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileSystemType(String value) {
        this.fileSystemType = value;
    }

    /**
     * Gets the value of the ftpPort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFtpPort() {
        return ftpPort;
    }

    /**
     * Sets the value of the ftpPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFtpPort(String value) {
        this.ftpPort = value;
    }

    /**
     * Gets the value of the ftpType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFtpType() {
        return ftpType;
    }

    /**
     * Sets the value of the ftpType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFtpType(String value) {
        this.ftpType = value;
    }

    /**
     * Gets the value of the hostDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostDescription() {
        return hostDescription;
    }

    /**
     * Sets the value of the hostDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostDescription(String value) {
        this.hostDescription = value;
    }

    /**
     * Gets the value of the fileHome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileHome() {
        return fileHome;
    }

    /**
     * Sets the value of the fileHome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileHome(String value) {
        this.fileHome = value;
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
