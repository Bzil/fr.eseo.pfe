package fr.eseo.sensor.api.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.eseo.sensor.api.bean.Data;

public class DataDao extends MyDaoManager<Data>{

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
			transaction.rollback();
			throw e ;
		}
	}

}
