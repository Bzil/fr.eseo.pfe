package fr.eseo.sensor.api.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.eseo.sensor.api.bean.Sensor;
import fr.eseo.sensor.api.dao.SensorDao;

@Path("/sensor")
@Produces(MediaType.APPLICATION_JSON)
public class SensorService {

	private SensorDao dao = new SensorDao();
	
	@GET
	@Path("sensors")
	public List<Sensor> getDatas(@PathParam("param") String msg){
		return dao.getAll();
	}
	
	@GET
	@Path("{id}")
	public Sensor getData(@PathParam("param") String id){
		return dao.getOne(Integer.parseInt(id));
	}
	
	@POST
	@Path("post")
	public Response createDataInJSON(Sensor sensor) {
		dao.saveOrUpdate(sensor);
		String result = "Data saved : " + sensor;
		return Response.status(201).entity(result).build();
 	}

}
