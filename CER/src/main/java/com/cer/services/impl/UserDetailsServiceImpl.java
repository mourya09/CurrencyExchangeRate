/**
 * 
 */
package com.cer.services.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cer.dao.CERDao;
import com.cer.persistent.UserDetails;
import com.cer.persistent.UserRole;
import com.cer.services.UserDetailsService;

/**
 * @author Praveen Kumar
 * 
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CERDao cerDao;
	
	/*@Autowired
	private PropertyConfigurer propertyConfigurer;*/
	
	private Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.cer.services.UserDetailsService#addUsersDetails(com.cer.persistent.UserDetails)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean addUsersDetails(UserDetails userDetails) {
		logger.info("addUsersDetails start ");
		Boolean result = false;
		try{
		cerDao.save(userDetails);
		
		result = true;
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("addUsersDetails end ");
		return result;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean deleteUsersDetails(UserDetails userDetails) {
		logger.info("deleteUsersDetails start ");
		Boolean result = false;
		try{
			if(userDetails == null || userDetails.getId() == null)
			{
				logger.error("Please provide the userDetails. UserId is missing.");
				return result;
				
			}
		this.deleteUserRole(userDetails);
		userDetails = this.getUserDetails(userDetails.getId());
		if(userDetails != null){
			logger.info("UserDetails are not empty!!!");
			cerDao.delete(userDetails);
		}
		result = true;
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("deleteUsersDetails end ");
		return result;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public UserDetails updateUserDetails(UserDetails userDetails) {
		logger.info("updateUserDetails start ");
		UserDetails result = null;
		try {
			UserDetails temp = null;
			List<UserDetails> list = (List) cerDao.find("from UserDetails where id=?", userDetails.getId());
			if (list.size() > 0) {
				temp = list.get(0);
				temp.setEmailID(userDetails.getEmailID());
				temp.setFirstName(userDetails.getFirstName());
				temp.setLastName(userDetails.getLastName());
				temp.setLastModifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				temp.setPassword(userDetails.getPassword());
				result = temp;
			}
		} catch (Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("updateUserDetails end ");
		return result;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED, readOnly=true)
	public UserDetails getUserDetails(Long userId) {
		logger.info("getUserDetails start ");
		UserDetails result = null;
		try{
			UserDetails temp = null;
			List<UserDetails> list= 	(List)cerDao.find("from UserDetails where id=?",userId);
		if(list != null && list.size() > 0){
			temp = list.get(0);
		}
		result = temp;
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("getUserDetails end ");
		return result;
		
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED, readOnly=true)
	public UserDetails getUserDetailsWithRole(Long userId) {
		
		return this.getUserDetails(userId);
	}
	public List<UserDetails> getUserDetails(String userHQL, Object[] objectArray) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean addUserRole(UserDetails userDetails, UserRole userRole) {
		logger.info("addUserRole start ");
		Boolean result = false;
		try{
			userDetails = this.getUserDetails(userDetails.getId());
			userRole.setUserId(userDetails);
			userRole.setUserRoleId(null);
			cerDao.save(userRole);
		result = true;
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("addUserRole end ");
		return result;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean updateUserRole(UserRole userRole) {
		logger.info("updateUserRole start ");
		Boolean result = false;
		try{
			UserRole temp =(UserRole) cerDao.find("from UserRole where userRoleId=?", new Object[]{userRole.getUserRoleId()});
			temp.setRoleName(userRole.getRoleName());
			temp.setIsCurrencyAddAllow(userRole.getIsCurrencyAddAllow());
			temp.setIsCurrencyDeleteAllow(userRole.getIsCurrencyDeleteAllow());
			temp.setIsCurrencyUpdateAllow(userRole.getIsCurrencyUpdateAllow());
			cerDao.update(temp);			
		result = true;
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("updateUserRole end ");
		return result;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean deleteUserRole(UserRole userRole) {
		logger.info("deleteUserRoles start ");
		Boolean result = false;
		try{
			UserRole temp =(UserRole) cerDao.find("from UserRole where userRoleId=?", new Object[]{userRole.getUserRoleId()});
			cerDao.delete(temp);			
		result = true;
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("deleteUserRoles end ");
		return result;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean deleteUserRole(UserDetails userDetails) {
		
		logger.info("deleteUserRoles start ");
		Boolean result = false;
		try{
			UserRole temp =null;
			logger.info("Delete request for UserDetails " + userDetails.getId());
			List<UserRole> list =(List<UserRole>) cerDao.find("from UserRole ur where ur.userId.id=?", new Object[]{userDetails.getId()});
			if(list != null && list.size() > 0 && list.get(0) != null)
			{
				logger.info("Obtained UserDetails for " + list.get(0));
				temp = list.get(0);
				temp.setUserId(null);
				list = null;
				cerDao.delete(temp);
			}
						
		result = true;
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("deleteUserRoles end ");
		return result;
	}
	@Transactional(isolation=Isolation.READ_COMMITTED, readOnly=true)
	public UserRole getUserRole(UserDetails userDetails)
	{
		UserRole result = null;
		if(userDetails.getId() == null)
		{
			logger.error("Please provide the UserDetails.");
			return result;
		}else
		{
			List<UserRole> list = cerDao.find("from UserRole where userId.id=? ", userDetails.getId());
			if(list.size() > 0)
			{
				result = list.get(0);//Only one entry is allowed for a particular User ID
				if(result.getUserId() != null)
				{
					logger.info("UserId associated is " + result.getUserId().getFirstName());
				}
				
			}
		}
		
		return result;
				
		
	}
	
	
}
