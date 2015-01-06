package fr.eseo.sensor.api.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;
/**
 * Cette entité défini les capteurs qui composent l'application
 * @author Basile Chapellier
 *
 */

@Entity
@XmlRootElement
public class Sensor implements Serializable{

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -8908304865712781641L;
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
	 * 
	 */
	@NotNull
	@Type(type="true_false")
	private Boolean lowBattery;
	
	@OneToMany(mappedBy="sensor", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Data> datas = new HashSet<Data>();
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

	public Set<Data> getDatas() {
		return datas;
	}

	public void setDatas(Set<Data> datas) {
		this.datas = datas;
	}

	public Boolean getLowBattery() {
		return lowBattery;
	}

	public void setLowBattery(Boolean lowBattery) {
		this.lowBattery = lowBattery;
	}
	
	

}
