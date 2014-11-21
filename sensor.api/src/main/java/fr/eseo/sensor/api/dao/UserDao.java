package fr.eseo.sensor.api.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.eseo.sensor.api.bean.User;

public class UserDao extends MyDaoManager<User> {

	@Override
	public User getOne(int id) {
		Session s = getSessionFactory().getCurrentSession();
		Query q = s.createQuery("from User u where u.id= :id").setParameter("id", id);
		User u = (User)q.uniqueResult();
		s.close();
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		Session session = getSessionFactory().getCurrentSession();
		List<User> list = session.createQuery("from User u").list();
		session.close();
		return list;
	}

	@Override
	public void delete(int id) {
		Session session = getSessionFactory().getCurrentSession();
		User user = getOne(id);
		session.delete(user);
		session.close();
	}

}
