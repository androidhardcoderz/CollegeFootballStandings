package com.collegefootballstandings.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class GetCurrentWeekRankings {

	private final String WEEK_CALL = "https://s3-us-west-2.amazonaws.com/standings/week.txt";

	public GetCurrentWeekRankings() {
		// TODO Auto-generated constructor stub
	}

	public Integer getWeekRanking() {
		String responseString;
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;

		try {
			// make a HTTP request
			response = httpclient.execute(new HttpGet(WEEK_CALL));
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				// request successful - read the response and close the
				// connection
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
				responseString = responseString.trim();

				System.out.println(responseString);

				int number;

				try {
					number = Integer.parseInt(responseString);
					return number;
				} catch (NumberFormatException nfe) {

				}

			} else {
				// request failed - close the connection
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());

			}
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
			return null;

		} catch (Exception e) {
			Log.d("Test", "Couldn't make a successful request! + AWS CALL");
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
}
