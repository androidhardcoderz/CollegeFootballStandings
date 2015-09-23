package com.collegefootballstandings.app;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Storage {

	Context context;
	public static final String KEY = "DATA";
	public static final String DATE = "DATE";

	SharedPreferences preferences;

	public Storage(Context context) {
		this.context = context;
		this.preferences = PreferenceManager
				.getDefaultSharedPreferences(context.getApplicationContext());
	}

	public void storeDate() {
		edit().putString(DATE, new Dater(context).getFormattedDate()).apply();

	}

	public void storeString(String s) {
		edit().putString(KEY, s).apply();
	}

	public String getString(String key, String defValue) {
		return preferences.getString(key, defValue);
	}

	public Editor edit() {
		return preferences.edit();
	}

}
