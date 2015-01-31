package fr.eseo.sensor.api.clientex;

import javax.ws.rs.FormParam;
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

	public static void main(String[] args) {
		ClientExempleWithParam ce = new ClientExempleWithParam();
		System.out.println("Add sensor");
		ce.addSensorEx();
		System.out.println("Add Data");
		ce.addDataEx();
	}


	private void addSensorEx(){
		Form form = new Form();
		form.add("name", "TEST_Param");
		form.add("date", "2015-01-31 17:14:06");
		form.add("unity", "ms");
		form.add("sensorType", SensorType.GRAPH.toString());
		form.add("samplingFrequency", 1222);
		form.add("statementFrequency", 1000);
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource("http://localhost:8080/WeatherBase");
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

	private void addDataEx(){
		Form form = new Form();
		form.add("date", "2012-01-31 17:14:06");
		form.add("value", "2222");
		form.add("sensorId", "1");
		try {
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource("http://localhost:8080/WeatherBase");
			ClientResponse response = webResource.path("rest").path("data").path("post").path("param").type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);

			if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			System.out.println("Sensor : " + output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}


}
