package fr.eseo.sensor.api.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


/**
 * <h1>Class which allows the correct instance of a session.</h1>
 * <h2>The session represents a connection to the database.</h2>
 * @author Basile Chapellier.
 * @version 1.0
 *
 */

public class HibernateUtilTest {
	/**
	 * Logger
	 */
	private static final Log LOGGER = LogFactory.getLog(HibernateUtil.class);

	private static final SessionFactory sessionFactory;

	public static final ThreadLocal session = new ThreadLocal();

	private HibernateUtilTest(){}

	static {
		try {
			LOGGER.info("Trying to create a test connection with the database.");
			Configuration cfg = new Configuration().configure("hibernate.cfg.xml");         
			StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
			sb.applySettings(cfg.getProperties());
			StandardServiceRegistry standardServiceRegistry = sb.build();                   
			sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
			LOGGER.info("Test connection with the database created successfuly.");
		} catch (ExceptionInInitializerError e) {
			LOGGER.info("Test connection with the database tot created.");
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

	@SuppressWarnings("unchecked")
	public static void closeSession() throws HibernateException {
		Session s = (Session) session.get();
		session.set(null);
		if (s != null){
			s.close();
		}
	}
}