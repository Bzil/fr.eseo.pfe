package fr.eseo.sensor.api.bean;
/**
 * Enumération présantant tout les types de graphique
 * @author Basile CHAPELLIER
 *@version 1.0
 */
public enum SensorType {
	/**
	 * Fléche de direction, par exemple la direction du vent
	 */
	ARROW,
	/**
	 * Graphique standart d'une courbe y = f(x)
	 */
	GRAPH,
	/**
	 * Graphique affichant plusieurs données du même type, pour comparer
	 */
	GRAPH_X,
	/**
	 * Image
	 */
	PICTURE
}
