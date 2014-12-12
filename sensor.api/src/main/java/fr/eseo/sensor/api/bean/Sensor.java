package fr.eseo.sensor.api.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Cette entité défini les capteurs qui composent l'application
 * @author Basile Chapellier
 *
 */

@Entity
@XmlRootElement
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
	@NotNull
	private Date addDate;
	/**
	 * Type of the data, unity
	 */
	@NotNull
	private String unity;
	/**
	 * Define what type of graphic
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private SensorType sensorType;
	/**
	 * Sampling frequency 
	 */
	private long samplingFrequency;
	
	public Sensor() {
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

	public long getSamplingFrequency() {
		return samplingFrequency;
	}

	public void setSamplingFrequency(long samplingFrequency) {
		this.samplingFrequency = samplingFrequency;
	}
	
	

}
