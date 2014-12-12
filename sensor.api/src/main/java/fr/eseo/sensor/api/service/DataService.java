package fr.eseo.sensor.api.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.eseo.sensor.api.bean.Data;
import fr.eseo.sensor.api.dao.DataDao;

@Path("/data")
@Produces(MediaType.APPLICATION_JSON)
public class DataService {
	
	// Read http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-jersey-client/
	
	private DataDao dao = new DataDao();
		
	@GET
	@Path("datas")
	public List<Data> getDatas(@PathParam("param") String msg){
		return dao.getAll();
	}
	
	@GET
	@Path("{id}")
	public Data getData(@PathParam("param") String id){
		//return new Data(new Sensor(new Date(System.currentTimeMillis()), "m", SensorType.GRAPH), "1.2", 1);
		return dao.getOne(Integer.parseInt(id));
	}
	
	@POST
	@Path("post")
	public Response createDataInJSON(Data data) {
		dao.saveOrUpdate(data);
		String result = "Data saved : " + data;
		return Response.status(201).entity(result).build();
 	}
}
