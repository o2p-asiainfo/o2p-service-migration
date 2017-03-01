package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.asiainfo.integretion.o2p.servicemigration.common.annotation.Parent;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getValueExpr", propOrder = {
	"getValueExprCode",
    "condEvaluatorCode",
    "upGetValueExpr",
    "expr",
    "reqRsp",
    "exprType"
})
public class GetValueExpr extends BasedBean {

	@XmlElement(required = true)
	protected String getValueExprCode;
    @XmlElement(required = true)
    protected String condEvaluatorCode;
    @XmlElement(required = true)
    @Parent
    protected GetValueExpr upGetValueExpr;
    @XmlElement(required = true)
    protected String expr;
    @XmlElement(required = true)
    protected String reqRsp;
    @XmlElement(required = true)
    protected String exprType;

    

    public String getGetValueExprCode() {
		return getValueExprCode;
	}

	public void setGetValueExprCode(String getValueExprCode) {
		this.getValueExprCode = getValueExprCode;
	}

	/**
     * Gets the value of the condEvaluatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondEvaluatorCode() {
        return condEvaluatorCode;
    }

    /**
     * Sets the value of the condEvaluatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondEvaluatorCode(String value) {
        this.condEvaluatorCode = value;
    }

    /**
     * Gets the value of the upGetValueExpr property.
     * 
     * @return
     *     possible object is
     *     {@link GetValueExpr }
     *     
     */
    public GetValueExpr getUpGetValueExpr() {
        return upGetValueExpr;
    }

    /**
     * Sets the value of the upGetValueExpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetValueExpr }
     *     
     */
    public void setUpGetValueExpr(GetValueExpr value) {
        this.upGetValueExpr = value;
    }

    /**
     * Gets the value of the expr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpr() {
        return expr;
    }

    /**
     * Sets the value of the expr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpr(String value) {
        this.expr = value;
    }

    /**
     * Gets the value of the reqRsp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReqRsp() {
        return reqRsp;
    }

    /**
     * Sets the value of the reqRsp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReqRsp(String value) {
        this.reqRsp = value;
    }

    /**
     * Gets the value of the exprType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExprType() {
        return exprType;
    }

    /**
     * Sets the value of the exprType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExprType(String value) {
        this.exprType = value;
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
