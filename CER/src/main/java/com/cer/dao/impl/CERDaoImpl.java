/**
 * 
 */
package com.cer.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cer.dao.CERDao;

/**
 * @author PRaveen Kumar
 *
 */
@Repository
public class CERDaoImpl implements CERDao {

	 @Autowired
	 private SessionFactory sessionFactory;

	
	

	public void save(Object entity) throws IllegalArgumentException {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
		session.close();
	}

	public void update(Object entity) throws IllegalArgumentException {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(entity);
		tx.commit();
		session.close();		
	}

	public void delete(Object entity) throws IllegalArgumentException {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
		
		
	}

	public List find(String queryString) throws IllegalArgumentException {
		return find(queryString, null);
	}

	public List find(String queryString, Object value) throws IllegalArgumentException {
		return find(queryString, new Object[] { value });
	}

	public List find(String queryString, Object[] values) throws IllegalArgumentException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryString);		
		if (values != null) {
			for (int i = 1; i <= values.length; i++) {
				query.setParameter(i-1, values[i-1]);
			}
		}
		return query.list();
	}

	/*public Object get(Class entityClass, Object id) throws IllegalArgumentException {
		return sessionFactory.getCurrentSession().get(entityClass.getClass(), id);
		
	}*/

	public List executeDataBaseQuery(String sql, String[] values) throws SQLException {
		List<String> list = new ArrayList<String>();
		/*Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			if (values != null) {
				int idx = 1;
				for (String value : values) {
					ps.setString(idx, (String) value);
					idx++;
				}
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// list.add(rs.getString("entity_id"));
				list.add(rs.getString(1));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}*/
		return list;
	}


	public Object get(Class entityClass, Object id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
}
