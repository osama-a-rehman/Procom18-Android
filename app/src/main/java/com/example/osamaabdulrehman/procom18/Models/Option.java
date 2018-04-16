package com.example.osamaabdulrehman.procom18.Models;

/**
 * Created by Osama Abdul Rehman on 3/30/2018.
 */

public class Option {
    public String name;
    public int imageId;

    public Option(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
