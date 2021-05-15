package com.example.infocovid.datalayer.model;

import com.example.infocovid.datalayer.model.Region;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RegionService {

    @GET("regions")
    Call<ArrayList<Region>> getRegions();
}
