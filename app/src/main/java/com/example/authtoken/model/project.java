package com.example.authtoken.model;

import java.util.List;

public class project {
    private String tas_uid;
    private String pro_title;
    private String pro_uid;
    List<project> projects;

    public project() {
    }

    public project(String tas_uid, String pro_title, String pro_uid) {
        this.tas_uid = tas_uid;
        this.pro_title = pro_title;
        this.pro_uid = pro_uid;
    }

    public List<project> getProjects() {
        return projects;
    }

    public void setProjects(List<project> projects) {
        this.projects = projects;
    }

    public String getTas_uid() {
        return tas_uid;
    }

    public void setTas_uid(String tas_uid) {
        this.tas_uid = tas_uid;
    }

    public String getPro_title() {
        return pro_title;
    }

    public void setPro_title(String pro_title) {
        this.pro_title = pro_title;
    }

    public String getPro_uid() {
        return pro_uid;
    }

    public void setPro_uid(String pro_uid) {
        this.pro_uid = pro_uid;
    }
}
