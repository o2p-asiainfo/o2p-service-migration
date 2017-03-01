package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="EndpointChoiceType")
public class EndpointChoice extends BasedBean {
	
	public Object getEndpointOrCode() {
		return endpointOrCode;
	}

	public void setEndpointOrCode(Object endpointOrCode) {
		this.endpointOrCode = endpointOrCode;
	}

	@XmlElements({
		@XmlElement(name="endpointCode", type=String.class),
		@XmlElement(name="endpoint", type=Endpoint.class)
	})
	protected Object endpointOrCode;

	@Override
	public String getCode() {
		return null;
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
