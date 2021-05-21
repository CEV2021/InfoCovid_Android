package com.example.infocovid.ui.main;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.model.Region;
import org.jetbrains.annotations.NotNull;


public class MainViewModel extends AndroidViewModel {

    // This is the definition of the MutableData object we are going to use in here
    // - we can have multiple objects
    // - we can have multiple subscribers
    private MutableLiveData<Region> currentData;

    // Constructor for the ViewModel
    public MainViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    // This method provides the data for the subscriber
    public MutableLiveData<Region> getData() {
        // First we crate a MutableData object of the type we want to return
        // - in this case we return a Region object
        currentData = new MutableLiveData<Region>();
        // now we call a method where we are going to obtain the data
        // - this usually calls an ASync function, but we do that stuff when launching the app
        // - we keep it separated because that way we can call it from outside the fragment at trigger the refresh (nice)
        refreshData();
        return currentData;
    }

    // If you call this method, the fragment refresh gets triggered
    public void refreshData() {
        // First we load the data we want
        // - in this case we are loading the current Region
        Region region = PreferencesManager.getCurrentRegion(getApplication());
        // Then we set it on the MutableData object
        // - the type of data has to match the definition we did up on top
        currentData.setValue(region);
    }

}