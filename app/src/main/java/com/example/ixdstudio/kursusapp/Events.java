package com.example.ixdstudio.kursusapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Events extends AppCompatActivity {

    dbHelper myhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }

    public void saveEventOnClick(View v) {

        final EditText editDate = (EditText) findViewById(R.id.editDate);
        final EditText editTime = (EditText) findViewById(R.id.editTime);
        final EditText editDrink = (EditText) findViewById(R.id.editDrik);
        Log.d("Længden på editText", String.valueOf(editDate.getText().length()));
        if(editDate.getText().length() > 0 && editTime.getText().length() > 0 && editDrink.getText().length() > 0) {

            myhelper = new dbHelper(this);
            ContentValues valuesToInsertDrinks = new ContentValues();
            SQLiteDatabase sd = myhelper.getWritableDatabase();

            valuesToInsertDrinks.put(dbHelper.DATE, "25");
            valuesToInsertDrinks.put(dbHelper.TIME, "50");
            valuesToInsertDrinks.put(dbHelper.DRINK, "50");
            sd.insert(dbHelper.TABLE_NAME_EVENTS,null, valuesToInsertDrinks);
            android.widget.Toast.makeText(getApplicationContext(), "Reservation made.", android.widget.Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else {
            android.widget.Toast.makeText(getApplicationContext(), "Please fill out all fields", android.widget.Toast.LENGTH_LONG).show();
        }
    }
}
