package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceObjectType", propOrder = {
	"services",
    "components",
    "contractVersions"
})
@XmlRootElement(name="serviceObject")
public class ServiceObject extends BasedBean {

    @XmlElement(name="component")
    @XmlElementWrapper(name="components")
    protected List<Component> components;
    @XmlElement(name="contractVersion")
    @XmlElementWrapper(name="contractVersions")
    protected List<ContractVersion> contractVersions;
    @XmlElement(required = true,name="service")
    @XmlElementWrapper(name="services")
    protected List<Service> services;

	public List<Service> getServices() {
		if(services == null) {
			services = new ArrayList<Service>();
		}
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Component> getComponents() {
		if(components == null) {
			components = new ArrayList<Component>();
		}
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	@Override
	public String getCode() {
		return null;
	}

	@Override
	public boolean fieldCompare(String attr, Object currentValue,
			Object targetValue, BasedBeanHelper helper) {
		return true;
	}

	public List<ContractVersion> getContractVersions() {
		if(contractVersions == null) {
			contractVersions = new ArrayList<ContractVersion>();
		}
		return contractVersions;
	}

	public void setContractVersions(List<ContractVersion> contractVersions) {
		this.contractVersions = contractVersions;
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