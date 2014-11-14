package fr.eseo.sensor.api.bean;

import java.util.Date;
/**
 * Cette entité défini les capteurs qui composent l'application
 * @author Basile Chapellier
 *
 */
//@Entity
//@Table(name="SENSOR")
public class Sensor {

	/**
	 * Unique id
	 */
	//@Id
	//@GeneratedValue
	//private int id;
	/**
	 * Date where sensor had been add to the app
	 */
	private Date addDate;
	/**
	 * Type of the data, unity
	 */
	private String type;


	public Sensor(Date addDate, String type) {
		super();
		this.addDate = addDate;
		this.type = type;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
