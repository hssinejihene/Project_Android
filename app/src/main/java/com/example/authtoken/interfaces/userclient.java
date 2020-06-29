package com.example.authtoken.interfaces;
import com.example.authtoken.model.login;
import com.example.authtoken.model.userinfo;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface userclient {
    @POST("/isi/oauth2/token")
    Call<userinfo> login(@Body login login);
}
