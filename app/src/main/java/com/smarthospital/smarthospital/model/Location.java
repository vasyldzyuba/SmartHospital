package com.smarthospital.smarthospital.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HOME-PC on 27.11.2016.
 */


public class Location implements Parcelable {

//   змінні -  рядок адреси, довгота і широта числом з плаваючою комою
    public String address;
    public double latitude;
    public double longitude;

    public Location(){

    }
// введення значень
    protected Location(Parcel in) {
        address = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }
// створюється нова локація
    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
// методи, які повертають введені значення
    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
// локація виводиться в консоль
    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
// об'єднює всі методи
    @Override
    public int describeContents() {
        return 0;
    }
// встановлюємо наш об'єкт в parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }
}

