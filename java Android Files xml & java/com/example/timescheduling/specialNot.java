package com.example.timescheduling;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.timescheduling.SpecialNotice.MessageAdapter;
import com.example.timescheduling.SpecialNotice.MessageGetter;
import com.example.timescheduling.timetable.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class specialNot extends AppCompatActivity {

    List<MessageGetter> messageList;
    MessageAdapter adapter; //@@@@@@@@@@

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_not);

        messageList=new ArrayList<>();
        recyclerView=findViewById(R.id.message_recycler);//@@@@@@@@
        recyclerView.setHasFixedSize(true);//@@@@@@@@
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//@@@@@@@@

        messages();
    }
    public void messages(){
        //final String index=SharedPrefManager.getInstance(this).getIndex();
        //final String index="";
        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,
                Constant.URL_MESSAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array=new JSONArray(response);

                            //not true object.getBoolean("error") means user success fully authenticate
                            for (int i=0; i<array.length(); i++){

                                JSONObject object=array.getJSONObject(i);

                                //            Toast.makeText(getApplicationContext()," For FOR.",Toast.LENGTH_SHORT).show();

                                //String date=object.getString("date");
                                String message=object.getString("messages");


                                MessageGetter messageGetter=new MessageGetter(message);
                                messageList.add(messageGetter);

                                //Toast.makeText(getApplicationContext(),object.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                            adapter = new MessageAdapter(specialNot.this,messageList); //@@@@@@@@
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
                        params.put("dep_batch","ict1617");
                        //params.put("lecturer","em01");
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        //Volley.newRequestQueue(this).add(stringRequest);

        //whatever can use through above two
    }

}