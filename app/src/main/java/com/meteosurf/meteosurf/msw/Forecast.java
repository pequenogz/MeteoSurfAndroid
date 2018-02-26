package com.meteosurf.meteosurf.msw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("timestamp")
    @Expose
    private int timestamp;
    @SerializedName("localTimestamp")
    @Expose
    private int localTimestamp;
    @SerializedName("issueTimestamp")
    @Expose
    private int issueTimestamp;
    @SerializedName("fadedRating")
    @Expose
    private int fadedRating;
    @SerializedName("solidRating")
    @Expose
    private int solidRating;
    @SerializedName("swell")
    @Expose
    private Swell swell;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("condition")
    @Expose
    private Condition condition;
    @SerializedName("charts")
    @Expose
    private Charts charts;

    public Forecast() {
    }

    public Forecast(int timestamp, int localTimestamp, int issueTimestamp, int fadedRating, int solidRating, Swell swell, Wind wind, Condition condition, Charts charts) {
        this.timestamp = timestamp;
        this.localTimestamp = localTimestamp;
        this.issueTimestamp = issueTimestamp;
        this.fadedRating = fadedRating;
        this.solidRating = solidRating;
        this.swell = swell;
        this.wind = wind;
        this.condition = condition;
        this.charts = charts;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public Forecast withTimestamp(int timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public int getLocalTimestamp() {
        return localTimestamp;
    }

    public void setLocalTimestamp(int localTimestamp) {
        this.localTimestamp = localTimestamp;
    }

    public Forecast withLocalTimestamp(int localTimestamp) {
        this.localTimestamp = localTimestamp;
        return this;
    }

    public int getIssueTimestamp() {
        return issueTimestamp;
    }

    public void setIssueTimestamp(int issueTimestamp) {
        this.issueTimestamp = issueTimestamp;
    }

    public Forecast withIssueTimestamp(int issueTimestamp) {
        this.issueTimestamp = issueTimestamp;
        return this;
    }

    public int getFadedRating() {
        return fadedRating;
    }

    public void setFadedRating(int fadedRating) {
        this.fadedRating = fadedRating;
    }

    public Forecast withFadedRating(int fadedRating) {
        this.fadedRating = fadedRating;
        return this;
    }

    public int getSolidRating() {
        return solidRating;
    }

    public void setSolidRating(int solidRating) {
        this.solidRating = solidRating;
    }

    public Forecast withSolidRating(int solidRating) {
        this.solidRating = solidRating;
        return this;
    }

    public Swell getSwell() {
        return swell;
    }

    public void setSwell(Swell swell) {
        this.swell = swell;
    }

    public Forecast withSwell(Swell swell) {
        this.swell = swell;
        return this;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Forecast withWind(Wind wind) {
        this.wind = wind;
        return this;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Forecast withCondition(Condition condition) {
        this.condition = condition;
        return this;
    }

    public Charts getCharts() {
        return charts;
    }

    public void setCharts(Charts charts) {
        this.charts = charts;
    }

    public Forecast withCharts(Charts charts) {
        this.charts = charts;
        return this;
    }

}
