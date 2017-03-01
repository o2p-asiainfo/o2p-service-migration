package com.asiainfo.integretion.o2p.servicemigration.domain;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import com.asiainfo.integretion.o2p.servicemigration.common.jaxbAdapter.JaxbDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileRelocationServiceInvocationInstance", propOrder = {
    "fileRelocationServiceInvocationInstanceCode",
    "fileDirectory",
    "filefilterExpression",
    "batch",
    "fileCheck",
    "suffixFormat",
    "fileAlgorithm",
    "originalFileToDelete",
    "reqOrRsp",
    "createTime",
    "status",
    "fileFormats"
})
public class FileRelocationServiceInvocationInstance extends BasedBean {

    @XmlElement(required = true)
    protected String fileRelocationServiceInvocationInstanceCode;
    @XmlElement(required = true)
    protected FileDirectory fileDirectory;
    protected String filefilterExpression;
    @XmlElement(required = true)
    protected String batch;
    @XmlElement(required = true)
    protected String fileCheck;
    protected String suffixFormat;
    protected String fileAlgorithm;
    @XmlElement(required = true)
    protected String originalFileToDelete;
    @XmlElement(required = true)
    protected String reqOrRsp;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    protected XMLGregorianCalendar createTime;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected String fileFormats;

    /**
     * Gets the value of the fileRelocationServiceInvocationInstanceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileRelocationServiceInvocationInstanceCode() {
        return fileRelocationServiceInvocationInstanceCode;
    }

    /**
     * Sets the value of the fileRelocationServiceInvocationInstanceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileRelocationServiceInvocationInstanceCode(String value) {
        this.fileRelocationServiceInvocationInstanceCode = value;
    }

    /**
     * Gets the value of the fileDirectory property.
     * 
     * @return
     *     possible object is
     *     {@link FileDirectory }
     *     
     */
    public FileDirectory getFileDirectory() {
        return fileDirectory;
    }

    /**
     * Sets the value of the fileDirectory property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileDirectory }
     *     
     */
    public void setFileDirectory(FileDirectory value) {
        this.fileDirectory = value;
    }

    /**
     * Gets the value of the filefilterExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilefilterExpression() {
        return filefilterExpression;
    }

    /**
     * Sets the value of the filefilterExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilefilterExpression(String value) {
        this.filefilterExpression = value;
    }

    /**
     * Gets the value of the batch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatch() {
        return batch;
    }

    /**
     * Sets the value of the batch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatch(String value) {
        this.batch = value;
    }

    /**
     * Gets the value of the fileCheck property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileCheck() {
        return fileCheck;
    }

    /**
     * Sets the value of the fileCheck property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileCheck(String value) {
        this.fileCheck = value;
    }

    /**
     * Gets the value of the suffixFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuffixFormat() {
        return suffixFormat;
    }

    /**
     * Sets the value of the suffixFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuffixFormat(String value) {
        this.suffixFormat = value;
    }

    /**
     * Gets the value of the fileAlgorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileAlgorithm() {
        return fileAlgorithm;
    }

    /**
     * Sets the value of the fileAlgorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileAlgorithm(String value) {
        this.fileAlgorithm = value;
    }

    /**
     * Gets the value of the originalFileToDelete property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalFileToDelete() {
        return originalFileToDelete;
    }

    /**
     * Sets the value of the originalFileToDelete property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalFileToDelete(String value) {
        this.originalFileToDelete = value;
    }

    /**
     * Gets the value of the reqOrRsp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReqOrRsp() {
        return reqOrRsp;
    }

    /**
     * Sets the value of the reqOrRsp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReqOrRsp(String value) {
        this.reqOrRsp = value;
    }

    /**
     * Gets the value of the createTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTime(XMLGregorianCalendar value) {
        this.createTime = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
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

	@Override
	public boolean equals(Object obj){
		return super.equals(obj);
	}

	@Override
	public int hashCode(){
		return super. hashCode();
	}
}
