package com.example.infocovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LocationsActivity extends AppCompatActivity {

    BottomNavigationView dBottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        // control de navegacion por iconos
        dBottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        dBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.menu_home) {
                    setContentView(R.layout.activity_main);
                }
                if (menuItem.getItemId() == R.id.menu_graphics) {
                    setContentView(R.layout.activity_detail);
                }
                if (menuItem.getItemId() == R.id.menu_search) {
                    setContentView(R.layout.activity_locations);
                }
                if (menuItem.getItemId() == R.id.menu_settings) {
                    setContentView(R.layout.activity_settings);
                }
                return false;
            }
        });
    }
}