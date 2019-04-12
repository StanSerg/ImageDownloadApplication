package com.example.imagedownloadapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.imagedownloadapplication.R;

import java.util.List;

public class ImgRecyclerViewAdapter extends RecyclerView.Adapter<ImgRecyclerViewAdapter.ViewHolder> {

    private List<String> urls;
    private Context context;

    public ImgRecyclerViewAdapter(List<String> urls, Context context) {
        this.urls = urls;
        this.context = context;
    }

    @Override
    public ImgRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(this.context)
                .load(this.urls.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.getImage());
    }

    @Override
    public int getItemCount() {
        return this.urls.size();
    }


    @Override
    public int getItemViewType(int position) {
        int index = 1;
        if ((position + 1) % 3 == 1) {
            index = 2;
        }
        return index;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        private ViewHolder(View v) {
            super(v);
            this.image = (ImageView) v.findViewById(R.id.iv);
        }

        private ImageView getImage() {
            return this.image;
        }
    }

}
