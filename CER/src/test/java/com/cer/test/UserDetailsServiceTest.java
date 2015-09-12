/**
 * 
 */
package com.cer.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cer.persistent.UserDetails;
import com.cer.persistent.UserRole;
import com.cer.services.UserDetailsService;

/**
 * @author Praveen Kumar
 * Test all the services for User informations.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:CER.xml" })
@TransactionConfiguration(transactionManager = "telenorTransactionManager", defaultRollback = false)
@Transactional
public class UserDetailsServiceTest {

	protected final Logger logger = LoggerFactory.getLogger(UserDetailsServiceTest.class);
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Test
	public void testService1()
	{
		logger.debug("UserDetailsServiceTest Class");
		UserDetails u=new UserDetails();
		u.setFirstName("Praveen1");
		u.setLastName("Kumar1");
		u.setEmailID("mourya.09@gmail.com");
		u.setPassword("mourya09");
		u.setCreationDate(new Date());
		u.setLastModifiedDate(new Date());
		userDetailsService.addUsersDetails(u);
		
	}
	
	@Test
	public void testUpdateService()
	{
		logger.debug("testUpdateService Class");
		testService1();
		UserDetails u=new UserDetails();
		u.setFirstName("John");
		u.setLastName("Kamthan");
		u.setId(1L);
		u.setPassword("mourya09");
		u.setEmailID("mourya.0911@gmail.com");
		u.setLastModifiedDate(new Date());
		u = userDetailsService.updateUserDetails(u);
		if(u.getFirstName().equals("John"))
		{
			logger.info("Updated Successfully");
		}else
		{
			Assert.fail();
		}
		
		logger.debug("testUpdateService end");
	}
	@Test
	public void testDeleteUserDetails()
	{
		logger.debug("testDeleteUserDetails Class");
		testService1();
		UserDetails u=new UserDetails();		
		u.setId(1L);
		
		boolean result = userDetailsService.deleteUsersDetails(u);
		if(result)
		{
			logger.info("Deleted Successfully");
		}else
		{
			Assert.fail();
		}
		
		logger.debug("testDeleteUserDetails end");
	}
	
	@Test
	public void testAddUserRole()
	{
		logger.debug("UserDetailsServiceTest Class");
		testService1();
		UserRole ur= new UserRole();
		ur.setIsCurrencyAddAllow(true);
		ur.setIsCurrencyDeleteAllow(true);
		ur.setIsCurrencyUpdateAllow(true);
		ur.setRoleName("Admin");
		UserDetails ud = userDetailsService.getUserDetails(2L);
		ur.setUserId(ud);
		boolean result = userDetailsService.addUserRole(ud, ur);
		if(result)
		{
			logger.info("UserRole Updated successfully");
		}else
		{
			Assert.fail();
		}
		
		
		
	}
}
