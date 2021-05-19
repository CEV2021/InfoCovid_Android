package com.example.infocovid.datalayer.model;

import java.util.ArrayList;

public class SearchData {
    private ArrayList<String> regionNamesList;
    private ArrayList<Region> favoriteRegions;
    private Region myFavoriteRegion;


    /**
     *
     */
    public SearchData() {
        regionNamesList = new ArrayList<String>();
        favoriteRegions = new ArrayList<Region>();
        myFavoriteRegion = null;
    }

    /**
     *
     * @param regionNamesList
     * @param favoriteRegions
     */
    public SearchData(ArrayList<String> regionNamesList, ArrayList<Region> favoriteRegions, Region myFavoriteRegion) {
        regionNamesList = regionNamesList;
        favoriteRegions = favoriteRegions;
        myFavoriteRegion = myFavoriteRegion;
    }

    /**
     *
     * @return
     */
    public Region getMyFavoriteRegion() {
        return myFavoriteRegion;
    }

    /**
     *
     * @param myFavoriteRegion
     */
    public void setMyFavoriteRegion(Region myFavoriteRegion) {
        this.myFavoriteRegion = myFavoriteRegion;
    }

    /**
     *
     * @param index
     * @return
     */
    public boolean isItFavorite(int index) {
        if (myFavoriteRegion != null) {
            return favoriteRegions.get(index).getId() == myFavoriteRegion.getId();
        }
        return false;
    }

    /**
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getRegionNamesList() {
        return regionNamesList;
    }

    /**
     *
     * @param regionNamesList
     */
    public void setRegionNamesList(ArrayList<String> regionNamesList) {
        regionNamesList = regionNamesList;
    }

    /**
     *
     * @param regionName
     */
    public void addRegionName(String regionName) {
        this.regionNamesList.add(regionName);
    }

    /**
     *
     */
    public void clearRegionNamesList() {
        this.regionNamesList = new ArrayList<String>();
    }

    /**
     *
     * @return String[]
     */
    public String[] getAutocompleteStrings() {
        return regionNamesList.toArray(new String[0]);
    }

    /**
     *
     * @return
     */
    public ArrayList<Region> getFavoriteRegions() {
        return favoriteRegions;
    }

    /**
     *
     * @param favoriteRegions
     */
    public void setFavoriteRegions(ArrayList<Region> favoriteRegions) {
        this.favoriteRegions = favoriteRegions;
    }

    /**
     *
     * @param region
     */
    public void addFavoriteRegion(Region region) {
        this.favoriteRegions.add(region);
    }

}
