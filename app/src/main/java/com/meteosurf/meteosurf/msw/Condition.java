package com.meteosurf.meteosurf.msw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Condition {

    @SerializedName("pressure")
    @Expose
    private int pressure;
    @SerializedName("temperature")
    @Expose
    private int temperature;
    @SerializedName("weather")
    @Expose
    private String weather;
    @SerializedName("unitPressure")
    @Expose
    private String unitPressure;
    @SerializedName("unit")
    @Expose
    private String unit;

    public Condition() {
    }

    public Condition(int pressure, int temperature, String weather, String unitPressure, String unit) {
        this.pressure = pressure;
        this.temperature = temperature;
        this.weather = weather;
        this.unitPressure = unitPressure;
        this.unit = unit;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public Condition withPressure(int pressure) {
        this.pressure = pressure;
        return this;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Condition withTemperature(int temperature) {
        this.temperature = temperature;
        return this;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Condition withWeather(String weather) {
        this.weather = weather;
        return this;
    }

    public String getUnitPressure() {
        return unitPressure;
    }

    public void setUnitPressure(String unitPressure) {
        this.unitPressure = unitPressure;
    }

    public Condition withUnitPressure(String unitPressure) {
        this.unitPressure = unitPressure;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Condition withUnit(String unit) {
        this.unit = unit;
        return this;
    }

}
