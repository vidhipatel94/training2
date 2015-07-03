package com.example.vidhipatel.myapplication2;

import retrofit.RestAdapter;

/**
 * Created by vidhi.patel on 7/3/2015.
 */
public class RestClient {
    private static final String API_URL = "http://jsonplaceholder.typicode.com";
    private static final Api api= new RestAdapter.Builder()
            .setEndpoint(API_URL)
            .build()
            .create(Api.class);

    public static Api getApi(){
        return api;
    }
}
