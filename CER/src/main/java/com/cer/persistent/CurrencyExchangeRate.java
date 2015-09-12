/**
 * 
 */
package com.cer.persistent;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author NEX6UYU
 *
 */
@Entity
@Table(name= "CURRENCY_EXCHANGE_RATE")
public class CurrencyExchangeRate {
	@Id
	@GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
	@Column(name="CER_ID")
	private Long cerId;
	
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="CURRENCY_ID")
	private Currency currencyId;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="CHILD_CURRENCY_ID")
	private Currency childCurrencyDetails;
	
	@Column(name="CONVERSATION_RATE",precision=2)
	private Double conversationRate;
	
	@Transient
	private Set<Currency> childCurrency= new HashSet<Currency>();

	public Long getCerId() {
		return cerId;
	}

	public Currency getCurrencyId() {
		return currencyId;
	}

	public Currency getChildCurrencyDetails() {
		return childCurrencyDetails;
	}

	public Double getConversationRate() {
		return conversationRate;
	}

	public Set<Currency> getChildCurrency() {
		return childCurrency;
	}

	public void setCerId(Long cerId) {
		this.cerId = cerId;
	}

	public void setCurrencyId(Currency currencyId) {
		this.currencyId = currencyId;
	}

	public void setChildCurrencyDetails(Currency childCurrencyDetails) {
		this.childCurrencyDetails = childCurrencyDetails;
	}

	public void setConversationRate(Double conversationRate) {
		this.conversationRate = conversationRate;
	}

	public void setChildCurrency(Set<Currency> childCurrency) {
		this.childCurrency = childCurrency;
	}
	
}
