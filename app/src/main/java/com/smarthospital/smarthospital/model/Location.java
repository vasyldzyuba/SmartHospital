package com.smarthospital.smarthospital.model;

/**
 * Created by HOME-PC on 27.11.2016.
 */

public class Location {

    public String address;
    public double latitude;
    public double longitude;

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

