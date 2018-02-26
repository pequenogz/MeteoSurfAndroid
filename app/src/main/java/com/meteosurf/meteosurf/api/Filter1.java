package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filter1 {

    @SerializedName("id1")
    @Expose
    private int id1;
    @SerializedName("name")
    @Expose
    private String name;

    public Filter1() {
    }

    public Filter1(int id1, String name) {
        this.id1 = id1;
        this.name = name;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Filter1 withId1(int id1){
        this.id1 = id1;
        return this;
    }

    public Filter1 withName(String name){
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
