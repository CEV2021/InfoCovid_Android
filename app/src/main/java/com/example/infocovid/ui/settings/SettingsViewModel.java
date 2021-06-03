package com.example.infocovid.ui.settings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.datamodels.RegionList;
import com.example.infocovid.datalayer.model.MySettings;
import com.example.infocovid.datalayer.model.Region;
import com.example.infocovid.datalayer.model.SearchData;

import org.jetbrains.annotations.NotNull;
public class SettingsViewModel extends AndroidViewModel {

    private MutableLiveData<MySettings> currentData;
    private MySettings mySettings;

    public SettingsViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<MySettings> getData() {

        currentData = new MutableLiveData<MySettings>();
        refreshData();
        return currentData;
    }

    public void setData(MySettings newSettings) {
        PreferencesManager.setMySettings(getApplication(), newSettings);

        // setting a default favorite region if we have some on the favorites list
        if (!newSettings.getAllowLocation()) {
            SearchData searchData = PreferencesManager.getSearchData(getApplication());
            if (searchData != null && searchData.getFavoriteRegions() != null && searchData.getFavoriteRegions().size() > 0) {
                searchData.setMyFavoriteRegion(searchData.getFavoriteRegions().get(0));
                PreferencesManager.setSearchData(getApplication(), searchData);
                PreferencesManager.setCurrentRegion(getApplication(), searchData.getFavoriteRegions().get(0));
            }
        }

        refreshData();
    }

    public void refreshData() {

        mySettings = PreferencesManager.getMySettings(getApplication());
        currentData.setValue(mySettings);
    }

}