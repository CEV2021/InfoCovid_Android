package com.example.infocovid.datalayer.model;

import com.example.infocovid.datalayerOld.model.Provincia;

import java.util.ArrayList;

public class SearchData {
    private ArrayList<String> regionNamesList;

    public SearchData() {
        regionNamesList = new ArrayList<String>();
    }

    public SearchData(ArrayList<String> regionNamesList) {
        regionNamesList = regionNamesList;
    }

    public ArrayList<String> getRegionNamesList() {
        return regionNamesList;
    }

    public void setRegionNamesList(ArrayList<String> regionNamesList) {
        regionNamesList = regionNamesList;
    }

    public void addRegionName(String regionName) {
        this.regionNamesList.add(regionName);
    }

    public String[] getAutocompleteStrings() {
        return regionNamesList.toArray(new String[0]);
    }
}
