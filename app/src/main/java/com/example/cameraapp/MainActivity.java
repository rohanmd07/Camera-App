package com.example.cameraapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

/*import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;*/

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
// import androidx.appcompat.widget.Toolbar;

import android.view.View;

/*import android.view.Menu;
import android.view.MenuItem;*/

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    // Define the pic id
    static final int REQUEST_IMAGE_CAPTURE = 1;

    // Define the button and ImageView type variable
    Button camera_open_id;
    ImageView click_image_id;

    /*Context context = this;
    Activity activity = this;*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // By ID we can get each component
        // which id is assigned in XML file
        // get Buttons and image view.
        camera_open_id = (Button)findViewById(R.id.camera_button);
        click_image_id = (ImageView)findViewById(R.id.click_image1);

        // Camera Permission request
        if(ContextCompat.checkSelfPermission( MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(  MainActivity.this,
                new String[]{
                        Manifest.permission.CAMERA
                },
                 REQUEST_IMAGE_CAPTURE);
    }


        // Camera_open button is for open the camera
        // and add the setOnClickListener in this button
        camera_open_id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    // This method will help to retrieve the image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Match the request pic id with requestCode
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {

            // BitMap is data structure of image file
            // which stores the image in memory
            Bitmap captureImage = (Bitmap)data.getExtras().get("data");

            // Set the image in imageview for display
            click_image_id.setImageBitmap(captureImage);
        }
    }
}