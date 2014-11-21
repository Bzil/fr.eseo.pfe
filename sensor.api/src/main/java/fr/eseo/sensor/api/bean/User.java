package fr.eseo.sensor.api.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/***
 * This class defini the user who can access the data
 * @author Basile Chapellier
 *
 */

@Entity
public class User {
	
	/**
	 * Unique id
	 */
	@Id
	@GeneratedValue
	private int id;
	private String lastName;
	private String firstName;
	private Date lastConnection;
	@Enumerated
	private UserRole userRole;
	
	protected User() {
		// for hibernate
	}

	public User(String lastName, String firstName, Date lastConnection,	UserRole userRole) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.lastConnection = lastConnection;
		this.userRole = userRole;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}

	public UserRole getRole() {
		return userRole;
	}

	public void setRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}
}
