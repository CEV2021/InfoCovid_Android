package com.example.infocovid.datalayer.model;

import java.util.ArrayList;
import java.util.Iterator;

public class RegionList extends ArrayList<Region> {

    public ArrayList<Region> regions;

    public RegionList() {
        regions = new ArrayList<Region>();
    }

    public RegionList(ArrayList<Region> regions) {
        regions = regions;
    }

    /**
     *
     * @param idRegion
     * @return
     */
    public Region getRegionById(Integer idRegion) {
        Iterator<Region> iterator = this.regions.iterator();
        while (iterator.hasNext()) {
            Region tempRegion = iterator.next();
            if (tempRegion.getId().equals(idRegion)) {
                return tempRegion;
            }
        }
        return null;
    }

    /**
     *
     * @param regionName
     * @return
     */
    public Region getRegionByName(String regionName) {
        Iterator<Region> iterator = this.regions.iterator();
        while (iterator.hasNext()) {
            Region tempRegion = iterator.next();
            if (tempRegion.getName().equals(regionName)) {
                return tempRegion;
            }
        }
        return null;
    }


}
