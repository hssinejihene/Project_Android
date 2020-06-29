package com.example.authtoken.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.authtoken.R;
import com.example.authtoken.model.project;


public class items extends Activity {
    project p=new project();
    String[] items={"1","2","3","4","5"};
    ListView myList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        myList=(ListView)findViewById(R.id.list);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        myList.setAdapter(adapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                          @Override
                                          public void onItemClick(AdapterView parent, View view,int position, long id) {
                                              Toast.makeText(items.this,"work : " ,Toast.LENGTH_SHORT).show();
                                          }

            });






    }





}
