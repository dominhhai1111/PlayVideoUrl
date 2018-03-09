package com.example.admin.playvideourl;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by admin on 3/9/2018.
 */

public class RegisterResult implements Serializable{
    @SerializedName("isRegisted")
    private Boolean isRegisted;
    @SerializedName("message")
    private String message;

    public RegisterResult() {
    }

    public RegisterResult(Boolean isRegisted, String message) {
        this.isRegisted = isRegisted;
        this.message = message;
    }

    public Boolean getRegisted() {
        return isRegisted;
    }

    public void setRegisted(Boolean registed) {
        isRegisted = registed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
