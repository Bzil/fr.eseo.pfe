package fr.eseo.sensor.api.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * <h1>Class which allows the correct instance of a session.</h1>
 * <h2>The session represents a connection to the database.</h2>
 * @version 1.0
 *
 */

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	private HibernateUtil(){}
	
	
	static {
		try {
			//logger.info("Trying to create a test connection with the database.");
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");         
			StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
			sb.applySettings(cfg.getProperties());
			StandardServiceRegistry standardServiceRegistry = sb.build();                   
			sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
			//logger.info("Test connection with the database created successfuly.");
		} catch (ExceptionInInitializerError e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * <h2>Method which gets the sessionFactory.</h2>
	 * @return A SessionFactory.
	 */
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}