package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import com.asiainfo.integretion.o2p.servicemigration.common.annotation.ReferenceType;
import com.asiainfo.integretion.o2p.servicemigration.common.jaxbAdapter.JaxbDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transformer", propOrder = {
    "transformerCode",
    "sourceContractFormatCode",
    "targetContractFormatCode",
    "adapterName",
    "adapterType",
    "scriptSrc",
    "nodeDescMapers",
    "nodeValueAdapterReqs",
    "variableMaps",
    "contractAdapterEndpoints",
    "createDate",
    "status"
})
public class Transformer extends BasedBean{
	@XmlElement(required=true)
	protected String transformerCode;
	@ReferenceType(ContractFormat.class)
	protected String sourceContractFormatCode;
	@ReferenceType(ContractFormat.class)
	protected String targetContractFormatCode;
	protected String adapterName;
	@XmlElement(required=true)
	protected String adapterType;
	protected String scriptSrc;
	@XmlElementWrapper(name="nodeDescMapers")
	@XmlElement(name="nodeDescMaper",required=true)
	protected List<NodeDescMaper> nodeDescMapers;
	@XmlElementWrapper(name="nodeValueAdapterReqs")
	@XmlElement(name="nodeValueAdapterReq",required=true)
	protected List<NodeValueAdapterReq> nodeValueAdapterReqs;
	@XmlElement(required = true)
    @XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
    protected XMLGregorianCalendar createDate;
	protected String status;
	@XmlElement(name="variableMap",required=true)
    @XmlElementWrapper(name="variableMaps")
    protected List<VariableMap> variableMaps;
	@XmlElement(name="contractAdapterEndpoint",required=true)
    @XmlElementWrapper(name="contractAdapterEndpoints")
	protected List<ContractAdapterEndpoint> contractAdapterEndpoints;
	public String getTransformerCode() {
		return transformerCode;
	}
	public void setTransformerCode(String transformerCode) {
		this.transformerCode = transformerCode;
	}
	
	public String getSourceContractFormatCode() {
		return sourceContractFormatCode;
	}
	public void setSourceContractFormatCode(String sourceContractFormatCode) {
		this.sourceContractFormatCode = sourceContractFormatCode;
	}
	public String getTargetContractFormatCode() {
		return targetContractFormatCode;
	}
	public void setTargetContractFormatCode(String targetContractFormatCode) {
		this.targetContractFormatCode = targetContractFormatCode;
	}
	public String getAdapterName() {
		return adapterName;
	}
	public void setAdapterName(String adapterName) {
		this.adapterName = adapterName;
	}
	public String getAdapterType() {
		return adapterType;
	}
	public void setAdapterType(String adapterType) {
		this.adapterType = adapterType;
	}
	public String getScriptSrc() {
		return scriptSrc;
	}
	public void setScriptSrc(String scriptSrc) {
		this.scriptSrc = scriptSrc;
	}
	public List<NodeDescMaper> getNodeDescMapers() {
		return nodeDescMapers;
	}
	public void setNodeDescMapers(List<NodeDescMaper> nodeDescMapers) {
		this.nodeDescMapers = nodeDescMapers;
	}
	public List<NodeValueAdapterReq> getNodeValueAdapterReqs() {
		return nodeValueAdapterReqs;
	}
	public void setNodeValueAdapterReqs(
			List<NodeValueAdapterReq> nodeValueAdapterReqs) {
		this.nodeValueAdapterReqs = nodeValueAdapterReqs;
	}
	public XMLGregorianCalendar getCreateDate() {
		return createDate;
	}
	public void setCreateDate(XMLGregorianCalendar createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<VariableMap> getVariableMaps() {
		return variableMaps;
	}

	public void setVariableMaps(List<VariableMap> variableMaps) {
		this.variableMaps = variableMaps;
	}
	public List<ContractAdapterEndpoint> getContractAdapterEndpoints() {
		return contractAdapterEndpoints;
	}
	public void setContractAdapterEndpoints(
			List<ContractAdapterEndpoint> contractAdapterEndpoints) {
		this.contractAdapterEndpoints = contractAdapterEndpoints;
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
