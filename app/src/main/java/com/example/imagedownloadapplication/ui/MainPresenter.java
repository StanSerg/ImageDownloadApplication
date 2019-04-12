package com.example.imagedownloadapplication.ui;

import com.example.imagedownloadapplication.model.Child;
import com.example.imagedownloadapplication.model.ChildData;
import com.example.imagedownloadapplication.model.Data;

import java.util.ArrayList;
import java.util.List;

import static com.example.imagedownloadapplication.utils.Constants.IMAGE;
import static com.example.imagedownloadapplication.utils.Constants.LIMIT;
import static com.example.imagedownloadapplication.utils.Constants.TYPE_NEW;
import static com.example.imagedownloadapplication.utils.Constants.TYPE_TOP;

public class MainPresenter implements MainContract.Presenter, MainContract.GetImgInteractor.OnFinishedListener {

    private MainContract.View mainView;
    private MainContract.GetImgInteractor getImgInteractor;

    private List<String> listUrls;

    public MainPresenter(MainContract.View mainView, MainContract.GetImgInteractor getImgInteractor) {
        this.mainView = mainView;
        this.getImgInteractor = getImgInteractor;
        this.listUrls = new ArrayList<>();
    }

    @Override
    public void onTopButtonClick() {
        this.getImgInteractor.getListingArrayList(TYPE_TOP, LIMIT, this);
    }

    @Override
    public void onNewButtonClick() {
        this.getImgInteractor.getListingArrayList(TYPE_NEW, LIMIT, this);
    }

    @Override
    public void attachView(MainContract.View view) {
        this.mainView = view;
        this.mainView.setDataToView(this.listUrls);
    }

    @Override
    public void detachView() {
        this.mainView = null;
    }


    @Override
    public void onFinished(Data listingData) {
        if (mainView != null) {
            this.listUrls.clear();
            this.listUrls.addAll(parseDataToList(listingData));
            mainView.setDataToView(this.listUrls);
        }
    }

    private List<String> parseDataToList(Data listingData) {
        List<String> list = new ArrayList<String>();
        if (listingData == null) return list;
        for (Child child : listingData.getChildren()) {
            ChildData childData = child.getChildData();
            if (childData != null
                    && childData.getPostHint()!= null
                    && childData.getPostHint().trim().toLowerCase().equals(IMAGE)
                    && childData.getUrl().trim().length() > 0){
                list.add(childData.getUrl());
            }
        }
        return list;
    }

    @Override
    public void onFailure(Throwable t) {
        if (mainView != null) {
            mainView.onResponseFailure(t);
        }
    }
}
