package com.example.infocovid.ui.locations;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infocovid.R;
import com.example.infocovid.datalayer.model.SearchData;
import com.example.infocovid.datalayer.model.adapters.FavoriteRegionsAdapter;
import com.example.infocovid.ui.search.SearchViewModel;
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
    AutoCompleteTextView searchBox;
    ListView favoriteRegionsListView;
    RecyclerView favoriteRegionsRecyclerView;

    // Adapter
//    FavoriteRegionsAdapter favoriteRegionsAdapter;
    FavoriteRegionsAdapter favoriteRegionsAdapter;

    private SearchViewModel searchViewModel;

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
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        root = inflater.inflate(R.layout.fragment_search, container, false);

//        favoriteRegionsListView = root.findViewById(R.id.favoriteRegionsListView);
        favoriteRegionsRecyclerView = root.findViewById(R.id.favoriteRegionsReciclerView);

        searchViewModel.getData().observe(getViewLifecycleOwner(), new Observer<SearchData>() {
            @Override
            public void onChanged(@Nullable SearchData searchData) {
                if (searchData != null && searchData.getRegionNamesList().size() > 0) {
                    processFavoriteRegionsData(searchData);
                }
            }

        });

        return root;
    }

    public void processFavoriteRegionsData(SearchData searchData) {

        if (searchData.getFavoriteRegions().size() > 0) {
            Log.e("Favorites: ", "Processing favorites");
            // Filling the listview in with the bands
//            favoriteRegionsAdapter = new FavoriteRegionsAdapter(getActivity(), R.layout.favorite_item, searchData);
//            favoriteRegionsListView.setAdapter(favoriteRegionsAdapter);
//            favoriteRegionsListView.setClickable(true);


//            // Click listener for the favorite regions list
//            favoriteRegionsListView.setOnItemClickListener((parent, view, position, id) -> {
//                Log.e("Search Activity", "Clicking on favorites list on position " + position);
//                // We get the position, and ask the model to set it as the selected for display
//                // i.e.: the current Region to display on the app and widget
//                searchViewModel.setMyFavoriteRegion(position);
//            });

            favoriteRegionsAdapter = new FavoriteRegionsAdapter(searchData);
            favoriteRegionsRecyclerView.setAdapter(favoriteRegionsAdapter);
            favoriteRegionsRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            favoriteRegionsRecyclerView.setClickable(true);

        } else {
            Log.e("Favorites: ", "No favorites");
            // If the response is NOT OK:
            Toast.makeText(getActivity(), R.string.status_favorites_list_empty, Toast.LENGTH_SHORT).show();
        }
    }

}