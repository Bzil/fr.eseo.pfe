package fr.eseo.sensor.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import fr.eseo.sensor.api.bean.Data;
import fr.eseo.sensor.api.bean.Sensor;
import fr.eseo.sensor.api.bean.SensorType;

public class DataGenerator {

	public static Data getOneData(boolean onPhone){
		Data data = new Data();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			data.setDate(simpleDateFormat.parse("18/11/1990"));
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			sensor.setAddDate(simpleDateFormat.parse("18/11/1990"));
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
