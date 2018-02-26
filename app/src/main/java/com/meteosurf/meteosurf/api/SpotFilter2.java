package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpotFilter2 {

    @SerializedName("filter2")
    @Expose
    private List<Filter2> filter2 = null;

    public SpotFilter2() {
    }

    public SpotFilter2(List<Filter2> filter2) {
        this.filter2 = filter2;
    }

    public List<Filter2> getFilter2() {
        return filter2;
    }

    public void setFilter2(List<Filter2> filter2) {
        this.filter2 = filter2;
    }

    public SpotFilter2 withFilter2(List<Filter2> filter2) {
        this.filter2 = filter2;
        return this;
    }

}
