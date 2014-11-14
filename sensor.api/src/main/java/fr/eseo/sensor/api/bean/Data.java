package fr.eseo.sensor.api.bean;

import java.util.Date;
/**
 * Class who define the data send by the sensor
 * @author Basile CHAPELLIER
 *
 */
//@Entity
//@Table(name="DATA")
public class Data {

	/**
	 * Unique id
	 */
	 //@Id
	 //@GeneratedValue
	 //private int id;
	/**
	 * the date of the mesure
	 */
	private Date date;
	/**
	 * the sensor where the data come from
	 */
	private Sensor sensor;	
	/**
	 * The value of the data
	 */
	private String value;
	
	public Data(Date date, Sensor sensor, String value) {
		super();
		this.date = date;
		this.sensor = sensor;
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
