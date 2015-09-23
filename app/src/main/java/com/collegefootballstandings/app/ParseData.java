package com.collegefootballstandings.app;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseData {

	public ParseData() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Team> parseRankings(String data) throws JSONException {

		ArrayList<Team> rankings = new ArrayList<Team>();

		JSONObject obj = new JSONObject(data);

		JSONArray array = obj.getJSONArray("rankings");

		for (int i = 0; i < array.length(); i++) {
			Team team = new Team();

			JSONObject tObject = array.getJSONObject(i);
			team.setWeek(obj.getString("week"));
			team.setId(tObject.getString("id"));
			team.setMarket(tObject.getString("market"));
			team.setName(tObject.getString("name"));
			team.setPoints(tObject.getString("points"));
			team.setRank(tObject.getString("rank"));

			rankings.add(team);
		}

		return rankings;
	}

	public String getWeekNumber(String data) throws JSONException {

		JSONObject obj = new JSONObject(data);

		return obj.getString("week");
	}

}
