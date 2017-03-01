package com.asiainfo.integretion.o2p.servicemigration.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import com.asiainfo.integretion.o2p.servicemigration.common.jaxbAdapter.JaxbDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "technologyImplementNodeType", propOrder = {
    "nodeCode",
    "host",
    "ip",
    "port",
    "createTime",
    "modifyTime",
    "heartAdd",
    "syncAdd",
    "techRouteExpr",
    "status"
})
public class TechnologyImplementNode extends BasedBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(required = true)
    protected String nodeCode;
    @XmlElement(required = true)
    protected String host;
    @XmlElement(required = true)
    protected String ip;
    protected Integer port;
    @XmlElement(name = "createTime")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    protected XMLGregorianCalendar createTime;

	@XmlElement(name = "modifyTime")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    protected XMLGregorianCalendar modifyTime;
    protected String heartAdd;
    protected String syncAdd;
    protected String techRouteExpr;

	protected String status;

   

	public String getNodeCode() {
		return nodeCode;
	}



	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}



	public String getHost() {
		return host;
	}



	public void setHost(String host) {
		this.host = host;
	}



	public String getIp() {
		return ip;
	}



	public void setIp(String ip) {
		this.ip = ip;
	}



	public Integer getPort() {
		return port;
	}



	public void setPort(Integer port) {
		this.port = port;
	}



	public String getHeartAdd() {
		return heartAdd;
	}



	public void setHeartAdd(String heartAdd) {
		this.heartAdd = heartAdd;
	}



	public String getSyncAdd() {
		return syncAdd;
	}



	public void setSyncAdd(String syncAdd) {
		this.syncAdd = syncAdd;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



    public XMLGregorianCalendar getCreateTime() {
		return createTime;
	}



	public void setCreateTime(XMLGregorianCalendar createTime) {
		this.createTime = createTime;
	}



	public XMLGregorianCalendar getModifyTime() {
		return modifyTime;
	}



	public void setModifyTime(XMLGregorianCalendar modifyTime) {
		this.modifyTime = modifyTime;
	}

    public String getTechRouteExpr() {
		return techRouteExpr;
	}



	public void setTechRouteExpr(String techRouteExpr) {
		this.techRouteExpr = techRouteExpr;
	}
	
	@Override
	public String getCode() {
		return "nodeCode";
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
