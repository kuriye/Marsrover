package com.mustart.marspictures;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Bou's Laptop on 30/05/2018.
 */

public class MarsPicturesAdapter extends RecyclerView.Adapter<MarsPicturesAdapter.ViewHolder> {
    private ArrayList<Picture> pictures;

    private OnItemClickListener itemClickListener;

    public MarsPicturesAdapter(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    @NonNull
    @Override
    public MarsPicturesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mars_recyclerview_item, parent, false);

        //Log.d("ADAPTER", "reachable");
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String id = "Image ID: " + pictures.get(position).getId();
        holder.id.setText(id);

        Picasso.get().load(pictures.get(position).getImageUrl()).into(holder.picture);
    }

    public Picture getItem(int position) {
        return pictures.get(position);
    }

    @Override
    public int getItemCount() {
        Log.d("ADAPTER", "getItemCount: " + pictures.size());
        return pictures.size();
    }

    public void setItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView id;
        public ImageView picture;

        public ViewHolder(View view) {
            super(view);

            view.setOnClickListener(this);

            id = view.findViewById(R.id.lvPictureId);
            picture = view.findViewById(R.id.lvImageId);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getItem(getLayoutPosition()));
            }
        }

    }
    public interface OnItemClickListener {
        void onItemClick(View v, Picture picture);
    }
}
