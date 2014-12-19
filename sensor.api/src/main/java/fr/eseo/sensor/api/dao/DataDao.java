package fr.eseo.sensor.api.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.eseo.sensor.api.bean.Data;

public class DataDao extends MyDaoManager<Data>{
	
	private static final Log LOGGER = LogFactory.getLog(DataDao.class);
	
	@Override
	public Data getOne(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		Data data = null;

		try {
			Query query = session.createQuery("from Data d where d.id= :id").setParameter("id", id);
			data = (Data)query.uniqueResult();
			transaction.commit();
		}
		catch (RuntimeException e){
			transaction.rollback();
			throw e ;
		}	
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Data> getAll() {
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		List<Data> list = null;

		try {
			list = session.createQuery("from Data d").list();
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
		Data data = getOne(id);
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(data);
			transaction.commit();
		}
		catch (RuntimeException e){
			StringBuilder sb = new StringBuilder();
			sb.append("Can't delete data : ");
			sb.append(id); 
			LOGGER.info(sb.toString());
			transaction.rollback();
			throw e ;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Data> getAllFromSensor(int id){
		Session session = getSessionFactory().getCurrentSession();
		Transaction  transaction = session.beginTransaction();
		List<Data> list = null;

		try {
			Query query = session.createQuery("from Data where sensor_id=:sensorId");
			query.setParameter("sensorId", id);
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
