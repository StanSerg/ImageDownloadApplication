package com.example.imagedownloadapplication.ui;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.imagedownloadapplication.R;
import com.example.imagedownloadapplication.model.ChildData;
import com.example.imagedownloadapplication.model.Data;
import com.example.imagedownloadapplication.network.GetImgInteractorImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private final String TAG = MainActivity.class.getSimpleName();
    private MainContract.Presenter presenter;
    private TextView tvInfo;
    private Button btnTop;
    private Button btnNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachPresenter();
        initViewElements();
    }

    private void initViewElements() {
        this.tvInfo = (TextView) findViewById(R.id.tvInfo);
        this.btnNew = (Button) findViewById(R.id.btnNew);
        this.btnNew.setOnClickListener((v) -> this.presenter.onNewButtonClick());
        this.btnTop = (Button) findViewById(R.id.btnTop);
        this.btnTop.setOnClickListener((v) -> this.presenter.onTopButtonClick());
    }

    private void attachPresenter() {
        presenter = (MainContract.Presenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new MainPresenter(this, new GetImgInteractorImpl());
        }
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setDataToView(List<String> listChildUrl) {
        if (this.tvInfo == null) initViewElements();
        this.tvInfo.setText("GET INFORMATION\n " + ((listChildUrl.size()==0) ? "": Collections.singletonList(listChildUrl)));
        Log.d(TAG, "GET INFORMATION\n " + Collections.singletonList(listChildUrl));
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        this.tvInfo.setText(throwable.getMessage());
        Log.d(TAG, throwable.getMessage());
    }
}
