package com.meteosurf.meteosurf.msw;

public class Wind {

    private int speed;
    private int direction;
    private String compassDirection;
    private int chill;
    private int gusts;
    private String unit;

    public Wind() {
    }

    public Wind(int speed, int direction, String compassDirection, int chill, int gusts, String unit) {
        this.speed = speed;
        this.direction = direction;
        this.compassDirection = compassDirection;
        this.chill = chill;
        this.gusts = gusts;
        this.unit = unit;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Wind withSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Wind withDirection(int direction) {
        this.direction = direction;
        return this;
    }

    public String getCompassDirection() {
        return compassDirection;
    }

    public void setCompassDirection(String compassDirection) {
        this.compassDirection = compassDirection;
    }

    public Wind withCompassDirection(String compassDirection) {
        this.compassDirection = compassDirection;
        return this;
    }

    public int getChill() {
        return chill;
    }

    public void setChill(int chill) {
        this.chill = chill;
    }

    public Wind withChill(int chill) {
        this.chill = chill;
        return this;
    }

    public int getGusts() {
        return gusts;
    }

    public void setGusts(int gusts) {
        this.gusts = gusts;
    }

    public Wind withGusts(int gusts) {
        this.gusts = gusts;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Wind withUnit(String unit) {
        this.unit = unit;
        return this;
    }

}
