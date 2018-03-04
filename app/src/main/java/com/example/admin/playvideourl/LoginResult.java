package com.example.admin.playvideourl;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 4/3/2018.
 */

public class LoginResult implements Serializable{
    @SerializedName("isLogin")
    private Boolean isLogin;
    @SerializedName("message")
    private String message;
    @SerializedName("user_id")
    private String user_id;

    public LoginResult() {
    }

    public LoginResult(Boolean isLogin, String message, String user_id) {
        this.isLogin = isLogin;
        this.message = message;
        this.user_id = user_id;
    }

    public Boolean isLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
