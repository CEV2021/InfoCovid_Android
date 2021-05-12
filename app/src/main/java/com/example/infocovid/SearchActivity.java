package com.example.infocovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        textView1 = findViewById(R.id.searchComunity);


        comunidades = new ArrayList<>();

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
            }


            //Cargamos array Adaptativo con las comunidades almacenadas de la carga
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, comunidades);
            textView1.setAdapter(adapter1);
            textView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Log.e("Search Activity","" + adapter1.getItem(position));

                    Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                    String item;
                    String onItemSelected = item = String.valueOf(comunidades.indexOf(adapter1.getItem(position)));
                    intent.putExtra("onItemSelected", onItemSelected);

                    startActivity(intent);
                }
            });
        }else{
        }

    }



}