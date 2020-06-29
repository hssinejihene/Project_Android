package com.example.authtoken.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.authtoken.R;
import com.example.authtoken.interfaces.iproject;
import com.example.authtoken.model.participated;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Participant extends AppCompatActivity {

    private TextView part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participant);

        Intent intent = getIntent();
        final String token= (String) intent.getStringExtra("tok");
        part = findViewById(R.id.part);
        //Toast.makeText(Participant.this,token,Toast.LENGTH_LONG).show();

        OkHttpClient.Builder okhttpbuilder= new OkHttpClient.Builder();
        okhttpbuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                Request.Builder newreq=request.newBuilder().header("Authorization","Bearer "+token);


                return chain.proceed(newreq.build());
            }
        });



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://process.isiforge.tn/api/1.0/")
                .client(okhttpbuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iproject iproject = retrofit.create(iproject.class);

        Call<List<participated>> call = iproject.getParticipated();

        call.enqueue(new Callback<List<participated>>() {
            @Override
            public void onResponse(Call<List<participated>> call, Response<List<participated>> response) {

                if (response.isSuccessful()) {

                List<participated> participateds = response.body();

                for (participated p : participateds) {
                    String content = "";
                    content += "Number : " + p.getApp_number() + "\n";
                    content += "- " +p.getApp_pro_title() + "\n\n";

                    part.append(content);
                }
            }
            }

            @Override
            public void onFailure(Call<List<participated>> call, Throwable t) {
                part.setText(t.getMessage());
            }
        });



    }

}
