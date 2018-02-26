package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.SerializedName;

public class LoginBody {

    @SerializedName("mail")
    private String mail;
    @SerializedName("password")
    private String password;

    public LoginBody(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
