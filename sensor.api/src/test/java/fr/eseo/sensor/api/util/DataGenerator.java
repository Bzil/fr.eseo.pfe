package fr.eseo.sensor.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import fr.eseo.sensor.api.bean.Data;
import fr.eseo.sensor.api.bean.Sensor;
import fr.eseo.sensor.api.bean.SensorType;

public class DataGenerator {

	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public static Data getOneData(boolean onPhone){
		Data data = new Data();
		try {
			data.setDate(DATE_FORMATTER.parse("18-11-1990 00:00:00").getTime());
		} catch (ParseException e) {
		}
		data.setIsOnPhone(onPhone);
		data.setSensor(getOneSensor(false));
		data.setValue("3,2");
		return data;
	}
	
	public static Data getOneData(boolean onPhone, Sensor sensor){
		Data data = getOneData(onPhone);
		data.setSensor(sensor);
		return data;
	}
	
	public static Sensor getOneSensor(boolean lowBattery){
		Sensor sensor = new Sensor();
		try {
			sensor.setAddDate(DATE_FORMATTER.parse("18-11-1990 00:00:00"));
		} catch (ParseException e) {
		}
		sensor.setLowBattery(lowBattery);
		sensor.setSamplingFrequency(0);
		sensor.setSensorType(SensorType.ARROW);
		sensor.setPlace("place");
		sensor.setUnity("m");
		return sensor;
	}
	
}
