package fr.eseo.sensor.api.bean;
/**
 * Enum who define role of the user
 * @author Basile Chapellier
 */
public enum UserRole {
	/**
	 * This role can manage sensor and view
	 */
	ADMIN,
	/**
	 * This role can view the data
	 */
	VIEWER,
	/**
	 * This role can just see global informations
	 */
	//TODO change name
	INVITE
}