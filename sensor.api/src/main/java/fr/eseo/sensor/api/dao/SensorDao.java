package fr.eseo.sensor.api.dao;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.eseo.sensor.api.bean.Data;
import fr.eseo.sensor.api.bean.Sensor;
/**
 * Specific dao for sensor 
 * @author Basile Chapellier
 * @version 1.0
 */
public class SensorDao extends MyDaoManager<Sensor> {
	/**
	 * Logger
	 */
	private static final Log LOGGER = LogFactory.getLog(SensorDao.class);
	
	public SensorDao(){
		super();
	}
	/**
	 * @see MyDaoManager#getOne(int)
	 */
	@Override
	public Sensor getOne(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		Sensor sensor = null;

		try {
			Query query = session.createQuery("FROM Sensor s WHERE s.id = :id").setParameter("id", id);
			sensor = (Sensor)query.uniqueResult();
			transaction.commit();
		}
		catch (RuntimeException e){
			transaction.rollback();
			LOGGER.info("Looking for Sensor with id : " + id + "\n Trace : " + e);
		}	
		return sensor;
	}
	/**
	 * @see MyDaoManager#getAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Sensor> getAll() {
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		List<Sensor> list = null;

		try {
			list = session.createQuery("FROM Sensor s").list();
			transaction.commit();
		}
		catch (RuntimeException e){
			
			//throw e ;
			LOGGER.info("Can't load Sensors in base \n Trace : " + e);
			transaction.rollback();
		}	
		return list;
	}
	/**.
	 * @see MyDaoManager#delete(int)
	 */
	@Override
	public boolean delete(int id) {
		boolean result = true;
		Session session = getSessionFactory().getCurrentSession();
		Sensor sensor = getOne(id);
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(sensor);
			transaction.commit();
		}
		catch (RuntimeException e){
			LOGGER.info("Can't delete Sensor : " + id +"\n Trace : " + e); 
			transaction.rollback();
			result = false;
			//throw e ;
		}
		return result;
	}
	/**
	 * Get all sensor with low battery
	 * @return list of sensor found
	 */
	@SuppressWarnings("unchecked")
	public List<Sensor> getAllSensorWithLowBattery(){
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		List<Sensor> list = null;

		try {
			list = session.createQuery("FROM Sensor WHERE lowBattery=true").list();
			transaction.commit();
		}
		catch (RuntimeException e){
			LOGGER.info("Can't find sensors whitout battery \n Trace :" + e);
			transaction.rollback();
			//throw e ;
		}	
		return list;
	}
}
