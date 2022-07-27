package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.gallery.model.ApiInterface;
import com.example.gallery.model.ResponsePojo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ItemOnClickListener{
    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;
    GridLayoutManager gridLayoutManager;


    //API code
    String baseURL = "https://api.unsplash.com";
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        networkLibraryInitializer();
        getData();

    }

    //Api code
    private void networkLibraryInitializer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }


    private void getData() {

        Call<ArrayList<ResponsePojo>> responseDetails=apiInterface.getResponseDetails("9qQ2b3hOLsdJ8MFtaHl-iGXB-6GzyC1E872EAXHDwQ4",1,60);

        responseDetails.enqueue(new Callback<ArrayList<ResponsePojo>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponsePojo>> call, Response<ArrayList<ResponsePojo>> response) {

                ArrayList<ResponsePojo> responsePojo=response.body();

                programAdapter = new MainActRCVAdapter(MainActivity.this,responsePojo,MainActivity.this);
                    recyclerView.setAdapter(programAdapter);



            }

            @Override
            public void onFailure(Call<ArrayList<ResponsePojo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onImageClicked(String imagePath) {
        Log.e("PastedValue","image number  "+imagePath);

        Intent fullImageIntent = new Intent(MainActivity.this, FullScreenImageViewActivity.class);
        fullImageIntent.putExtra("imageNumber",imagePath);
        startActivity(fullImageIntent);
    }
}