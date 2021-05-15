package com.example.infocovid;

import android.os.Bundle;

import com.example.infocovid.datalayer.datamodels.CityListAdapter;
import com.example.infocovid.datalayer.datamodels.FavoriteListAdapter;
import com.example.infocovid.datalayer.datamodels.PreferencesManager;
import com.example.infocovid.datalayer.datamodels.Region;
import com.example.infocovid.datalayer.datamodels.RegionList;


import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;
import android.widget.ListView;


import java.util.ArrayList;

public class FavoriteListActivity extends AppCompatActivity {

    ListView comunitylistView;
    ListView favoriteList;
    ArrayList<Region> regionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_list_layout);

        comunitylistView = (ListView) findViewById(R.id.comunityList);
        favoriteList = (ListView) findViewById(R.id.favoriteList);

       regionList =  loadAllRegions(regionList);

        FavoriteListAdapter favoriteListAdapter = new FavoriteListAdapter(regionList, getApplicationContext());
        favoriteList.setAdapter(favoriteListAdapter);
        CityListAdapter cityListAdapter = new CityListAdapter(regionList, getApplicationContext());
        comunitylistView.setAdapter(cityListAdapter);

        Log.e("Favorite List", " " + regionList.size());


    }

    public ArrayList<Region> loadAllRegions (ArrayList<Region> regionList) {

        regionList = new ArrayList<>();

        regionList = PreferencesManager.loadPreferences(this);

        if (regionList == null) {
            regionList = new RegionList();
        }

        return regionList;
    }


}