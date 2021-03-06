package com.meteosurf.meteosurf.msw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tertiary {

    @SerializedName("height")
    @Expose
    private double height;
    @SerializedName("period")
    @Expose
    private int period;
    @SerializedName("direction")
    @Expose
    private double direction;
    @SerializedName("compassDirection")
    @Expose
    private String compassDirection;

    public Tertiary() {
    }

    public Tertiary(double height, int period, double direction, String compassDirection) {
        this.height = height;
        this.period = period;
        this.direction = direction;
        this.compassDirection = compassDirection;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Tertiary withHeight(double height) {
        this.height = height;
        return this;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Tertiary withPeriod(int period) {
        this.period = period;
        return this;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public Tertiary withDirection(double direction) {
        this.direction = direction;
        return this;
    }

    public String getCompassDirection() {
        return compassDirection;
    }

    public void setCompassDirection(String compassDirection) {
        this.compassDirection = compassDirection;
    }

    public Tertiary withCompassDirection(String compassDirection) {
        this.compassDirection = compassDirection;
        return this;
    }

}
