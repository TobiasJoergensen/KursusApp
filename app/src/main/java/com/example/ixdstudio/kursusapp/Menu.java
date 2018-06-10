package com.example.ixdstudio.kursusapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;

public class Menu extends AppCompatActivity {

    View view;
    TextView text;
    ListView list;
    dbHelper myhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        updateList();

        /*
        myhelper = new dbHelper(this);

        SQLiteDatabase sd = myhelper.getWritableDatabase();
        long size = new File(sd.getPath()).length();
        Log.d("size", String.valueOf(size));

        Cursor c = sd.rawQuery("SELECT * from " + myhelper.TABLE_NAME_DRINKS, null);

        while (c.moveToNext() ) {
            int colid = c.getColumnIndex(myhelper.NAME);
            String name = c.getString(colid);
            Log.d("HER ER DATA", name);
        }*/
    }

    public void updateList() {
        myhelper = new dbHelper(this);
        ListView MylistView = (ListView) findViewById(R.id.listView);

        SQLiteDatabase sd = myhelper.getWritableDatabase();
        Cursor c = sd.rawQuery("SELECT * FROM " + myhelper.TABLE_NAME_DRINKS, null);

        final String[] presentedData = new String[] {myhelper.NAME, myhelper.PRICE_CHEAP,
                myhelper.PRICE_EXPENSIVE, myhelper.PICTURE};
        int[] presentedViews = new int[] {R.id.name, R.id.price_cheap, R.id.price_expensive, R.id.picture};

        final SimpleCursorAdapter myAdapterDB = new SimpleCursorAdapter(this, R.layout.row_layout,
                c, presentedData, presentedViews, 0);

        MylistView.setAdapter(myAdapterDB);
    }


    public void returnOnClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}
