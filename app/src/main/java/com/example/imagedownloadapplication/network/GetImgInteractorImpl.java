package com.example.imagedownloadapplication.network;

import android.util.Log;

import com.example.imagedownloadapplication.model.Listing;
import com.example.imagedownloadapplication.ui.MainContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetImgInteractorImpl implements MainContract.GetImgInteractor {
    private final String TAG = GetImgInteractorImpl.class.getSimpleName();

    @Override
    public void getListingArrayList(String type, int limit, final OnFinishedListener onFinishedListener) {

        Api api = RetrofitInstance.getRetrofitInstance().create(Api.class);
        Call<Listing> call = api.getData(type, limit);
        Log.d(TAG, "NEW" + call.request().url() + "");
        call.enqueue(new Callback<Listing>() {
            @Override
            public void onResponse(Call<Listing> call, Response<Listing> response) {
                assert response.body() != null;
                onFinishedListener.onFinished(response.body().getData());
            }

            @Override
            public void onFailure(Call<Listing> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
