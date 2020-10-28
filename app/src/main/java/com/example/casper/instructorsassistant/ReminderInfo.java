package com.example.casper.instructorsassistant;

/**
 * Created by CASPER on 12/14/2017.
 */

public class ReminderInfo {
    public ReminderInfo() {
    }
    String msgInfo;
    long msgDate;
    String courseid;

    public ReminderInfo(String msgInfo, long msgDate, String courseid) {
        this.msgInfo = msgInfo;
        this.msgDate = msgDate;
        this.courseid = courseid;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    public long getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(long msgDate) {
        this.msgDate = msgDate;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }
}
