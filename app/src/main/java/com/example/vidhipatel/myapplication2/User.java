package com.example.vidhipatel.myapplication2;

/**
 * Created by vidhi.patel on 7/3/2015.
 */
public class User {
    private int id;
    private String name;
    private String username;
    private String email;

    //package-protected
    User(int id, String name, String username, String email){
        this.id=id;
        this.name=name;
        this.username=username;
        this.email=email;
    }

    //accessors without mutators
    int getId(){
        return this.id;
    }

    String getName(){
        return this.name;
    }

    String getUsername(){
        return this.username;
    }

    String getEmail(){
        return this.email;
    }
}
