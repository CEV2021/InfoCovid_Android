package com.example.infocovid.datalayer.model.adaptersOld;

// Importing relevant stuff

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.infocovid.R;
import com.example.infocovid.datalayer.model.SearchData;

// Adapter used to set each band item into the listview, using the custom layout "band_item"
public class FavoriteRegionsAdapter extends ArrayAdapter {

    Context context;
    int layout;
    SearchData searchData;
    int position;

    // Constructor
    public FavoriteRegionsAdapter(@NonNull Context context, int resource, @NonNull SearchData searchData) {
        super(context, resource, searchData.getFavoriteRegions());
        this.context = context;
        this.layout = resource;
        this.searchData = searchData;
    }

    // The getView method is already explained on previous deliverables.
    // The main differences are:
    // - we are using the Picasso library to set the image
    // - we are using a relative layout for each item (this doesn't really affect the following code)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(layout, parent, false);
        }

        // Retrieving the views we are going to use
        TextView regionName = convertView.findViewById(R.id.region_name);
        ImageView favoriteImageStatus = convertView.findViewById(R.id.favorite_image_status);


        // Settign the values for this band item
        regionName.setText(searchData.getFavoriteRegions().get(position).getName());
        if (searchData.isItFavorite(position)) {
            favoriteImageStatus.setImageResource(R.drawable.ic_favorite_on);
        } else {
            favoriteImageStatus.setImageResource(R.drawable.ic_favorite_off);
        }
//        Button selectButton = convertView.findViewById(R.id.select_favorite_button);
//        selectButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                View parentRow = (View) view.getParent();
//                ListView listView = (ListView) parentRow.getParent();
//                final int position = listView.getPositionForView(parentRow);
//                Log.e("Search Activity", " clicking select on " + String.valueOf(position));
//                //Here perform the action you want
//                boolean check = true;
//            }
//        });

        return convertView;
    }
}
