package com.example.authtoken.model;

public class caserep {
   private String app_uid;
   private String app_number;

    public caserep(String app_uid, String app_number) {
        this.app_uid = app_uid;
        this.app_number = app_number;
    }

    public String getApp_uid() {
        return app_uid;
    }

    public void setApp_uid(String app_uid) {
        this.app_uid = app_uid;
    }

    public String getApp_number() {
        return app_number;
    }

    public void setApp_number(String app_number) {
        this.app_number = app_number;
    }
}
