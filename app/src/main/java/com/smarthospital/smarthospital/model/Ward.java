package com.smarthospital.smarthospital.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by V on 12/10/2016.
 */

public class Ward implements Parcelable{
    public String name;
    public String id;
    List<Doctor> doctors = new ArrayList<>();

    public Ward() {
    }

    protected Ward(Parcel in) {
        name = in.readString();
        id = in.readString();
        doctors = in.createTypedArrayList(Doctor.CREATOR);
    }

    public static final Creator<Ward> CREATOR = new Creator<Ward>() {
        @Override
        public Ward createFromParcel(Parcel in) {
            return new Ward(in);
        }

        @Override
        public Ward[] newArray(int size) {
            return new Ward[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", doctors=" + doctors +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(id);
        parcel.writeTypedList(doctors);
    }
}
