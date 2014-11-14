package fr.eseo.sensor.api.bean;
/**
 * Enum who define role of the user
 * @author Basile Chapellier
 */
public enum Role {
	/**
	 * This role can manage sensor and view
	 */
	ADMIN,
	/**
	 * This role can view the data
	 */
	VIEWER,
	/**
	 * This role can't do anything whith the app
	 */
	//TODO change name
	INVITE
}
