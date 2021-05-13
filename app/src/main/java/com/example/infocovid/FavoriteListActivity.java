package com.example.infocovid;

import android.os.Bundle;

import com.example.infocovid.datalayer.datamodels.PreferencesManager;
import com.example.infocovid.datalayer.datamodels.Region;
import com.example.infocovid.datalayer.datamodels.RegionList;


import androidx.appcompat.app.AppCompatActivity;


import android.widget.ListView;


import java.util.ArrayList;

public class FavoriteListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Region> regionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_list_layout);

        listView = findViewById(R.id.favoriteList);

        regionList = new ArrayList<>();

        regionList = PreferencesManager.loadPreferences(this);


        CustomListAdapter customListAdapter = new CustomListAdapter(this, R.layout.row, regionList);
        listView.setAdapter(customListAdapter);


    }

    public void load (ArrayList<Region> regionList) {

        regionList = new ArrayList<>();

        regionList = PreferencesManager.loadPreferences(this);

        if (regionList == null) {
            regionList = new RegionList();
        }
    }
}