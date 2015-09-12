/**
 * 
 */
package com.cer.persistent;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.type.TimestampType;

/**
 * @author Praveen Kumar
 * Currency contain the information related with a particular Currency.
 *
 */
@Entity
@Table(name= "CURRENCY")
public class Currency {
	@Id
	@GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
	@Column(name="CURRENCY_ID")
	private String currencyID;
	
	@Column(name="CURRENCY_NAME")
	private String currencyName;
	
	@Column(name="CURRENCY_SHORT_NAME")
	private String currencyShortName;
	
	@Column(name="CURRENCY_IMAGE")
	private String currencyImageUrl;
	
	@Column(name="SYMBOL")
	private String currencySymbol;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@Column(name="LAST_MODIFIED_DATE")
	private Date lastModifiedDate;

	
	public String getCurrencyID() {
		return currencyID;
	}

	public void setCurrencyID(String currencyID) {
		this.currencyID = currencyID;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyShortName() {
		return currencyShortName;
	}

	public void setCurrencyShortName(String currencyShortName) {
		this.currencyShortName = currencyShortName;
	}

	public String getCurrencyImageUrl() {
		return currencyImageUrl;
	}

	public void setCurrencyImageUrl(String currencyImageUrl) {
		this.currencyImageUrl = currencyImageUrl;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	
}
