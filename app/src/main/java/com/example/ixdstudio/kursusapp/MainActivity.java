package com.example.ixdstudio.kursusapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
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

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    dbHelper myhelper;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    int[] songs;
    MediaPlayer mp;
    int currentID = 0;
    long lastUpdate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Ulla Terkelsen Cafe");
        mHandlerTask.run();
        myhelper = new dbHelper(this);

        songs = new int[] {R.raw.sang2, R.raw.sang3};
        musicPlayer();
        Log.e("Array size", Integer.toString(songs.length));
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

    }

    protected void musicPlayer() {
        mp = MediaPlayer.create(this, songs[0]);
        //mp.start();
    }

    public boolean isPlaying = false;

    public void playPause(View view) {
        Button button = (Button) findViewById(R.id.playPause);
        if(mp.isPlaying()) {
            mp.pause();
            button.setText("Paused");
            isPlaying = false;
            return;
        }
        if(!mp.isPlaying()) {
            mp.start();
            button.setText("Playing");
            isPlaying = true;
            return;
        }
    }

    public void nextSong(View view) {
        if(currentID < (songs.length - 1)) {
            currentID++;
            mp.stop();
            mp = MediaPlayer.create(this, songs[currentID]);
            mp.start();
        }
    }

    public void lastSong(View view) {
        if(currentID > 0 ) {
            currentID--;
            mp.stop();
            mp = MediaPlayer.create(this, songs[currentID]);
            mp.start();
        }
    }

    public void nextingSong() {
        if(currentID < (songs.length - 1)) {
            currentID++;
            mp.stop();
            mp = MediaPlayer.create(this, songs[currentID]);
            mp.start();
        }
        else {
            currentID = 0;
            mp.stop();
            mp = MediaPlayer.create(this, songs[currentID]);
            mp.start();
        }
    }

    public void testOnClick(View v) {
        Intent i = new Intent(this, Test.class);
        if(mp.isPlaying()) {mp.stop();}
        senSensorManager.unregisterListener(this);
        startActivity(i);
    }

    public void menuOnClick(View view) {
        Intent i = new Intent(this, Menu.class);
        senSensorManager.unregisterListener(this);
        if(mp.isPlaying()) {mp.stop();}
        startActivity(i);
    }

    public void getDirectionsOnClick(View view) {
        Intent i = new Intent(this, GetDirections.class);
        senSensorManager.unregisterListener(this);
        if(mp.isPlaying()) {mp.stop();}
        startActivity(i);

    }
    public void aboutUsOnClick(View view) {
        Intent i = new Intent(this, AboutUs.class);
        senSensorManager.unregisterListener(this);
        if(mp.isPlaying()) {mp.stop();}
        startActivity(i);
    }
    public void contactUsOnClick(View view) {
        Intent i = new Intent(this, ContactUs.class);
        senSensorManager.unregisterListener(this);
        if(mp.isPlaying()) {mp.stop();}
        startActivity(i);
    }
    public void makeProfileOnClick(View view) {
        Intent i = new Intent(this, profile.class);
        senSensorManager.unregisterListener(this);
        if(mp.isPlaying()) {mp.stop();}
        startActivity(i);
    }
    public void eventsOnClick(View view) {
        Intent i = new Intent(this, Events.class);
        senSensorManager.unregisterListener(this);
        if(mp.isPlaying()) {mp.stop();}
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


    @Override
    public void onSensorChanged(SensorEvent event) {
        float NOISE = (float) 2.0;

        float xAxis = event.values[0];
        float yAxis = event.values[1];
        float zAxis = event.values[2];
        long actualTime = System.currentTimeMillis();

        if ((actualTime - lastUpdate) > 400) {

            if (xAxis > 4 && isPlaying != false) { nextingSong();}

            Log.e("Movement on X", Float.toString(xAxis));
            Log.e("Movement on Y", Float.toString(yAxis));
            Log.e("Movement on Z", Float.toString(zAxis));

            lastUpdate = actualTime;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
