package com.collegefootballstandings.app;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TeamLayout extends LinearLayout {

	public TeamLayout(Context context, Team team) {
		super(context);

		TeamLayout.inflate(getContext(), R.layout.team_layout, this);

		assignVariables(team);
	}

	private void assignVariables(Team team) {

		TextView number = (TextView) this.findViewById(R.id.rankTextView);
		TextView teamName = (TextView) this.findViewById(R.id.teamNameTextView);
		TextView votes = (TextView) this.findViewById(R.id.votesTextView);

		number.setText(team.getRank());
		teamName.setText(team.getMarket() + " " + team.getName());
		votes.setText(team.getPoints());
	}

}
