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

import fr.eseo.sensor.api.bean.Sensor;
import fr.eseo.sensor.api.bean.SensorType;
import fr.eseo.sensor.api.dao.SensorDao;
/**
 * Sensor Web Service to produce json response
 * @author Basile Chapellier
 * @version 1.0
 */
@Path("/sensor")
public class SensorService {
	/**
	 * Date formatter to add date with the same pattern
	 */
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	/**
	 * Specific encoding
	 */
	private static final String ENCODING  = "UTF-8";
	/**
	 * Sensor dao
	 */
	private SensorDao sensorDao;

	public SensorService(){
		this(new SensorDao());
	}

	public SensorService(SensorDao sensorDao) {
		super();
		this.sensorDao = sensorDao;
	}
	/**
	 * Get list of all sensor
	 * @return list of sensor
	 */
	@GET
	@Path("sensors")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + ENCODING)
	public List<Sensor> getSensors(){
		return sensorDao.getAll();
	}
	/**
	 * Get one sensor information
	 * @param id of the sensor
	 * @return sensor information in json
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + ENCODING)
	public Sensor getSensor(@PathParam("id") String id){
		return sensorDao.getOne(Integer.parseInt(id));
	}
	@GET
	@Path("sensors/lowbattery")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + ENCODING)
	public List<Sensor> getSensorWhithoutBattery(){
		return sensorDao.getAllSensorWithLowBattery();
	}
	/**
	 * Service to Add one sensor
	 * @param sensor 
	 * @return Response in json
	 */
	@POST
	@Path("post")
	@Consumes(MediaType.APPLICATION_JSON) 
	public Response createSensorInJSON(Sensor sensor) {
		sensorDao.saveOrUpdate(sensor);
		String result = "Data saved : " + sensor;
		return Response.status(Response.Status.CREATED).entity(result).build();
	}
	/**
	 * Service to Add one sensor
	 * @param name of the sensor
	 * @param date of add
	 * @param unity of measure
	 * @param sensorType king of graph
	 * @param samplingFrequency frequency of measure
	 * @param statementFrequency frequency of statement
	 * @return response in json
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
	@Path("post/param")
	public Response createSensorInJSON(
			@FormParam("name") String name,
			@FormParam("date") String date,
			@FormParam("unity") String unity,
			@FormParam("sensorType") String sensorType,
			@FormParam("samplingFrequency") long samplingFrequency,
			@FormParam("statementFrequency") long statementFrequency
			) {
		Sensor sensor = new Sensor();
		sensor.setName(name);
		sensor.setUnity(unity);
		sensor.setSamplingFrequency(samplingFrequency);
		sensor.setStatementFrequency(statementFrequency);
		sensor.setSensorType(SensorType.valueOf(sensorType));
		try {
			sensor.setAddDate(DATE_FORMATTER.parse(date));
		} catch ( ParseException e){
			sensor.setAddDate(new Date(System.currentTimeMillis()));
		}
		sensor.setLowBattery(false);
		sensorDao.saveOrUpdate(sensor);
		String result = "Data saved : " + sensor;
		return Response.status(Response.Status.CREATED).entity(result).build();       
	}
	/**
	 * Get a list of lastest data
	 * @param size of the list expected
	 * @return list of data
	 */
	@GET
	@Path("lasts/{size}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + ENCODING)
	public List<Sensor> getLastData(@PathParam("size") String size){
		return sensorDao.getLastest(Integer.parseInt(size));
	}
	/**
	 * Method to edit data form sensor
	 * @param id of the sensor
	 * @param unity new value of unity, -1 if unchange
	 * @param name new value of name, -1 if unchange
	 * @param statementFrequency new value of statementFrequency, -1 if unchange
	 * @param samplingFrequency new value ofsamplingFrequency, -1 if unchange
	 * @return OK if success else FAILED 
	 */
	@POST
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response editSensorById(
			@PathParam("id") int id,
			@FormParam("unity") String unity,
			@FormParam("name") String name,
			@FormParam("statementFrequency") long statementFrequency,
			@FormParam("samplingFrenquency") long samplingFrequency ) {
		Sensor sensor = sensorDao.getOne(id);
		if(sensor != null){
			if(!unity.equals("-1") && !unity.equals(sensor.getUnity())) sensor.setUnity(unity);
			if(!name.equals("-1") && !name.equals(sensor.getName())) sensor.setName(name);
			if(samplingFrequency != -1 && samplingFrequency != sensor.getSamplingFrequency()) sensor.setSamplingFrequency(samplingFrequency);
			if(statementFrequency != -1 && statementFrequency != sensor.getStatementFrequency()) sensor.setStatementFrequency(statementFrequency);

			sensorDao.update(sensor);
			return Response.status(Response.Status.OK).entity("Data saved : " + sensor).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity("Data saved : " + sensor).build();
	}

	public SensorDao getSensorDao() {
		return sensorDao;
	}

	public void setSensorDao(SensorDao sensorDao) {
		this.sensorDao = sensorDao;
	}

}
