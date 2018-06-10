package com.example.ixdstudio.kursusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
    public void returnOnClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
