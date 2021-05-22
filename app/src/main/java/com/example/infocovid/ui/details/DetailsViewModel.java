package com.example.infocovid.ui.details;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.model.Region;
import org.jetbrains.annotations.NotNull;

public class DetailsViewModel extends AndroidViewModel {

    private MutableLiveData<Region> currentData;
    
    public DetailsViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<Region> getData() {
        currentData = new MutableLiveData<Region>();
        refreshData();
        return currentData;
    }

    public void refreshData() {
        Region region = PreferencesManager.getCurrentRegion(getApplication());
        currentData.setValue(region);
    }
}