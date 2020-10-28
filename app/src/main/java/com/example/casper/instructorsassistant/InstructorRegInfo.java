package com.example.casper.instructorsassistant;

/**
 * Created by CASPER on 10/27/2017.
 */

public class InstructorRegInfo {
    String instId;
    String instEmail;
    String instToken;

    public InstructorRegInfo(String instId, String instEmail, String instToken) {
        this.instId = instId;
        this.instEmail = instEmail;
        this.instToken = instToken;
    }

    public InstructorRegInfo() {
    }
}
