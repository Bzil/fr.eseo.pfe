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
/**
 * Data Web Service to produce json
 * @author Basile Chapellier
 * @version 1.0
 */
@Path("/data")
public class DataService {
	
	/**
	 * Date parser
	 */
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy");
	/**
	 * Specific encoding type
	 */
	private static final String ENCODING  = "UTF-8";
	/**
	 * Data dao
	 */
	private DataDao dataDao = new DataDao();
	
	/**
	 * Produce in json the full list of data containts in base 
	 * @return list of data
	 */
	@GET
	@Path("/datas")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + ENCODING)
	public List<Data> getDatas(){
		return dataDao.getAll();
	}
	/**
	 * Produce in json a specific list of data from a sensor
	 * @param id of the sensor
	 * @return list of data
	 */
	@GET
	@Path("/datas/sensor/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + ENCODING)
	public List<Data> getDatasFromSensor(@PathParam("id") String id){
		return dataDao.getAllFromSensor(Integer.parseInt(id));
	}
	/**
	 * Get a specific data in json from id
	 * @param id of data
	 * @return data
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + ENCODING)
	public Data getData(@PathParam("id") String id){
		/*SensorDao sensorDao = new SensorDao();
		Sensor s = new Sensor(new Date(System.currentTimeMillis()), "m", SensorType.GRAPH);
		s.setLowBattery(false);
		sensorDao.saveOrUpdate(s);
		Data d = new Data(s, "1.2", 1);
		d.setIsOnPhone(false);
		d.setDate(new Date(System.currentTimeMillis()));
		dataDao.saveOrUpdate(d);*/
		return dataDao.getOne(Integer.parseInt(id));
	}
	/**
	 * Save a data
	 * @param data 
	 * @return reponse in json
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON}) 
	@Path("/post")
	public Response createDataInJSON(Data data) {
		dataDao.saveOrUpdate(data);
		String result = "Data saved : " + data;
		return Response.status(201).entity(result).build();
	}
	// curl -H "Content-Type: application/json" -d '{"date":"12-12-2014","value":"111", "sensorId":1  }' http://localhost:8080/sensorAPI/
	/**
	 * Save a data
	 * @param date of measure
	 * @param value of the measure
	 * @param sensorId
	 * @return response in json
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
	@Path("/post")
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
