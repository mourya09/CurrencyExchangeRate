/**
 * 
 */
package com.cer.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * @author Praveen Kumar
 *
 */

@Entity
@Table(name= "USER_DETAILS")
/*@javax.persistence.TableGenerator(
        name="SEQ_GEN_TAB",
        table="SEQUENCE",
        pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_COUNT",
        pkColumnValue="USER_DETAIL_SEQ",
        allocationSize=1
)*/
public class UserDetails  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4031152287103560967L;
	
	private Long id;
	private String username;
	private String firstName;
	
	private String lastName;
	private String emailID;
	private String password;
	private Date creationDate;
	private Date lastModifiedDate;
	
	@Id
	@GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
	@Column(name="USER_ID")
	public Long getId() {
		return id;
	}
	@Column(name="USER_NAME", nullable = false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="FIRST_NAME", nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="LAST_NAME", nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="EMAIL_ID")
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	@Column(name="PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="CREATION_DATE")
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@Column(name="LAST_MODIFIED_DATE")
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/*
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="userId")
	public Set<UserAddresses> getAddresses() {
		return addresses;
	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="USER_LOCATION_MAPPING", 
    joinColumns={@JoinColumn(name="USER_ID")}, 
    inverseJoinColumns={@JoinColumn(name="USER_MAPPED_ID")})
	public Set<UserMappedLocation> getUserLocations() {
		return userLocations;
	}*/

	

	


	

	
	
	
	
}
