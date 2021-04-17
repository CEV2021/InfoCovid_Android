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
        }) ;






    }
}