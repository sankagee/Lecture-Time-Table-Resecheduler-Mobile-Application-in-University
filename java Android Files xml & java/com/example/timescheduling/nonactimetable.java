package com.example.timescheduling;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.timescheduling.timetable.RequestHandler;
import com.example.timescheduling.timetable.TimeTableAdapter;
import com.example.timescheduling.timetable.TimeTableGetter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class nonactimetable extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    List<TimeTableGetter> timeTableList;
    TimeTableAdapter adapter; //@@@@@@@@@@

    RecyclerView recyclerView;
    TextView days;
    Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_acadamic_time_table_over_view);

        days=findViewById(R.id.t_v_name);


        timeTableList=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler);//@@@@@@@@
        recyclerView.setHasFixedSize(true);//@@@@@@@@
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));//@@@@@@@@

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        /*day=getIntent().getStringExtra("DAY");
        days.setText(day);*/

        spinner1=findViewById(R.id.spinner_days);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.days,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        String table= (String) DateFormat.format("EEEE", Calendar.getInstance().getTime());
        timeTable(table);
    }

    public void timeTable(final String day){
        timeTableList.clear();
        //final String index=SharedPrefManager.getInstance(this).getIndex();
        //final String index="";
        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,
                Constant.URL_TIMETABLE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array=new JSONArray(response);

                            //not true object.getBoolean("error") means user success fully authenticate
                            for (int i=0; i<array.length(); i++){

                                JSONObject object=array.getJSONObject(i);

                                //            Toast.makeText(getApplicationContext()," For FOR.",Toast.LENGTH_SHORT).show();

                                String day=object.getString("day");
                                String t1=object.getString("t1");
                                String t2=object.getString("t2");
                                String t3=object.getString("t3");
                                String t4=object.getString("t4");
                                String t5=object.getString("t5");
                                String t6=object.getString("t6");
                                String t7=object.getString("t7");
                                String t8=object.getString("t8");
                                String t9=object.getString("t9");
                                String t10=object.getString("t10");

                                TimeTableGetter resultGetter=new TimeTableGetter(day,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10);
                                timeTableList.add(resultGetter);

                                //Toast.makeText(getApplicationContext(),object.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                            adapter = new TimeTableAdapter(nonactimetable.this,timeTableList); //@@@@@@@@
                            recyclerView.setAdapter(adapter);//@@@@@@@@
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Toast.makeText(this,"Still not set that Feature",Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),"catch",Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            //pass the username and password to stringRequest bellow
            //For that we override the method get params
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("timetable",day);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        //Volley.newRequestQueue(this).add(stringRequest);

        //whatever can use through above two
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position==0){
            ((TextView) view).setTextColor(ContextCompat.getColor(this,R.color.textGray));
        }
        if (position>0){
            String day=spinner1.getSelectedItem().toString();
            days.setText(day);
            timeTable(day);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}