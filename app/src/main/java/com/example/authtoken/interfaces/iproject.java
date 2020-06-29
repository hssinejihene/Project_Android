package com.example.authtoken.interfaces;
import com.example.authtoken.model.caserep;
import com.example.authtoken.model.cases;
import com.example.authtoken.model.dyform;
import com.example.authtoken.model.participated;
import com.example.authtoken.model.project;
import com.example.authtoken.model.project_activity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface iproject {
    //@Headers({ "Content-Type: application/json","cache-control: no-cache"})
    @GET("/api/1.0/isi/case/start-cases")
    Call<List<project>> getProjects();

    @GET("/api/1.0/isi/project/{prouid}/activity/{taskuid}/steps")
    Call<List<project_activity>> getProjects_activity(@Path("prouid") String pro, @Path("taskuid") String task);

    @GET("/api/1.0/isi/project/{prouid}/dynaform/{stepuidobj}")
    Call<dyform> getdyform(@Path("prouid") String pro, @Path("stepuidobj") String stepuidobj);

    @POST("/api/1.0/isi/cases")
    Call<caserep> lastpost(@Body cases cas);

    @GET("/api/1.0/isi/cases/participated")
    Call<List<participated>> getParticipated();
}
