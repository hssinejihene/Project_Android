package com.example.authtoken.model;

import java.util.List;

public class variable {

    private String var_uid;
   // private String prj_uid;
    private String var_name;


    public variable() { }

    public variable(String var_uid, String var_name) {
        this.var_uid = var_uid;
      //  this.prj_uid = prj_uid;
        this.var_name = var_name;
    }

    public String getVar_uid() {
        return var_uid;
    }

    public void setVar_uid(String var_uid) {
        this.var_uid = var_uid;
    }



    public String getVar_name() {
        return var_name;
    }

    public void setVar_name(String var_name) {
        this.var_name = var_name;
    }
}
