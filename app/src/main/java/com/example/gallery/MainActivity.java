package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gallery.model.ApiInterface;
import com.example.gallery.model.ResponsePojo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ItemOnClickListener {
    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;
    GridLayoutManager gridLayoutManager;

    MySharedPref mySharedPref;

    LoadingProgressBarDialog loadingProgressBarDialog;

    //API code
    String baseURL = "https://api.unsplash.com";
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySharedPref = new MySharedPref(this);

        recyclerView = findViewById(R.id.recyclerView);

        loadingProgressBarDialog = new LoadingProgressBarDialog(this);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);


        //Now, checking internet connection, when device is online, saving previous response in shared pref in String form.
        //when device is offline,reconverting that string into Json object again from shared pref and loading data of that previous response.
        //This is called offline caching mechanism, as data is present, though device is offline.

        //checking internet connection with ConnectivityManager
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo != null) {   //means internet is on in mobile, so call API

            networkLibraryInitializer();
            getData();
        } else {  // means internet is off in mobile

            loadingProgressBarDialog.startProgressBarLoading();
            //converting string value saved in shared preference to Json object again
            ArrayList<ResponsePojo> responsePojo = new Gson().fromJson(mySharedPref.getString("stringKey"), new TypeToken<List<ResponsePojo>>() {
            }.getType());
            // setting adapter here with previously saved data to show previous data when device is offline
            programAdapter = new MainActRCVAdapter(MainActivity.this, responsePojo, MainActivity.this);
            recyclerView.setAdapter(programAdapter);
            loadingProgressBarDialog.dismissProgressBarDialog();
        }


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

        loadingProgressBarDialog.startProgressBarLoading();
        String clintID = "9qQ2b3hOLsdJ8MFtaHl-iGXB-6GzyC1E872EAXHDwQ4";

        Call<ArrayList<ResponsePojo>> responseDetails = apiInterface.getResponseDetails(clintID, 1, 60);

        responseDetails.enqueue(new Callback<ArrayList<ResponsePojo>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponsePojo>> call, Response<ArrayList<ResponsePojo>> response) {

                ArrayList<ResponsePojo> responsePojo = response.body();

                //converting response into string & saving to shared preference
                Gson gson = new Gson();
                String saveConvertedString = gson.toJson(response.body());
                mySharedPref.putString("stringKey", saveConvertedString);

                programAdapter = new MainActRCVAdapter(MainActivity.this, responsePojo, MainActivity.this);
                recyclerView.setAdapter(programAdapter);

                loadingProgressBarDialog.dismissProgressBarDialog();
            }

            @Override
            public void onFailure(Call<ArrayList<ResponsePojo>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onImageClicked(String imagePath) {
        Log.e("PastedValue", "image number  " + imagePath);

        Intent fullImageIntent = new Intent(MainActivity.this, FullScreenImageViewActivity.class);
        fullImageIntent.putExtra("imageNumber", imagePath);
        startActivity(fullImageIntent);
    }
}