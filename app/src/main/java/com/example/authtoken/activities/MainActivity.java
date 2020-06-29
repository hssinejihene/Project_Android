package com.example.authtoken.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import android.widget.EditText;


import com.example.authtoken.R;
import com.example.authtoken.interfaces.userclient;
import com.example.authtoken.model.login;
import com.example.authtoken.model.userinfo;
import com.victor.loading.rotate.RotateLoading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private Button btnlog;
private EditText password;
private EditText username;
private RotateLoading rotateLoading;
    //retrofit permet de consomer les api  (donnée json)
    Retrofit retrofit = new Retrofit.Builder()//instance de retrofit
            .baseUrl("http://process.isiforge.tn")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //instance de l interface
    com.example.authtoken.interfaces.userclient userclient=retrofit.create(userclient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         rotateLoading = findViewById(R.id.rotateloading);
        btnlog=findViewById(R.id.button);
        password=(EditText)findViewById(R.id.password);
        username=(EditText)findViewById(R.id.username);


        btnlog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });

    }


    private static String token;
    private static String type;
    private void  login(){
        login login=new login(username.getText().toString(), password.getText().toString());
       //login login=new login("etudiant", "pm2020");
        if(rotateLoading.isStart()){
            rotateLoading.stop();
        }else{
            rotateLoading.start();
        }

        Call<userinfo> call= userclient.login(login);
        call.enqueue(new Callback<userinfo>()
        {
            @Override
            public void onResponse(Call<userinfo> call, Response<userinfo> response)
            {
                if (response.isSuccessful()) {
                  //  Toast.makeText(MainActivity.this,response.body().getToken_type(),Toast.LENGTH_LONG).show();
                    token=response.body().getAccess_token();
                   // type=response.body().getToken_type();
                    //passage
                    Intent i = new Intent(MainActivity.this, PrincipalMenu.class);
                    i.putExtra("tok", token);
                    //i.putExtra("type",type);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this,"username or password not correct",Toast.LENGTH_LONG).show();
                    }

            }

            @Override
            public void onFailure(Call<userinfo> call, Throwable t) {
                Toast.makeText(MainActivity.this,"No response",Toast.LENGTH_LONG).show();
            }


                     });



    }

}
