package com.example.osamaabdulrehman.procom18.Models;

import android.net.Uri;

import java.util.List;

/**
 * Created by Osama Abdul Rehman on 3/30/2018.
 */

public class Category {
    public String name;
    public String imageURL;
    public List<Competition> competitionList;

    public Category(){

    }

    public Category(String name, String imageURL, List<Competition> competitionList) {
        this.name = name;
        this.imageURL = imageURL;
        this.competitionList = competitionList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<Competition> getCompetitionList() {
        return competitionList;
    }

    public void setCompetitionList(List<Competition> competitionList) {
        this.competitionList = competitionList;
    }
}
