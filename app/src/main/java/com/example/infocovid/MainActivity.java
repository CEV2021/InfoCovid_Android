package com.example.infocovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.infocovid.datalayer.model.ApiClient;
import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.model.Region;
import com.example.infocovid.datalayer.model.RegionList;
import com.example.infocovid.datalayer.model.RegionService;
import com.example.infocovid.datalayer.model.SearchData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    // old code -- to adapt
    BottomNavigationView nBottomNavigation;


    //API
    RegionList regionList;
    SearchData searchData;
    RegionService regionService;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.loadData();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_main, R.id.navigation_details, R.id.navigation_search, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


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
            Toast.makeText(this,"No connection", Toast.LENGTH_SHORT).show();
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
    public  void getData() {
        Call<ArrayList<Region>> call = regionService.getRegions();

        call.enqueue(new Callback<ArrayList<Region>>() {
            @Override
            public void onResponse(Call<ArrayList<Region>> call, Response<ArrayList<Region>> response) {
                Log.e("onResponse", "Response -->" + response.body().size());

                regionList = new RegionList();
                searchData = new SearchData();


                for (int i = 0; i < response.body().size(); i ++) {
                    regionList.regions.add(i, response.body().get(i));
                    searchData.addRegionName(response.body().get(i).getName());

                    boolean check = true;
                }

                PreferencesManager.setRegions(getApplicationContext(), regionList.regions);
                PreferencesManager.setSearchData(getApplicationContext(), searchData);
                check = true;;

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
}
