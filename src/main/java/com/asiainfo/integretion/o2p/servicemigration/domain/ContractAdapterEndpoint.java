package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import com.asiainfo.integretion.o2p.servicemigration.common.annotation.ReferenceType;
import com.asiainfo.integretion.o2p.servicemigration.common.jaxbAdapter.JaxbDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contractAdapterEndpointType", propOrder = {
    "contractAdapterEndpointCode",
    "endpointCode",
    "contractFormatCode",
    "actionType",
    "createDate"
})
public class ContractAdapterEndpoint extends BasedBean {
	@XmlElement(required=true)
	private String contractAdapterEndpointCode;
	@XmlElement(required=true)
	@ReferenceType(value=Endpoint.class)
	private String endpointCode;
	@XmlElement(required=true)
	private String contractFormatCode;
	@XmlElement(required=true)
	private String actionType;
	@XmlElement(required=true)
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	private XMLGregorianCalendar createDate;
	
	public String getContractAdapterEndpointCode() {
		return contractAdapterEndpointCode;
	}
	public void setContractAdapterEndpointCode(String contractAdapterEndpointCode) {
		this.contractAdapterEndpointCode = contractAdapterEndpointCode;
	}
	public String getEndpointCode() {
		return endpointCode;
	}
	public void setEndpointCode(String endpointCode) {
		this.endpointCode = endpointCode;
	}
	public String getContractFormatCode() {
		return contractFormatCode;
	}
	public void setContractFormatCode(String contractFormatCode) {
		this.contractFormatCode = contractFormatCode;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public XMLGregorianCalendar getCreateDate() {
		return createDate;
	}
	public void setCreateDate(XMLGregorianCalendar createDate) {
		this.createDate = createDate;
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
