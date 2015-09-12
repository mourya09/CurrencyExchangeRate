/**
 * 
 */
package com.cer.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Praveen Kumar
 *
 */
public interface CERDao {

	/**
	 * It will save only HQL persistent object only.
	 * */
	public void save(Object entity) throws IllegalArgumentException;
	
	/**
	 * It will update only JPA Persistent object only.
	 * */
	
	public void update(Object entity) throws IllegalArgumentException;
	
	/**
	 * It will delete only JPA Persistent object only.
	 * */
	
	public void delete(Object entity) throws IllegalArgumentException;
	
	/**
	 * Please provide the HQL and it will return the persistent object only.
	 * */
		
	public List find(String queryString) throws IllegalArgumentException;
	
	/**
	 * Please provide the HQL and it will return the persistent object only. Object value will contains the primary key of the HQL.
	 * */

	public List find(String queryString, Object value) throws IllegalArgumentException;
	
	/**
	 * Please provide the HQL and it will return the persistent object only.
	 * Object array should contains all the required searched object in a HQL.
	 * */
	public List find(String queryString, Object[] values) throws IllegalArgumentException;
	
	/**
	 * It will searched for a particular EntityClass usually it should be JPA persistent Class and id should be its primary key.
	 * */
	
	public Object get(Class entityClass, Object id) throws IllegalArgumentException;
	

	List executeDataBaseQuery(String sql, String[] values) throws SQLException;
}
