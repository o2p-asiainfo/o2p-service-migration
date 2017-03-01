package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.asiainfo.integretion.o2p.servicemigration.common.annotation.BeforeMigration;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.ReferenceType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceRouteConfig", propOrder = {
	"serviceRouteConfigCode",
    "routePolicy",
    "synAsyn",
    "srcEndpointCode",
    "tarEndpointCode",
    "mapCode",
    "status"
})
public class ServiceRouteConfig extends BasedBean {

	@XmlElement(required = true)
	protected String serviceRouteConfigCode;
    @XmlElement(required = true)
    @BeforeMigration
    protected RoutePolicy routePolicy;
    @XmlElement(required = true)
    protected String synAsyn;
    @ReferenceType(Endpoint.class)
    @XmlElement(required=true)
    protected String srcEndpointCode;
    @ReferenceType(Endpoint.class)
    @XmlElement(required=true)
    protected String tarEndpointCode;
    protected String mapCode;
    @XmlElement(required = true)
    protected String status;

    /**
     * Gets the value of the routePolicy property.
     * 
     * @return
     *     possible object is
     *     {@link RoutePolicy }
     *     
     */
    public RoutePolicy getRoutePolicy() {
        return routePolicy;
    }

    /**
     * Sets the value of the routePolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoutePolicy }
     *     
     */
    public void setRoutePolicy(RoutePolicy value) {
        this.routePolicy = value;
    }

    /**
     * Gets the value of the synAsyn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSynAsyn() {
        return synAsyn;
    }

    /**
     * Sets the value of the synAsyn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynAsyn(String value) {
        this.synAsyn = value;
    }
    
	public String getSrcEndpointCode() {
		return srcEndpointCode;
	}

	public void setSrcEndpointCode(String srcEndpointCode) {
		this.srcEndpointCode = srcEndpointCode;
	}

	public String getTarEndpointCode() {
		return tarEndpointCode;
	}

	public void setTarEndpointCode(String tarEndpointCode) {
		this.tarEndpointCode = tarEndpointCode;
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
    
	public String getMapCode() {
		return mapCode;
	}

	public void setMapCode(String mapCode) {
		this.mapCode = mapCode;
	}

	public String getServiceRouteConfigCode() {
		return serviceRouteConfigCode;
	}

	public void setServiceRouteConfigCode(String serviceRouteConfigCode) {
		this.serviceRouteConfigCode = serviceRouteConfigCode;
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
