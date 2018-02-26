package com.meteosurf.meteosurf.msw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Components {

    @SerializedName("combined")
    @Expose
    private Combined combined;
    @SerializedName("primary")
    @Expose
    private Primary primary;
    @SerializedName("secondary")
    @Expose
    private Secondary secondary;
    @SerializedName("tertiary")
    @Expose
    private Tertiary tertiary;

    public Components() {
    }

    public Components(Combined combined, Primary primary, Secondary secondary) {
        this.combined = combined;
        this.primary = primary;
        this.secondary = secondary;
        this.tertiary = tertiary;
    }

    public Combined getCombined() {
        return combined;
    }

    public void setCombined(Combined combined) {
        this.combined = combined;
    }

    public Components withCombined(Combined combined) {
        this.combined = combined;
        return this;
    }

    public Primary getPrimary() {
        return primary;
    }

    public void setPrimary(Primary primary) {
        this.primary = primary;
    }

    public Components withPrimary(Primary primary) {
        this.primary = primary;
        return this;
    }

    public Secondary getSecondary() {
        return secondary;
    }

    public void setSecondary(Secondary secondary) {
        this.secondary = secondary;
    }

    public Components withSecondary(Secondary secondary) {
        this.secondary = secondary;
        return this;
    }

    public Tertiary getTertiary() {
        return tertiary;
    }

    public void setTertiary(Tertiary tertiary) {
        this.tertiary = tertiary;
    }

    public Components withTertiary(Tertiary tertiary) {
        this.tertiary = tertiary;
        return this;
    }

}
