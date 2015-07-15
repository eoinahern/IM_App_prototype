package com.example.eoin_a.im_app20;

import android.app.Application;
import com.example.eoin_a.im_app20.Components.DaggerappComponent;
import com.example.eoin_a.im_app20.Modules.AppModule;
import com.example.eoin_a.im_app20.Components.appComponent;

import javax.inject.Inject;


/**
 * Created by eoin_a on 23/06/2015.
 */
public class MyApplication extends Application {

    private static appComponent component;

    @Override
    public void onCreate()
    {
        super.onCreate();
        component = DaggerappComponent.builder().appModule(new AppModule(this)).build();
    }


    public static appComponent component()
    {
        return component;
    }







}
