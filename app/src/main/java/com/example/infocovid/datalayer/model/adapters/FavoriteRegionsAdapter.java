package com.example.infocovid.datalayer.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infocovid.R;
import com.example.infocovid.datalayer.model.Region;
import com.example.infocovid.datalayer.model.SearchData;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class FavoriteRegionsAdapter extends RecyclerView.Adapter<FavoriteRegionsAdapter.ViewHolder> {


    // Store a member variable for the contacts
    private SearchData searchData;

    // Pass in the contact array into the constructor
    public FavoriteRegionsAdapter(SearchData searchData) {
        this.searchData = searchData;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView regionNameTextView;
        public ImageView favoriteImageStatusImageView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            regionNameTextView = itemView.findViewById(R.id.region_name);
            favoriteImageStatusImageView = itemView.findViewById(R.id.favorite_image_status);
        }
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public FavoriteRegionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View regionItemView = inflater.inflate(R.layout.favorite_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(regionItemView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(FavoriteRegionsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Region region = searchData.getFavoriteRegions().get(position);

        // Set item views based on your views and data model
        TextView textView = holder.regionNameTextView;
        textView.setText(region.getName());
        ImageView imageView = holder.favoriteImageStatusImageView;
        if (searchData.isItFavorite(position)) {
            imageView.setImageResource(R.drawable.ic_favorite_on);
        } else {
            imageView.setImageResource(R.drawable.ic_favorite_off);
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return searchData.getFavoriteRegions().size();
    }
}