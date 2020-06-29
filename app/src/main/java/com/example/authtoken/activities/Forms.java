package com.example.authtoken.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.authtoken.R;
import com.example.authtoken.interfaces.iproject;
import com.example.authtoken.model.caserep;
import com.example.authtoken.model.cases;
import com.example.authtoken.model.dyform;
import com.example.authtoken.model.project_activity;
import com.example.authtoken.model.variable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Forms extends AppCompatActivity {
    //HASHMAP

  final HashMap<String,View> map=new HashMap<String, View>();
    JSONArray ja3 = null;
    final cases cas=new cases();

   final HashMap<String,String> viewType = new HashMap<String, String>();

    final variable var =new variable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forms);
      // final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
      //  final SharedPreferences.Editor editor = pref.edit();
        //EditText editText;
      //  EditText textarea;
        RadioGroup radioGroup;
        LinearLayout linearLayout = findViewById(R.id.editTextContainer);
       // Button btn =(Button)findViewById(R.id.button2);
      //  btn.setText("send");


// hide the keyboard in order to avoid getTextBeforeCursor on inactive InputConnection
       // InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);





        Intent intent = getIntent();
      final String step_uid_obj ;
        String step_uid_obj1;


        //project_activity pa;
        final project_activity pa=new project_activity();
     final String  token= (String) intent.getStringExtra("tok");
     final String  pro_uid= (String) intent.getStringExtra("pro_uid");
     final String  task_uid= (String) intent.getStringExtra("task_uid");

        //Log.i("---------->>>",pro_uid+"---------"+task_uid+"-------------"+token);

        OkHttpClient.Builder okhttpbuilder= new OkHttpClient.Builder();
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

          final iproject iproject = retrofit.create(iproject.class);
            Call<List<project_activity>> call = iproject.getProjects_activity(pro_uid,task_uid);
       // Call<dyform> call2 = iproject.getdyform(pro_uid,step_uid_obj);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Response<List<project_activity>> rep = call.execute();

            if(rep.isSuccessful()){

                //Log.i("-------cc---obj>>>",rep.body().get(0).getStep_uid_obj());
                step_uid_obj =rep.body().get(0).getStep_uid_obj();

                //*******************call-dynaform-endpoint**************
                Call<dyform> call2 = iproject.getdyform(pro_uid,step_uid_obj);
                Response<dyform> rep2 =call2.execute();
                if(rep2.isSuccessful())
                {
                    //Log.i("----------type>>>",rep2.body().getDyn_content());
                   // System.out.println("SUCCESS");


                    //**********************json-part*********************

                    try {
                        //String rep22=rep2.body().toString();
                        //JSONObject dyn_content =(JSONObject)new JSONTokener(rep2.body().getDyn_content().toString()).nextValue();
                        //String dyn_content=(String) jo.get("dyn_content");

                        JSONObject joo =(JSONObject)new JSONTokener(rep2.body().getDyn_content().toString()).nextValue();

                        JSONArray ja = joo.getJSONArray("items");
                       for(int i=0;i<ja.length();i++)
                        {
                            JSONObject jo1 =(JSONObject)new JSONTokener(ja.get(i).toString()).nextValue();

                            JSONArray ja2=jo1.getJSONArray("items");

                                 for(int j=0;j<ja2.length();j++)
                                {
                                  ja3 =(JSONArray)new JSONTokener(ja2.get(j).toString()).nextValue();

                                        for(int k=0;k<ja3.length();k++)
                                        {
                                       final     JSONObject jo4 =(JSONObject)new JSONTokener(ja3.get(k).toString()).nextValue();
                                            if(jo4.has("type")) {
                                                String type = jo4.getString("type");

                                                //Log.i("----------type-->>>", type);
                                               // System.out.println("TYPE" + type);


                                                cas.setPro_uid(pro_uid);
                                                cas.setTas_uid(task_uid);
                                                switch (type) {
                                                    case "text" :
                                                    // Create TextView programmatically.
                                                    TextView textView = new TextView(this);
                                                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                    textView.setGravity(Gravity.CENTER);
                                                    textView.setText(jo4.getString("label"));
                                                        textView.setTextColor(Color.parseColor("#585959"));
                                                        textView.setPadding(20,40,20,40);
                                                        textView.setTextSize(18);

                                                    // Create EditText
                                                        EditText edit = new EditText(this);
                                                        edit.setHint(jo4.getString("placeholder"));
                                                        edit.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                        edit.setPadding(20, 40, 20, 40);

                                                        var.setVar_uid(jo4.getString("var_uid").toString());
                                                        var.setVar_name(jo4.getString("var_name"));
                                                        map.put(var.getVar_name(),edit);
                                                        viewType.put(jo4.getString("var_name"),jo4.getString("type"));
                                                    if (linearLayout != null) {
                                                        linearLayout.addView(textView);
                                                    }
                                                    if (linearLayout != null) {
                                                        linearLayout.addView(edit);
                                                    }
                                                    break;


                                                    case"datetime":
                                                        TextView tv = new TextView(this);
                                                        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                        tv.setGravity(Gravity.CENTER);
                                                        tv.setText(jo4.getString("label"));
                                                        tv.setTextColor(Color.parseColor("#585959"));
                                                        tv.setPadding(20,40,20,40);
                                                        tv.setTextSize(18);

                                                      EditText  editText = new EditText(this);
                                                        editText.setHint(jo4.getString("placeholder"));
                                                        editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                        editText.setPadding(20, 40, 20, 40);
                                                        editText.setInputType(InputType.TYPE_CLASS_DATETIME);

                                                        var.setVar_uid(jo4.getString("var_uid").toString());
                                                        var.setVar_name(jo4.getString("var_name"));
                                                        map.put(var.getVar_name(),editText);
                                                        viewType.put(jo4.getString("var_name"),jo4.getString("type"));
                                                        if (linearLayout != null) {
                                                            linearLayout.addView(tv);
                                                        }
                                                        if (linearLayout != null) {
                                                            linearLayout.addView(editText);
                                                        }

                                                            break;



                                                    case "title" :
                                                        TextView title = new TextView(this);
                                                        title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                        title.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
                                                        title.setTypeface(null, Typeface.BOLD);
                                                        title.setTextColor(Color.parseColor("#318CE7"));
                                                        title.setPadding(20,20,20,50);
                                                        title.setAllCaps(true);
                                                        title.setTextSize(35);

                                                        title.setText(jo4.getString("label"));
                                                        if (linearLayout != null) {
                                                            linearLayout.addView(title);
                                                        }



                                                        break;

                                                    case "subtitle" :
                                                        TextView subtitle = new TextView(this);
                                                        subtitle.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                        subtitle.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
                                                        subtitle.setTypeface(null, Typeface.BOLD);
                                                        subtitle.setTextColor(Color.parseColor("#5F7E80"));
                                                        subtitle.setPadding(20,40,20,40);
                                                        subtitle.setTextSize(17);
                                                        subtitle.setAllCaps(true);

                                                        subtitle.setText(jo4.getString("label"));
                                                        if (linearLayout != null) {
                                                            linearLayout.addView(subtitle);
                                                        }
                                                        break;
                                                        /*


                                                    case "image" :
                                                        // Add image path from source.
                                                        URL url = new URL(jo4.getString("src"));
                                                        Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                                        ImageView imageview = new ImageView(Forms.this);
                                                        imageview.setImageBitmap(image);
                                                        imageview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                        // Add EditText to LinearLayout
                                                        if (linearLayout != null) {
                                                            linearLayout.addView(imageview);
                                                        }

                                                        break;
                                                        */

                                                    case "textarea" :

                                                        TextView label2 = new TextView(this);
                                                        label2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                        label2.setGravity(Gravity.CENTER);
                                                        label2.setText(jo4.getString("label"));
                                                        label2.setTextColor(Color.parseColor("#585959"));
                                                        label2.setPadding(20,40,20,40);
                                                        label2.setTextSize(18);

                                                       EditText area = new EditText(this);
                                                        area.setHint(jo4.getString("placeholder"));
                                                        area.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                                                        area.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                        area.setPadding(20, 40, 20, 40);
                                                        area.setLines(2);

                                                        var.setVar_uid(jo4.getString("var_uid").toString());
                                                        var.setVar_name(jo4.getString("var_name").toString());
                                                        map.put(var.getVar_name(),area);
                                                        viewType.put(jo4.getString("var_name"),jo4.getString("type"));
                                                        // Add label2 to LinearLayout
                                                        if (linearLayout != null) {
                                                            linearLayout.addView(label2);}
                                                        // Add Textarea to LinearLayout

                                                        if (linearLayout != null) {
                                                            linearLayout.addView(area);}

                                                        break;


                                                   case "radio":
                                                        TextView txtRadio = new  TextView(Forms.this);
                                                        txtRadio.setText(jo4.getString("label"));
                                                        txtRadio.setTextColor(Color.parseColor("#585959"));
                                                        txtRadio.setTextSize(18);
                                                        txtRadio.setPadding(20, 40, 20, 40);
                                                       radioGroup = new RadioGroup(Forms.this);
                                                        JSONArray options = new JSONArray(jo4.getString("options"));
                                                        for(int l=0;l<options.length();l++){
                                                            RadioButton radioBtn = new RadioButton(Forms.this);

                                                            radioBtn.setText(options.getJSONObject(l).optString("label"));
                                                            radioBtn.setTextSize(18);
                                                            radioBtn.setTextColor(Color.parseColor("#585959"));
                                                            radioGroup.addView(radioBtn);
                                                        }

                                                        var.setVar_uid(jo4.getString("var_uid").toString());
                                                        var.setVar_name(jo4.getString("var_name").toString());
                                                        map.put(var.getVar_name(),radioGroup);
                                                        viewType.put(jo4.getString("var_name"),jo4.getString("type"));
                                                        linearLayout.addView(txtRadio);
                                                        linearLayout.addView(radioGroup);

                                                        break;



                                                    case "dropdown":
                                                        TextView sp = new TextView(this);
                                                        sp.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                        sp.setGravity(Gravity.CENTER);
                                                        sp.setTextColor(Color.parseColor("#585959"));
                                                        sp.setPadding(20,40,20,40);
                                                        sp.setTextSize(18);
                                                        sp.setText(jo4.getString("label"));
                                                        JSONArray op = new JSONArray(jo4.getString("options"));
                                                        ArrayList<String> spinnerArray = new ArrayList<String>();
                                                        for(int n=0;n<op.length();n++){
                                                            spinnerArray.add(op.getJSONObject(n).optString("value"));

                                                        }
                                                        Spinner spinner = new Spinner(this);
                                                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
                                                        spinner.setAdapter(spinnerArrayAdapter);
                                                        var.setVar_name(jo4.getString("var_name").toString());
                                                        map.put(var.getVar_name(),spinner);
                                                        viewType.put(jo4.getString("var_name"),jo4.getString("type"));
                                                        if (linearLayout != null) {
                                                            linearLayout.addView(sp);
                                                        }
                                                        linearLayout.addView(spinner);


                                                        break;


                                                    case"checkbox":
                                                        TextView cb = new TextView(this);
                                                        cb.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                        cb.setGravity(Gravity.CENTER);
                                                        cb.setText(jo4.getString("label"));
                                                        cb.setTextColor(Color.parseColor("#585959"));
                                                        cb.setPadding(20,40,20,40);
                                                        cb.setTextSize(18);

                                                        CheckBox checkBox = new CheckBox(this);
                                                        checkBox.setText(jo4.getString("label"));
                                                        checkBox.setTextColor(Color.DKGRAY);
                                                        checkBox.setTextSize(20);
                                                        checkBox.setTextColor(Color.parseColor("#585959"));;
                                                        checkBox.setPadding(20,40,20,40);
                                                        checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                        var.setVar_name(jo4.getString("var_name").toString());
                                                        map.put(var.getVar_name(),checkBox);
                                                        viewType.put(jo4.getString("var_name"),jo4.getString("type"));
                                                        linearLayout.addView(checkBox);
                                                        if (linearLayout != null) {
                                                            linearLayout.addView(cb);
                                                        }



                                                        break;



                                                }


                                            }




                                        }
                                }
                            Button btn=new Button(this);
                            ((Button) btn).setText("Envoyer");
                            ((Button) btn).setBackgroundColor(Color.parseColor("#318CE7"));

                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    for (String key : map.keySet()){
                                        if (viewType.get(key).equals("textarea") ||viewType.get(key).equals("text"))
                                        {
                                            EditText et = (EditText) map.get(key);
                                            try {
                                                cas.getVariables().put(key, et.getText().toString());
                                                System.out.println("+++++++++++++++++++++++++++++++++");
                                                System.out.println("------------>>"+cas.getVariables().getString(key));
                                                System.out.println("+++++++++++++++++++++++++++++++++");
                                            } catch (JSONException e1) {
                                                e1.printStackTrace();
                                            }


                                        }
                                        else if(viewType.get(key).equals("radio"))
                                        {
                                            RadioGroup rd=(RadioGroup)map.get(key);
                                            int id=rd.getCheckedRadioButtonId();
                                            RadioButton rb=(RadioButton) findViewById(id);
                                            System.out.println("******radioo :"+rb.getText().toString());
                                            try {
                                                cas.getVariables().put(key, rb.getText().toString());
                                                System.out.println("****** :"+ cas.getVariables().toString());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        else if(viewType.get(key).equals("dropdown"))
                                        {
                                            Spinner sp=(Spinner)map.get(key);
                                            String val = sp.getSelectedItem().toString();
                                            try {
                                                cas.getVariables().put(key,val);
                                                System.out.println("****** :"+ cas.getVariables().toString());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                        else if(viewType.get(key).equals("checkBok"))
                                        {
                                            CheckBox ck=(CheckBox)map.get(key);
                                            if(ck.isChecked())
                                            {
                                                try {
                                                    cas.getVariables().put(key,ck.getText().toString());
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }

                                    }


                                    Call<caserep> call3 = iproject.lastpost(cas);
                                    Response<caserep> rep3 = null;
                                    try {
                                        rep3 = call3.execute();

                                        if (rep3.isSuccessful()) {
                                            System.out.println("SUCCESS");
                                            Log.i("------------------>", rep3.body().getApp_uid());

                                            Toast.makeText(Forms.this,"Application number is :"+rep3.body().getApp_number(),Toast.LENGTH_LONG).show();      //passage

                                                                Intent i = new Intent(Forms.this, Participant.class);
                                                                i.putExtra("tok", token);

                                                                startActivity(i);

                                        } else {
                                            System.out.println("ERROR");
                                        }



                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });


                            linearLayout.addView(btn);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }









                }
                else{Log.i("404 ---------->>>","err2");
                System.out.println("fail");
                }

            }
            else{Log.i("402---------->>>","err");}
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
