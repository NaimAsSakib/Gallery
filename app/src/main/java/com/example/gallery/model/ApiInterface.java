package com.example.gallery.model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/photos")
    Call<ArrayList<ResponsePojo>> getResponseDetails (@Query("client_id") String clientID,
                                                      @Query("page") int page,
                                                      @Query("per_page") int perPage );

}
