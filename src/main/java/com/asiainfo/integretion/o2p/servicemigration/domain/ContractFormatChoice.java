package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContractFormatChoice extends BasedBean {
	@XmlElements({
        @XmlElement(name = "contractFormatCode", type = String.class),
        @XmlElement(name = "contractFormat", type = ContractFormat.class)
    })
    protected Object contractFormatOrCode;

	@Override
	public String getCode() {
		return null;
	}
	

	public Object getContractFormatOrCode() {
		return contractFormatOrCode;
	}

	public void setContractFormatOrCode(Object contractFormatOrCode) {
		this.contractFormatOrCode = contractFormatOrCode;
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
