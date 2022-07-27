package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FullScreenImageViewActivity extends AppCompatActivity {

    ZoomageView zoomableImageView;

    ImageView imgDownload, imgShare;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image_view);


        zoomableImageView = findViewById(R.id.ivFullScreen);
        imgDownload = findViewById(R.id.ivDownload);
        imgShare = findViewById(R.id.ivShare);



        Intent intent = getIntent();
        String image = intent.getStringExtra("imageNumber");
        Log.e("Image INT", "Image INT " + image);

        Glide.with(this)
                .load(image)
                //error shows its image if server image is unreachable or having error from server image
                .error(R.drawable.myphoto)
                .into(zoomableImageView);



        //for share
        imgShare.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             // Code for sharing image
              Drawable drawable=zoomableImageView.getDrawable();
              Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();

              try {
                  File file = new File(getApplicationContext().getExternalCacheDir(), File.separator +"image.jpeg");
                  FileOutputStream fOut = new FileOutputStream(file);
                  bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                  fOut.flush();
                  fOut.close();
                  file.setReadable(true, false);
                  final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID +".provider", file);

                  intent.putExtra(Intent.EXTRA_STREAM, photoURI);
                  intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                  intent.setType("image/jpeg");

                  startActivity(Intent.createChooser(intent, "Share image via"));
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      });

        //for download
        imgDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Calling download method
                downloadImage("Gallery", image);
            }
        });

    }

    //Method for downloading image
    void downloadImage(String fileName, String imageURL) {
        try {
            DownloadManager downloadManager = null;
            downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(imageURL);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                    DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle(fileName)
                    .setMimeType("image/jpeg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + fileName + ".jpeg");

            downloadManager.enqueue(request);

            Toast.makeText(this, "Download completed", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Image download failed", Toast.LENGTH_SHORT).show();
        }
    }

}