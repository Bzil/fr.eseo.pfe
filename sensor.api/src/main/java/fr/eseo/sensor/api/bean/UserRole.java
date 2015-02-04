package fr.eseo.sensor.api.bean;
/**
 * Enumération des roles possibles
 * @author Basile CHAPELLIER
 * @version 1.0
 */
public enum UserRole {
	/**
	 * Peut mananger les capteurs, utilisateurs, possède les droits de vue des données.
	 */
	ADMIN,
	/**
	 * Peut voir les informations relatives aux capteurs (données, fréquences...)
	 */
	VIEWER,
	/**
	 * Peut juste voir les information globales, liste des capteurs
	 */
	GUEST
}
