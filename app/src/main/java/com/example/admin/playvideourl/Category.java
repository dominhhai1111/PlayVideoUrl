package com.example.admin.playvideourl;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by admin on 4/4/2018.
 */

public class Category implements Serializable{
    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    public Category() {
    }

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
