package com.cer.persistent;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Praveen Kumar
 * ${ClassName} use for defining the User role for the user accessing the webservices.
 * 
 * */

@Entity
@Table(name= "USER_ROLE")
public class UserRole {
	@Id
	@GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
	@Column(name="ROLE_ID")
	private Long userRoleId;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID", unique = true)
	private UserDetails userId;
	
	@Column(name="ROLE_NAME", nullable = false)
	private String roleName;
	
	@Column(name="IsCurrencyAddAllow", nullable = false)
	private Boolean isCurrencyAddAllow = false;
	
	@Column(name="IsCurrencyUpdateAllow", nullable = false)
	private Boolean isCurrencyUpdateAllow = false;
	
	@Column(name="IsCurrencyDeleteAllow", nullable = false)
	private Boolean isCurrencyDeleteAllow = false;

	public Long getUserRoleId() {
		return userRoleId;
	}

	public UserDetails getUserId() {
		return userId;
	}

	public String getRoleName() {
		return roleName;
	}

	public Boolean getIsCurrencyAddAllow() {
		return isCurrencyAddAllow;
	}

	public Boolean getIsCurrencyUpdateAllow() {
		return isCurrencyUpdateAllow;
	}

	public Boolean getIsCurrencyDeleteAllow() {
		return isCurrencyDeleteAllow;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public void setUserId(UserDetails userId) {
		this.userId = userId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setIsCurrencyAddAllow(Boolean isCurrencyAddAllow) {
		this.isCurrencyAddAllow = isCurrencyAddAllow;
	}

	public void setIsCurrencyUpdateAllow(Boolean isCurrencyUpdateAllow) {
		this.isCurrencyUpdateAllow = isCurrencyUpdateAllow;
	}

	public void setIsCurrencyDeleteAllow(Boolean isCurrencyDeleteAllow) {
		this.isCurrencyDeleteAllow = isCurrencyDeleteAllow;
	}
	
	
	
}
