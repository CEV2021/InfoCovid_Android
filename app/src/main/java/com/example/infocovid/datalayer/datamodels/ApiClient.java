package com.example.infocovid.datalayer.datamodels;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://limitless-meadow-81250.herokuapp.com/api/";

    public static Retrofit getClient() {

        if (ApiClient.retrofit == null) {
            ApiClient.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return ApiClient.retrofit;
    }
}
