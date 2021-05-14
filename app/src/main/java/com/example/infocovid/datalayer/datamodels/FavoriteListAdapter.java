package com.example.infocovid.datalayer.datamodels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.infocovid.FavoriteListActivity;
import com.example.infocovid.R;

import java.util.ArrayList;

public class FavoriteListAdapter extends ArrayAdapter<Region> {
    public ArrayList<Region> regions;
    Context context;

    private static class ViewHolder{
        TextView cityName;
        ImageView icon;
    }

    public FavoriteListAdapter(ArrayList<Region> regions, Context context) {
        super(context, R.layout.row_favorite_list, regions);
        this.regions = regions;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Region region = getItem(position);

        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_favorite_list, parent, false);
            viewHolder.cityName = (TextView) convertView.findViewById(R.id.cityName);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.cityName.setText(region.getName());
        viewHolder.icon.setImageResource(R.drawable.ic_baseline_favorite);

        return convertView;
    }

}
