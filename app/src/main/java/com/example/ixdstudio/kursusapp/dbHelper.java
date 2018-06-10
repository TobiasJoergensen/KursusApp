package com.example.ixdstudio.kursusapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class dbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ulla";
    public static final String TABLE_NAME_DRINKS = "drinks";
    public static final String TABLE_NAME_EVENTS = "events";
    public static final int DATABASE_VERSION = 1;

    public static final String DRINKS_ID = "_id";
    public static final String NAME = "name";
    public static final String PICTURE = "picture";
    public static final String PRICE_CHEAP = "price_cheap";
    public static final String PRICE_EXPENSIVE = "price_expensive";

    public static final String EVENT_ID = "_id";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String PERSON = "person";
    public static final String DRINK = "drink";

    SQLiteDatabase dbS;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //context.deleteDatabase(DATABASE_NAME);
        Log.d("We are adding:", "ABOUT TO START!!!");
        this.cc = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.dbS = db;
        String query_Drinks = "CREATE TABLE " + TABLE_NAME_DRINKS + " ("
                            + DRINKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + NAME + " TEXT, "
                            + PICTURE + " TEXT, "
                            + PRICE_CHEAP + " TEXT, "
                            + PRICE_EXPENSIVE + " TEXT);";

        String query_Events = "CREATE TABLE " + TABLE_NAME_EVENTS + " ("
                + EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE + " TEXT, "
                + TIME + " TEXT, "
                + PERSON + " TEXT, "
                + DRINK + " TEXT);";

        db.execSQL(query_Drinks);
        db.execSQL(query_Events);
        thread.start();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DRINKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EVENTS);
        Log.d("We are adding:", "ABOUT TO START!!!");
        onCreate(db);
    }

    Context cc;

    public void insertFirstTime (SQLiteDatabase db) {
        ContentValues valuesToInsertDrinks = new ContentValues();

        valuesToInsertDrinks.put(dbHelper.NAME, "Drink name: Coffee");
        valuesToInsertDrinks.put(dbHelper.PICTURE, R.drawable.black_coffee);
        valuesToInsertDrinks.put(dbHelper.PRICE_CHEAP, "Drink lowest price: 25");
        valuesToInsertDrinks.put(dbHelper.PRICE_EXPENSIVE, "Drink highest price: 50");
        db.insert(dbHelper.TABLE_NAME_DRINKS,null, valuesToInsertDrinks);

        valuesToInsertDrinks.put(dbHelper.NAME, "Drink name: Caffee Latte");
        valuesToInsertDrinks.put(dbHelper.PICTURE, R.drawable.caffe_latte);
        valuesToInsertDrinks.put(dbHelper.PRICE_CHEAP, "Drink lowest price: 35");
        valuesToInsertDrinks.put(dbHelper.PRICE_EXPENSIVE, "Drink highest price: 65");
        db.insert(dbHelper.TABLE_NAME_DRINKS,null, valuesToInsertDrinks);

        valuesToInsertDrinks.put(dbHelper.NAME, "Drink name: Espresso");
        valuesToInsertDrinks.put(dbHelper.PICTURE, R.drawable.espressoshot_600x426);
        valuesToInsertDrinks.put(dbHelper.PRICE_CHEAP, "Drink lowest price: 20");
        valuesToInsertDrinks.put(dbHelper.PRICE_EXPENSIVE, "Drink highest price: 30");
        db.insert(dbHelper.TABLE_NAME_DRINKS,null, valuesToInsertDrinks);
        Log.d("We are adding:", "AND ENDING");
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            Log.d("We are adding:", "WE ARE RUNNING!!!");
            insertFirstTime(dbS);
        }
    });

}

