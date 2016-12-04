package com.smarthospital.smarthospital.model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HOME-PC on 27.11.2016.
 */

public class Hospital implements Parcelable {

    public String id;
    public String name;
    public String description;
    public Image image;
    public Location location;

    public Hospital (){
    }

    protected Hospital(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        image = in.readParcelable(Image.class.getClassLoader());
        location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<Hospital> CREATOR = new Creator<Hospital>() {
        @Override
        public Hospital createFromParcel(Parcel in) {
            return new Hospital(in);
        }

        @Override
        public Hospital[] newArray(int size) {
            return new Hospital[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public Image getImage() {
        return image;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", location=" + location +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeParcelable(image, i);
        parcel.writeParcelable(location, i);
    }
}
