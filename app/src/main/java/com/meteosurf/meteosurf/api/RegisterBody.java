package com.meteosurf.meteosurf.api;

import com.google.gson.annotations.SerializedName;

public class RegisterBody {

    @SerializedName("name")
    private String name;
    @SerializedName("mail")
    private String mail;
    @SerializedName("password")
    private String password;

    public RegisterBody(String name, String mail, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
