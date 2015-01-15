package fr.eseo.sensor.api.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.eseo.sensor.api.bean.Data;
/**
 * Specific dao for data
 * @author Basile Chapellier
 * @version 1.0
 */
public class DataDao extends MyDaoManager<Data>{

	private static final Log LOGGER = LogFactory.getLog(DataDao.class);

	public DataDao(){
		super();
	}

	/**
	 * @see MyDaoManager#getOne(int)
	 */
	@Override
	public Data getOne(int id) throws RuntimeException {
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		Data data = null;

		try {
			Query query = session.createQuery("FROM Data d WHERE d.id=:id").setParameter("id", id);
			data = (Data)query.uniqueResult();
			transaction.commit();
		}
		catch (RuntimeException e){
			transaction.rollback();
			throw e ;
		}	
		return data;
	}
	/**
	 * @see MyDaoManager#getAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Data> getAll() throws RuntimeException {
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		List<Data> list = null;
		list = session.createQuery("FROM Data d").list();
		transaction.commit();

		return list;
	}
	/**
	 * @see MyDaoManager#delete(int)
	 */
	@Override
	public boolean delete(int id) throws RuntimeException {
		boolean result = false;
		Data data = getOne(id);
		Session session = getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(data);
			transaction.commit();
			result = true;
		}
		catch (RuntimeException e){
			LOGGER.info("Can't delete data with id : " + id + "\n Trace : "+ e);
			transaction.rollback();
			throw e ;
		}
		return result;
	}
	/**
	 * Get all data from specific sensor
	 * @param id of the sensor
	 * @return list of data found
	 */
	@SuppressWarnings("unchecked")
	public List<Data> getAllFromSensor(int id) throws RuntimeException {
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		List<Data> list = null;
		Query query = session.createQuery("FROM Data WHERE sensor_id=:sensorId");
		query.setParameter("sensorId", id);
		list = query.list();
		transaction.commit();

		return list;
	}
	/**
	 * Get old data 
	 * @param dateDiff date dont on estime que les données sont trop vieilles
	 * @return list of data found
	 */
	@SuppressWarnings("unchecked")
	public List<Data> getOldData(int dateDiff) throws RuntimeException {
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		List<Data> list = null;

		try {
			Query query = session.createQuery("FROM Data d WHERE d.isOnPhone = 'true' AND DATEDIFF(DAY, d.date, NOW() ) > :dateDiff ");
			query.setParameter("dateDiff", dateDiff);
			list = query.list();
			transaction.commit();
		}
		catch (RuntimeException e){
			transaction.rollback();
			throw e ;
		}	
		return list;
	}
}
