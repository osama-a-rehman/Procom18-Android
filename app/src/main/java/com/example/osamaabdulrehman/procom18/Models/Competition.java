package com.example.osamaabdulrehman.procom18.Models;

public class Competition {
    public String competitionId;
    public String competitionName;
    public String competitionDescription;
    public String competitionSponsoredBy;
    public String competitionSponsoredByLogoURL;
    public String totalMembersAllowed;
    public String competitionURL;

    public Competition() {
    }

    public Competition(String competitionId, String competitionName, String competitionDescription, String competitionSponsoredBy, String competitionSponsoredByLogoURL, String totalMembersAllowed, String competitionURL) {
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.competitionDescription = competitionDescription;
        this.competitionSponsoredBy = competitionSponsoredBy;
        this.competitionSponsoredByLogoURL = competitionSponsoredByLogoURL;
        this.totalMembersAllowed = totalMembersAllowed;
        this.competitionURL = competitionURL;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionDescription() {
        return competitionDescription;
    }

    public void setCompetitionDescription(String competitionDescription) {
        this.competitionDescription = competitionDescription;
    }

    public String getCompetitionSponsoredBy() {
        return competitionSponsoredBy;
    }

    public void setCompetitionSponsoredBy(String competitionSponsoredBy) {
        this.competitionSponsoredBy = competitionSponsoredBy;
    }

    public String getCompetitionSponsoredByLogoURL() {
        return competitionSponsoredByLogoURL;
    }

    public void setCompetitionSponsoredByLogoURL(String competitionSponsoredByLogoURL) {
        this.competitionSponsoredByLogoURL = competitionSponsoredByLogoURL;
    }

    public String getTotalMembersAllowed() {
        return totalMembersAllowed;
    }

    public void setTotalMembersAllowed(String totalMembersAllowed) {
        this.totalMembersAllowed = totalMembersAllowed;
    }

    public String getCompetitionURL() {
        return competitionURL;
    }

    public void setCompetitionURL(String competitionURL) {
        this.competitionURL = competitionURL;
    }
}
