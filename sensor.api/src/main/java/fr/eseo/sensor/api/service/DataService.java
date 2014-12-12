package fr.eseo.sensor.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.eseo.sensor.api.bean.Data;
import fr.eseo.sensor.api.bean.Sensor;
import fr.eseo.sensor.api.bean.SensorType;
import fr.eseo.sensor.api.dao.DataDao;
import fr.eseo.sensor.api.dao.SensorDao;

@Path("/data")
@Produces(MediaType.APPLICATION_JSON)
public class DataService {

	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy");
	
	private DataDao dataDao = new DataDao();
	private SensorDao sensorDao = new SensorDao();

	@GET
	@Path("datas")
	public List<Data> getDatas(@PathParam("param") String msg){
		return dataDao.getAll();
	}

	@GET
	@Path("{id}")
	public Data getData(@PathParam("param") String id){
		/*Sensor s = new Sensor(new Date(System.currentTimeMillis()), "m", SensorType.GRAPH);
		sensorDao.saveOrUpdate(s);
		Data d = new Data(s, "1.2", 1);
		dataDao.saveOrUpdate(d);
		return dataDao.getAll().get(0);
		//return new Data(new Sensor(new Date(System.currentTimeMillis()), "m", SensorType.GRAPH), "1.2", 1);*/
		return dataDao.getOne(Integer.parseInt(id));
	}

	@POST
	@Consumes({MediaType.APPLICATION_JSON}) 
	@Path("post")
	public Response createDataInJSON(Data data) {
		dataDao.saveOrUpdate(data);
		String result = "Data saved : " + data;
		return Response.status(201).entity(result).build();
	}
	// curl -H "Content-Type: application/json" -d '{"date":"12-12-2014","value":"111", "sensorId":1  }' http://localhost:8080/sensorAPI/
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
	@Path("post")
	public Response createSensorInJSON(
			@FormParam("date") String date,
			@FormParam("value") String value,
			@FormParam("sensorId") String sensorId
			) {
		Data data = new Data();
		data.setValue(value);
		//data.setSensor(sensorDao.getOne(Integer.parseInt(sensorId)));
		try {
			data.setDate(DATE_FORMATTER.parse(date));
		} catch ( ParseException e){
			data.setDate(new Date(System.currentTimeMillis()));
		}

		dataDao.saveOrUpdate(data);
		String result = "Data saved : " + data;
		return Response.status(201).entity(result).build();       
	}
}
