package fr.eseo.sensor.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Matchers.anyInt;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import fr.eseo.sensor.api.bean.Data;
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
		assertEquals(" Id : 0 Value : 3,2 Sensor Id : 0 Date : Sun Nov 18 00:00:00 CET 1990 On Phone :false", ds.getData("12").toString());
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
		assertEquals("[ Id : 0 Value : 3,2 Sensor Id : 0 Date : Sun Nov 18 00:00:00 CET 1990 On Phone :false,  Id : 0 Value : 3,2 Sensor Id : 0 Date : Sun Nov 18 00:00:00 CET 1990 On Phone :true]", ds.getDatas().toString());
	}

	public void testGetWrongDatas(){
		doReturn(null).when(daoMock).getAll();
		assertNull(ds.getDatas());
	}
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testGetWrongDatasThrowException(){
		doReturn(null).when(daoMock).getAll();
		assertNull(ds.getDatas().toString());
	}
	
	
}
