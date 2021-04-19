package com.example.infocovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        startActivity(new Intent(LocationsActivity.this, MainActivity.class));
                        return true;
                    case R.id.menu_graphics:
                        startActivity(new Intent(LocationsActivity.this, DetailActivity.class));
                        return true;
                    case R.id.menu_search:
                        startActivity(new Intent (LocationsActivity.this, SearchActivity.class));
                        return true;
                    case R.id.menu_settings:
                        startActivity(new Intent (LocationsActivity.this, SettingsActivity.class));
                        return true;

                }
                return false;

            }
        }) ;
    }
}