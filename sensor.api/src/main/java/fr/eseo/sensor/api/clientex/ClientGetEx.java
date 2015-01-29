package fr.eseo.sensor.api.clientex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientGetEx {

	public static void main(String[] args) {
		ClientGetEx ce = new ClientGetEx();
		ce.realAllSensorEx();
	}
	
	/*
	 sensor/sensors
	 sensor/<id>
	 sensor/lowbattery
	 data/datas
	 data/<id>
	 data/datas/sensor/<id>
	 */
	
	
	// TODO changer localhost par ip
	public void realAllSensorEx(){
		String output = null;
		try {
			URL url = new URL("http://localhost:8080/WeatherBase/rest/sensor/sensors");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
