package com.smarthospital.smarthospital.model;

/**
 * Created by HOME-PC on 27.11.2016.
 */

public class Image {

    public String id;
    public String url;

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
