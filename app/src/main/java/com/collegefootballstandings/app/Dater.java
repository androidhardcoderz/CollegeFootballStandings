package com.collegefootballstandings.app;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.Log;

public class Dater {

	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	Context context;
	private String formattedDate;

	public Dater(Context context) {
		this.context = context;
		Date date = new Date();
		setFormattedDate(dateFormat.format(date));

	}

	public boolean compareDates() {
		Log.i("TODAY DATE", getFormattedDate());
		Log.i("STORED DATE",
				new Storage(this.context).getString(Storage.DATE, ""));
		if (new Storage(this.context).getString(Storage.DATE, "").equals(
				getFormattedDate())) {

			return true;
		}

		return false;

	}

	/**
	 * @return the formattedDate
	 */
	public String getFormattedDate() {
		return formattedDate;
	}

	/**
	 * @param formattedDate
	 *            the formattedDate to set
	 */
	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

}
