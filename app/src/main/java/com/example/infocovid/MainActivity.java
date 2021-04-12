package com.example.infocovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView nBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



    }
