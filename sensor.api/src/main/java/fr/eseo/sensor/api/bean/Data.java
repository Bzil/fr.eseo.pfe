package fr.eseo.sensor.api.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Data implements Serializable{

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 7783315427114107586L;
	/**
	 * Unique id
	 */
	@Id
	@GeneratedValue
	private int id;
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
//	@XmlElement(name="data_value")
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
	
	public int getId() {
		return id;
	}

	public Boolean getIsOnPhone() {
		return isOnPhone;
	}

	public void setIsOnPhone(Boolean isOnPhone) {
		this.isOnPhone = isOnPhone;
	}
}
