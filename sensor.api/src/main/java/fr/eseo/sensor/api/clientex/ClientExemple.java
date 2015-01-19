package fr.eseo.sensor.api.clientex;
/**
 * @author Basile Chapellier
 * @version 1.0
 */
import java.util.Date;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import fr.eseo.sensor.api.bean.Data;
import fr.eseo.sensor.api.bean.Sensor;
import fr.eseo.sensor.api.bean.SensorType;

public class ClientExemple {

	public static void main(String[] args) {
		ClientExemple ce = new ClientExemple();
		ce.addSensorEx();
		ce.addDataEx();
	}
	
	private void addSensorEx(){
		Sensor input = new Sensor();
		input.setUnity("m");
		input.setAddDate(new Date(System.currentTimeMillis()));
		input.setLowBattery(false);
		input.setSamplingFrequency(1000);
		input.setSensorType(SensorType.ARROW);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
						
			WebResource webResource = client.resource("http://localhost:8080/sensorAPI/rest/sensor/post/");
			ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			System.out.println("Sensor : " + output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	private void addDataEx(){
		Sensor sensor = new Sensor();
		sensor.setAddDate(new Date(System.currentTimeMillis()));
		sensor.setUnity("s");
		sensor.setSensorType(SensorType.GRAPH);
		Data input = new Data();
		input.setDate(System.currentTimeMillis());
		input.setIsOnPhone(false);
		input.setValue("11");
		input.setSensor(sensor);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			
			WebResource webResource = client.resource("http://localhost:8080/sensorAPI/rest/data/post/");
			ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			System.out.println("Data : " + output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}