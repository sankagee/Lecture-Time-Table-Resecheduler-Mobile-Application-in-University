package com.example.timescheduling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Ar extends AppCompatActivity{

    private CardView hallallo,logout,noticeview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        hallallo=(CardView) findViewById(R.id.hallalocation);
        logout=(CardView) findViewById(R.id.logoutar);
        noticeview=(CardView)findViewById(R.id.special);

        noticeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent specialactivity=new Intent(Ar.this,specialNot.class);
                startActivity(specialactivity);
            }
        });
        hallallo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stdaddactivity=new Intent(Ar.this,nonactimetable.class);
                startActivity(stdaddactivity);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout(){
        SharedPrefManager.getInstance(this).logout();
        Intent spn=new Intent(Ar.this, MainActivity.class);
        startActivity(spn);
        finish();
    }

}
