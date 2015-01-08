package fr.eseo.sensor.api.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
/***
 * This class defini the user who can access the data
 * @author Basile Chapellier
 *	@version 1.0
 */

@Entity
@XmlRootElement
public class User implements Serializable{
	
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 6372470904979536158L;
	/**
	 * Unique id
	 */
	@Id
	@GeneratedValue
	private int id;
	/**
	 * last name of the user
	 */
	@NotNull
	private String lastName;
	/**
	 * first name of the user
	 */
	@NotNull
	private String firstName;
	/**
	 * last date of connection
	 */
	private Date lastConnection;
	@Enumerated
	private UserRole userRole;
	
	public User() {
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

}
