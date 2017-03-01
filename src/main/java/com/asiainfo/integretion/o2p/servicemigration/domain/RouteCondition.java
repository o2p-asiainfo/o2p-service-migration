package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.asiainfo.integretion.o2p.servicemigration.common.annotation.BeforeMigration;
import com.asiainfo.integretion.o2p.servicemigration.common.annotation.Parent;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "routeCondition", propOrder = {
	"routeConditionCode",
    "upRouteCondition",
    "operatorCode",
    "getValueExpr",
    "matchValue",
    "condRelation",
    "routeConditionExpr"
})
public class RouteCondition extends BasedBean {
	@XmlElement(required=true)
	protected String routeConditionCode;
	@Parent
    protected RouteCondition upRouteCondition;
    @XmlElement(required = true)
    protected String operatorCode;
    @XmlElement(required = true)
    protected GetValueExpr getValueExpr;
    protected String matchValue;
    protected String condRelation;
    @XmlElement(required = true)
    protected String routeConditionExpr;

    
    
    public String getRouteConditionCode() {
		return routeConditionCode;
	}

	public void setRouteConditionCode(String routeConditionCode) {
		this.routeConditionCode = routeConditionCode;
	}

	/**
     * Gets the value of the upRouteCondition property.
     * 
     * @return
     *     possible object is
     *     {@link RouteCondition }
     *     
     */
    public RouteCondition getUpRouteCondition() {
        return upRouteCondition;
    }

    /**
     * Sets the value of the upRouteCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link RouteCondition }
     *     
     */
    public void setUpRouteCondition(RouteCondition value) {
        this.upRouteCondition = value;
    }

    /**
     * Gets the value of the operatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorCode() {
        return operatorCode;
    }

    /**
     * Sets the value of the operatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorCode(String value) {
        this.operatorCode = value;
    }

    /**
     * Gets the value of the getValueExpr property.
     * 
     * @return
     *     possible object is
     *     {@link GetValueExpr }
     *     
     */
    public GetValueExpr getGetValueExpr() {
        return getValueExpr;
    }

    /**
     * Sets the value of the getValueExpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetValueExpr }
     *     
     */
    public void setGetValueExpr(GetValueExpr value) {
        this.getValueExpr = value;
    }

    /**
     * Gets the value of the matchValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchValue() {
        return matchValue;
    }

    /**
     * Sets the value of the matchValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchValue(String value) {
        this.matchValue = value;
    }

    /**
     * Gets the value of the condRelation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondRelation() {
        return condRelation;
    }

    /**
     * Sets the value of the condRelation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondRelation(String value) {
        this.condRelation = value;
    }

    /**
     * Gets the value of the routeConditionExpr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRouteConditionExpr() {
        return routeConditionExpr;
    }

    /**
     * Sets the value of the routeConditionExpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRouteConditionExpr(String value) {
        this.routeConditionExpr = value;
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
