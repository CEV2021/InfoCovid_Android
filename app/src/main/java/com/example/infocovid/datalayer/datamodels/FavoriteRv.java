package com.example.infocovid.datalayer.datamodels;


import android.media.Image;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infocovid.R;

import java.util.ArrayList;

public class FavoriteRv extends RecyclerView.Adapter<FavoriteRv.ViewHolder>{

    ArrayList<Region> regions;

    public FavoriteRv(ArrayList<Region>regions) {
        this.regions = regions;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(regions.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return regions.size();
    }


    /*********************************************** viewholders ***********************************************/

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView cityName;
        ImageView imageView;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_favorite_list, parent, false));
            cityName = itemView.findViewById(R.id.cityName);
            imageView = itemView.findViewById(R.id.imageView);
        }

        private void bind(String name) {

            cityName.setText(name);
            imageView.setImageResource(R.drawable.ic_fully_favorite);
        }
    }

}
