package com.example.authtoken.activities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;//utiliser avec listeView ou Spinner
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.authtoken.R;
import com.example.authtoken.interfaces.iproject;
import com.example.authtoken.model.project;

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

public class ListProcessFragment extends Fragment {
   // private TextView textViewResult;
    private ListView listview;

    View view;
    android.app.Fragment fragment = this;
    //methode oncreateview:return view
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //chaque liste view besoin de inflater :Instantiates a layout XML file into its corresponding View objects. It is never used directly.
        view = inflater.inflate(R.layout.activity_main2,container,false);
        Intent intent = this.getActivity().getIntent();
        final String token= (String) intent.getStringExtra("tok");
     // final String type= (String) intent.getStringExtra("type");
       // textViewResult = view.findViewById(R.id.text_view_result);


        OkHttpClient.Builder okhttpbuilder= new OkHttpClient.Builder();
        //addInterceptor: joue intermediare entre cliet et serveur et travail en backgrount et permet de gérer les requet
        okhttpbuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                Request.Builder newreq=request.newBuilder().header("Authorization", "Bearer "+token);


                return chain.proceed(newreq.build());
            }
        });




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://process.isiforge.tn/api/1.0/")
                .client(okhttpbuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iproject iproject = retrofit.create(iproject.class);

        Call<List<project>> call = iproject.getProjects();

        call.enqueue(new Callback<List<project>>() {
            @Override
            public void onResponse(Call<List<project>> call, Response<List<project>> response) {

                if (!response.isSuccessful()) {
                   // textViewResult.setText("Code: " + response.code());
                    Toast.makeText(getActivity().getApplicationContext(),"Unsuccessful Response",Toast.LENGTH_LONG).show();
                    return;
                }

                final project p=new project();
                p.setProjects(response.body());

                ListProcessFragment.IconicAdapter adapter = new ListProcessFragment.IconicAdapter(p.getProjects());
                listview = (ListView) view.findViewById(R.id.list);
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        //String item = p.getProjects().get(position).getPro_title();

                        Intent i = new Intent(fragment.getActivity(), Forms.class);
                        i.putExtra("tok", token);
                        i.putExtra("pro_uid",p.getProjects().get(position).getPro_uid());
                        i.putExtra("task_uid",p.getProjects().get(position).getTas_uid());
                        startActivity(i);
                    }
                });
            }


            @Override
            public void onFailure(Call<List<project>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),"No Response",Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    class IconicAdapter extends BaseAdapter
    {
        List<project> list ;
        public IconicAdapter(List<project> list){
            super();
            this.list = list;
        }


        @Override
        public int getCount() {
            return this.list.size();
        }

        @Override
        public Object getItem(int position) {
            return this.list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater= getActivity().getLayoutInflater();
            View ligne= inflater.inflate(R.layout.item,null);
            TextView title=(TextView)ligne.findViewById(R.id.text_view_result);
            title.setText(list.get(position).getPro_title());

            return ligne;
        }
    }
    }

