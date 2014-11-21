package fr.eseo.sensor.api.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.eseo.sensor.api.dao.DataDao;

@Path("/json/data")
public class DataService {

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDatas(@PathParam("param") String msg){
		return null;
		//return dataDao.getDatas(msg);
	}
}
