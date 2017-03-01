package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.util.Date;

public class ServiceMigrationLog {
	private Integer id;
	private String operateUser;
	private Date operateDate;
	private String operateTime;
	private String ip;
	private String old_doc;
	private String new_doc;
	private Integer tenantId;

	private char status;

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
		this.operateTime = CommonUtil.dateFormat(operateDate);
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOld_doc() {
		return old_doc;
	}

	public void setOld_doc(String old_doc) {
		this.old_doc = old_doc;
	}

	public String getNew_doc() {
		return new_doc;
	}

	public void setNew_doc(String new_doc) {
		this.new_doc = new_doc;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
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
