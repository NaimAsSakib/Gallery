package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.jsibbold.zoomage.ZoomageView;

public class FullScreenImageViewActivity extends AppCompatActivity {

    ZoomageView zoomableImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image_view);
        zoomableImageView=findViewById(R.id.ivFullScreen);


        Intent intent=getIntent();

        String image=intent.getStringExtra("imageNumber");
        int imageID=Integer.parseInt(image);
        Log.e("Image INT","Image INT "+imageID);

        zoomableImageView.setImageResource(imageID);

    }
}