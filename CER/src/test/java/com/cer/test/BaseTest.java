package com.cer.test;

import java.io.Serializable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:CER.xml" })
@TransactionConfiguration(transactionManager = "telenorTransactionManager", defaultRollback = false)
@Transactional
public class BaseTest implements Serializable {
protected final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	
	private static final long serialVersionUID = 5884666847076636121L;
		
	protected Gson gson = new Gson();
	
	
	/**
	 * Default test for the base class.
	 * */
	@Test
	public void testDefault()
	{
		logger.debug("BaseTest Class");
	}

	/*public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		
		
	}*/
}
