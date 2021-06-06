package com.example.timescheduling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class nonacedmic extends AppCompatActivity {
    private CardView nonacetimet,logout,special;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonacedmic);
        nonacetimet =(CardView) findViewById(R.id.nonactt);
        logout =(CardView) findViewById(R.id.logout);
        special=(CardView)findViewById(R.id.specialNotice);

        special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent spn=new Intent(nonacedmic.this,specialNot.class);
                startActivity(spn);
            }
        });

        nonacetimet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spn=new Intent(nonacedmic.this,nonactimetable.class);
                spn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(spn);
                finish();
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
        Intent spn=new Intent(nonacedmic.this, MainActivity.class);
        startActivity(spn);
        finish();
    }
}
