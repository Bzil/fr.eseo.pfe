package fr.eseo.sensor.api.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.eseo.sensor.api.bean.Data;

public class DataDao extends MyDaoManager<Data>{

	@Override
	public Data getOne(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from Data d where d.id= :id").setParameter("id", id);
		Data data = (Data)query.uniqueResult();
		session.close();
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Data> getAll() {
		Session session = getSessionFactory().getCurrentSession();
		List<Data> list = session.createQuery("from Data d").list();
		session.close();
		return list;
	}

	@Override
	public void delete(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Data data = getOne(id);
		session.delete(data);
		session.close();
		
	}
	
}
