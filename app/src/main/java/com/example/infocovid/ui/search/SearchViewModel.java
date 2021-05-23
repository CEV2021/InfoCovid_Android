package com.example.infocovid.ui.search;

import androidx.lifecycle.MutableLiveData;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.model.Region;
import com.example.infocovid.datalayer.model.RegionList;
import com.example.infocovid.datalayer.model.SearchData;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

public class SearchViewModel extends AndroidViewModel {

    private MutableLiveData<SearchData> currentData;
    private SearchData searchData;

    public SearchViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<SearchData> getData() {

        currentData = new MutableLiveData<SearchData>();
        refreshData();
        return currentData;
    }

    public boolean setMyFavoriteRegion(Integer index) {

        // First we get the Region we are going to handle
        // ...we start by getting the name
        String regionNameToAdd = searchData.getRegionNamesList().get(index);
        // .. and, based on that, we identify and pull the region from the full regions list
        RegionList regionsList = new RegionList();
        regionsList.regions = PreferencesManager.getRegions(getApplication());
        Region regionToAdd = regionsList.getRegionByName(regionNameToAdd);

        // Now we set the favorite on the searchData object we are using on this adapter
        searchData.setMyFavoriteRegion(regionToAdd);

        // Now we store the changes on the preferences so we can later call it from other fragments in an easy way
        PreferencesManager.setSearchData(getApplication(), searchData);
        PreferencesManager.setCurrentRegion(getApplication(), regionToAdd);

        // Now we call the refresh method to reload data and notify the View
        refreshData();

        return true;
    }

    public void refreshData() {
        searchData = PreferencesManager.getSearchData(getApplication());
        currentData.setValue(searchData);
    }

}