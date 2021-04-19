package com.example.infocovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {
    BottomNavigationView BottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // control de navegacion por iconos
        BottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        BottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                        return true;
                    case R.id.menu_graphics:
                        startActivity(new Intent(SettingsActivity.this, DetailActivity.class));
                        return true;
                    case R.id.menu_search:
                        startActivity(new Intent (SettingsActivity.this, SearchActivity.class));
                        return true;
                    case R.id.menu_settings:
                        startActivity(new Intent (SettingsActivity.this, SettingsActivity.class));
                        return true;

                }
                return false;

            }
        }) ;
    }
}