package com.example.ixdstudio.kursusapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;

public class    profile extends AppCompatActivity {

    android.content.SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedpreferences  = getSharedPreferences("MyPrefsFile", Activity.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();

        if(sharedpreferences.contains("firstName")) {
            ImageView image = (ImageView) findViewById(R.id.imageView);
            final EditText FirstName = (EditText) findViewById(R.id.firstName);
            final EditText LastName = (EditText) findViewById(R.id.lastName);
            final EditText EMail = (EditText) findViewById(R.id.eMail);

            String firstNameString = sharedpreferences.getString("firstName", null);
            String lastNameString = sharedpreferences.getString("lastName", null);
            String eMailString = sharedpreferences.getString("eMail", null);
            String bitMapString = sharedpreferences.getString("imagePreferance", null);

            Bitmap imagebMap = decodeBase64(bitMapString);

            android.graphics.drawable.BitmapDrawable bitmapDrawable =
                    new android.graphics.drawable.BitmapDrawable(imagebMap);
            image.setImageDrawable(bitmapDrawable);
            FirstName.setText(firstNameString);
            LastName.setText(lastNameString);
            EMail.setText(eMailString);
        }
    }

    private static final int CAMERA_PIC_REQUEST = 1337;
    Intent MyCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    public void takePicOnClick(View view) {
        startActivityForResult(MyCameraIntent, CAMERA_PIC_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {
            //we get the data
            Bitmap NewFace = (Bitmap) data.getExtras().get("data");
            //we find the Imageview to put them
            ImageView image = (ImageView) findViewById(R.id.imageView);
            image.setImageBitmap(NewFace);
        }
    }

    public void saveOnClick(View view) {

    sharedpreferences  = getSharedPreferences("MyPrefsFile", Activity.MODE_PRIVATE);
    android.content.SharedPreferences.Editor editor = sharedpreferences.edit();

    ImageView image = (ImageView) findViewById(R.id.imageView);
    final EditText FirstName = (EditText) findViewById(R.id.firstName);
    final EditText LastName = (EditText) findViewById(R.id.lastName);
    final EditText EMail = (EditText) findViewById(R.id.eMail);

    if(FirstName.getText().length() > 0 && LastName.getText().length() > 0 && EMail.getText().length() > 0) {
        image.buildDrawingCache();
        Bitmap bmap = image.getDrawingCache();

        editor.putString("firstName", FirstName.getText().toString());
        editor.putString("lastName", LastName.getText().toString());
        editor.putString("eMail", EMail.getText().toString());
        editor.putString("imagePreferance", encodeToBase64(bmap));
        editor.commit();

        android.widget.Toast.makeText(getApplicationContext(),
                "Your profile has been updated.", android.widget.Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        }
    }

    public static String encodeToBase64(Bitmap image) {
    Bitmap immage = image;
    java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
    immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
    byte[] b = baos.toByteArray();
    String imageEncoded = android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT);

    android.util.Log.d("Image Log:", imageEncoded);
    return imageEncoded;
    }

    public static Bitmap decodeBase64(String input) {
    byte[] decodedByte = android.util.Base64.decode(input, 0);
    return android.graphics.BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
