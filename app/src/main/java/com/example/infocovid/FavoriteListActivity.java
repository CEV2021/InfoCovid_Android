package com.example.infocovid;

import android.os.Bundle;

import com.example.infocovid.datalayer.datamodels.CityListAdapter;
import com.example.infocovid.datalayer.datamodels.FavoriteListAdapter;
import com.example.infocovid.datalayer.datamodels.PreferencesManager;
import com.example.infocovid.datalayer.datamodels.RecyclerViewAdapter;
import com.example.infocovid.datalayer.datamodels.Region;
import com.example.infocovid.datalayer.datamodels.RegionList;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.widget.ListView;


import java.util.ArrayList;

public class FavoriteListActivity extends AppCompatActivity {

    RecyclerView comunityRecyclerView;
    RecyclerView favoriteRecyclerView;
    ArrayList<Region> regionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_list_layout);

        comunityRecyclerView = (RecyclerView) findViewById(R.id.comunityList);
        favoriteRecyclerView = (RecyclerView) findViewById(R.id.favoriteList);

       regionList =  loadAllRegions(regionList);

        RecyclerView.LayoutManager firstLayoutManager = new LinearLayoutManager(this);
        favoriteRecyclerView.setLayoutManager(firstLayoutManager);

        RecyclerView.LayoutManager secondLayoutManager = new LinearLayoutManager(this);
        comunityRecyclerView.setLayoutManager(secondLayoutManager);

        RecyclerViewAdapter firstAdapter = new RecyclerViewAdapter(this, regionList,0);
        RecyclerViewAdapter secondAdapter = new RecyclerViewAdapter(this, regionList, 1);

        favoriteRecyclerView.setAdapter(firstAdapter);
        comunityRecyclerView.setAdapter(secondAdapter);


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