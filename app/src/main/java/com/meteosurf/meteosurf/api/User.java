package com.meteosurf.meteosurf.api;

public class User {

    private String name;
    private String mail;
    private String token;
    private String apiKey;

    public User(String name, String mail, String token, String apiKey) {
        this.name = name;
        this.mail = mail;
        this.token = token;
        this.apiKey = apiKey;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
