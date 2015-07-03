package com.example.vidhipatel.myapplication2;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by vidhi.patel on 6/19/2015.
 */
public interface Api {
    @GET("/users")
    void getUsers(Callback<List<User>> callback);
}