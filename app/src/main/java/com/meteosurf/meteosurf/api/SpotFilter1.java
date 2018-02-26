package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpotFilter1 {

    @SerializedName("filter1")
    @Expose
    private List<Filter1> filter1 = null;

    public SpotFilter1() {
    }

    public SpotFilter1(List<Filter1> filter1) {
        this.filter1 = filter1;
    }

    public List<Filter1> getFilter1() {
        return filter1;
    }

    public void setFilter1(List<Filter1> filter1) {
        this.filter1 = filter1;
    }

    public SpotFilter1 withFilter1(List<Filter1> filter1) {
        this.filter1 = filter1;
        return this;
    }

}
