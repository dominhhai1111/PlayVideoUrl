package com.example.admin.playvideourl;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comment implements Serializable{
    @SerializedName("userId")
    String userId;
    @SerializedName("email")
    String email;
    @SerializedName("content")
    String content;

    public Comment(String userId, String email, String content) {
        this.userId = userId;
        this.email = email;
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
