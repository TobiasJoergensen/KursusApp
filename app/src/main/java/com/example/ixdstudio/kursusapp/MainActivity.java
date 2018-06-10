package com.example.ixdstudio.kursusapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    dbHelper myhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Ulla Terkelsen Cafe");
        mHandlerTask.run();
        myhelper = new dbHelper(this);
    }

    public void testOnClick(View v) {
        Intent i = new Intent(this, Test.class);
        startActivity(i);
    }

    public void menuOnClick(View view) {
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
        return;
    }
    public void getDirectionsOnClick(View view) {
        Intent i = new Intent(this, GetDirections.class);
        startActivity(i);

    }
    public void aboutUsOnClick(View view) {
        Intent i = new Intent(this, AboutUs.class);
        startActivity(i);
    }
    public void contactUsOnClick(View view) {
        Intent i = new Intent(this, ContactUs.class);
        startActivity(i);
    }
    public void makeProfileOnClick(View view) {
        Intent i = new Intent(this, profile.class);
        startActivity(i);
    }
    public void eventsOnClick(View view) {
        Intent i = new Intent(this, Events.class);
        startActivity(i);
    }

    private final static int INTERVAL = 1000;
    Handler mHandler = new Handler();

    Runnable mHandlerTask = new Runnable()
    {
        @Override
        public void run() {
            TextView text = (TextView) findViewById(R.id.textView5);
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
            String strDate = "Current Time : " + mdformat.format(currentTime.getTime());
            text.setText(strDate);
            mHandler.postDelayed(mHandlerTask, INTERVAL);
        }
    };


}
