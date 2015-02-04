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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;
/**
 * Cette entité défini les capteurs qui composent l'application
 * @author Basile Chapellier
 * @version 1.0
 */

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sensor implements Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -8908304865712781641L;
	/**
	 * Identifiant unique
	 */
	@Id
	@GeneratedValue
	private int id;
	/**
	 * Date d'ajout du capteur à la station.
	 */
	@NotNull
	private Date addDate;
	/**
	 * Unité de la donnée.
	 */
	@NotNull
	private String unity;
	/**
	 * Type de graphique
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private SensorType sensorType;	
	/**
	 * Etat de la batterie, faux par défaut
	 */
	@NotNull
	@Type(type="true_false")
	private Boolean lowBattery;
	/**
	 * Localisation sur capteur.
	 */
	private double longitude;
	private double latitude;
	/**
	 * Nom du capteur
	 */
	private String name;
	
	@OneToMany(mappedBy="sensor", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Data> datas = new HashSet<Data>();
	/**
	 * Sampling frequency 
	 */
	@NotNull
	private long samplingFrequency;
	/**
	 * Sampling frequency 
	 */
	@NotNull
	private long statementFrequency;

	public Sensor() {
		// for hibernate
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
	
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public long getStatementFrequency() {
		return statementFrequency;
	}

	public void setStatementFrequency(long statementFrequency) {
		this.statementFrequency = statementFrequency;
	}

	public void setGpsLocation(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Override
	public String toString(){
		return new StringBuffer(" Id : ").append(getId())
				.append(" SensorType : ").append(getSensorType())
				.append(" Fs : ").append(getSamplingFrequency())
				.append(" Fe : ").append(getStatementFrequency())
				.append(" unity : ").append(getUnity())
				.append(" Low Battery : ").append(getLowBattery())
				.toString();
	}

}
