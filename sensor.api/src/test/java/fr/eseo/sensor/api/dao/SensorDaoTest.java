package fr.eseo.sensor.api.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.commons.lang.builder.EqualsBuilder;

import fr.eseo.sensor.api.bean.Sensor;
import fr.eseo.sensor.api.bean.SensorType;
import fr.eseo.sensor.api.util.DataGenerator;

public class SensorDaoTest {

	private Session session;
	private SensorDao dao;

	@Before
	public void setUp(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		dao = new SensorDao();
		dao.setSessionFactory(session.getSessionFactory());
	}

	@After
	public void tearDown(){
		HibernateUtilTest.closeSession();
	}

	@Test
	public void testAddSuccess(){
		Sensor sensor = DataGenerator.getOneSensor(true);
		boolean tmp = dao.add(sensor);
		assertTrue(tmp);
	}

	@Test( expected = RuntimeException.class )
	public void getAddFailure(){
		assertFalse(dao.add(null));
	}

	@Test
	public void testgetOneSucces(){
		dao.saveOrUpdate(DataGenerator.getOneSensor(true));
		Sensor sensor = dao.getOne(1);
		assertNotNull(sensor);
		assertEquals(sensor.getLatitude(), -0.127512, 0.0001);
		assertEquals(sensor.getLongitude(),51.507222,  0.0001);
		assertEquals(sensor.getId(), 1);
		assertEquals(sensor.getSensorType(), SensorType.ARROW);
	}

	@Test
	public void testgetAllSensorWithLowBattery(){
		dao.saveOrUpdate(DataGenerator.getOneSensor(false));
		List<Sensor> found = dao.getAllSensorWithLowBattery();
		assertNotNull(found);
		for (Sensor sensor : found) {
			assertTrue(sensor.getLowBattery());
		}
	}

	@Test
	public void testgetOneFailure(){
		assertNull(dao.getOne(-1));
	}

	@Test
	public void testGetAll(){
		List<Sensor> found = dao.getAll();
		assertNotNull(found);
		assertEquals(2,found.size());
	}

	@Test( expected = RuntimeException.class )
	public void testDeleteFailure(){
		assertNull(dao.delete(-1));
	}

	@Test
	public void testDeleteSuccess(){
		assertTrue(dao.delete(1));
	}

	protected class SensorComparable extends EqualsBuilder{

		private Sensor s;
		public SensorComparable(Sensor s){ 
			this.s = s;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || obj.getClass() != s.getClass()) { return false; }

			Sensor sensor = (Sensor) obj;
			String[] excludeFields = {"id", "datas", "addDate"};
			return EqualsBuilder.reflectionEquals(sensor, s, excludeFields);
		}
	}
}