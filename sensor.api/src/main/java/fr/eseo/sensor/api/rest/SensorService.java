package fr.eseo.sensor.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/json/sensor")
public class SensorService {

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSenorInJson() { //@PathParam("param") String msg
		// SensorDao sensorDao = new SensorDao();
		// return sensorDao.find(msg);
		return null;
	}

}