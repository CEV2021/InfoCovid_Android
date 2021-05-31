package com.example.infocovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.infocovid.datalayer.model.ApiClient;
import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.model.Region;
import com.example.infocovid.datalayer.model.RegionList;
import com.example.infocovid.datalayer.model.RegionService;
import com.example.infocovid.datalayer.model.SearchData;
import com.example.infocovid.utils.GPSTracker;
import com.example.infocovid.utils.GeneralHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // old code -- to adapt
    BottomNavigationView nBottomNavigation;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;


    BottomNavigationView navView;

    //API
    RegionList regionList;
    SearchData searchData;
    RegionService regionService;
    NavController navController;
    boolean check = false;

    // Location
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    // GPSTracker class
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting the Main Activity as the activity for the app
        setContentView(R.layout.activity_main);

        // Calling the method to load data
        this.loadData();

        // Creating a bottomNavigation element to display the menu
        // - the bottom navigation is set on the main activity
        // - we use this plus a navigation controller to handle the navigation throughout the app in a simpler way
        navView = findViewById(R.id.nav_view);

        // Passing each of the ids from the navigation item into the app bar
        // - if we want to include a new item on the navigation bar, we add it in here
        // - the fragment which gets displayed it's defined on the file mobile_navigation.xml
        // - for more info @see /res/navigation/mobile_navigation.xml
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_main, R.id.navigation_details, R.id.navigation_search, R.id.navigation_settings)
                .build();

        // Now we set the navigation controller
        // - we have the nav controller as a class variable so we can access it on any place of the MainActivity class
        // - also, by doing that we can easily handle the navigation from within the fragments in case we'd need it
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Here we link the action bar and the bottom navigation so the status of the appbar (like the titles) change
        // - with this, the bottom navigation also reacts to those changes
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission}, REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Method to check if the mobile phone is connected before starting the request
    private boolean isConnected() {
        boolean validConnection = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            // As far as I know, cellphones can connect through mobile or wifi.
            // We also leave ethernet there for those weird cases.
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                // support for new versions
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    validConnection = true;
                }
            } else {
                // support for old versions
                switch (networkInfo.getType()) {
                    case ConnectivityManager.TYPE_MOBILE:
                    case ConnectivityManager.TYPE_WIFI:
                    case ConnectivityManager.TYPE_ETHERNET:
                        validConnection = true;
                        break;
                }
            }
        }

        return validConnection;
    }


    //Method to refreshDat, views and get the Data From API
    public void refreshData() {
        if (isConnected()) {
            Log.e("loading stuff", "Connected");
            regionService = ApiClient.getClient().create(RegionService.class);
            getData(); //Peticion

        } else {
            Log.e("loading stuff", "Not connected");
            // If there is no connection:
            Toast.makeText(this, "No connection", Toast.LENGTH_SHORT).show();
        }
    }

    //Getting data from preferences if we have it
    public void loadData() {
        regionList = new RegionList();

//        regionList.regions = PreferencesManager.getRegions(this);
        this.refreshData();
        Log.e("loading stuff", "Loading data");
        if (regionList.regions == null) {
            Log.e("loading stuff", "Refreshing data");
            this.refreshData();
        } else {
            check = true;

//            setUpViews(regionList, 1);
        }
    }

    public void setUpViews(RegionList list, int index) {

        // To update views
        String totalCases;
        String newCases;
        String healths;
        String deaths;
        String incidentRate;

        if (regionList.regions != null) { // Update views
//            nombreCiudad.setText(list.regions.get(index).getName());
//            incidenciaAcumulada.setText(incidentRate = String.valueOf(list.regions.get(index).getData().get(index).getIncidentRate()));
//            casosTotales.setText(totalCases = String.valueOf(list.regions.get(index).getData().get(index).getConfirmed()) );
//            nuevosCasos.setText( newCases = String.valueOf( list.regions.get(index).getData().get(index).getActive()));
//            curados.setText( healths = String.valueOf(list.regions.get(index).getData().get(index).getRecovered()));
//            fallecidos.setText(deaths = String.valueOf(list.regions.get(index).getData().get(index).getDeaths()));
        } else {
            refreshData();
        }
    }

    // Getting data from API and save in Preferences
    public void getData() {
        Call<ArrayList<Region>> call = regionService.getRegions();

        call.enqueue(new Callback<ArrayList<Region>>() {
            @Override
            public void onResponse(Call<ArrayList<Region>> call, Response<ArrayList<Region>> response) {
                Log.e("onResponse", "Response -->" + response.body().size());

                regionList = new RegionList();
                searchData = PreferencesManager.getSearchData(getApplicationContext());
                if (searchData != null) {
                    searchData.clearRegionNamesList();
                } else {
                    searchData = new SearchData();
                }

                for (int i = 0; i < response.body().size(); i++) {

                    response.body().get(i).setName(GeneralHelper.normalizeRegionName(response.body().get(i).getName()));
                    regionList.regions.add(i, response.body().get(i));
                    // No need to normalize again, names are already normalized by the previous loop
                    searchData.addRegionName(response.body().get(i).getName());

                }

                PreferencesManager.setRegions(getApplicationContext(), regionList.regions);
                PreferencesManager.setSearchData(getApplicationContext(), searchData);
                check = true;

                RegionList list = new RegionList();
                list.regions = PreferencesManager.getRegions(getApplicationContext());

                setUpViews(list, 1);

            }

            @Override
            public void onFailure(Call<ArrayList<Region>> call, Throwable t) {
                Log.e("onFailure", "Error " + t.getLocalizedMessage());
                check = false;
            }
        });

    }


    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /// App Bar -- Toolbar -- Menu Items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                navController.navigate(R.id.navigation_main);
                return true;

            case R.id.addItem:
                SearchData searchData = PreferencesManager.getSearchData(getApplication());
                Region currentRegion = PreferencesManager.getCurrentRegion(getApplication());

                if (currentRegion != null && searchData.getRegionFromFavorites(currentRegion.getId()) == null) {
                    searchData.getFavoriteRegions().add(currentRegion);
                    PreferencesManager.setSearchData(getApplication(), searchData);
                    Snackbar.make(navView, "Region added to the favorites list", Snackbar.LENGTH_SHORT).show();
                } else if (currentRegion == null) {
                    Snackbar.make(navView, "The region was already on the list", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(navView, "There is no region to add", Snackbar.LENGTH_SHORT).show();
                }

                return true;

            case R.id.seeList:
                navController.navigate(R.id.navigation_locations);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
