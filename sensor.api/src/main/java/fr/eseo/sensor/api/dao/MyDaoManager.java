package fr.eseo.sensor.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * <h1>Manager class which allows to make the transactions to the database.</h1>
 * <h1>This class is never instanced, it is extended by other manager classes existing.</h1>
 * <h2>For one model class there is one manager class.</h2>
 * @author Basile Chapellier.
 * @version 1.0.
 * @param <T> The model class that the manager class will manage.
 */

public abstract class MyDaoManager<T> {

	private SessionFactory sessionFactory = null ;

	/**
	 * <h2>Constructor which will get the sessionFactory from the class HibernateUtil</h2>
	 */
	public MyDaoManager(){
		this.sessionFactory = HibernateUtil.getSessionFactory() ;
	}

	/**
	 * <h2>Method which gets the SessionFactory.</h2>
	 * @return A SessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * <h2>Method which sets the SessionFactory at the moment of the construction.</h2>
	 * @param sessionFactory The SessionFactory to set.
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * <h2>Method which adds a given object from the model in the database.</h2>
	 * @param object Object to add.
	 */
	public boolean add(T object){
		Session session = getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			session.save(object);
			transaction.commit();
		}
		catch (RuntimeException e){
			transaction.rollback();
			throw e ;
		}
		return true;
	}

	/**
	 * <h2>Method which updates a given Object from the model in the database.</h2>
	 * @param object Object to update.
	 */
	public void update(T object){
		Session session = getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.merge(object);
			transaction.commit();
		}
		catch (RuntimeException e){
			transaction.rollback();
			throw e ;
		}
	}


	/**
	 * <h2>Method which gets an object from the model thanks to its id in the database.</h2>
	 * @param id The object's id to get.
	 * @return An object.
	 */
	public abstract T getOne(int id);

	/**
	 * <h2>Method which gets all of an object from the model.</h2>
	 * @return A list of the object.
	 */
	public abstract List<T> getAll();

	/**
	 * <h2>Method which deletes a given object thanks to its id.</h2>
	 * @param id The object's id to delete.
	 */
	public abstract void delete(int id);
}