package com.earathou.webmonitor.ping.service;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class PingWebService {
	
	public static final String WEBPAGE_STATUS_OK = "OK";
	public static final String WEBPAGE_STATUS_NOT_AVAILABLE = "NOT AVAILABLE";
	
	public PingWebService() {
	}

	public String url(String url) {
		int responseCode = 0;
		HttpURLConnection connection;
		String responseString = WEBPAGE_STATUS_OK;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");

			responseCode = connection.getResponseCode();
			if (responseCode != 200) {
				System.out.println("Website not available: " + url);
				responseString = WEBPAGE_STATUS_NOT_AVAILABLE;
			}

		} catch (Exception e) {
			System.out.println("Website not available: " + url);
			responseString = WEBPAGE_STATUS_NOT_AVAILABLE;
		}
		
		return responseString;
	}
}
