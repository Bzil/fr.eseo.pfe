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

import fr.eseo.sensor.api.bean.User;
import fr.eseo.sensor.api.dao.UserDao;

/**
 * User Web Service to produce json
 * @author Basile Chapellier
 * @version 1.0
 */
@Path("/user")
public class UserService {
	/**
	 * Specific encoding type
	 */
	private static final String ENCODING  = "UTF-8";
	/**
	 * Data dao
	 */
	private UserDao userDao = new UserDao();
		
	/**
	 * Save a user
	 * @param user 
	 * @return reponse in json
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON}) 
	@Path("/post")
	public Response createDataInJSON(User user) {
		userDao.saveOrUpdate(user);
		String result = "Data saved : " + user;
		return Response.status(201).entity(result).build();
	}
	
	/**
	 * Get a specific user in json from id
	 * @param id of user
	 * @return user
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + ENCODING)
	public User getData(@PathParam("id") String id){
		return userDao.getOne(Integer.parseInt(id));
	}
	/**
	 * Produce in json the full list of user containts in base 
	 * @return list of user
	 */
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + ENCODING)
	public List<User> getDatas(){
		return userDao.getAll();
	}
}
