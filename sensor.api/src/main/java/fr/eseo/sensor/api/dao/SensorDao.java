package fr.eseo.sensor.api.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.eseo.sensor.api.bean.Sensor;

public class SensorDao extends MyDaoManager<Sensor> {

	@Override
	public Sensor getOne(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from User u where u.id= :id").setParameter("id", id);
		Sensor sensor = (Sensor)query.uniqueResult();
		session.close();
		return sensor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sensor> getAll() {
		Session session = getSessionFactory().getCurrentSession();
		List<Sensor> list = session.createQuery("from Sensor s").list();
		session.close();
		return list;
	}

	@Override
	public void delete(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Sensor sensor = getOne(id);
		session.delete(sensor);
		session.close();
		
	}

}
