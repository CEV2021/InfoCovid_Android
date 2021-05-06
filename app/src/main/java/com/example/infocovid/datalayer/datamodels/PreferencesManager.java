package com.example.infocovid.datalayer.datamodels;

import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PreferencesManager {

    private static final String LIST_KEY = "list_key";

    public static void saveInPreferences(Context context, ArrayList<Region> regionList) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(regionList);

        SharedPreferences prefs = context.getSharedPreferences("Preferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LIST_KEY,jsonString);
        editor.apply();
    }

    public static ArrayList<Region> loadPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String jsonString = prefs.getString(LIST_KEY, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Region>>() {}.getType();

        return gson.fromJson(jsonString,type);
    }
}
