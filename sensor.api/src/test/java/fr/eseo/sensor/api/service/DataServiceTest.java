package fr.eseo.sensor.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

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
import fr.eseo.sensor.api.dao.DataDao;
import fr.eseo.sensor.api.util.DataGenerator;

@RunWith(MockitoJUnitRunner.class)
public class DataServiceTest {

	@Mock
	private DataDao daoMock = new DataDao();
	
	private DataService ds;
	
	@Before
	public void setUp(){
		ds = new DataService(daoMock);
	}
	
	@Test
	public void testGetOneCorrectData(){
		Data d = DataGenerator.getOneData(false);
		doReturn(d).when(daoMock).getOne(anyInt());
		assertEquals(d.toString(), ds.getData("12").toString());
	}
	
	@Test
	public void testGetOneWrongData(){
		doReturn((Data)null).when(daoMock).getOne(anyInt());
		assertNull(ds.getData("12"));
	}
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testGetOneWrongDataThrowException(){
		doReturn((Data)null).when(daoMock).getOne(anyInt());
		assertNull(ds.getData("12").toString());
	}
	
	@Test
	public void testGetCorrectDatas(){
		List<Data> list = new ArrayList<Data>();
		list.add(DataGenerator.getOneData(false));
		list.add(DataGenerator.getOneData(true));
		doReturn(list).when(daoMock).getAll();
		assertEquals(list.toString(), ds.getDatas().toString());
	}
	
	@Test
	public void testGetWrongDatas(){
		doReturn(null).when(daoMock).getAll();
		assertNull(ds.getDatas());
	}
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testGetWrongDatasThrowException(){
		doReturn(null).when(daoMock).getAll();
		assertNull(ds.getDatas().toString());
	}

	@Test
	public void testGetCorrectDatasFromSensor(){
		List<Data> list = new ArrayList<Data>();
		list.add(DataGenerator.getOneData(false));
		list.add(DataGenerator.getOneData(true));
		doReturn(list).when(daoMock).getAllFromSensor(anyInt());
		assertEquals(list.toString(), ds.getDatasFromSensor("0").toString());
	}
	
	@Test
	public void testGetWrongDatasFromSensor(){
		doReturn(null).when(daoMock).getAllFromSensor(anyInt());
		assertNull(ds.getDatasFromSensor("1"));
	}
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testGetWrongDatasThrowExceptionFromSensor(){
		doReturn(null).when(daoMock).getAllFromSensor(anyInt());
		assertNull(ds.getDatasFromSensor("1").toString());
	}
	
	@Test
	public void testGetCorrectLastDatas(){
		List<Data> list = new ArrayList<Data>();
		list.add(DataGenerator.getOneData(false));
		list.add(DataGenerator.getOneData(true));
		doReturn(list).when(daoMock).getLastest(anyInt());
		assertEquals(list.toString(), ds.getLastData("2").toString());
	}
	
	@Test
	public void testGetWrongLastDatas(){
		doReturn(null).when(daoMock).getLastest(anyInt());
		assertNull(ds.getLastData("1"));
	}

	@Test
	public void testCreateDataInJSON(){
		Data d = DataGenerator.getOneData(false);
		doNothing().when(daoMock).saveOrUpdate(any(Data.class));;

		Response r = ds.createDataInJSON(d);
		assertEquals(201,r.getStatus());
		assertEquals("Data saved :  Id : 0 Value : 3,2 Sensor Id : 0 Date : Sun Nov 18 00:00:00 CET 1990 On Phone :false", r.getEntity().toString());
	}
	
	@Test
	public void testCreateSensorInJSONWithParameter(){
		doNothing().when(daoMock).saveOrUpdate(any(Data.class));;
		
		Response r = ds.createDataInJSON("18-11-1990", "3,2", "0");
		assertEquals(201,r.getStatus());
		assertEquals("Data saved :  Id : 0 Value : 3,2 Sensor Id : null Date : Sun Nov 18 00:00:00 CET 1990 On Phone :null", r.getEntity().toString());
	}
}
