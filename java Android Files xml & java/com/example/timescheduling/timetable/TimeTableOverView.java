package com.example.timescheduling.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.timescheduling.Constant;
import com.example.timescheduling.MainActivity;
import com.example.timescheduling.R;
import com.example.timescheduling.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeTableOverView extends AppCompatActivity {

    String table,day;
    List<TimeTableGetter> timeTableList;
    TimeTableAdapter adapter; //@@@@@@@@@@
    TextView user;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_over_view);

        user=findViewById(R.id.t_v_name);
        user.setText("Welcome\n"+String.valueOf(SharedPrefManager.getInstance(getApplicationContext()).getUsername()));
        timeTableList=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler);//@@@@@@@@
        recyclerView.setHasFixedSize(true);//@@@@@@@@
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));//@@@@@@@@   normally we can use for vertical recycler view (Default view is vertical)

        // Horizontal recycler view start
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        // Horizontal recycler view end

        user.setText(String.valueOf(SharedPrefManager.getInstance(this).getUser()));
        table=String.valueOf(SharedPrefManager.getInstance(this).getTable());
        day=getIntent().getStringExtra("DAY");

        timeTable();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return true;
        //switch (item.getItemId()){}

    }


    public void timeTable(){
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
                                //TimeTableGetter resultGetter = new TimeTableGetter("day", "t1", "t2", "t3", "t4", "t5", "t6", "t7", "t8", "t9", "t10");

                                timeTableList.add(resultGetter);

                                //Toast.makeText(getApplicationContext(),object.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                            adapter = new TimeTableAdapter(TimeTableOverView.this,timeTableList); //@@@@@@@@
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
                params.put("timetable",table);
                //params.put("day",day);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        //Volley.newRequestQueue(this).add(stringRequest);

        //whatever can use through above two
    }

}