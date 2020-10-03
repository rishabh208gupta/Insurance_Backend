package com.lti.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.SplittableRandom;

import org.springframework.stereotype.Component;

@Component
public class SendOtp {

	public static void sendSMS(String otpStr, long phoneNumber) {
		try {

			String apiKey = "apiKey=" + "YmV7pYnICng-Kc4NoVrZrK7P2O5thjSBpA9HNfsTZr";

			String message = "&message=" + URLEncoder.encode("Never share your OTP. Your OTP is " + otpStr, "UTF-8");

			String numbers = "&numbers=" + phoneNumber;

			String apiURL = "https://api.textlocal.in/send/?" + apiKey + message + numbers;

			URL url = new URL(apiURL);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line = "";
			StringBuilder sb = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}

			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String generateOtp() {
		SplittableRandom splittableRandom = new SplittableRandom();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 5; i++) {
			sb.append(splittableRandom.nextInt(0, 10));
		}
		return sb.toString();
	}

	public String generatePass() {
		SplittableRandom splittableRandom = new SplittableRandom();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 8; i++) {
			sb.append(splittableRandom.nextInt(0, 10));
		}
		return sb.toString();
	}

}
