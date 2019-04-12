package com.example.imagedownloadapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Listing {

    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

}
