package com.collegefootballstandings.app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.AsyncTask.Status;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class APFragment extends Fragment {

	ProgressBar pBar;
	LinearLayout layout;

	interface Compeletion {
		void onComplete(ArrayList<Team> data);
	}

	Compeletion completion = new Compeletion() {

		@Override
		public void onComplete(ArrayList<Team> data) {
			pBar.setVisibility(View.INVISIBLE);

			// check null
			if (data == null) {
				Log.i("DATA", "null");
				// check for stored data
				if (!new Storage(getActivity()).getString(Storage.KEY, "")
						.equals("")) {

					// use last data stored!
					ParseData pData = new ParseData();
					try {
						for (Team team : pData.parseRankings(new Storage(
								getActivity()).getString(Storage.KEY, ""))) {
							layout.addView(new TeamLayout(getActivity(), team));
						}
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				}

			} else {
				// load data into layouts
				for (Team team : data) {
					layout.addView(new TeamLayout(getActivity(), team));
				}
			}

		}
	};

	public void showErrorDialog() {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				getActivity());

		// set title
		alertDialogBuilder.setTitle("Network Error");

		// set dialog message
		alertDialogBuilder
				.setMessage(
						"No Internet Connection Detected, Please Connect To A Network")
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, close
						// current activity
						System.exit(0);

					}
				});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	public APFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			if (new CheckForInternetConnectionThread().execute(getActivity())
					.get() == false) {
				// no connection stop thread to download
				showErrorDialog();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_host, null);

		pBar = (ProgressBar) view.findViewById(R.id.progressBar1);
		layout = (LinearLayout) view.findViewById(R.id.rankingsLinearLayout);
		customTitle(view);

		TextView weekTV = (TextView) view.findViewById(R.id.weekTextView);

		BackgroundDownloaderTask task = new BackgroundDownloaderTask(
				completion, weekTV, getActivity());
		task.execute();

		return view;
	}

	private void customTitle(View view) {

		TextView title = (TextView) view.findViewById(R.id.titleTextView);
		title.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
		new Fonts().setFontLollipopLightRoboto(title);
	}

	BackgroundDownloaderTask task;

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (task != null && task.getStatus() == Status.RUNNING) {
			try {
				task.cancel(true);
			} catch (Exception ex) {

			}
		}
	}
}
