package com.example.vidhipatel.myapplication2;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by vidhi.patel on 6/19/2015.
 */
public interface Api {
    @GET("/users")
    Observable<List<User>> getUsers();
}