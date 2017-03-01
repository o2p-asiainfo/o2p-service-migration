package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ruleStrategy", propOrder = {
    "ruleStrategyCode",
    "ruleStrategyName",
    "ruleStrategyDesc"
})
public class RuleStrategy extends BasedBean {

    @XmlElement(required = true)
    protected String ruleStrategyCode;
    @XmlElement(required = true)
    protected String ruleStrategyName;
    protected String ruleStrategyDesc;

    /**
     * Gets the value of the ruleStrategyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleStrategyCode() {
        return ruleStrategyCode;
    }

    /**
     * Sets the value of the ruleStrategyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleStrategyCode(String value) {
        this.ruleStrategyCode = value;
    }

    /**
     * Gets the value of the ruleStrategyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleStrategyName() {
        return ruleStrategyName;
    }

    /**
     * Sets the value of the ruleStrategyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleStrategyName(String value) {
        this.ruleStrategyName = value;
    }

    /**
     * Gets the value of the ruleStrategyDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleStrategyDesc() {
        return ruleStrategyDesc;
    }

    /**
     * Sets the value of the ruleStrategyDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleStrategyDesc(String value) {
        this.ruleStrategyDesc = value;
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
