package com.example.authtoken.model;

public class participated {

    private String app_uid;
    private int app_number;
    private String app_status;
    private String app_pro_title;
    private String app_tas_title;

    public String getApp_pro_title() {
        return app_pro_title;
    }

    public void setApp_pro_title(String app_pro_title) {
        this.app_pro_title = app_pro_title;
    }

    public String getApp_tas_title() {
        return app_tas_title;
    }

    public void setApp_tas_title(String app_tas_title) {
        this.app_tas_title = app_tas_title;
    }

    public participated(String app_uid, int app_number, String app_status) {
        this.app_uid = app_uid;
        this.app_number = app_number;
        this.app_status = app_status;
    }

    public String getApp_uid() {
        return app_uid;
    }

    public void setApp_uid(String app_uid) {
        this.app_uid = app_uid;
    }

    public int getApp_number() {
        return app_number;
    }

    public void setApp_number(int app_number) {
        this.app_number = app_number;
    }

    public String getApp_status() {
        return app_status;
    }

    public void setApp_status(String app_status) {
        this.app_status = app_status;
    }
}
