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
 * Classe définisant un utilisateur
 * @author Basile Chapellier
 * @version 1.0
 */

@Entity
@XmlRootElement
public class User implements Serializable{
	
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 6372470904979536158L;
	/**
	 * Identifiant unique
	 */
	@Id
	@GeneratedValue
	private int id;
	/**
	 * Nom de famille de l'utilisateur
	 */
	@NotNull
	private String lastName;
	/**
	 * Prénom de l'utilisateur
	 */
	@NotNull
	private String firstName;
	/**
	 * Date de dernière connexion
	 */
	private Date lastConnection;
	/**
	 * Rôles de l'utilisateur
	 */
	@Enumerated
	private UserRole userRole;
	/**
	 * Mot de passe de l'utilisateur
	 */
	//TODO hash pwd
	private String password;
	
	public User() {
		// for hibernate
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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
