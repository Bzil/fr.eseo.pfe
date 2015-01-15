package fr.eseo.sensor.api.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Type;

/**
 * Class who define the data send by the sensor
 * @author Basile CHAPELLIER
 *	@version 1.0
 */

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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
	 * 
	 */
	private int sensorId;
	/**
	 * The value of the data
	 */
	@NotNull
	private String value;
	/**
	 * Boolean to check if the data is on any smartphone
	 */
	@NotNull
	@Type(type="true_false")
	private Boolean isOnPhone;

	/**
	 * the sensor where the data come from
	 */
	@XmlTransient
	@ManyToOne
	private Sensor sensor;

	public Data() {
		// for hibernate
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
		if(sensor!=null){
			this.sensor = sensor;
			this.sensorId = sensor.getId();
		}
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

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	
	@Override
	public String toString(){
		return new StringBuffer(" Id : ").append(getId())
				.append(" Value : ").append(getValue())
				.append(" Sensor Id : ").append((getSensor() != null) ? this.getSensor().getId() : "null")
				.append(" Date : ").append(getDate())
				.append(" On Phone :").append(getIsOnPhone())
				.toString();
	}

}
