package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.SerializedName;

public class CommentBody {

    @SerializedName("idSpot")
    private int idSpot;
    @SerializedName("comment")
    private String comment;

    public CommentBody(int idSpot, String comment) {
        this.idSpot = idSpot;
        this.comment = comment;
    }

    public int getIdSpot() {
        return idSpot;
    }

    public void setIdSpot(int idSpot) {
        this.idSpot = idSpot;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
