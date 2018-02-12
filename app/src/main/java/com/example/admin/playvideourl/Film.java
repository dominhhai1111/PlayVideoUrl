package com.example.admin.playvideourl;

import java.io.Serializable;

/**
 * Created by Administrator on 30/1/2018.
 */

public class Film implements Serializable{
    private String name;
    private int time;
    private String url;

    public Film(String name, int time, String url) {
        this.name = name;
        this.time = time;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
