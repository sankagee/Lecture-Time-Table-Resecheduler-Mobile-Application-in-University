package com.example.timescheduling;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.timescheduling.timetable.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class comlogin extends AppCompatActivity {
    EditText user_name,password;
    Button s_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdlogin);
        user_name=findViewById(R.id.e_t_s_login_username);
        password=findViewById(R.id.e_t_s_login_password);
        s_login=findViewById(R.id.s_login_btn);

        s_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentLogin();
            }
        });

    }
    private void studentLogin(){

        final String username=user_name.getText().toString().trim();
        final String pwd=password.getText().toString().trim();
        //final String dep=spinner.getSelectedItem().toString();
        String type="register";

        if( TextUtils.isEmpty(username) ||TextUtils.isEmpty(pwd)) {
            Toast.makeText(comlogin.this, "some field not filled", Toast.LENGTH_SHORT).show();
        } else {
                    /*progressDialog.setMessage("Registering User....");
                    progressDialog.show();*/
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constant.URL_COM_LOGIN,
                    //"http://192.168.43.20/LectureSS/Android/StudentLogin.php",

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //progressDialog.dismiss();
                            /*this is the JSONobject {"error":true,"message":"Invalid Requst"} to show this we create JSONobject
                             * in here "error" is the key & "true" is value. same message & Invalid request*/
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                if (!jsonObject.getBoolean("error")){
                                    //store the user data to shared preference
                                    SharedPrefManager.getInstance(getApplicationContext())
                                            .login(
                                                    //in here should equal the parsing variables with php response
                                                    jsonObject.getString("name"),
                                                    jsonObject.getString("time table"),
                                                    jsonObject.getString("user")
                                            );
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "User login success",
                                            Toast.LENGTH_LONG
                                    ).show();

                                    if ("Student".equals(jsonObject.getString("user"))) {

                                        Intent intent=new Intent(comlogin.this,student.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }else if ("Lecture".equals(jsonObject.getString("user"))){
                                        Intent intent=new Intent(comlogin.this,lecture.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }else if ("Admin".equals(jsonObject.getString("user"))){
                                        Intent intent=new Intent(comlogin.this,Ar.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();

                                    }else if ("NonAcadamic".equals(jsonObject.getString("user"))){
                                        Intent intent=new Intent(comlogin.this,nonacedmic.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }

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

                    params.put("username",username);
                    params.put("password",pwd);
                    return params;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/



        }


    }
}