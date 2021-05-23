package com.example.infocovid.datalayer.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PreferencesManager {

    private static final String REGIONS_DATA = "regions_data";
    private static final String MY_SETTINGS = "my_settings";
    private static final String CURRENT_REGION = "current_region";
    private static final String SEARCH_DATA = "search_data";

    /**
     *
     * @param context
     * @return
     */
    public static ArrayList<Region> getRegions(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String jsonString = prefs.getString(REGIONS_DATA, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Region>>() {}.getType();

        return gson.fromJson(jsonString,type);
    }

    /**
     *
     * @param context
     * @param regionList
     */
    public static void setRegions(Context context, ArrayList<Region> regionList) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(regionList);

        SharedPreferences prefs = context.getSharedPreferences("Preferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REGIONS_DATA,jsonString);
        editor.apply();
    }

    /**
     *
     * @param context
     * @return
     */
    public static Region getCurrentRegion(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String jsonString = prefs.getString(CURRENT_REGION, "");

        Gson gson = new Gson();
        Type type = new TypeToken<Region>() {}.getType();

        return gson.fromJson(jsonString,type);
    }

    /**
     *
     * @param context
     * @param region
     */
    public static void setCurrentRegion(Context context, Region region) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(region);

        SharedPreferences prefs = context.getSharedPreferences("Preferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(CURRENT_REGION,jsonString);
        editor.apply();
    }

    /**
     *
     * @param context
     * @return
     */
    public static MySettings getMySettings(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String jsonString = prefs.getString(MY_SETTINGS, "");

        Gson gson = new Gson();
        Type type = new TypeToken<MySettings>() {}.getType();

        return gson.fromJson(jsonString,type);
    }

    /**
     *
     * @param context
     * @param mySettings
     */
    public static void setMySettings(Context context, MySettings mySettings) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(mySettings);

        SharedPreferences prefs = context.getSharedPreferences("Preferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(MY_SETTINGS,jsonString);
        editor.apply();
    }

    /**
     *
     * @param context
     * @return
     */
    public static SearchData getSearchData(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String jsonString = prefs.getString(SEARCH_DATA, "");

        Gson gson = new Gson();
        Type type = new TypeToken<SearchData>() {}.getType();

        return gson.fromJson(jsonString,type);
    }

    /**
     *
     * @param context
     * @param searchData
     */
    public static void setSearchData(Context context, SearchData searchData) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(searchData);

        SharedPreferences prefs = context.getSharedPreferences("Preferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SEARCH_DATA,jsonString);
        editor.apply();
    }
}
