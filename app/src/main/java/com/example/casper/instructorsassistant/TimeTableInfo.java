package com.example.casper.instructorsassistant;

/**
 * Created by CASPER on 12/20/2017.
 */

public class TimeTableInfo {
    public TimeTableInfo() {
    }
    String coursename;
    String period;
    String classType;
    String className;

    public TimeTableInfo(String coursename, String period, String classType, String className) {
        this.coursename = coursename;
        this.period = period;
        this.classType = classType;
        this.className = className;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
