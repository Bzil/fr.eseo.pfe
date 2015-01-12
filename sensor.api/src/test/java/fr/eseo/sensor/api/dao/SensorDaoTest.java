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
import org.junit.Test;
import org.apache.commons.lang.builder.EqualsBuilder;

import fr.eseo.sensor.api.bean.Sensor;
import fr.eseo.sensor.api.bean.SensorType;

public class SensorDaoTest {

	private Session session;
	//private SensorDao dao;

	@Before
	public void setUp(){
		session = HibernateUtilTest.getSessionFactory().openSession();
		//dao = new SensorDao();
		//dao.setSessionFactory(session.getSessionFactory());
	}

	@After
	public void tearDown(){
		session.close();	
	}

	@Test
	public void testAddSuccess(){
		SensorDao dao = new SensorDao();
		Sensor sensor = getOneSensor(true);
		boolean tmp = dao.add(sensor);
		assertTrue(tmp);
	}

	@Test 
	public void getAddFailure(){
		SensorDao dao = new SensorDao();
		assertFalse(dao.add(null));
	}

	@Test
	public void testgetOneSucces(){
		SensorDao dao = new SensorDao();
		dao.saveOrUpdate(getOneSensor(true));
		Sensor sensor = dao.getOne(1);
		
		assertNotNull(sensor);
		
		SensorComparable sc = new SensorComparable(getOneSensor(true));
		assertTrue(sc.equals(sensor));
	}
	
	@Test
	public void testgetAllSensorWithLowBattery(){
		SensorDao dao = new SensorDao();
		dao.saveOrUpdate(getOneSensor(false));
		List<Sensor> found = dao.getAllSensorWithLowBattery();
		assertNotNull(found);
		for (Sensor sensor : found) {
			assertTrue(sensor.getLowBattery());
		}
	}
	
	@Test
	public void testgetOneFailure(){
		SensorDao dao = new SensorDao();
		assertNull(dao.getOne(-1));
	}
	
	@Test
	public  void testGetAll(){
		SensorDao dao = new SensorDao();
		List<Sensor> found = dao.getAll();
		assertNotNull(found);
		assertEquals(2,found.size());
	}
	private static Sensor getOneSensor(boolean lowBattery){
		Sensor sensor = new Sensor();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			sensor.setAddDate(simpleDateFormat.parse("18/11/1990"));
		} catch (ParseException e) {
		}
		sensor.setLowBattery(lowBattery);
		sensor.setSamplingFrequency(0);
		sensor.setSensorType(SensorType.ARROW);
		sensor.setPlace("place");
		sensor.setUnity("m");
		return sensor;
	}
	
	protected class SensorComparable extends EqualsBuilder{

		private Sensor s;
		public SensorComparable(Sensor s){ 
			this.s = s;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || obj.getClass() != s.getClass()) { return false; }
			if (obj == this) { return true; }
			
			Sensor sensor = (Sensor) obj;
			String[] excludeFields = {"id", "datas"};
			return EqualsBuilder.reflectionEquals(s, sensor, excludeFields);
		}
	}
}