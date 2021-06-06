package com.example.timescheduling;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.timescheduling.timetable.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class comregister extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText ETname, ETbatch, mail, userName, password;
    private ProgressDialog progressDialog;
    Button register;
    String dep;
    Spinner spinner,spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdregform);

        spinner=findViewById(R.id.spinner_s);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.department,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        spinner1=findViewById(R.id.spinner_roll);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.roll,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        register=findViewById(R.id.stu_register);

        ETname=findViewById(R.id.e_t_name);
        ETbatch=findViewById(R.id.e_t_batch);
        mail=findViewById(R.id.e_t_email);
        userName=findViewById(R.id.e_t_username);
        password=findViewById(R.id.e_t_password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=spinner1.getSelectedItemPosition();
                if (pos==1){
                    registerStudent();
                }else if(pos==2){
                    registerLecture();
                }else if(pos==3){
                    registerNonAcadamic();
                }
            }

        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0) {
            dep=parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), dep, Toast.LENGTH_SHORT).show();

            String spinner_rol=spinner1.getSelectedItem().toString();

            switch (spinner_rol){
                case "Student":
                    spinner.setVisibility(View.VISIBLE);
                    ETbatch.setHint("your Batch /n 1617");
                    ETbatch.setFilters(new InputFilter[] {new InputFilter.LengthFilter(4)});
                    break;
                case "Lecture":
                    spinner.setVisibility(View.VISIBLE);
                    ETbatch.setHint("Employee ID");
                    ETbatch.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});
                    break;
                case "Non-Academic":
                    spinner.setVisibility(View.INVISIBLE);
                    ETbatch.setHint("Employee ID");
                    ETbatch.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});
                    break;
            }
        }
        if (position==0){
            ((TextView) view).setTextColor(ContextCompat.getColor(this,R.color.textGray));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void registerStudent(){
        final String shortname=ETname.getText().toString().trim();
        final String batch=ETbatch.getText().toString();
        final String dep=spinner.getSelectedItem().toString();
        //final int batch=Integer.parseInt(ETbatch.getText().toString());
        final String email=mail.getText().toString().trim();
        final String username=userName.getText().toString().trim();
        final String pwd=password.getText().toString().trim();

        String type="register";

        if(TextUtils.isEmpty(shortname) || TextUtils.isEmpty(batch) || TextUtils.isEmpty(dep) || TextUtils.isEmpty(email) || TextUtils.isEmpty(username) ||TextUtils.isEmpty(pwd)) {
            Toast.makeText(comregister.this, "some field not filled", Toast.LENGTH_SHORT).show();
        } else {
                    /*progressDialog.setMessage("Registering User....");
                    progressDialog.show();*/
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constant.URL_S_REGISTER,
                    //"http://192.168.43.20/LectureSS/Android/StudentRegister.php",

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //progressDialog.dismiss();
                            /*this is the JSONobject {"error":true,"message":"Invalid Requst"} to show this we create JSONobject
                             * in here "error" is the key & "true" is value. same message & Invalid request*/
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                if (!jsonObject.getBoolean("error")){
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), comlogin.class));
                                    finish();
                                }else {
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //when use this "progressDialog.hide();" app was crashed
                    // progressDialog.hide();
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String,String> params=new HashMap<>();
                    params.put("name",shortname);
                    params.put("batch",batch);
                    params.put("dep",dep);
                    params.put("email",email);
                    // params.put("dep",dep);
                    params.put("username",username);
                    params.put("password",pwd);
                    return params;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        //requestQueue.add(stringRequest);



        }


    }


    private void registerNonAcadamic(){
        final String shortname=ETname.getText().toString().trim();
        final String batch=ETbatch.getText().toString();

        //final int batch=Integer.parseInt(ETbatch.getText().toString());
        final String email=mail.getText().toString().trim();
        final String username=userName.getText().toString().trim();
        final String pwd=password.getText().toString().trim();

        String type="register";

        if(TextUtils.isEmpty(shortname) || TextUtils.isEmpty(batch) || TextUtils.isEmpty(dep) || TextUtils.isEmpty(email) || TextUtils.isEmpty(username) ||TextUtils.isEmpty(pwd)) {
            Toast.makeText(comregister.this, "some field not filled", Toast.LENGTH_SHORT).show();
        } else {
                    /*progressDialog.setMessage("Registering User....");
                    progressDialog.show();*/
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constant.URL_N_REGISTER,
                    //"http://192.168.43.20/LectureSS/Android/StudentRegister.php",

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //progressDialog.dismiss();
                            /*this is the JSONobject {"error":true,"message":"Invalid Requst"} to show this we create JSONobject
                             * in here "error" is the key & "true" is value. same message & Invalid request*/
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                if (!jsonObject.getBoolean("error")){
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), comlogin.class));
                                    finish();
                                }else {
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //when use this "progressDialog.hide();" app was crashed
                    // progressDialog.hide();
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String,String> params=new HashMap<>();
                    params.put("name",shortname);
                    params.put("index",batch);
                    params.put("email",email);
                    // params.put("dep",dep);
                    params.put("username",username);
                    params.put("password",pwd);
                    return params;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            //requestQueue.add(stringRequest);



        }


    }


    private void registerLecture(){
        final String shortname=ETname.getText().toString().trim();
        final String batch=ETbatch.getText().toString();
        final String dep=spinner.getSelectedItem().toString();
        //final String roll=spinner1.getSelectedItem().toString();
        //final int batch=Integer.parseInt(ETbatch.getText().toString());
        final String email=mail.getText().toString().trim();
        final String username=userName.getText().toString().trim();
        final String pwd=password.getText().toString().trim();

        String type="register";

        if(TextUtils.isEmpty(shortname) || TextUtils.isEmpty(batch) || TextUtils.isEmpty(dep) || TextUtils.isEmpty(email) || TextUtils.isEmpty(username) ||TextUtils.isEmpty(pwd)) {
            Toast.makeText(comregister.this, "some field not filled", Toast.LENGTH_SHORT).show();
        } else {
                    /*progressDialog.setMessage("Registering User....");
                    progressDialog.show();*/
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constant.URL_L_REGISTER,
                    //"http://192.168.43.20/LectureSS/Android/StudentRegister.php",

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //progressDialog.dismiss();
                            /*this is the JSONobject {"error":true,"message":"Invalid Requst"} to show this we create JSONobject
                             * in here "error" is the key & "true" is value. same message & Invalid request*/
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                if (!jsonObject.getBoolean("error")){
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), comlogin.class));
                                    finish();
                                }else {
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //when use this "progressDialog.hide();" app was crashed
                    // progressDialog.hide();
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String,String> params=new HashMap<>();
                    params.put("name",shortname);
                    params.put("index",batch);
                    params.put("dep",dep);
                    params.put("email",email);
                    // params.put("dep",dep);
                    params.put("username",username);
                    params.put("password",pwd);
                    return params;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            //requestQueue.add(stringRequest);



        }


    }
}