package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filter4 {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("id3")
    @Expose
    private int id3;
    @SerializedName("name")
    @Expose
    private String name;

    public Filter4() {
    }

    public Filter4(int id, int id3, String name) {
        this.id = id;
        this.id3 = id3;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Filter4 withId(int id) {
        this.id = id;
        return this;
    }

    public int getId3() {
        return id3;
    }

    public void setId3(int id3) {
        this.id3 = id3;
    }

    public Filter4 withId3(int id3) {
        this.id3 = id3;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Filter4 withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
