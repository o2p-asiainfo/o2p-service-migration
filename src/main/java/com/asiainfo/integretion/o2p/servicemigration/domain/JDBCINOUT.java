package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JDBC_INOUT", propOrder = {
    "tracingFlag",
    "dataLogFlag",
    "jdbcSql",
    "startLineIndex",
    "limit",
    "dataSourceId",
    "isExportCsv",
    "csvTemplateId"
})
public class JDBCINOUT extends BasedBean {

    @XmlElement(required = true)
    protected String tracingFlag;
    @XmlElement(required = true)
    protected String dataLogFlag;
    @XmlElement(required = true)
    protected String jdbcSql;
    protected String startLineIndex;
    protected String limit;
    @XmlElement(required = true)
    protected String dataSourceId;
    protected String isExportCsv;
    @XmlElement(required = true)
    protected String csvTemplateId;

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
     * Gets the value of the jdbcSql property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJdbcSql() {
        return jdbcSql;
    }

    /**
     * Sets the value of the jdbcSql property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJdbcSql(String value) {
        this.jdbcSql = value;
    }

    /**
     * Gets the value of the startLineIndex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartLineIndex() {
        return startLineIndex;
    }

    /**
     * Sets the value of the startLineIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartLineIndex(String value) {
        this.startLineIndex = value;
    }

    /**
     * Gets the value of the limit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLimit() {
        return limit;
    }

    /**
     * Sets the value of the limit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimit(String value) {
        this.limit = value;
    }

    /**
     * Gets the value of the dataSourceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSourceId() {
        return dataSourceId;
    }

    /**
     * Sets the value of the dataSourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSourceId(String value) {
        this.dataSourceId = value;
    }

    /**
     * Gets the value of the isExportCsv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsExportCsv() {
        return isExportCsv;
    }

    /**
     * Sets the value of the isExportCsv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsExportCsv(String value) {
        this.isExportCsv = value;
    }

    /**
     * Gets the value of the csvTemplateId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsvTemplateId() {
        return csvTemplateId;
    }

    /**
     * Sets the value of the csvTemplateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsvTemplateId(String value) {
        this.csvTemplateId = value;
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
