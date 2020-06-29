package com.example.authtoken.model;

import java.util.List;

public class project_activity {


   private String step_uid;
    private String step_type_obj;
    private String step_uid_obj;
    private String  step_condition;
    private int  step_position;
    private String  step_mode;
    private String   obj_title;
    private String obj_description;
    private List triggers;
    List<project_activity> project_activities;

    public project_activity() {
    }

    public project_activity(String step_uid, String step_type_obj, String step_uid_obj, String step_condition, int step_position, String step_mode, String obj_title, String obj_description, List triggers) {
        this.step_uid = step_uid;
        this.step_type_obj = step_type_obj;
        this.step_uid_obj = step_uid_obj;
        this.step_condition = step_condition;
        this.step_position = step_position;
        this.step_mode = step_mode;
        this.obj_title = obj_title;
        this.obj_description = obj_description;
        this.triggers = triggers;
    }

    public List<project_activity> getProject_activities() {
        return project_activities;
    }

    public void setProject_activities(List<project_activity> project_activities) {
        this.project_activities = project_activities;
    }

    public String getStep_uid() {
        return step_uid;
    }

    public void setStep_uid(String step_uid) {
        this.step_uid = step_uid;
    }

    public String getStep_type_obj() {
        return step_type_obj;
    }

    public void setStep_type_obj(String step_type_obj) {
        this.step_type_obj = step_type_obj;
    }

    public String getStep_uid_obj() {
        return step_uid_obj;
    }

    public void setStep_uid_obj(String step_uid_obj) {
        this.step_uid_obj = step_uid_obj;
    }

    public String getStep_condition() {
        return step_condition;
    }

    public void setStep_condition(String step_condition) {
        this.step_condition = step_condition;
    }

    public int getStep_position() {
        return step_position;
    }

    public void setStep_position(int step_position) {
        this.step_position = step_position;
    }

    public String getStep_mode() {
        return step_mode;
    }

    public void setStep_mode(String step_mode) {
        this.step_mode = step_mode;
    }

    public String getObj_title() {
        return obj_title;
    }

    public void setObj_title(String obj_title) {
        this.obj_title = obj_title;
    }

    public String getObj_description() {
        return obj_description;
    }

    public void setObj_description(String obj_description) {
        this.obj_description = obj_description;
    }
    public List getTriggers() {
        return triggers;
    }

    public void setTriggers(List triggers) {
        this.triggers = triggers;
    }
}
