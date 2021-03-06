package com.example.miguel.reouteselectorschool;

import java.util.ArrayList;

/**
 * Created by MIGUEL on 24/02/2018.
 */

public class Route {
    private ArrayList<Float> latitudeOfTheStops;
    private ArrayList<Float> longitudeOfTheStops;
    private int estimatedTimeMilliseconds;
    private int retryTimeMilliseconds;

    public Route() {
        this.latitudeOfTheStops = new ArrayList<>();
        this.longitudeOfTheStops = new ArrayList<>();
    }

    public void addLatitude(float latitude){
        latitudeOfTheStops.add(latitude);
    }

    public void addLongitude(float longitude){
        longitudeOfTheStops.add(longitude);
    }

    public ArrayList<Float> getLatitudeOfTheStops() {
        return latitudeOfTheStops;
    }

    public ArrayList<Float> getLongitudeOfTheStops() {
        return longitudeOfTheStops;
    }

    public int getEstimatedTimeMilliseconds() {
        return estimatedTimeMilliseconds;
    }
    public float getEstimatedTimeMinutes() {
        return (estimatedTimeMilliseconds/1000);
    }

    public void setEstimatedTimeMilliseconds(int estimatedTimeMilliseconds) {
        this.estimatedTimeMilliseconds = estimatedTimeMilliseconds;
    }

    public int getRetryTimeMilliseconds() {
        return retryTimeMilliseconds;
    }

    public float getRetryTimeMinutes() {
        return (retryTimeMilliseconds/1000);
    }

    public void setRetryTimeMilliseconds(int retryTimeMilliseconds) {
        this.retryTimeMilliseconds = retryTimeMilliseconds;
    }
}
