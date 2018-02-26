package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("idSpot")
    @Expose
    private int idSpot;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("comment")
    @Expose
    private String comment;

    public Comment() {
    }

    public Comment(int idSpot, String user, String date, String comment) {
        this.idSpot = idSpot;
        this.user = user;
        this.date = date;
        this.comment = comment;
    }

    public int getIdSpot() {
        return idSpot;
    }

    public void setIdSpot(int idSpot) {
        this.idSpot = idSpot;
    }

    public Comment withIdSpot(int idSpot) {
        this.idSpot = idSpot;
        return this;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Comment withUser(String user) {
        this.user = user;
        return this;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Comment withDate(String date) {
        this.date = date;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment withComment(String comment) {
        this.comment = comment;
        return this;
    }

}
