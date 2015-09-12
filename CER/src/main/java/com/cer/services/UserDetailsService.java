/**
 * 
 */
package com.cer.services;

import java.util.List;

import com.cer.persistent.UserDetails;
import com.cer.persistent.UserRole;

/**
 * @author Praveen kumar
 * UserDetailsService provides list of services for all operations related with User and its role.
 *
 */
public interface UserDetailsService {
	
	public Boolean addUsersDetails(UserDetails userDetails);
	public Boolean deleteUsersDetails(UserDetails userDetails);
	public UserDetails updateUserDetails(UserDetails userDetails);	
	
	public UserDetails getUserDetails(Long userId);
	public UserDetails getUserDetailsWithRole(Long userId);	
	public List<UserDetails> getUserDetails(String userHQL, Object[] objectArray);
	
	public Boolean addUserRole(UserDetails userDetails, UserRole userRole );
	public Boolean updateUserRole(UserRole userRole);
	public Boolean deleteUserRole(UserRole userRole);
	public UserRole getUserRole(UserDetails userDetails);
}
