package com.example.casper.instructorsassistant;

/**
 * Created by CASPER on 11/2/2017.
 */

public class UserCategory {
    public UserCategory() {
    }
    String category;
    String devToken;

    public UserCategory(String category, String devToken) {
        this.category = category;
        this.devToken = devToken;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDevToken() {
        return devToken;
    }

    public void setDevToken(String devToken) {
        this.devToken = devToken;
    }
}
