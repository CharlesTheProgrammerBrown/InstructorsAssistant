package com.example.casper.instructorsassistant;

/**
 * Created by CASPER on 12/1/2017.
 */

public class CourseInfo {
    String coursename;
    String instructorsId;
    public CourseInfo() {
    }

    public CourseInfo(String coursename, String instructorsId) {
        this.coursename = coursename;
        this.instructorsId = instructorsId;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getInstructorsId() {
        return instructorsId;
    }

    public void setInstructorsId(String instructorsId) {
        this.instructorsId = instructorsId;
    }
}
