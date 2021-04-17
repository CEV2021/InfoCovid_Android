package com.example.infocovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.infocovid.datalayer.DataManager;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infocovid.datalayer.connection.eldiario.Connection;
import com.example.infocovid.datalayer.model.Datos;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.infocovid.datalayer.SupportsDataManager;

public class MainActivity extends AppCompatActivity implements SupportsDataManager {

    BottomNavigationView nBottomNavigation;
    DataManager dataManager;
    Connection connection;

    TextView nombreCiudad;
    TextView incidenciaAcumulada;
    TextView casosTotales;
    TextView nuevosCasos;
    TextView curados;
    TextView fallecidos;

    // for testing purposes
    Button refreshButton;
    EditText cityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // elementos para test
        cityInput = findViewById(R.id.nombreCiudadInput);
        refreshButton = findViewById(R.id.refreshButton);

        // datos a actualizar
        nombreCiudad = findViewById(R.id.ciudadNombre);
        incidenciaAcumulada = findViewById(R.id.incidenciaAcumuladaNumero);
        casosTotales = findViewById(R.id.casosTotalesTableNumero);
        nuevosCasos = findViewById(R.id.nuevosCasosTableNumero);
        curados = findViewById(R.id.curadosTableNumero);
        fallecidos = findViewById(R.id.fallecidosTableNumero);

        // Initializing dataManager
        dataManager = new DataManager(this);
        // Refreshing apps data
        this.refreshData();

        //Creamos el menu de navegacion y el metodo de navegacion por pulsacion.
        nBottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);


        nBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.menu_home) {
                    // aqui va la ruta que cambie de activity al seleccionar ese item.
                }
                if (menuItem.getItemId() == R.id.menu_graphics) {
                    // aqui va la ruta que cambie de activity al seleccionar ese item.
                }
                if (menuItem.getItemId() == R.id.menu_search) {
                    // aqui va la ruta que cambie de activity al seleccionar ese item.
                }
                if (menuItem.getItemId() == R.id.menu_settings) {
                    // aqui va la ruta que cambie de activity al seleccionar ese item.                }
                }
                return false;
            }
        });
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

    // Method to refresh the bands list
    public void refreshData(View view) {
        this.refreshData();
    }
    // Method to refresh the bands list
    public void refreshData() {
        if (isConnected()) {
            dataManager.execute();
        } else {
            // If there is no connection:
            Toast.makeText(this,"No connection", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to refresh the bands list
    public void refreshView(View view) {
        this.refreshView();
    }

    // method required by the dataManager class
    public void refreshView() {
        // a partir de una cadena de texto
        String currentCityName = cityInput.getText().toString();
        Boolean checkpoint = true;

        // podemos llamar al conector y buscar los datos
        Datos datos2Display = connection.getDatos(currentCityName);
        if (datos2Display != null) {
            nombreCiudad.setText(datos2Display.getMunicipio().getNombre());
            incidenciaAcumulada.setText(datos2Display.getIa14Dias().toString());
            casosTotales.setText(datos2Display.getTasa().toString());
            curados.setText(datos2Display.getRecuperados().toString());
            fallecidos.setText(datos2Display.getMuertos().toString());
        }
    }

    @Override
    public void setConnection(Connection connection) {
        Boolean debugCheckPoint;
        this.connection = connection;
    }
}
