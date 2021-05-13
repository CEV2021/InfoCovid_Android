package com.example.infocovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infocovid.datalayer.datamodels.ApiClient;
import com.example.infocovid.datalayer.datamodels.PreferencesManager;
import com.example.infocovid.datalayer.datamodels.Region;
import com.example.infocovid.datalayer.datamodels.RegionList;
import com.example.infocovid.datalayer.datamodels.RegionService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity{

    BottomNavigationView nBottomNavigation;
    Toolbar myToolbar;

    TextView nombreCiudad;
    TextView incidenciaAcumulada;
    TextView casosTotales;
    TextView nuevosCasos;
    TextView curados;
    TextView fallecidos;
    ListView favoriteList;

    //API
    RegionList regionList;
    RegionService regionService;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TextViews
        nombreCiudad = findViewById(R.id.ciudadNombre);
        incidenciaAcumulada = findViewById(R.id.incidenciaAcumuladaNumero);
        casosTotales = findViewById(R.id.casosTotalesTableNumero);
        nuevosCasos = findViewById(R.id.nuevosCasosTableNumero);
        curados = findViewById(R.id.curadosTableNumero);
        fallecidos = findViewById(R.id.fallecidosTableNumero);

        //listView
        favoriteList = findViewById(R.id.favoriteList);

        loadData();
        loadSelectedCity();


        // NavigationMenu
        nBottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);


        nBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        startActivity(new Intent (MainActivity.this, MainActivity.class));
                    return true;
                    case R.id.menu_graphics:
                        startActivity(new Intent(MainActivity.this, DetailActivity.class));
                        return true;
                    case R.id.menu_search:
                        startActivity(new Intent (MainActivity.this, SearchActivity.class));
                        return true;
                    case R.id.menu_settings:
                        startActivity(new Intent (MainActivity.this, SettingsActivity.class));
                        return true;

                }
                return false;

            }
        });
        myToolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);
    }
    /// App Bar -- Toolbar -- Menu Items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.toolbar_menu_items, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.addItem) {


        } else if(id == R.id.seeList) {
            Intent intent = new Intent(MainActivity.this, FavoriteListActivity.class);
            startActivity(intent);
        }

        return true;
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
            isVisible(check);
            regionService = ApiClient.getClient().create(RegionService.class);
            getData(); //Peticion
        } else {
            // If there is no connection:
            Toast.makeText(this,"No connection", Toast.LENGTH_SHORT).show();
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

                for (int i = 0; i < response.body().size(); i ++) {
                  regionList.regions.add(i, response.body().get(i));
                }

               PreferencesManager.saveInPreferences(getApplicationContext(), regionList.regions);
                check = true;
                isVisible(check);

                RegionList list = new RegionList();
                list.regions = PreferencesManager.loadPreferences(getApplicationContext());

                setUpViews(list, 1);

            }

            @Override
            public void onFailure(Call<ArrayList<Region>> call, Throwable t) {
                Log.e("onFailure", "Error " + t.getLocalizedMessage());
                check = false;
                isVisible(check);
            }
        });

    }
    //Getting data from preferences if we have it
    public void loadData() {
        regionList = new RegionList();

        regionList.regions = PreferencesManager.loadPreferences(this);

        if (regionList.regions == null) {
            this.refreshData();
        } else {
            check = true;
            isVisible(check);
            setUpViews(regionList, 1);
        }
    }
    // We try to load our city from SearchActiviyty it that exist if not try to LoadData form preferences
    public void loadSelectedCity() {
        String city = getIntent().getStringExtra("onItemSelected");

        if (city == null) {
            loadData();
        } else {
            Integer index = Integer.parseInt(city);
            Log.e("INTENT", "RESPUESTA ----> " + city);
            Log.e("index " + city, "En region es " + regionList.regions.get(index).getName());

            setUpViews(regionList, index);
        }

    }
    // Setting up visibility
    public void isVisible(boolean check) {

        if (!check) { //Ocultamos vistas
            nombreCiudad.setVisibility(View.INVISIBLE);
            incidenciaAcumulada.setVisibility(View.INVISIBLE);
            casosTotales.setVisibility(View.INVISIBLE);
            nuevosCasos.setVisibility(View.INVISIBLE);
            curados.setVisibility(View.INVISIBLE);
            fallecidos.setVisibility(View.INVISIBLE);
        } else {
            nombreCiudad.setVisibility(View.VISIBLE);
            incidenciaAcumulada.setVisibility(View.VISIBLE);
            casosTotales.setVisibility(View.VISIBLE);
            nuevosCasos.setVisibility(View.VISIBLE);
            curados.setVisibility(View.VISIBLE);
            fallecidos.setVisibility(View.VISIBLE);
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
            nombreCiudad.setText(list.regions.get(index).getName());
            incidenciaAcumulada.setText(incidentRate = String.valueOf(list.regions.get(index).getData().get(index).getIncidentRate()));
            casosTotales.setText(totalCases = String.valueOf(list.regions.get(index).getData().get(index).getConfirmed()) );
            nuevosCasos.setText( newCases = String.valueOf( list.regions.get(index).getData().get(index).getActive()));
            curados.setText( healths = String.valueOf(list.regions.get(index).getData().get(index).getRecovered()));
            fallecidos.setText(deaths = String.valueOf(list.regions.get(index).getData().get(index).getDeaths()));
        } else {
            refreshData();
        }
    }


}
