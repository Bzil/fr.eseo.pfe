package fr.eseo.sensor.api.clientex;

import java.text.SimpleDateFormat;
import java.util.Random;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.representation.Form;

import fr.eseo.sensor.api.bean.SensorType;

public class ClientExempleWithParam {

	private static String URL_SERVEUR = "http://192.168.4.191:8080/WeatherBase";

	public static void main(String[] args) {
		ClientExempleWithParam ce = new ClientExempleWithParam();
		System.out.println("Add sensor");
		ce.addSensorEx();
		System.out.println("Add Data");
		Random rand = new Random();
		int i = 0;
		while(true){
		//for(int i = 0; i<50; i++){
			ce.addDataEx("02-02-2014 00:" + i * 10 + ":03",String.valueOf(rand.nextInt(150)),"1"); //10 * Math.sin(i*0.1)
			i++;
		}
	}


	private void addSensorEx(){
		Form form = new Form();
		form.add("name", "Capteur_Vent");
		form.add("date", "2015-01-31 17:14:06");
		form.add("unity", "m/s");
		form.add("sensorType", SensorType.GRAPH.toString());
		form.add("samplingFrequency", 1222);
		form.add("statementFrequency", 1000);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource(URL_SERVEUR);
			ClientResponse response = webResource.path("rest").path("sensor").path("post").path("param").type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);

			if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			System.out.println("Sensor : " + output);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addDataEx(String date, String value, String sensorId){
		Form form = new Form();
		form.add("date", date);
		form.add("value", value);
		form.add("sensorId", sensorId);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource(URL_SERVEUR);
			ClientResponse response = webResource.path("rest").path("data").path("post").path("param").type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);

			if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			System.out.println("Data : " + output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	private void editSensorEx(){
		Form form = new Form();
		form.add("unity", "mm");
		form.add("name", "Capteur_Pluie");
		form.add("statementFrequency", -1);
		form.add("samplingFrenquency", 1234);

		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource(URL_SERVEUR);
			ClientResponse response = webResource.path("rest").path("sensor").path("2").type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);

			if (response.getStatus() != Response.Status.OK.getStatusCode()) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			System.out.println("Sensor : " + output);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
