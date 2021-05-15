package com.example.infocovid.ui.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.model.Region;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DetailsViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Region>> currentData;


    public DetailsViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<ArrayList<Region>> getData() {

        currentData = new MutableLiveData<ArrayList<Region>>();

        ArrayList<Region> regionList = new ArrayList<Region>();
        regionList = PreferencesManager.getRegions(getApplication());
        currentData.setValue(regionList);

        return currentData;
    }
}