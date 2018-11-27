package edu.fsu.cs.mobile.nudge;

public class Card {

    String cardID;
    String cardTitle;
    String displayName;
    String cellNumber;
    String workNumber;
    String homeNumber;
    String personalEmail;
    String workEmail;
    String personalWebsite;
    String workWebsite;
    String linkedIn;
    String facebook;
    String twitter;
    String note;

    public Card() {
    }

    public Card(String cardID, String cardTitle, String displayName, String cellNumber, String workNumber, String homeNumber, String personalEmail, String workEmail, String personalWebsite, String linkedIn, String facebook, String twitter, String note) {
        this.cardID = cardID;
        this.cardTitle = cardTitle;
        this.displayName = displayName;
        this.cellNumber = cellNumber;
        this.workNumber = workNumber;
        this.homeNumber = homeNumber;
        this.personalEmail = personalEmail;
        this.workEmail = workEmail;
        this.personalWebsite = personalWebsite;
        this.linkedIn = linkedIn;
        this.facebook = facebook;
        this.twitter = twitter;
        this.note = note;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getPersonalWebsite() {
        return personalWebsite;
    }

    public void setPersonalWebsite(String personalWebsite) {
        this.personalWebsite = personalWebsite;
    }

    public String getWorkWebsite() {
        return workWebsite;
    }

    public void setWorkWebsite(String workWebsite) {
        this.workWebsite = workWebsite;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
