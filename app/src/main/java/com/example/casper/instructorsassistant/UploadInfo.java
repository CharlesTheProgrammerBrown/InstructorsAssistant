package com.example.casper.instructorsassistant;

/**
 * Created by CASPER on 12/22/2017.
 */

public class UploadInfo {
    public UploadInfo() {

    }
    String name;
    String path;
    String date;

    public UploadInfo(String name, String path, String date) {
        this.name = name;
        this.path = path;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
