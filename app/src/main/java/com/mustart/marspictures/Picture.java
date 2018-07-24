package com.mustart.marspictures;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Bou's Laptop on 30/05/2018.
 */

public class Picture implements Serializable{
    private int id;
    private String imageUrl;
    private String name;
    private String fullName;

    public Picture(int id, String imageUrl, String camera, String Fullname) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = camera;
        this.fullName = fullName;
    }

    protected Picture(Parcel in) {
        id = in.readInt();
        imageUrl = in.readString();
        name = in.readString();
        fullName = in.readString();
    }


    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
