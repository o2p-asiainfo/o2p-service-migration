package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "routePolicy", propOrder = {
	"routePolicyCode",
    "routeCondition",
    "ruleStrategyCode"
})
public class RoutePolicy extends BasedBean {

	@XmlElement(required = true)
	protected String routePolicyCode;
    protected RouteCondition routeCondition;
    @XmlElement(required = true)
    protected String ruleStrategyCode;

    /**
     * Gets the value of the routeCondition property.
     * 
     * @return
     *     possible object is
     *     {@link RouteCondition }
     *     
     */
    public RouteCondition getRouteCondition() {
        return routeCondition;
    }

    /**
     * Sets the value of the routeCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link RouteCondition }
     *     
     */
    public void setRouteCondition(RouteCondition value) {
        this.routeCondition = value;
    }

	public String getRoutePolicyCode() {
		return routePolicyCode;
	}

	public void setRoutePolicyCode(String routePolicyCode) {
		this.routePolicyCode = routePolicyCode;
	}

	public String getRuleStrategyCode() {
		return ruleStrategyCode;
	}

	public void setRuleStrategyCode(String ruleStrategyCode) {
		this.ruleStrategyCode = ruleStrategyCode;
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
