package com.smarthospital.smarthospital.model;

/**
 * Created by HOME-PC on 27.11.2016.
 */

public class Hospital {

    public String id;
    public String name;
    public String description;
    public Image image;
    public Location location;

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
}
