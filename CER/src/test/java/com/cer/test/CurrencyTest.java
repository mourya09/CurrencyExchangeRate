/**
 * 
 */
package com.cer.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cer.services.UserDetailsService;

/**
 * @author Praveen Kumar
 * It test all the Currency Exchange Rate related scenarios.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:CER.xml" })
@TransactionConfiguration(transactionManager = "telenorTransactionManager", defaultRollback = false)
@Transactional
public class CurrencyTest {
	
	protected final Logger logger = LoggerFactory.getLogger(CurrencyTest.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public void addCurrency()
	{
		
	}
}
