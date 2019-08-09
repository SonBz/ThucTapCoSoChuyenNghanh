package com.hvktmm.at12i.controller;

import com.hvktmm.at12i.model.User;

public class Context {
    private final static Context instance=new Context();

    public static Context getInstance(){
        return instance;
    }

    public User user1=new User();

    public User currentCountry(){
        return user1;
    }
}
