package com.smarthospital.smarthospital.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by V on 12/10/2016.
 */

public class Doctor implements Parcelable{
    public String name;
    public String speciality;
    public String bio;
    public Image photo;

    public Doctor (){

    }

    protected Doctor(Parcel in) {
        name = in.readString();
        speciality = in.readString();
        bio = in.readString();
        photo = in.readParcelable(Image.class.getClassLoader());
    }

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getBio() {
        return bio;
    }

    public Image getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", bio='" + bio + '\'' +
                ", photo=" + photo +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(speciality);
        parcel.writeString(bio);
        parcel.writeParcelable(photo, i);
    }
}
