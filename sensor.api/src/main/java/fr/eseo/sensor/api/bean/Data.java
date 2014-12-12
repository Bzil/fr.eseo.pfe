package fr.eseo.sensor.api.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Class who define the data send by the sensor
 * @author Basile CHAPELLIER
 *
 */

@Entity
@XmlRootElement
public class Data {

	/**
	 * Unique id
	 */
	 @Id
	@GeneratedValue
	private long id;
	/**
	 * the date of the mesure
	 */
	@NotNull
	private Date date;
	/**
	 * the sensor where the data come from
	 */
	@ManyToOne
	private Sensor sensor;	
	/**
	 * The value of the data
	 */
	@NotNull
	private String value;
	/**
	 * Boolean to check if the data is on any smartphone
	 */
	private Boolean isOnPhone;
	
	
	public Data() {
		// for hibernate
	}

	public Data(Sensor sensor, String value, int order) {
		super();
		// definir dans le spec, 
		//this.date = new Date(long)(System.currentTimeMillis()-1000*order));
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
	
	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public Boolean getIsOnPhone() {
		return isOnPhone;
	}

	public void setIsOnPhone(Boolean isOnPhone) {
		this.isOnPhone = isOnPhone;
	}
}
