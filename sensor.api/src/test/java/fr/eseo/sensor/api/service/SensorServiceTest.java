package fr.eseo.sensor.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.eseo.sensor.api.bean.Data;
import fr.eseo.sensor.api.bean.Sensor;
import fr.eseo.sensor.api.dao.SensorDao;
import fr.eseo.sensor.api.util.DataGenerator;

@RunWith(MockitoJUnitRunner.class)
public class SensorServiceTest {
	
	@Mock
	private SensorDao daoMock = new SensorDao();
	
	private SensorService ss;
	
	@Before
	public void setUp(){
		ss = new SensorService(daoMock);
	}

	@Test
	public void testGetOneCorrectSensor(){
		Sensor s = DataGenerator.getOneSensor(false);
		doReturn(s).when(daoMock).getOne(anyInt());
		assertEquals(s.toString(), ss.getSensor("12").toString());
	}
	
	@Test
	public void testGetOneWrongSensor(){
		doReturn((Data)null).when(daoMock).getOne(anyInt());
		assertNull(ss.getSensor("12"));
	}
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testGetOneWrongSensorThrowException(){
		doReturn((Data)null).when(daoMock).getOne(anyInt());
		assertNull(ss.getSensor("12").toString());
	}
	
	@Test
	public void testGetCorrectDatas(){
		List<Sensor> list = new ArrayList<Sensor>();
		list.add(DataGenerator.getOneSensor(false));
		list.add(DataGenerator.getOneSensor(true));
		doReturn(list).when(daoMock).getAll();
		assertEquals(list.toString(), ss.getSensors().toString());
	}
	
	@Test
	public void testGetWrongDatas(){
		doReturn(null).when(daoMock).getAll();
		assertNull(ss.getSensors());
	}
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testGetWrongDatasThrowException(){
		doReturn(null).when(daoMock).getAll();
		assertNull(ss.getSensors().toString());
	}
	
	@Test
	public void testGetCorrectSensorWhithoutBattery(){
		List<Sensor> list = new ArrayList<Sensor>();
		list.add(DataGenerator.getOneSensor(true));
		list.add(DataGenerator.getOneSensor(true));
		doReturn(list).when(daoMock).getAllSensorWithLowBattery();
		assertEquals(list.toString(), ss.getSensorWhithoutBattery().toString());
	}
	
	@Test
	public void testGetWrongSensorWhithoutBattery(){
		doReturn(null).when(daoMock).getAllSensorWithLowBattery();
		assertNull(ss.getSensorWhithoutBattery());
	}
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testGetWrongSensorWhithoutBatteryThrowException(){
		doReturn(null).when(daoMock).getAllSensorWithLowBattery();
		assertNull(ss.getSensorWhithoutBattery().toString());
	}
	
	@Test
	public void testGetCorrectLastDatas(){
		List<Sensor> list = new ArrayList<Sensor>();
		list.add(DataGenerator.getOneSensor(false));
		list.add(DataGenerator.getOneSensor(true));
		doReturn(list).when(daoMock).getLastest(anyInt());
		assertEquals(list.toString(), ss.getLastData("2").toString());
	}
	
	@Test
	public void testGetWrongLastDatas(){
		doReturn(null).when(daoMock).getLastest(anyInt());
		assertNull(ss.getLastData("1"));
	}
	
	@Test
	public void testCreateSensorInJSON(){
		Sensor s = DataGenerator.getOneSensor(false);
		doNothing().when(daoMock).saveOrUpdate(any(Sensor.class));;

		Response r = ss.createSensorInJSON(s);
		assertEquals(201,r.getStatus());
		assertEquals("Data saved :  Id : 0 SensorType : ARROW Fs : 0 unity : m", r.getEntity().toString());
	}
	
	@Test
	public void testCreateSensorInJSONWithParameter(){
		doNothing().when(daoMock).saveOrUpdate(any(Sensor.class));;

		Response r = ss.createSensorInJSON("18-11-1990", "m", "ARROW", (long)0);
		assertEquals(201,r.getStatus());
		assertEquals("Data saved :  Id : 0 SensorType : ARROW Fs : 0 unity : m", r.getEntity().toString());
	}
}
