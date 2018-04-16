package com.example.osamaabdulrehman.procom18.Models;

/**
 * Created by Osama Abdul Rehman on 4/12/2018.
 */

public class Team {
    private int teamImage;
    private String teamName;
    private String teamDesignation;

    public Team(int teamImage, String teamName, String teamDesignation) {
        this.teamImage = teamImage;
        this.teamName = teamName;
        this.teamDesignation = teamDesignation;
    }

    public int getTeamImage() {
        return teamImage;
    }

    public void setTeamImage(int teamImage) {
        this.teamImage = teamImage;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDesignation() {
        return teamDesignation;
    }

    public void setTeamDesignation(String teamDesignation) {
        this.teamDesignation = teamDesignation;
    }


}
