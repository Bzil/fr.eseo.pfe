package fr.eseo.sensor.api.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.apache.commons.lang.builder.EqualsBuilder;

import fr.eseo.sensor.api.bean.Data;
import fr.eseo.sensor.api.bean.Sensor;

public class DataDaoTest {

	private Session session;
	private DataDao dao;

	@Before
	public void setUp(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		dao = new DataDao();
		dao.setSessionFactory(session.getSessionFactory());
	}

	@After
	public void tearDown(){
		HibernateUtilTest.closeSession();
	}

	@Test
	public void testAddSuccess(){
		// DataDao dao = new DataDao();
		Data Data = getOneData(true);
		boolean tmp = dao.add(Data);
		assertTrue(tmp);
	}

	@Test 
	public void getAddFailure(){
		// DataDao dao = new DataDao();
		assertFalse(dao.add(null));
	}

	@Test
	public void testgetOneSuccess(){
		// DataDao dao = new DataDao();
		dao.saveOrUpdate(getOneData(true));
		Data Data = dao.getOne(1);
		
		assertNotNull(Data);
		
		DataComparable sc = new DataComparable(getOneData(true));
		assertTrue(sc.equals(Data));
	}
	
	
	@Test
	public void testgetOneFailure(){
		// DataDao dao = new DataDao();
		assertNull(dao.getOne(-1));
	}
	
	@Test
	public  void testGetAll(){
		// DataDao dao = new DataDao();
		List<Data> found = dao.getAll();
		assertNotNull(found);
		assertEquals(2,found.size());
	}
	@Ignore
	@Test
	public void testDeleteFailure(){
		// DataDao dao = new DataDao();
		assertFalse(dao.delete(-1));
	}
	@Ignore
	@Test
	public void testDeleteSuccess(){
		// DataDao dao = new DataDao();
		assertTrue(dao.delete(1));
	}
	
	private static Data getOneData(boolean onPhone){
		Data data = new Data();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			data.setDate(simpleDateFormat.parse("18/11/1990"));
		} catch (ParseException e) {
		}
		data.setIsOnPhone(onPhone);
		data.setSensor(new Sensor());
		data.setValue("3,2");
		return data;
	}
	
	protected class DataComparable extends EqualsBuilder{

		private Data s;
		public DataComparable(Data s){ 
			this.s = s;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || obj.getClass() != s.getClass()) { return false; }
			if (obj == this) { return true; }
			
			Data Data = (Data) obj;
			String[] excludeFields = {"id", "datas"};
			return EqualsBuilder.reflectionEquals(s, Data, excludeFields);
		}
	}
}