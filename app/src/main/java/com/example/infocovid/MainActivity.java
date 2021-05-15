package com.example.infocovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infocovid.datalayer.connection.eldiario.Connection;
import com.example.infocovid.datalayer.datamodels.ApiClient;
import com.example.infocovid.datalayer.datamodels.PreferencesManager;
import com.example.infocovid.datalayer.datamodels.Region;
import com.example.infocovid.datalayer.datamodels.RegionList;
import com.example.infocovid.datalayer.datamodels.RegionService;
import com.example.infocovid.datalayer.model.Datos;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    // Global variables to use the data throughout the app
    Datos currentData;


    // old code -- to adapt
    BottomNavigationView nBottomNavigation;


    //API
    RegionList regionList;
    RegionService regionService;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.refreshData();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_main, R.id.navigation_details, R.id.navigation_locations, R.id.navigation_settings)
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


    public void refreshData() {
        if (isConnected()) {
//            isVisible(check);
            regionService = ApiClient.getClient().create(RegionService.class);
            this.getData(); //Peticion
        } else {
            // If there is no connection:
            Toast.makeText(this,"No connection", Toast.LENGTH_SHORT).show();
        }
    }

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
//                isVisible(check);

                RegionList list = new RegionList();
                list.regions = PreferencesManager.loadPreferences(getApplicationContext());


            }

            @Override
            public void onFailure(Call<ArrayList<Region>> call, Throwable t) {
                Log.e("onFailure", "Error " + t.getLocalizedMessage());
                check = false;
//                isVisible(check);
            }
        });

    }


//    // Method to refresh the bands list
//    public void refreshView(View view) {
//        this.refreshView();
//    }
//
//    // method required by the dataManager class
//    public void refreshView() {
//        // a partir de una cadena de texto
//        String currentCityName = cityInput.getText().toString();
//
//        // podemos llamar al conector y buscar los datos
//        Datos datos2Display = connection.getDatos(currentCityName);
//        if (datos2Display != null) {
//            nombreCiudad.setText(datos2Display.getMunicipio().getNombre());
//            incidenciaAcumulada.setText(datos2Display.getIa14Dias().toString());
//            casosTotales.setText(datos2Display.getTasa().toString());
//            curados.setText(datos2Display.getRecuperados().toString());
//            fallecidos.setText(datos2Display.getMuertos().toString());
//        } else {
//            Log.w("debug_mess", "no carga la ciudad");
//        }
//    }
//
//    @Override
//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }

    // Setting up visibility

//    public void isVisible(boolean check) {
//
//        if (!check) { //Ocultamos vistas
//            nombreCiudad.setVisibility(View.INVISIBLE);
//            incidenciaAcumulada.setVisibility(View.INVISIBLE);
//            casosTotales.setVisibility(View.INVISIBLE);
//            nuevosCasos.setVisibility(View.INVISIBLE);
//            curados.setVisibility(View.INVISIBLE);
//            fallecidos.setVisibility(View.INVISIBLE);
//        } else {
//            nombreCiudad.setVisibility(View.VISIBLE);
//            incidenciaAcumulada.setVisibility(View.VISIBLE);
//            casosTotales.setVisibility(View.VISIBLE);
//            nuevosCasos.setVisibility(View.VISIBLE);
//            curados.setVisibility(View.VISIBLE);
//            fallecidos.setVisibility(View.VISIBLE);
//        }
//    }


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
