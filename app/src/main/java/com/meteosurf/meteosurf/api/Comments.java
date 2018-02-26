package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comments {

    @SerializedName("comment")
    @Expose
    private List<Comment> comment = null;

    public Comments() {
    }

    public Comments(List<Comment> comment) {
        this.comment = comment;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public Comments withComment(List<Comment> comment) {
        this.comment = comment;
        return this;
    }

}
