package com.smarthospital.smarthospital.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOME-PC on 27.11.2016.
 */
/*Цей клас може використовуватися для передачі посилання на об'єкт
  від одного виду діяльності до іншої діяльності*/
public class Hospital implements Parcelable {

    /*Це для того щоб кожна лікарня мала своє id,
    потім за нею будем витягувати госпіталь з серверу*/
    public String id;
    /*Це для того щоб кожна лікарня мала своє ім'я*/
    public String name;
    /*Це для того щоб кожна лікарня мала свій опис*/
    public String description;
    /*Це для того щоб кожна лікарня мала своє фото*/
    public Image image;
    /*Це для того щоб кожна лікарня мала своє місце знаходження*/
    public Location location;

    public List<Ward> wards = new ArrayList<>();

    public Hospital() {
    }

    /*Використовуючи змінну "in" можемо отримати значення яке ми писали в 'Parcelable' це
       як правило приватний конструктор, так що тільки поле `CREATOR` може отримати доступ*/
    protected Hospital(Parcel in) {
        /*Щоб отримати id*/
        id = in.readString();
        /*Щоб отримати ім'я*/
        name = in.readString();
        /*Щоб отримати опис*/
        description = in.readString();
        image = in.readParcelable(Image.class.getClassLoader());
        location = in.readParcelable(Location.class.getClassLoader());
    }

    /*Це поле необхідно для Android, щоб мати можливість створювати нові об'єкти,
    окремо або в масивах. Це також означає, що ви можете використовувати за замовчуванням*/
    public static final Creator<Hospital> CREATOR = new Creator<Hospital>() {
        @Override

        public Hospital createFromParcel(Parcel in) {return new Hospital(in);
        }

        @Override
        public Hospital[] newArray(int size) {
            return new Hospital[size];
        }
    };

    /*Щоб одержувати інформацію для всіх змінних з класу Hospital*/
    public String getDescription() {
        return description;
    }

    /*Одержання id*/
    public String getId() {
        return id;
    }

    /*Отримання фото*/
    public Image getImage() {
        return image;
    }

    /*Отримання місця знаходження*/
    public Location getLocation() {
        return location;
    }

    /*Отримання імені*/
    public String getName() {
        return name;
    }

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }

    @Override
    /*Метод для об'єктів*/
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
    /*У переважній більшості випадків ви можете просто повернутися для цього*/
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
