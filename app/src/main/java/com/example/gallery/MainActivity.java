package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemOnClickListener{
    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;
    GridLayoutManager gridLayoutManager;

    ArrayList<Integer> arrayList;

    ItemOnClickListener itemOnClickListener;
    String imagePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        /*Bitmap yourBitmap;
        Bitmap resized = Bitmap.createScaledBitmap(yourBitmap, 130, 130, true);*/

        arrayList=new ArrayList<>();
        arrayList.add(R.drawable.myphoto);
        arrayList.add(R.drawable.myphoto);
        arrayList.add(R.drawable.myphoto);
        arrayList.add(R.drawable.myphoto);
        arrayList.add(R.drawable.myphoto);
        arrayList.add(R.drawable.myphoto);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);
        arrayList.add(R.drawable.sakib);


        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        programAdapter = new MainActRCVAdapter(MainActivity.this,arrayList,MainActivity.this);
        recyclerView.setAdapter(programAdapter);



    }


    @Override
    public void onImageClicked(String imagePath) {
        imagePosition=imagePath;
        Log.e("PastedValue","image number  "+imagePosition);

        Intent fullImageIntent = new Intent(MainActivity.this, FullScreenImageViewActivity.class);
        fullImageIntent.putExtra("imageNumber",imagePosition);
        startActivity(fullImageIntent);
    }
}