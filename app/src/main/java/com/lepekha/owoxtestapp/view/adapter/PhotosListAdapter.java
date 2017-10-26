package com.lepekha.owoxtestapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lepekha.owoxtestapp.R;
import com.lepekha.owoxtestapp.model.pojo.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ruslan on 25.10.2017.
 */

public class PhotosListAdapter extends RecyclerView.Adapter<PhotosListAdapter.ViewHolder> {

    private List<Photo> photos;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.thumbneilPhoto)
        ImageView imgThumbneilPhoto;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public PhotosListAdapter(List<Photo> photos, Context context) {
        this.photos = photos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumbneil, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(photos.get(position).getUrls().getThumb()).into(holder.imgThumbneilPhoto);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

}
