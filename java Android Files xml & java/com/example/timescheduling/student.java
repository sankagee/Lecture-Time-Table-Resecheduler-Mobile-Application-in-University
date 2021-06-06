package com.example.timescheduling;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class student extends AppCompatActivity implements View.OnClickListener{

    private CardView timetablecard,logoutCart,noticeview;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        timetablecard=(CardView) findViewById(R.id.itt);
        logoutCart=findViewById(R.id.logout);
        noticeview=(CardView)findViewById(R.id.specialview);

        noticeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent spn=new Intent(student.this,specialNot.class);
                startActivity(spn);
            }
        });

        timetablecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spn=new Intent(student.this,stdtimetable.class);
                startActivity(spn);
            }
        });

        logoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


    }
    private void logout(){
        SharedPrefManager.getInstance(this).logout();
        Intent spn=new Intent(student.this, MainActivity.class);
        startActivity(spn);
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
