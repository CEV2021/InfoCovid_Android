package com.example.infocovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.infocovid.datalayer.datamodels.PreferencesManager;
import com.example.infocovid.datalayer.datamodels.RegionList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    BottomNavigationView BottomNavigation;
    AutoCompleteTextView textView1;


    ArrayList<String> comunidades;
    RegionList list;
    String totalCases;
    String newCases;
    String healths;
    String deaths;
    String incidentRate;
    int ciudadElegida;

    TextView ciudadNombre, incidenciaAcumuladaNumero, casosTotalesTableNumero, nuevosCasosTableNumero, curadosTableNumero, fallecidosTableNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        comunidades = new ArrayList<>();
        textView1 = findViewById(R.id.searchComunity);
        ciudadNombre = findViewById(R.id.ciudadNombre);
        incidenciaAcumuladaNumero = findViewById(R.id.incidenciaAcumuladaNumero);
        casosTotalesTableNumero = findViewById(R.id.casosTotalesTableNumero);
        nuevosCasosTableNumero = findViewById(R.id.nuevosCasosTableNumero);
        curadosTableNumero = findViewById(R.id.curadosTableNumero);
        fallecidosTableNumero = findViewById(R.id.fallecidosTableNumero);




        // control de navegacion por iconos
        BottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        BottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    startActivity(new Intent(SearchActivity.this, MainActivity.class));
                    return true;
                case R.id.menu_graphics:
                    startActivity(new Intent(SearchActivity.this, DetailActivity.class));
                    return true;
                case R.id.menu_search:
                    startActivity(new Intent (SearchActivity.this, SearchActivity.class));
                    return true;
                case R.id.menu_settings:
                    startActivity(new Intent (SearchActivity.this, SettingsActivity.class));
                    return true;

            }
            return false;
        });

        list = new RegionList();

        try{
            list.regions = PreferencesManager.loadPreferences(this);
        }catch (Exception e){

        }


        if(!list.regions.isEmpty()){

            for(int i= 0; i < list.regions.size(); i++){
                comunidades.add(list.regions.get(i).getName());
                Log.e("cas", list.regions.get(i).getName());
            }


            //Cargamos array Adaptativo con las comunidades almacenadas de la carga
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, comunidades);
            textView1.setAdapter(adapter1);
        }else{
        }

    }



    public RegionList ChargeData(){

        RegionList list = new RegionList();

        try{
            list.regions = PreferencesManager.loadPreferences(this);


            if(list.isEmpty()){
                return new RegionList();
            }else{
                return list;
            }
        }catch (Exception e){

            return new RegionList();
        }
    }

    public void SelectComunity(View v){

        if(!textView1.getText().toString().isEmpty()){

            for(int i=0 ; i < list.regions.size(); i++){
                if (textView1.getText().toString().equals(list.regions.get(i).getName())){
                    ciudadElegida = i;
                    i = list.regions.size() -1;
                }
            }
        }

        setUpViews();
    }

    public void setUpViews() {

        if (list.regions != null) { // Update views
            ciudadNombre.setText(list.regions.get(ciudadElegida).getName());
            incidenciaAcumuladaNumero.setText(incidentRate = String.valueOf(list.regions.get(ciudadElegida).getData().get(1).getIncidentRate()));
            casosTotalesTableNumero.setText(totalCases = String.valueOf(list.regions.get(ciudadElegida).getData().get(1).getConfirmed()) );
            nuevosCasosTableNumero.setText( newCases = String.valueOf( list.regions.get(ciudadElegida).getData().get(1).getActive()));
            curadosTableNumero.setText( healths = String.valueOf(list.regions.get(ciudadElegida).getData().get(1).getRecovered()));
            fallecidosTableNumero.setText(deaths = String.valueOf(list.regions.get(ciudadElegida).getData().get(1).getDeaths()));
        } else {
            //No hay Datos
        }
    }
}