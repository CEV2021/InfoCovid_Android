package com.example.infocovid.ui.locations;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infocovid.InfoCovidMiniWidget;
import com.example.infocovid.R;
import com.example.infocovid.datalayer.model.MySettings;
import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.model.Region;
import com.example.infocovid.datalayer.model.SearchData;
import com.example.infocovid.datalayer.model.adapters.FavoriteRegionsAdapter;
import com.example.infocovid.ui.search.SearchViewModel;
import com.example.infocovid.utils.RecyclerItemClickListener;
import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Views
    View root;
    RelativeLayout automaticRegionRelativeLayout;
    ImageView automaticRegionStatusImageView;

    TextView myFavoriteRegionTextView;
    ImageView myFavoriteRegionStatusImageView;
    RecyclerView favoriteRegionsRecyclerView;


    Region myFavoriteRegion;

    // Adapter
    FavoriteRegionsAdapter favoriteRegionsAdapter;

    private LocationsViewModel locationsViewModel;

    public LocationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationsFragment newInstance(String param1, String param2) {
        LocationsFragment fragment = new LocationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        locationsViewModel = new ViewModelProvider(this).get(LocationsViewModel.class);

        root = inflater.inflate(R.layout.fragment_locations, container, false);


        automaticRegionRelativeLayout = root.findViewById(R.id.automaticRegionWrapper);
        automaticRegionStatusImageView = root.findViewById(R.id.automaticRegionSelectedIcon);
        automaticRegionRelativeLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                locationsViewModel.setMyFavoriteRegionAuto();
            }
        });

        myFavoriteRegionTextView = root.findViewById(R.id.myFavoriteRegionName);
        myFavoriteRegionStatusImageView = root.findViewById(R.id.myFavoriteRegionSelectedIcon);

        favoriteRegionsRecyclerView = root.findViewById(R.id.favoriteRegionsRecyclerView);

        MySettings mySettings = PreferencesManager.getMySettings(getContext());

        if (mySettings.getAllowLocation()) {
            automaticRegionRelativeLayout.setVisibility(View.VISIBLE);
        } else {
            automaticRegionRelativeLayout.setVisibility(View.GONE);
        }

        locationsViewModel.getData().observe(getViewLifecycleOwner(), new Observer<SearchData>() {
            @Override
            public void onChanged(@Nullable SearchData searchData) {

                // Setting the value of the current region in case we have one
                if (searchData != null && searchData.getMyFavoriteRegion() != null) {
                    myFavoriteRegion = searchData.getMyFavoriteRegion();

                    myFavoriteRegionTextView.setText(myFavoriteRegion.getName());
                    if (myFavoriteRegion.getId() < 0) {
                        automaticRegionStatusImageView.setImageResource(R.drawable.ic_favorite_on);
                    } else {
                        automaticRegionStatusImageView.setImageResource(R.drawable.ic_favorite_off);
                    }
                }

                // Processing the list of favorites
                if (searchData != null && searchData.getFavoriteRegions().size() > 0) {

                    processFavoriteRegionsData(searchData);
                }
            }

        });

        return root;
    }

    public void processFavoriteRegionsData(SearchData searchData) {

        if (searchData.getFavoriteRegions().size() > 0) {
            Log.e("Favorites: ", "Processing favorites");

            favoriteRegionsAdapter = new FavoriteRegionsAdapter(searchData);
            favoriteRegionsRecyclerView.setAdapter(favoriteRegionsAdapter);
            favoriteRegionsRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            favoriteRegionsRecyclerView.setClickable(true);
            favoriteRegionsRecyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getContext(), favoriteRegionsRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            locationsViewModel.setMyFavoriteRegion(position);

                            // Here we trigger the widget update so we don't rely on the 30min update span
                            Intent intentUpdate = new Intent(root.getContext(), InfoCovidMiniWidget.class);
                            intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                            // @todo: check if we have to go back to the main fragment after setting the location as fovorite
                            // - uncomment line below to trigger the navigation into main fragment
                            // Navigation.findNavController(root).navigate(R.id.navigation_main);

                        }

                        @Override
                        public void onLongItemClick(View view, int position) {
                            // do whatever
                        }
                    })
            );

        } else {
            Log.e("Favorites: ", "No favorites");
            // If the response is NOT OK:
            Toast.makeText(getActivity(), R.string.status_favorites_list_empty, Toast.LENGTH_SHORT).show();
        }
    }

}