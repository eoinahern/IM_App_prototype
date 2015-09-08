package com.example.eoin_a.im_app20;

import android.app.Application;
import android.content.Context;

import com.example.eoin_a.im_app20.Components.DaggerappComponent;
import com.example.eoin_a.im_app20.Modules.AppModule;
import com.example.eoin_a.im_app20.Components.appComponent;

import javax.inject.Inject;


/**
 * Created by eoin_a on 23/06/2015.
 */
public class MyApplication extends Application {

    private static appComponent component;
    private static MyApplication myapp;

    @Override
    public void onCreate()
    {
        myapp = this;
        component = DaggerappComponent.builder().appModule(new AppModule(this)).build();
        super.onCreate();
    }


    public static MyApplication getInst()
    {
        return myapp;
    }


    public static appComponent component()
    {
        return component;
    }











}
