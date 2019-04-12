package com.example.imagedownloadapplication.model;

import com.google.gson.annotations.SerializedName;

public class ChildData {

    @SerializedName("post_hint")
    private String postHint;

    @SerializedName("url")
    private String url;

    public String getPostHint() {
        return postHint;
    }

    public String getUrl() {
        return url;
    }

}
