package com.meteosurf.meteosurf.msw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Charts {

    @SerializedName("swell")
    @Expose
    private String swell;
    @SerializedName("period")
    @Expose
    private String period;
    @SerializedName("wind")
    @Expose
    private String wind;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("sst")
    @Expose
    private String sst;

    public Charts() {
    }

    public Charts(String swell, String period, String wind, String pressure, String sst) {
        this.swell = swell;
        this.period = period;
        this.wind = wind;
        this.pressure = pressure;
        this.sst = sst;
    }

    public String getSwell() {
        return swell;
    }

    public void setSwell(String swell) {
        this.swell = swell;
    }

    public Charts withSwell(String swell) {
        this.swell = swell;
        return this;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Charts withPeriod(String period) {
        this.period = period;
        return this;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public Charts withWind(String wind) {
        this.wind = wind;
        return this;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public Charts withPressure(String pressure) {
        this.pressure = pressure;
        return this;
    }

    public String getSst() {
        return sst;
    }

    public void setSst(String sst) {
        this.sst = sst;
    }

    public Charts withSst(String sst) {
        this.sst = sst;
        return this;
    }

}
