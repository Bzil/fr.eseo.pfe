package fr.eseo.sensor.api.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.eseo.sensor.api.bean.User;
/**
 * Specific dao for user
 * @author Basile Chapellier
 * @version 1.0
 */
public class UserDao extends MyDaoManager<User> {
	/**
	 * Logger
	 */
	private static final Log LOGGER = LogFactory.getLog(UserDao.class);
	
	public UserDao(){
		super();
	}
	
	/**
	 * @see MyDaoManager#getOne(int)
	 */
	@Override
	public User getOne(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		User user = null;

		try {
			Query query = session.createQuery("from User d where d.id= :id").setParameter("id", id);
			user = (User)query.uniqueResult();
			transaction.commit();
		}
		catch (RuntimeException e){
			transaction.rollback();
			//throw e ;
			LOGGER.info("Looking for user with id : " + id + "\n Trace : " + e);
		}	
		return user;
	}
	/**
	 * @see MyDaoManager#getAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		List<User> list = null;

		try {
			list = session.createQuery("from User d").list();
			transaction.commit();
		}
		catch (RuntimeException e){
			transaction.rollback();
			//throw e ;
			LOGGER.info("Can't load user list. \n Trace : " + e);
		}	
		return list;
	}
	/**
	 * @see MyDaoManager#delete(int)
	 */
	@Override
	public boolean delete(int id) {
		boolean result = true;
		Session session = getSessionFactory().getCurrentSession();
		User user = getOne(id);
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(user);
			transaction.commit();
		}
		catch (RuntimeException e){
			transaction.rollback();
			result = false;
			LOGGER.info("Can't delete user with id : " + id +"\nTrace : " + e );
			//throw e ;
			
		}
		return result;
	}
}
