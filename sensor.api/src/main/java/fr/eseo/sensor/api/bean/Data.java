package fr.eseo.sensor.api.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 * Class who define the data send by the sensor
 * @author Basile CHAPELLIER
 *
 */

@Entity
public class Data {

	/**
	 * Unique id
	 */
	 @Id
	@GeneratedValue
	private int id;
	/**
	 * the date of the mesure
	 */
	private Date date;
	/**
	 * the sensor where the data come from
	 */
	@ManyToOne
	private Sensor sensor;	
	/**
	 * The value of the data
	 */
	private String value;
	
	protected Data() {
		// for hibernate
	}

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
	
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}
}
