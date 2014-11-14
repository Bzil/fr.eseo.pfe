package fr.eseo.sensor.api.bean;

import java.util.Date;
/***
 * This class defini the user who can access the data
 * @author Basile Chapellier
 *
 */
//@Entity
//@Table(name="USER")
public class User {

	private String lastName;
	private String firstName;
	private Date lastConnection;
	private Role role;
	
	public User(String lastName, String firstName, Date lastConnection,	Role role) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.lastConnection = lastConnection;
		this.role = role;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
