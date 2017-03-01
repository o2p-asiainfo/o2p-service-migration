package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tenantType", propOrder = {
    "tenantCode",
    "name",
    "country",
    "province",
    "language",
    "timeZone",
    "currency",
    "logo",
    "theme",
    "zipCode",
    "contractNum",
    "createDate",
    "startDate",
    "endDate",
    "status"
})
public class Tenant extends BasedBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7283328243648912621L;
	
	@XmlElement(required = true)
    protected String tenantCode;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String country;
    protected String province;
    @XmlElement(required = true)
    protected String language;
    @XmlElement(required = true)
    protected String timeZone;
    protected String currency;
    @XmlElement(required = true)
    protected String logo;
    @XmlElement(required = true)
    protected String theme;
    protected String zipCode;
    protected String contractNum;
    @XmlElement(required = true)
    protected Date createDate;
    @XmlElement(required = true)
    protected Date startDate;
    public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@XmlElement(required = true)
    protected Date endDate;
    @XmlElement(required = true)
    protected String status;


	@Override
	public boolean equals(Object obj){
		return super.equals(obj);
	}

	@Override
	public int hashCode(){
		return super.hashCode();
	}
}
