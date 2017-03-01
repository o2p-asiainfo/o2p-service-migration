package com.asiainfo.integretion.o2p.servicemigration.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orgType", propOrder = {
    "orgCode",
    "name",
    "tenant",
    "type",
    "area",
    "roles",
    "username",
    "password",
    "idType",
    "idNumber",
    "email",
    "contractNumber",
    "icon",
    "idScannedCopy",
    "description",
    "status"
})
public class Org extends BasedBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2493078701489866336L;
	@XmlElement(required = true)
    protected String orgCode;
    @XmlElement(required = true)
    protected String name;
    protected Tenant tenant;
    @XmlElement(required = true)
    protected String type;
    protected String area;
    @XmlElementWrapper(name="roles")
    @XmlElement(name="role")
    protected List<String> roles;
    protected String username;
    protected String password;
    @XmlElement(required = true)
    protected String idType;
    @XmlElement(required = true)
    protected String idNumber;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String contractNumber;
    protected String icon;
    protected String idScannedCopy;
    protected String description;
    @XmlElement(required = true)
    protected String status;

    /**
     * Gets the value of the orgCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * Sets the value of the orgCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgCode(String value) {
        this.orgCode = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the tenant property.
     * 
     * @return
     *     possible object is
     *     {@link Tenant }
     *     
     */
    public Tenant getTenant() {
        return tenant;
    }

    /**
     * Sets the value of the tenant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tenant }
     *     
     */
    public void setTenant(Tenant value) {
        this.tenant = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }
    
    

    public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	/**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the idType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdType() {
        return idType;
    }

    /**
     * Sets the value of the idType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdType(String value) {
        this.idType = value;
    }

    /**
     * Gets the value of the idNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * Sets the value of the idNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNumber(String value) {
        this.idNumber = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the contractNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     * Sets the value of the contractNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractNumber(String value) {
        this.contractNumber = value;
    }

    /**
     * Gets the value of the icon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets the value of the icon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcon(String value) {
        this.icon = value;
    }

    /**
     * Gets the value of the idScannedCopy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdScannedCopy() {
        return idScannedCopy;
    }

    /**
     * Sets the value of the idScannedCopy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdScannedCopy(String value) {
        this.idScannedCopy = value;
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

	@Override
	void valueTransform(String attr) {
		if("type".equals(attr)) {
			if("0".equals(type)) {
				type = "Internal Organization";
			} else if("1".equals(type)) {
				type = "Enterprise";
			} else if("2".equals(type)) {
				type = "Personal";
			} else if("".equals(type)) {
				type = null;
			}
		} else if("roles".equals(attr)) {
			if(roles != null) {
				List<String> rolesChange = new ArrayList<String>();
				for(String role: roles) {
					if("1".equals(role)) {
						role = "Developer";
					} else if("2".equals(role)) {
						role = "Provider";
					} else if("3".equals(role)) {
						role = "Partner";
					} else if("".equals(role)) {
						role = null;
					}
					if(role != null) {
						rolesChange.add(role);
					}
				}
				this.roles = rolesChange;
			}
		} else if("idType".equals(attr)) {
			if("1".equals(idType)){
				idType = "ID Card";
			} else if("2".equals(idType)) {
				idType = "Business License";
			}
		}
	}

	@Override
	void valueUnTransform(String attr) {
		if("type".equals(attr)) {
			if("Internal Organization".equals(type)) {
				type = "0";
			} else if("Enterprise".equals(type)) {
				type = "1";
			} else if("Personal".equals(type)) {
				type = "2";
			} else if("".equals(type)) {
				type = null;
			}
		} else if("roles".equals(attr)) {
			if(roles != null) {
				List<String> rolesChange = new ArrayList<String>();
				for(String role: roles) {
					if("Developer".equals(role)) {
						role = "1";
					} else if("Provider".equals(role)) {
						role = "2";
					} else if("Partner".equals(role)) {
						role = "3";
					} else if("".equals(role)) {
						role = null;
					}
					if(role != null) {
						rolesChange.add(role);
					}
				}
				this.roles = rolesChange;
			}
		} else if("idType".equals(attr)) {
			if("ID Card".equals(idType)){
				idType = "1";
			} else if("Business License".equals(idType)) {
				idType = "2";
			}
		}
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
