package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileShareType", propOrder = {
    "fileShareCode",
    "name",
    "content",
    "status"
})
public class FileShare extends BasedBean{
	@XmlElement(required = true)
	protected String fileShareCode;
	@XmlElement(required = true)
	protected String name;
	@XmlElement(required = true)
	protected String content;
	@XmlElement(required = true)
	protected String status;
	public String getFileShareCode() {
		return fileShareCode;
	}
	public void setFileShareCode(String fileShareCode) {
		this.fileShareCode = fileShareCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
