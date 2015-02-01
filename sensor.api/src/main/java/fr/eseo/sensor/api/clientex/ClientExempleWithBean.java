package fr.eseo.sensor.api.clientex;
/**
 * @author Basile Chapellier
 * @version 1.0
 */
import java.util.Date;

import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import fr.eseo.sensor.api.bean.Data;
import fr.eseo.sensor.api.bean.Sensor;
import fr.eseo.sensor.api.bean.SensorType;

public class ClientExempleWithBean {

	private static String URL_SERVEUR = "http://localhost:8080/WeatherBase";
	
	public static void main(String[] args) {
		ClientExempleWithBean ce = new ClientExempleWithBean();
		ce.addSensorEx();
		ce.addSensorEx2();
		ce.addDataEx();
	}
	
	private void addSensorEx(){
		Sensor input = new Sensor();
		input.setUnity("m");
		input.setAddDate(new Date(System.currentTimeMillis()));
		input.setLowBattery(false);
		input.setStatementFrequency(1222);
		input.setSamplingFrequency(1000);
		input.setGpsLocation(-0.127512, 51.507222);
		input.setName("Sensor_Arrow");
		input.setSensorType(SensorType.ARROW);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
						
			WebResource webResource = client.resource(URL_SERVEUR);
			ClientResponse response = webResource.path("rest").path("sensor").path("post").accept("application/json").type("application/json").post(ClientResponse.class, input);

			if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			System.out.println("Sensor : " + output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	private void addSensorEx2(){
		Sensor input = new Sensor();
		input.setUnity("s");
		input.setAddDate(new Date(System.currentTimeMillis()));
		input.setLowBattery(false);
		input.setStatementFrequency(1322);
		input.setSamplingFrequency(1050);
		input.setGpsLocation(-0.12712, 2.07222);
		input.setName("Sensor_Graph");
		input.setSensorType(SensorType.GRAPH);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
						
			WebResource webResource = client.resource(URL_SERVEUR);
			ClientResponse response = webResource.path("rest").path("sensor").path("post").accept("application/json").type("application/json").post(ClientResponse.class, input);

			if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			System.out.println("Sensor : " + output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	private void addDataEx(){
		/*
		 SensorDao dao = new SensorDao();
		Sensor sensor = dao.getOne(1);
		*/
		Sensor sensor = new Sensor();
		sensor.setAddDate(new Date(System.currentTimeMillis()));
		sensor.setUnity("s");
		sensor.setStatementFrequency(1222);
		sensor.setName("Sensor_Test");
		sensor.setSensorType(SensorType.GRAPH);
		sensor.setGpsLocation(-0.127512, 51.507222);
		Data input = new Data();
		input.setDate(System.currentTimeMillis());
		input.setIsOnPhone(false);
		input.setValue("11");
		input.setSensor(sensor);
		
		input.setSensorId(1);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			
			WebResource webResource = client.resource(URL_SERVEUR);
			ClientResponse response = webResource.path("rest").path("data").path("post").accept("application/json").type("application/json").post(ClientResponse.class, input);

			if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			System.out.println("Data : " + output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}