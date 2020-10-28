package com.example.casper.instructorsassistant;

/**
 * Created by CASPER on 11/2/2017.
 */

public class InstructorInfo {
    public InstructorInfo() {
    }
    String email,insUid,deviceToken,username;

    public InstructorInfo(String email, String insUid, String deviceToken, String username) {
        this.email = email;
        this.insUid = insUid;
        this.deviceToken = deviceToken;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInsUid() {
        return insUid;
    }

    public void setInsUid(String insUid) {
        this.insUid = insUid;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
