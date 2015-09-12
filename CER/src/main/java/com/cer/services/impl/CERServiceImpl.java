/**
 * 
 */
package com.cer.services.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cer.dao.CERDao;
import com.cer.persistent.Currency;
import com.cer.persistent.CurrencyExchangeRate;
import com.cer.persistent.UserDetails;
import com.cer.persistent.UserRole;
import com.cer.services.CERService;
import com.cer.services.UserDetailsService;

/**
 * @author Praveen Kumar
 * mourya.09@gmail.com
 * CERService is responsible for all the currency and its exchange related functionalities.
 *
 */

@Service("cerService")
public class CERServiceImpl implements CERService {

	protected final Logger logger = LoggerFactory.getLogger(CERServiceImpl.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CERDao cerDao;
	/* (non-Javadoc)
	 * @see com.cer.services.CERService#addCurrency(com.cer.persistent.Currency, com.cer.persistent.UserDetails)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean addCurrency(Currency currency, UserDetails userDetail) {
		logger.info("addCurrency start ");
		Boolean result = false;
		try{
		if(userDetail == null || userDetail.getId() == null)
		{
			
			logger.error("User Details is null. Request you to please provide the correct value ");
			return result;
		}
		if(currency == null || currency.getCurrencyName() == null)
		{
			logger.error("Request you to please provide the Currency Details");
			return result;
		}
		userDetail = userDetailsService.getUserDetails(userDetail.getId());
		UserRole userRole= userDetailsService.getUserRole(userDetail)	;
		if(userRole != null && userRole.getIsCurrencyAddAllow())
		{
			currency.setCreationDate(new Date());
			currency.setLastModifiedDate(new Date());
			cerDao.save(currency);
		}	
		
		result = true;
		}catch( Exception ex)
		{
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("addCurrency end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cer.services.CERService#deleteCurrency(com.cer.persistent.Currency, com.cer.persistent.UserDetails)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean deleteCurrency(Currency currency, UserDetails userDetail) {
		logger.info("deleteCurrency start ");
		Boolean result = false;
		try{
		if(userDetail == null || userDetail.getId() == null)
		{
			
			logger.error("User Details is null. Request you to please provide the correct value ");
			return result;
		}
		if(currency == null || currency.getCurrencyName() == null)
		{
			logger.error("Request you to please provide the Currency Details");
			return result;
		}
		UserRole userRole= userDetailsService.getUserRole(userDetail)	;
		if(userRole != null && userRole.getIsCurrencyDeleteAllow())
		{
			CurrencyExchangeRate cer= this.getACurrencyExchangeRate(currency.getCurrencyID());
			if(cer != null && cer.getCerId() != null)
			{
				boolean isChildCERDeleted = this.deleteCurrencyExchangeRate(cer, userDetail);
				if(isChildCERDeleted)
				{
					logger.info("Dependent Currency Exchange Rate is deleted ");
					
				}else
				{
					logger.error("Unable to delete the Dependent Currency Exchange Rate!!!");
				}
			}else
			{
				logger.info("No Dependent Child Currency Exchange found");
			}
			currency = this.getACurrency(currency.getCurrencyID());// it will fetch the Persists Currency, as incoming currency is detached currency.
			cerDao.delete(currency);
		}	
		
		result = true;
		}catch( Exception ex)
		{
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("deleteCurrency end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cer.services.CERService#updateCurrency(com.cer.persistent.Currency, com.cer.persistent.UserDetails)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Currency updateCurrency(Currency currency, UserDetails userDetail) {
		logger.info("updateCurrency start ");
		Currency result = null;
		try{
		if(userDetail == null || userDetail.getId() == null)
		{
			
			logger.error("User Details is null. Request you to please provide the correct value ");
			return result;
		}
		if(currency == null || currency.getCurrencyName() == null)
		{
			logger.error("Request you to please provide the Currency Details");
			return result;
		}
		UserRole userRole= userDetailsService.getUserRole(userDetail)	;
		if(userRole != null && userRole.getIsCurrencyUpdateAllow())
		{
			
			Currency temp = this.getACurrency(currency.getCurrencyID());// it will fetch the Persists Currency, as incoming currency is detached currency.
			temp.setCode(currency.getCode());
			temp.setCurrencyShortName(currency.getCurrencyShortName());
			temp.setCurrencyImageUrl(currency.getCurrencyImageUrl());
			temp.setCurrencyName(currency.getCurrencyName());
			temp.setLastModifiedDate(new Date());			
			cerDao.update(temp);
			result= temp;
		}	
		
		
		}catch( Exception ex)
		{
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("updateCurrency end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cer.services.CERService#getAllCurrency()
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED,readOnly = true)
	public List<Currency> getAllCurrency() {
		logger.info("getAllCurrency start ");
		List<Currency> result = null;
		try{
		result = cerDao.find("from Currency");
				
		}catch( Exception ex)
		{
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("getAllCurrency end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cer.services.CERService#getACurrency(java.lang.String)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED,readOnly = true)
	public Currency getACurrency(String currencyShortName) {
		logger.info("getACurrency start ");
		List<Currency> temp = null;
		Currency result = null;
		try{
		temp = cerDao.find("from Currency where currencyShortName=?", currencyShortName);
		if(temp != null && temp.size() > 0)
		{
			result = temp.get(0);
		}else
		{
			logger.info("Currency not found for " + currencyShortName);
		}
				
		}catch( Exception ex)
		{
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("getACurrency end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cer.services.CERService#getACurrency(java.lang.Long)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED,readOnly = true)
	public Currency getACurrency(Long currencyId) {
		logger.info("getACurrency start ");
		List<Currency> temp = null;
		Currency result = null;
		try{
		temp = cerDao.find("from Currency where currencyId=?", currencyId);
		if(temp != null && temp.size() > 0)
		{
			result = temp.get(0);
		}else
		{
			logger.info("Currency not found for " + currencyId);
		}
				
		}catch( Exception ex)
		{
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("getACurrency end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cer.services.CERService#addCurrencyExchangeRate(com.cer.persistent.CurrencyExchangeRate, com.cer.persistent.UserDetails)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean addCurrencyExchangeRate(CurrencyExchangeRate currencyExchangeRate, UserDetails userDetail) {
		logger.info("addCurrencyExchangeRate start ");
		Boolean result = false;
		try{
		if(userDetail == null || userDetail.getId() == null)
		{
			
			logger.error("User Details is null. Request you to please provide the correct value ");
			return result;
		}
		if(currencyExchangeRate == null || currencyExchangeRate.getConversationRate() == null)
		{
			logger.error("Request you to please provide the CurrencyExchangeRate Details");
			return result;
		}
		UserRole userRole= userDetailsService.getUserRole(userDetail)	;
		if(userRole != null && userRole.getIsCurrencyAddAllow())
		{
			
			List<Currency> currencyList = cerDao.find("find Currency where currencyID=?", currencyExchangeRate.getCurrencyId().getCurrencyID());
			
			if(currencyList.size()> 0)	
			{
				currencyExchangeRate.setCurrencyId(currencyList.get(0));
				currencyList = cerDao.find("find Currency where currencyID=?", currencyExchangeRate.getChildCurrencyDetails().getCurrencyID());
				if(currencyList.size()> 0)
				{
					currencyExchangeRate.setChildCurrencyDetails(currencyList.get(0));
					cerDao.save(currencyExchangeRate);
				}
			}
			
		}	
		
		result = true;
		}catch( Exception ex)
		{
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("addCurrencyExchangeRate end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cer.services.CERService#deleteCurrencyExchangeRate(com.cer.persistent.CurrencyExchangeRate, com.cer.persistent.UserDetails)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean deleteCurrencyExchangeRate(CurrencyExchangeRate currencyExchangeRate, UserDetails userDetail) {
		logger.info("deleteCurrencyExchangeRate start ");
		Boolean result = false;
		try{
		if(userDetail == null || userDetail.getId() == null)
		{
			
			logger.error("User Details is null. Request you to please provide the correct value ");
			return result;
		}
		if(currencyExchangeRate == null || currencyExchangeRate.getConversationRate() == null)
		{
			logger.error("Request you to please provide the CurrencyExchangeRate Details");
			return result;
		}
		UserRole userRole= userDetailsService.getUserRole(userDetail)	;
		if(userRole != null && userRole.getIsCurrencyDeleteAllow())
		{
			
			List<CurrencyExchangeRate> currencyList = cerDao.find("find CurrencyExchangeRate where cerId=?", currencyExchangeRate.getCerId());
			
			if(currencyList.size()> 0)	
			{
				
					cerDao.save(currencyList.get(0));
				
			}
			
		}	
		
		result = true;
		}catch( Exception ex)
		{
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("deleteCurrencyExchangeRate end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cer.services.CERService#updateCurrencyExchangeRate(com.cer.persistent.CurrencyExchangeRate, com.cer.persistent.UserDetails)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public CurrencyExchangeRate updateCurrencyExchangeRate(CurrencyExchangeRate currencyExchangeRate,
			UserDetails userDetail) {
		logger.info("updateCurrencyExchangeRate start ");
		CurrencyExchangeRate result = null;
		try{
		if(userDetail == null || userDetail.getId() == null)
		{
			
			logger.error("User Details is null. Request you to please provide the correct value ");
			return result;
		}
		if(currencyExchangeRate == null || currencyExchangeRate.getConversationRate() == null)
		{
			logger.error("Request you to please provide the CurrencyExchangeRate Details");
			return result;
		}
		UserRole userRole= userDetailsService.getUserRole(userDetail)	;
		if(userRole != null && userRole.getIsCurrencyUpdateAllow())
		{
			
			List<CurrencyExchangeRate> currencyList = cerDao.find("find CurrencyExchangeRate where cerId=?", currencyExchangeRate.getCerId());
			
			if(currencyList.size()> 0)	
			{
				CurrencyExchangeRate temp = currencyList.get(0);
				temp.setConversationRate(currencyExchangeRate.getConversationRate());
					cerDao.update(temp);
					result = temp;
			}
			
		}	
		
		
		}catch( Exception ex)
		{
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("updateCurrencyExchangeRate end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cer.services.CERService#getAllCurrencyExchange()
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED,readOnly = true)
	public List<CurrencyExchangeRate> getAllCurrencyExchange() {
		logger.info("updateCurrencyExchangeRate start ");
		List<CurrencyExchangeRate> result = null;
		try {

			result = cerDao.find("find CurrencyExchangeRate");
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("updateCurrencyExchangeRate end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cer.services.CERService#getACurrencyExchangeRate(java.lang.String)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED,readOnly = true)
	public CurrencyExchangeRate getACurrencyExchangeRate(String currencyShortName) {
		logger.info("getACurrencyExchangeRate start ");
		CurrencyExchangeRate result = null;
		try {

			List<CurrencyExchangeRate> resultList = cerDao
					.find("find CurrencyExchangeRate where currencyId.currencyShortName=?", currencyShortName);
			if (resultList.size() > 0) {
				CurrencyExchangeRate cer = resultList.get(0);

				List<Currency> childCurrencyList = cerDao.find(
						"SELECT childCurrencyDetails from CurrencyExchangeRate where currencyId.currencyID=?",
						cer.getCurrencyId().getCurrencyID());
				for (Currency cur : childCurrencyList) {
					cer.getChildCurrency().add(cur);
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("getACurrencyExchangeRate end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.cer.services.CERService#getACurrencyExchangeRate(java.lang.Long)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED,readOnly = true)
	public CurrencyExchangeRate getACurrencyExchangeRate(Long currencyId) {
		logger.info("getACurrencyExchangeRate start ");
		CurrencyExchangeRate result = null;
		try {

			List<CurrencyExchangeRate> resultList = cerDao
					.find("find CurrencyExchangeRate where currencyId.currencyId=?", currencyId);
			if (resultList.size() > 0) {
				CurrencyExchangeRate cer = resultList.get(0);

				List<Currency> childCurrencyList = cerDao.find(
						"SELECT childCurrencyDetails from CurrencyExchangeRate where currencyId.currencyID=?",
						cer.getCurrencyId().getCurrencyID());
				for (Currency cur : childCurrencyList) {
					cer.getChildCurrency().add(cur);
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("getACurrencyExchangeRate end ");
		return result;
	}

}
