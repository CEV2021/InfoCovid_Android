package com.example.infocovid;

import android.os.Bundle;

import com.example.infocovid.datalayer.datamodels.ComunityHeaderRv;
import com.example.infocovid.datalayer.datamodels.ComunityRv;
import com.example.infocovid.datalayer.datamodels.FavoriteHeaderRv;
import com.example.infocovid.datalayer.datamodels.PreferencesManager;
import com.example.infocovid.datalayer.datamodels.FavoriteRv;
import com.example.infocovid.datalayer.datamodels.Region;
import com.example.infocovid.datalayer.datamodels.RegionList;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import su.j2e.rvjoiner.JoinableAdapter;
import su.j2e.rvjoiner.JoinableLayout;
import su.j2e.rvjoiner.RvJoiner;

public class FavoriteListActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Region> regionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_list_layout);

       regionList =  loadAllRegions(regionList);

       rv = findViewById(R.id.rvList);
       rv.setLayoutManager(new LinearLayoutManager(this));

       //joiner
        RvJoiner rvJoiner = new RvJoiner();
        rvJoiner.add(new JoinableLayout(R.layout.favorite_header_item));
        rvJoiner.add(new JoinableAdapter(new FavoriteHeaderRv()));
        rvJoiner.add(new JoinableLayout(R.layout.row_favorite_list));
        rvJoiner.add(new JoinableAdapter(new FavoriteRv(regionList)));

        rvJoiner.add(new JoinableLayout(R.layout.comunity_header_item));
        rvJoiner.add(new JoinableAdapter(new ComunityHeaderRv()));
        rvJoiner.add(new JoinableLayout(R.layout.row_city_list));
        rvJoiner.add(new JoinableAdapter(new ComunityRv(regionList)));

        rv.setAdapter(rvJoiner.getAdapter());


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