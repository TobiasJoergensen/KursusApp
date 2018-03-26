package com.example.ixdstudio.kursusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Ulla Terkelsen Cafe");
    }

/*    Button test1 = (Button) findViewById(R.id.testButton);
    Button menu = (Button) findViewById(R.id.menuButton);
    Button getDirections = (Button) findViewById(R.id.getDirectionsButton);
    Button aboutUs = (Button) findViewById(R.id.aboutUsButton);
    Button contactUs = (Button) findViewById(R.id.contactUsButton);*/

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
}
