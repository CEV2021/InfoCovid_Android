package com.example.infocovid.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.infocovid.datalayer.datamodels.Data;
import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.datamodels.RegionList;
import com.example.infocovid.datalayer.model.MySettings;
import com.example.infocovid.datalayer.model.SearchData;

import org.jetbrains.annotations.NotNull;

public class SearchViewModel extends AndroidViewModel {

    private MutableLiveData<SearchData> currentData;

    public SearchViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<SearchData> getData() {

        currentData = new MutableLiveData<SearchData>();

        SearchData searchData = new SearchData();
        searchData = PreferencesManager.getSearchData(getApplication());
        currentData.setValue(searchData);

        return currentData;
    }
}