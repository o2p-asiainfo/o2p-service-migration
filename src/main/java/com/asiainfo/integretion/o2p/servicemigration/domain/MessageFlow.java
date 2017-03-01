package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.util.ArrayList;
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
@XmlType(name = "messageFlowType", propOrder = {
    "messageFlowCode",
    "messageFlowName",
    "startEndpointCode",
    "createTime",
    "serviceRouteConfigs",
    "changeTime",
    "description",
    "status",
    "endpoints"
})
public class MessageFlow extends BasedBean {

    @XmlElement(required = true)
    protected String messageFlowCode;
    @XmlElement(required = true)
    protected String messageFlowName;
    @ReferenceType(Endpoint.class)
    @XmlElement(required=true)
    protected String startEndpointCode;
//    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    protected XMLGregorianCalendar createTime;
    @XmlElement(required = true,name="serviceRouteConfig")
    @XmlElementWrapper(name="serviceRouteConfigs")
    protected List<ServiceRouteConfig> serviceRouteConfigs;
//    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    protected XMLGregorianCalendar changeTime;
    protected String description;
    @XmlElement(required = true)
    protected String status;
    @XmlElementWrapper(name="endpoints")
    @XmlElement(name="endpoint")
    protected List<Endpoint> endpoints;

    /**
     * Gets the value of the messageFlowCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageFlowCode() {
        return messageFlowCode;
    }

    /**
     * Sets the value of the messageFlowCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageFlowCode(String value) {
        this.messageFlowCode = value;
    }

    /**
     * Gets the value of the messageFlowName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageFlowName() {
        return messageFlowName;
    }

    /**
     * Sets the value of the messageFlowName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageFlowName(String value) {
        this.messageFlowName = value;
    }
    
	public String getStartEndpointCode() {
		return startEndpointCode;
	}

	public void setStartEndpointCode(String startEndpointCode) {
		this.startEndpointCode = startEndpointCode;
	}

	/**
     * Gets the value of the createTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTime(XMLGregorianCalendar value) {
        this.createTime = value;
    }

    public List<ServiceRouteConfig> getServiceRouteConfigs() {
		return serviceRouteConfigs;
	}

	public void setServiceRouteConfigs(List<ServiceRouteConfig> serviceRouteConfigs) {
		this.serviceRouteConfigs = serviceRouteConfigs;
	}

	public void setEndpoints(List<Endpoint> endpoints) {
		this.endpoints = endpoints;
	}

	/**
     * Gets the value of the changeTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getChangeTime() {
        return changeTime;
    }

    /**
     * Sets the value of the changeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setChangeTime(XMLGregorianCalendar value) {
        this.changeTime = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
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

    /**
     * Gets the value of the endpoints property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the endpoints property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEndpoints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EndpointsType }
     * 
     * 
     */
    public List<Endpoint> getEndpoints() {
        if (endpoints == null) {
            endpoints = new ArrayList<Endpoint>();
        }
        return this.endpoints;
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
