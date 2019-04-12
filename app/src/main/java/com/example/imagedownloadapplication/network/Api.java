package com.example.imagedownloadapplication.network;

import com.example.imagedownloadapplication.model.Listing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("{type}/.json")
    Call<Listing> getData (@Path ("type") String type, @Query("limit") int limit);
}

