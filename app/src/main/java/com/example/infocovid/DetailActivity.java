package com.example.infocovid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    BottomNavigationView nBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // control de navegacion por iconos
        nBottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        nBottomNavigation.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_home:

                        startActivity(new Intent(DetailActivity.this, MainActivity.class));
                        return true;
                    case R.id.menu_graphics:
                        startActivity(new Intent(DetailActivity.this, DetailActivity.class));
                        return true;
                    case R.id.menu_search:
                        startActivity(new Intent (DetailActivity.this, SearchActivity.class));
                        return true;
                    case R.id.menu_settings:
                        startActivity(new Intent (DetailActivity.this, SettingsActivity.class));
                        return true;
                }
                return false;

            }
        }) ;


    }


}