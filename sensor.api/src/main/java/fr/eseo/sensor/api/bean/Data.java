package fr.eseo.sensor.api.bean;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * Classe définissant une donnée envoyée par un capteur
 * @author Basile CHAPELLIER
 * @version 1.0
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
	 * Identifiant unique de la donnée.
	 */
	@Id
	@GeneratedValue
	private int id;
	/**
	 * Date de la mesure.
	 */
	@NotNull
	private Timestamp date;
	/**
	 * Valeur de la donnée reçue.
	 */
	@NotNull
	private String value;
	/**
	 * Validation de l'exportation de la données en base.
	 */
	@NotNull
	@Type(type="true_false")
	private Boolean isOnPhone;
	/**
	 * Id du sensor, utilisé pour éviter la boucle infini avec la persistance du capteur.
	 */
	private int sensorId;
	/**
	 * Le capteur qui envoie les données
	 */
	@XmlTransient
	@ManyToOne
	private Sensor sensor;

	public Data() {
		// for hibernate
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public void setDate(long date) {
		this.date = new Timestamp(date);
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
				.append(" On Phone : ").append(getIsOnPhone())
				.toString();
	}

}
