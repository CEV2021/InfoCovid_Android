package com.example.infocovid.ui.search;

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
import com.example.infocovid.R;
import com.example.infocovid.datalayer.model.SearchData;
import com.example.infocovid.datalayer.model.adapters.FavoriteRegionsAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Views
    AutoCompleteTextView searchBox;
    ListView favoriteRegionsListView;

    // Adapter
    FavoriteRegionsAdapter favoriteRegionsAdapter;

    // test array of strings for the autocomplete
    String[] fruits = {"Apple", "Banana", "Cherry", "Date", "Grape", "Kiwi", "Mango", "Pear"};

    private SearchViewModel searchViewModel;

    public SearchFragment() {
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
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

        View root = inflater.inflate(R.layout.fragment_search, container, false);

        //Creating the instance of ArrayAdapter containing list of fruit names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.select_dialog_item, fruits);

        //Getting the instance of AutoCompleteTextView
        searchBox = (AutoCompleteTextView) root.findViewById(R.id.searchComunity);

        searchBox.setThreshold(1);//will start working from first character
        searchBox.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        searchBox.setTextColor(Color.RED);

        favoriteRegionsListView = root.findViewById(R.id.favoriteRegionsListView);

        searchViewModel.getData().observe(getViewLifecycleOwner(), new Observer<SearchData>() {
            @Override
            public void onChanged(@Nullable SearchData searchData) {
                if (searchData != null && searchData.getRegionNamesList().size() > 0) {
                    processSearchBoxData(searchData, root);
                    processFavoriteRegionsData(searchData);
                }
            }

        });

        return root;
    }

    public void processSearchBoxData(SearchData searchData, View root) {
        ArrayAdapter<String> regionsAdapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.select_dialog_item, searchData.getAutocompleteStrings());
        searchBox.setAdapter(regionsAdapter);
        searchBox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.e("Search Activity", "" + regionsAdapter.getItem(position));
                // The user has clicked, so we clear the text from the searchBox
                searchBox.setText("");
                // Now we get the index of the region selected
                int itemIndex = searchData.getRegionNamesList().indexOf(regionsAdapter.getItem(position));
                // and we ask the model to add it to the favorites list
                searchViewModel.addToFavorites(itemIndex);
            }
        });

    }

    public void processFavoriteRegionsData(SearchData searchData) {

        if (searchData.getFavoriteRegions().size() > 0) {
            Log.e("Favorites: ", "Processing favorites");
            // Filling the listview in with the bands
            favoriteRegionsAdapter = new FavoriteRegionsAdapter(getActivity(), R.layout.favorite_item, searchData);

            favoriteRegionsListView.setAdapter(favoriteRegionsAdapter);

            favoriteRegionsListView.setClickable(true);

            favoriteRegionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.e("Search Activity", "Clicking on favorites list on position " + position);
                    searchViewModel.setMyFavoriteRegion(position);
                }
            });

        } else {
            Log.e("Favorites: ", "No favorites");
            // If the response is NOT OK:
            Toast.makeText(getActivity(), R.string.status_favorites_list_empty, Toast.LENGTH_SHORT).show();
        }
    }

}