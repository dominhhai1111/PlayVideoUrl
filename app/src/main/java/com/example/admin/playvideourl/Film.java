package com.example.admin.playvideourl;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 30/1/2018.
 */

public class Film implements Serializable{
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("time")
    private String time;
    @SerializedName("url")
    private String url;

    public Film(String id, String name, String time, String url) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
