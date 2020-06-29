package com.example.authtoken.model;

import android.view.View;

import org.json.JSONObject;

public class cases {
    private String pro_uid;
    private String tas_uid;
   private JSONObject variables=new JSONObject();
    public cases(){}

    public JSONObject getVariables() {
        return variables;
    }

    public void setVariables(JSONObject variables) {
        this.variables = variables;
    }

    public String getPro_uid() {
        return pro_uid;
    }

    public void setPro_uid(String pro_uid) {
        this.pro_uid = pro_uid;
    }

    public String getTas_uid() {
        return tas_uid;
    }

    public void setTas_uid(String tas_uid) {
        this.tas_uid = tas_uid;
    }


}
