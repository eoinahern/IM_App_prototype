package com.example.eoin_a.im_app20.Modules;

import android.content.Context;

import com.example.eoin_a.im_app20.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eoin_a on 25/06/2015.
 */
@Module
public class AppModule {

    private MyApplication myapp;

    public AppModule(MyApplication myappin)
    {
        myapp = myappin;
    }

    @Singleton
    @Provides
    Context provideAppContext()
    {
        return myapp;
    }
}
