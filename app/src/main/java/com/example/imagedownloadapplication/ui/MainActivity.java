package com.example.imagedownloadapplication.ui;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.example.imagedownloadapplication.R;
import com.example.imagedownloadapplication.adapters.ImgRecyclerViewAdapter;
import com.example.imagedownloadapplication.network.GetImgInteractorImpl;

import java.util.Collections;
import java.util.List;

import static com.example.imagedownloadapplication.utils.Constants.GRID_VIEW_TYPE_HEADER;
import static com.example.imagedownloadapplication.utils.Constants.GRID_VIEW_TYPE_ITEM;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private final String TAG = MainActivity.class.getSimpleName();
    private MainContract.Presenter presenter;
    private Button btnTop;
    private Button btnNew;

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachPresenter();
        initViewElements();
    }

    private void initViewElements() {
        this.btnNew = (Button) findViewById(R.id.btnNew);
        this.btnNew.setOnClickListener((v) -> this.presenter.onNewButtonClick());
        this.btnTop = (Button) findViewById(R.id.btnTop);
        this.btnTop.setOnClickListener((v) -> this.presenter.onTopButtonClick());
        this.recyclerView = (RecyclerView) findViewById(R.id.rvImages);
        this.gridLayoutManager = new GridLayoutManager(this, 2);
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
        this.gridLayoutManager = null;
        super.onDestroy();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setDataToView(List<String> listChildUrl) {
        if (this.recyclerView == null) initViewElements();
        ImgRecyclerViewAdapter adapter = new ImgRecyclerViewAdapter(listChildUrl, this);
        this.gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)) {
                    case GRID_VIEW_TYPE_HEADER:
                        return 2;
                    case GRID_VIEW_TYPE_ITEM:
                        return 1;
                    default:
                        return -1;
                }
            }
        });
        this.recyclerView.setLayoutManager(this.gridLayoutManager);
        this.recyclerView.setAdapter(adapter);
        Log.d(TAG, "GET INFORMATION\n " + Collections.singletonList(listChildUrl));
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.d(TAG, throwable.getMessage());
    }
}
