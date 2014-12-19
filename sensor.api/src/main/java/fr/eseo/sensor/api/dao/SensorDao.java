package fr.eseo.sensor.api.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.eseo.sensor.api.bean.Sensor;

public class SensorDao extends MyDaoManager<Sensor> {
	
	private static final Log LOGGER = LogFactory.getLog(SensorDao.class);
	
	@Override
	public Sensor getOne(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		Sensor sensor = null;

		try {
			Query query = session.createQuery("from Sensor s where s.id = :id").setParameter("id", id);
			sensor = (Sensor)query.uniqueResult();
			transaction.commit();
		}
		catch (RuntimeException e){
			transaction.rollback();
			throw e ;
		}	
		return sensor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sensor> getAll() {
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		List<Sensor> list = null;

		try {
			list = session.createQuery("from Sensor d").list();
			transaction.commit();
		}
		catch (RuntimeException e){
			transaction.rollback();
			throw e ;
		}	
		return list;
	}

	@Override
	public void delete(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Sensor sensor = getOne(id);
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(sensor);
			transaction.commit();
		}
		catch (RuntimeException e){
			StringBuilder sb = new StringBuilder();
			sb.append("Can't delete Sensor : ");
			sb.append(id); 
			LOGGER.info(sb.toString());
			transaction.rollback();
			throw e ;
		}
	}

}
