package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpotFilter4 {

    @SerializedName("filter4")
    @Expose
    private List<Filter4> filter4 = null;

    public SpotFilter4() {
    }

    public SpotFilter4(List<Filter4> filter4) {
        this.filter4 = filter4;
    }

    public List<Filter4> getFilter4() {
        return filter4;
    }

    public void setFilter4(List<Filter4> filter4) {
        this.filter4 = filter4;
    }

    public SpotFilter4 withFilter4(List<Filter4> filter4) {
        this.filter4 = filter4;
        return this;
    }

}
