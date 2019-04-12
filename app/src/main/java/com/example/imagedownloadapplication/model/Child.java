package com.example.imagedownloadapplication.model;

import com.google.gson.annotations.SerializedName;


public class Child {

    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private ChildData childData;

   public ChildData getChildData() {
        return childData;
    }

}
