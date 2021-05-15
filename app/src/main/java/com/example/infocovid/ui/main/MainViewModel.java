package com.example.infocovid.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.infocovid.datalayer.datamodels.Data;
import com.example.infocovid.datalayer.datamodels.PreferencesManager;
import com.example.infocovid.datalayer.datamodels.RegionList;

import org.jetbrains.annotations.NotNull;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<RegionList> currentData;

    public MainViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<RegionList> getData() {
        currentData = new MutableLiveData<RegionList>();

        RegionList list = new RegionList();
        list.regions = PreferencesManager.loadPreferences(getApplication());
        currentData.setValue(list);

        return currentData;
    }

}