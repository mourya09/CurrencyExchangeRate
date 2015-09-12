/**
 * 
 */
package com.cer.services;

import java.util.List;

import com.cer.persistent.Currency;
import com.cer.persistent.CurrencyExchangeRate;
import com.cer.persistent.UserDetails;

/**
 * @author Praveen Kumar
 * @email mourya.09@gmail.com
 * CERService classes provides Currency Exchange information to its client.
 * 
 *
 */
public interface CERService {

	/**
	 * Add a currency in the database, provided user has admin role.
	 * */
	public Boolean addCurrency(Currency currency, UserDetails userDetail);
	
	/**
	 * Delete a currency in the database, provided user has admin role.
	 * */
	public Boolean deleteCurrency(Currency currency, UserDetails userDetail);
	
	/**
	 * Update a currency in the database, provided user has admin role.
	 * */
	public Currency updateCurrency(Currency currency, UserDetails userDetail);
	
	/**
	 * List all the stored currency
	 * */
	public List<Currency> getAllCurrency();
	
	/**
	 * Obtain a currency information from the database.
	 * */
	public Currency getACurrency(String currencyShortName);
	/**
	 * Obtain a Currency Information based upon the currencyID. Suitable for AdminConsole.
	 * */
	public Currency getACurrency(Long currencyId);
	
	/**
	 * Add a currency exchange information in the database, provided user has admin role.
	 * */
	public Boolean addCurrencyExchangeRate(CurrencyExchangeRate currencyExchangeRate, UserDetails userDetail);
	/**
	 * delete a currency exchange information in the database, provided user has admin role.
	 * */
	public Boolean deleteCurrencyExchangeRate(CurrencyExchangeRate currencyExchangeRate, UserDetails userDetail);
	/**
	 * Update a currency exchange information in the database, provided user has admin role.
	 * */
	public CurrencyExchangeRate updateCurrencyExchangeRate(CurrencyExchangeRate currencyExchangeRate, UserDetails userDetail);
	/**
	 * List all the Currency Exchange Rate.
	 * */
	public List<CurrencyExchangeRate> getAllCurrencyExchange();
	/**
	 * Provide information for a particular currency exchange.
	 * */
	public CurrencyExchangeRate getACurrencyExchangeRate(String currencyShortName);
	
	/**
	 * Provide Information for particular Currency Exchange.
	 * */
	public CurrencyExchangeRate getACurrencyExchangeRate(Long currencyId);
	
}
