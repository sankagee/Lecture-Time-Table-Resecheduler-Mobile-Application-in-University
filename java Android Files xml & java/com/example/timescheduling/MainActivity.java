package com.example.timescheduling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity{
    Button signin_btn,signup_btn,arButton,stdButton,bottomlog,bottomlog1,bottomlog2,bottomlogn,bottomlogn2;
    EditText loginet,lecemail,lecpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signin_btn = findViewById(R.id.sign_in);
        signup_btn = findViewById(R.id.sign_up);

        /*nonacdemicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, nonacedmic.class);
                startActivity(intent);
                *//*BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                bottomSheetDialog.setContentView(R.layout.nonacbottom);

                bottomlog1= bottomSheetDialog.findViewById(R.id.nonlogin);
                bottomlog2= bottomSheetDialog.findViewById(R.id.nonreglay);

                bottomlog1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, nonlogin.class);
                        startActivity(intent);
                    }
                });
                bottomlog2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, nonregform.class);
                        startActivity(intent);
                    }
                });
                bottomSheetDialog.show();*//*
            }
        });*/
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_ar);

                final EditText aruser = bottomSheetDialog.findViewById(R.id.txtar);
                final EditText arpass = bottomSheetDialog.findViewById(R.id.passar);
                Button bottomlog = bottomSheetDialog.findViewById(R.id.arlogb);

                bottomlog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (aruser.getText().toString().equals("admin") && arpass.getText().toString().equals("admin")) {
                            Intent intent = new Intent(MainActivity.this, Ar.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(view.getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                bottomSheetDialog.show();*/

                Intent intent=new Intent(MainActivity.this, comlogin.class);
                startActivity(intent);
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                Intent intent=new Intent(MainActivity.this, comregister.class);
                startActivity(intent);
                /* BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                bottomSheetDialog.setContentView(R.layout.stdbottom);

                bottomlog1 = bottomSheetDialog.findViewById(R.id.stdlogin);
                bottomlog2= bottomSheetDialog.findViewById(R.id.stdreglay);

                bottomlog1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, stdlogin.class);
                            startActivity(intent);
                    }
                });
                bottomlog2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, stdregform.class);
                        startActivity(intent);
                    }
                });
                bottomSheetDialog.show();*/
            }
        });

        /*lecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_layout);

                bottomlog1 = bottomSheetDialog.findViewById(R.id.leclogin);
                bottomlog2= bottomSheetDialog.findViewById(R.id.lecreglay);

                bottomlog1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, leclogin.class);
                        startActivity(intent);
                    }
                });
                bottomlog2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, lecregform.class);
                        startActivity(intent);
                    }
                });
                bottomSheetDialog.show();
            }
        });*/

        if(SharedPrefManager.getInstance(this).isLectureLoggedIn()){
            if (SharedPrefManager.getInstance(this).getUser().equals("Lecture")){
                finish();
                startActivity(new Intent(this, lecture.class));
                return;

            }else if(SharedPrefManager.getInstance(this).getUser().equals("Student")){
                finish();
                startActivity(new Intent(this, student.class));
                return;
            }else if(SharedPrefManager.getInstance(this).getUser().equals("NonAcadamic")){
                finish();
                startActivity(new Intent(this, nonacedmic.class));
                return;
            }else if(SharedPrefManager.getInstance(this).getUser().equals("Admin")) {
                finish();
                startActivity(new Intent(this, Ar.class));
                return;
            }


        }

        /*if(SharedPrefManager.getInstance(this).isLectureLoggedIn()) {
            startActivity(new Intent(this, lecture.class));
            return;
        }*/
    }
}

