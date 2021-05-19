package com.example.infocovid.ui.settings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.datamodels.RegionList;
import com.example.infocovid.datalayer.model.MySettings;

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
        refreshData();
    }

    public void refreshData() {

        mySettings = PreferencesManager.getMySettings(getApplication());
        currentData.setValue(mySettings);
    }

}