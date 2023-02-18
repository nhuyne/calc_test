package com.example.calc_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Act_history extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_history);
        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String temp = intent.getStringExtra("username");
        temp=temp.replace("null","");
        String [] fntest=  temp.split("_");
        StringBuilder sb=new StringBuilder();
        for (String s:fntest){
            sb.append(s).append("\n");
        }
        textView.setText(sb.toString());
    }
}