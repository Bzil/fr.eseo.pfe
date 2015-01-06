package fr.eseo.sensor.api.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.eseo.sensor.api.bean.User;

public class UserDao extends MyDaoManager<User> {
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
			throw e ;
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
			throw e ;
		}	
		return list;
	}
	/**
	 * @see MyDaoManager#delete(int)
	 */
	@Override
	public void delete(int id) {
		Session session = getSessionFactory().getCurrentSession();
		User user = getOne(id);
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(user);
			transaction.commit();
		}
		catch (RuntimeException e){
			transaction.rollback();
			throw e ;
		}
	}
}
