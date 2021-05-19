package com.example.infocovid.datalayer.datamodels;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infocovid.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public int flag;
    public Activity activity;
    public ArrayList<Region> regions;

    public RecyclerViewAdapter(Activity activity, ArrayList<Region> regions, int flag) {
        this.activity = activity;
        this.regions = regions;
        this.flag = flag;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if( flag == 0 ) {
          LayoutInflater inflater = activity.getLayoutInflater();
          View view = inflater.inflate(R.layout.favorite_list_layout, parent, false);

          return new ViewHolder(view);
        } else {
            LayoutInflater inflater = activity.getLayoutInflater();
            View view = inflater.inflate(R.layout.row_city_list, parent, false);

            return new ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
      viewHolder.textView.setText(regions.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return regions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.cityName);
        }
    }
}
