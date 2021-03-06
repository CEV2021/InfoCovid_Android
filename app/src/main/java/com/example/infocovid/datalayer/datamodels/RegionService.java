package com.example.infocovid.datalayer.datamodels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RegionService {

    @GET("regions")
    Call<ArrayList<Region>> getRegions();
}
