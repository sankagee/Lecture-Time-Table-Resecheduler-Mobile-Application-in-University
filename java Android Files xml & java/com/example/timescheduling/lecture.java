package com.example.timescheduling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.timescheduling.timetable.TimeTableUpdate;

public class lecture extends AppCompatActivity {

    private CardView hallall,special,lectime,updatetable,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);
        hallall =(CardView) findViewById(R.id.hallalocation);
        special=(CardView) findViewById(R.id.specialNotice);
        lectime=(CardView) findViewById(R.id.lectimet);
        updatetable=(CardView) findViewById(R.id.updatetime);
        logout=(CardView)findViewById(R.id.logout);

        hallall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spn=new Intent(lecture.this,nonactimetable.class);
                startActivity(spn);
            }
        });
        special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spn=new Intent(lecture.this,specialNot.class);
                startActivity(spn);
            }
        });
        lectime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spn=new Intent(lecture.this,lectimetable.class);
                startActivity(spn);
            }
        });
        updatetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spn=new Intent(lecture.this, TimeTableUpdate.class);
                startActivity(spn);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }




        });

    }
    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        Intent spn=new Intent(lecture.this, MainActivity.class);
        spn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(spn);
        finish();
    }
}
