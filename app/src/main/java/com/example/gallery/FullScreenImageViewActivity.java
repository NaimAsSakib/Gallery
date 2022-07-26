package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;

public class FullScreenImageViewActivity extends AppCompatActivity {

    ZoomageView zoomableImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image_view);


        zoomableImageView=findViewById(R.id.ivFullScreen);


     /*   Intent intent=getIntent();
        String image=intent.getStringExtra("imageNumber");
        Log.e("Image INT","Image INT "+image);

        int imageID=Integer.parseInt(image);
        Log.e("Image INT","Image INT "+imageID);
        zoomableImageView.setImageResource(imageID);*/

        Intent intent=getIntent();
        String image=intent.getStringExtra("imageNumber");
        Log.e("Image INT","Image INT "+image);

        Glide.with(this)
                .load(image)
                //error shows its image if server image is unreachable or having error from server image
                .error(R.drawable.myphoto)
                .into(zoomableImageView);

    }
}