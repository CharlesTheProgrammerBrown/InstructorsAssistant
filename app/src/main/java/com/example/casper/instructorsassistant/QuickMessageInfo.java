package com.example.casper.instructorsassistant;

/**
 * Created by CASPER on 12/9/2017.
 */

public class QuickMessageInfo {
    public QuickMessageInfo() {
    }
    String msgHeader;
    String msgContent;
    String msgDate;

    public QuickMessageInfo(String msgHeader, String msgContent, String msgDate) {
        this.msgHeader = msgHeader;
        this.msgContent = msgContent;
        this.msgDate = msgDate;
    }

    public String getMsgHeader() {
        return msgHeader;
    }

    public void setMsgHeader(String msgHeader) {
        this.msgHeader = msgHeader;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }
}
