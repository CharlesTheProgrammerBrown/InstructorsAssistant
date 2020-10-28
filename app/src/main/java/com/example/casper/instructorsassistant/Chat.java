package com.example.casper.instructorsassistant;

/**
 * Created by CASPER on 12/26/2017.
 */

public class Chat {
    public Chat() {
    }
    String sender;
    String receiver;
    String sId;
    String rId;
    Long timeStamp;
    String message;

    public Chat(String sender, String receiver, String sId, String rId, Long timeStamp, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.sId = sId;
        this.rId = rId;
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
