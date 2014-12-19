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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.eseo.sensor.api.bean.Sensor;
import fr.eseo.sensor.api.bean.SensorType;
import fr.eseo.sensor.api.dao.HibernateUtil;
import fr.eseo.sensor.api.dao.SensorDao;

@Path("/sensor")
public class SensorService {

	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MMM-yyyy");
	private static final String ENCODING  = "UTF-8";
	
	private SensorDao sensorDao = new SensorDao();

	@GET
	@Path("sensors")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + ENCODING)
	public List<Sensor> getSensors(){
		return sensorDao.getAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + ENCODING)
	public Sensor getSensor(@PathParam("id") String id){
		return sensorDao.getOne(Integer.parseInt(id));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON) 
	@Path("post")
	public Response createDataInJSON(Sensor sensor) {
		sensorDao.saveOrUpdate(sensor);
		String result = "Data saved : " + sensor;
		return Response.status(201).entity(result).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
	@Path("post")
	public Response createSensorInJSON(
			@FormParam("date") String date,
			@FormParam("unity") String unity,
			@FormParam("sensorType") String sensorType,
			@FormParam("samplingFrequency") long samplingFrequency
			) {
		Sensor sensor = new Sensor();
		sensor.setUnity(unity);
		sensor.setSamplingFrequency(samplingFrequency);
		sensor.setSensorType(SensorType.valueOf(sensorType));
		try {
			sensor.setAddDate(DATE_FORMATTER.parse(date));
		} catch ( ParseException e){
			sensor.setAddDate(new Date(System.currentTimeMillis()));
		}

		sensorDao.saveOrUpdate(sensor);
		String result = "Data saved : " + sensor;
		return Response.status(201).entity(result).build();       
	}
}
