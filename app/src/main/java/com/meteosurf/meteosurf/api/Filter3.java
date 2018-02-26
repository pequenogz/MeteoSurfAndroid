package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filter3 {

    @SerializedName("id3")
    @Expose
    private int id3;
    @SerializedName("id2")
    @Expose
    private int id2;
    @SerializedName("name")
    @Expose
    private String name;

    public Filter3() {
    }

    public Filter3(int id3, int id2, String name) {
        this.id3 = id3;
        this.id2 = id2;
        this.name = name;
    }

    public int getId3() {
        return id3;
    }

    public void setId3(int id3) {
        this.id3 = id3;
    }

    public Filter3 withId3(int id3) {
        this.id3 = id3;
        return this;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public Filter3 withId2(int id2) {
        this.id2 = id2;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Filter3 withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
