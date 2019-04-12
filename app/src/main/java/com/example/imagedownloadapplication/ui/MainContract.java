package com.example.imagedownloadapplication.ui;

import com.example.imagedownloadapplication.model.Data;

import java.util.List;

public interface MainContract {

    interface View {
        void setDataToView(List<String> listImagesUrls);
        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void onTopButtonClick();
        void onNewButtonClick();

    }

    interface GetImgInteractor {
        interface OnFinishedListener {
            void onFinished(Data listingData);
            void onFailure(Throwable t);
        }
        void getListingArrayList(String type, int limit, OnFinishedListener onFinishedListener);}
}

