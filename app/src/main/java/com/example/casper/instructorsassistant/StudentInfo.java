package com.example.casper.instructorsassistant;

/**
 * Created by CASPER on 11/2/2017.
 */

public class StudentInfo {
    public StudentInfo() {
    }
    String name;
    String id;
    String email;
    String password;
    String deviceToken;
    String userUid;

    public StudentInfo(String name, String id, String email, String password, String deviceToken, String userUid) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.password = password;
        this.deviceToken = deviceToken;
        this.userUid = userUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }
}
