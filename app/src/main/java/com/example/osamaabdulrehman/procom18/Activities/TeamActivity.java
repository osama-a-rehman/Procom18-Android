package com.example.osamaabdulrehman.procom18.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.osamaabdulrehman.procom18.Adapters.TeamRVAdapter;
import com.example.osamaabdulrehman.procom18.Models.Team;
import com.example.osamaabdulrehman.procom18.R;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity {

    private RecyclerView teamRecyclerView;

    public List<Team> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        setTitle("Our Team");

        teamRecyclerView = (RecyclerView) findViewById(R.id.teamRecyclerView);

        teamList = new ArrayList<>();

        teamList.add(new Team(R.drawable.team_1, "BILAL MUNAF", "President"));
        teamList.add(new Team(R.drawable.team_2, "MUHAMMAD HAMZA", "Vice President (EE)"));
        teamList.add(new Team(R.drawable.team_3, "FARRUKH", "Vice President (BBA)"));
        teamList.add(new Team(R.drawable.team_4, "YOUSUF ASHRAF", "General Secretary"));
        teamList.add(new Team(R.drawable.team_5, "FARYAL KHASKHELI", "Director HR"));
        teamList.add(new Team(R.drawable.team_6, "MEHAR ALI", "Treasurer"));
        teamList.add(new Team(R.drawable.team_7, "TAHA HUSSAIN", "Event Administrator"));
        teamList.add(new Team(R.drawable.team_8, "SARA AKHTAR", "Event Administrator"));

        TeamRVAdapter teamRVAdapter = new TeamRVAdapter(this, teamList);

        teamRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        teamRecyclerView.setHasFixedSize(true);

        teamRecyclerView.setAdapter(teamRVAdapter);
    }
}
