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
import org.junit.Rule;
import org.junit.Test;
import org.apache.commons.lang.builder.EqualsBuilder;

import fr.eseo.sensor.api.bean.Data;
import fr.eseo.sensor.api.bean.Sensor;
import fr.eseo.sensor.api.util.DataGenerator;

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
		SensorDao sensorDao = new SensorDao();
		Sensor sensor;
		if(sensorDao.getOne(1)!=null){
			sensor = sensorDao.getOne(1);
		}else { 
			sensorDao.saveOrUpdate(DataGenerator.getOneSensor(false));
			sensor = sensorDao.getOne(1);
		}
		Data data = DataGenerator.getOneData(true, sensor);
		boolean tmp = dao.add(data);
		assertTrue(tmp);
	}

	@Test( expected = RuntimeException.class )
	public void getAddFailure(){
		assertFalse(dao.add(null));
	}

	@Test
	public void testgetOneSuccess(){
		SensorDao sensorDao = new SensorDao();
		Sensor sensor;
		if(sensorDao.getOne(1)!=null){
			sensor = sensorDao.getOne(1);
		}else { 
			sensorDao.saveOrUpdate(DataGenerator.getOneSensor(false));
			sensor = sensorDao.getOne(1);
		}
		// DataDao dao = new DataDao();
		dao.saveOrUpdate(DataGenerator.getOneData(true, sensor));
		Data data = dao.getOne(1);
		
		assertNotNull(data);
		
		DataComparable sc = new DataComparable(DataGenerator.getOneData(true));
		assertTrue(sc.equals(data));
	}
	
	@Test
	public void testgetOneFailure(){
		assertNull(dao.getOne(-1));
	}
	
	@Test
	public  void testGetAll(){
		List<Data> found = dao.getAll();
		assertNotNull(found);
		assertEquals(1,found.size());
	}

	@Test( expected = RuntimeException.class )
	public void testDeleteFailure(){
		assertFalse(dao.delete(-1));
	}
	@Ignore
	@Test
	public void testDeleteSuccess(){
		assertTrue(dao.delete(1));
	}
	
	protected class DataComparable extends EqualsBuilder{

		private Data d;
		public DataComparable(Data d){ 
			this.d = d;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || obj.getClass() != d.getClass()) { return false; }
			if (obj == this) { return true; }
			
			Data Data = (Data) obj;
			String[] excludeFields = {"id", "sensorId", "sensor"};
			return EqualsBuilder.reflectionEquals(d, Data, excludeFields);
		}
	}
}