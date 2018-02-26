package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpotFilter3 {

    @SerializedName("filter3")
    @Expose
    private List<Filter3> filter3 = null;

    public SpotFilter3() {
    }

    public SpotFilter3(List<Filter3> filter3) {
        this.filter3 = filter3;
    }

    public List<Filter3> getFilter3() {
        return filter3;
    }

    public void setFilter3(List<Filter3> filter3) {
        this.filter3 = filter3;
    }

    public SpotFilter3 withFilter3(List<Filter3> filter3) {
        this.filter3 = filter3;
        return this;
    }

}
