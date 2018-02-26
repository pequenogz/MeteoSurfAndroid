package com.meteosurf.meteosurf.msw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Swell {

    @SerializedName("absMinBreakingHeight")
    @Expose
    private double absMinBreakingHeight;
    @SerializedName("absMaxBreakingHeight")
    @Expose
    private double absMaxBreakingHeight;
    @SerializedName("probability")
    @Expose
    private int probability;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("minBreakingHeight")
    @Expose
    private double minBreakingHeight;
    @SerializedName("maxBreakingHeight")
    @Expose
    private double maxBreakingHeight;
    @SerializedName("components")
    @Expose
    private Components components;

    public Swell() {
    }

    public Swell(double absMinBreakingHeight, double absMaxBreakingHeight, int probability, String unit, double minBreakingHeight, double maxBreakingHeight, Components components) {
        this.absMinBreakingHeight = absMinBreakingHeight;
        this.absMaxBreakingHeight = absMaxBreakingHeight;
        this.probability = probability;
        this.unit = unit;
        this.minBreakingHeight = minBreakingHeight;
        this.maxBreakingHeight = maxBreakingHeight;
        this.components = components;
    }

    public double getAbsMinBreakingHeight() {
        return absMinBreakingHeight;
    }

    public void setAbsMinBreakingHeight(double absMinBreakingHeight) {
        this.absMinBreakingHeight = absMinBreakingHeight;
    }

    public Swell withAbsMinBreakingHeight(double absMinBreakingHeight) {
        this.absMinBreakingHeight = absMinBreakingHeight;
        return this;
    }

    public double getAbsMaxBreakingHeight() {
        return absMaxBreakingHeight;
    }

    public void setAbsMaxBreakingHeight(double absMaxBreakingHeight) {
        this.absMaxBreakingHeight = absMaxBreakingHeight;
    }

    public Swell withAbsMaxBreakingHeight(double absMaxBreakingHeight) {
        this.absMaxBreakingHeight = absMaxBreakingHeight;
        return this;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public Swell withProbability(int probability) {
        this.probability = probability;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Swell withUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public double getMinBreakingHeight() {
        return minBreakingHeight;
    }

    public void setMinBreakingHeight(double minBreakingHeight) {
        this.minBreakingHeight = minBreakingHeight;
    }

    public Swell withMinBreakingHeight(double minBreakingHeight) {
        this.minBreakingHeight = minBreakingHeight;
        return this;
    }

    public double getMaxBreakingHeight() {
        return maxBreakingHeight;
    }

    public void setMaxBreakingHeight(double maxBreakingHeight) {
        this.maxBreakingHeight = maxBreakingHeight;
    }

    public Swell withMaxBreakingHeight(double maxBreakingHeight) {
        this.maxBreakingHeight = maxBreakingHeight;
        return this;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public Swell withComponents(Components components) {
        this.components = components;
        return this;
    }

}
