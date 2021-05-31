package com.example.infocovid.ui.locations;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.model.Region;
import com.example.infocovid.datalayer.model.RegionList;
import com.example.infocovid.datalayer.model.SearchData;

import org.jetbrains.annotations.NotNull;

public class LocationsViewModel extends AndroidViewModel {

    private MutableLiveData<SearchData> currentData;
    private SearchData searchData;

    public LocationsViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<SearchData> getData() {

        currentData = new MutableLiveData<SearchData>();
        refreshData();
        return currentData;
    }


    public void setMyFavoriteRegion(Integer index) {
        // First we get the Region we are going to handle
        Region regionToAdd = searchData.getFavoriteRegions().get(index);
        // Now we set the favorite on the searchData object we are using on this adapter
        searchData.setMyFavoriteRegion(regionToAdd);

        // Now we store the changes on the preferences so we can later call it from other fragments in an easy way
        PreferencesManager.setSearchData(getApplication(), searchData);
        PreferencesManager.setCurrentRegion(getApplication(), regionToAdd);

        // Now we call the refresh method to reload data and notify the View
        refreshData();
    }

    public void refreshData() {
        searchData = PreferencesManager.getSearchData(getApplication());
        currentData.setValue(searchData);
    }

}