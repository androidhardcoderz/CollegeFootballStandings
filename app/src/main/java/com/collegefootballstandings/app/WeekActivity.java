package com.collegefootballstandings.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

public class WeekActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_week);

		Toolbar toolBar = (Toolbar) findViewById(R.id.my_awesome_toolbar);

		toolBar.setBackgroundColor(Color.parseColor("#EFB53D"));
		toolBar.setTitle(R.string.app_name);
		toolBar.setTitleTextColor(Color.parseColor("#000000"));

		if (savedInstanceState == null) {
			APFragment apFragment = new APFragment();
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.container, apFragment).commit();
		}

	}

}
