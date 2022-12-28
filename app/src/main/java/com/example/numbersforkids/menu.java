package com.example.numbersforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class menu extends AppCompatActivity {

    String username;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        txt = findViewById(R.id.txt);
        String username = getIntent().getStringExtra("username");
        txt.setText("Hello " + username);
    }
}