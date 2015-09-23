package com.collegefootballstandings.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.collegefootballstandings.app.APFragment.Compeletion;

public class BackgroundDownloaderTask extends
		AsyncTask<Void, String, ArrayList<Team>> {

	Compeletion completion;
	Context context;
	TextView tv;

	private String api_call;

	public BackgroundDownloaderTask(Compeletion completion, TextView tv,
			Context context) {
		this.completion = completion;
		this.tv = tv;
		this.context = context;
	}

	@Override
	protected void onProgressUpdate(String... values) {
		tv.setText("Week " + values[0]);
		new Fonts().setFontLollipopLightRoboto(tv);
		super.onProgressUpdate(values);
	}

	// "https://s3-us-west-2.amazonaws.com/standings/week.txt"

	@Override
	protected ArrayList<Team> doInBackground(Void... params) {

		String responseString = "";

		// stores repsonse string into context
		// if date has been downloaded already do not download again until the
		// next day
		if (!new Dater(context).compareDates()) {

			api_call = "https://s3-us-west-2.amazonaws.com/ncaastandings/ncaastandingsfile.txt";

			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response;

			try {
				// make a HTTP request
				response = httpclient.execute(new HttpGet(api_call));
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					// request successful - read the response and close the
					// connection
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					response.getEntity().writeTo(out);
					out.close();
					responseString = out.toString();

				} else {
					// request failed - close the connection
					response.getEntity().getContent().close();
					throw new IOException(statusLine.getReasonPhrase());

				}
			} catch (UnknownHostException uhe) {
				uhe.printStackTrace();
				return null;

			} catch (Exception e) {
				Log.d("Test", "Couldn't make a successful request!");
				e.printStackTrace();
				return null;
			}

			// store new date with response string
			new Storage(context).storeString(responseString);
			new Storage(context).storeDate();

			Log.i("RESULT", "DOWNLOADED");

		} else {
			// date is already the same for the day
			Log.i("RESULT", "NOT DOWNLAODED");
			responseString = new Storage(context).getString(Storage.KEY, "");
		}

		ParseData pData = new ParseData();
		try {
			publishProgress(pData.getWeekNumber(responseString));
			return pData.parseRankings(responseString);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

	}

	@Override
	protected void onPostExecute(ArrayList<Team> result) {
		super.onPostExecute(result);
		this.completion.onComplete(result);
	}

}
