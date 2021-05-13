package com.example.infocovid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.infocovid.datalayer.datamodels.Region;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Region> {

    public ArrayList<Region> regions;

    public CustomListAdapter(Context context, int resource, ArrayList<Region> regions) {
        super(context, resource);
        this.regions = regions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }

        TextView tvCityName = convertView.findViewById(R.id.cityName);
        String cityName = regions.get(position).getName();

        tvCityName.setText(cityName);

        return convertView;
    }
}
