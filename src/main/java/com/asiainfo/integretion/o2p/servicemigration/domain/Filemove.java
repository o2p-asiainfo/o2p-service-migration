package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filemove", propOrder = {
    "call",
    "backupFileDir",
    "remoteCallUrlId",
    "fileFormats",
    "fileDir",
    "fileDirType",
    "filePostReadAction"
})
public class Filemove extends BasedBean {

    @XmlElement(required = true)
    protected Call call;
    protected String backupFileDir;
    protected String remoteCallUrlId;
    protected String fileFormats;
    @XmlElement(required = true)
    protected String fileDir;
    @XmlElement(required = true)
    protected String fileDirType;
    protected String filePostReadAction;

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
     * Gets the value of the backupFileDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackupFileDir() {
        return backupFileDir;
    }

    /**
     * Sets the value of the backupFileDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackupFileDir(String value) {
        this.backupFileDir = value;
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

    /**
     * Gets the value of the fileFormats property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileFormats() {
        return fileFormats;
    }

    /**
     * Sets the value of the fileFormats property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileFormats(String value) {
        this.fileFormats = value;
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
     * Gets the value of the filePostReadAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilePostReadAction() {
        return filePostReadAction;
    }

    /**
     * Sets the value of the filePostReadAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilePostReadAction(String value) {
        this.filePostReadAction = value;
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
