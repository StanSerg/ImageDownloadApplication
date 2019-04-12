package com.example.imagedownloadapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("modhash")
    private String modhash;

    @SerializedName("dist")
    private String dist;

    @SerializedName("children")
    private List<Child> children;


    public List<Child> getChildren() {
        return children;
    }
}
