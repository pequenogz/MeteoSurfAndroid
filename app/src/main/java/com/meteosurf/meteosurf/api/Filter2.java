package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filter2 {

    @SerializedName("id2")
    @Expose
    private int id2;
    @SerializedName("id1")
    @Expose
    private int id1;
    @SerializedName("name")
    @Expose
    private String name;

    public Filter2() {
    }

    public Filter2(int id2, int id1, String name) {
        this.id2 = id2;
        this.id1 = id1;
        this.name = name;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public Filter2 withId2(int id2) {
        this.id2 = id2;
        return this;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public Filter2 withId1(int id1) {
        this.id1 = id1;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Filter2 withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
