package fr.eseo.sensor.api.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * Cette entité défini les capteurs qui composent l'application
 * @author Basile Chapellier
 *
 */

@Entity
public class Sensor {

	/**
	 * Unique id
	 */
	@Id
	@GeneratedValue
	private int id;
	/**
	 * Date where sensor had been add to the app
	 */
	private Date addDate;
	/**
	 * Type of the data, unity
	 */
	private String unity;
	/**
	 * Define what type of graphic
	 */
	private SensorType sensorType;

	protected Sensor() {
		// for hibernate
	}

	public Sensor(Date addDate, String unity, SensorType sensorType) {
		super();
		this.addDate = addDate;
		this.unity = unity;
		this.sensorType = sensorType;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getUnity() {
		return unity;
	}

	public void setUnity(String unity) {
		this.unity = unity;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public SensorType getSensorType() {
		return sensorType;
	}

	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}
	
	

}
