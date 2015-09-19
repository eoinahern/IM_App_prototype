package com.example.eoin_a.im_app20.Modules;

import android.content.Context;

import com.example.eoin_a.im_app20.MyApplication;


import org.mockito.Mockito;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by eoin_a on 25/06/2015.
 */
@Module
public class AppModule {

    private MyApplication myapp;
    private boolean setmock;


    public AppModule(MyApplication myappin)
    {
        setmock = false;
        myapp = myappin;
    }


    public void setMock(boolean mock)
    {
        setmock = mock;
    }

    @Singleton
    @Provides
    Context provideAppContext()
    {

        if(!setmock) {
            return myapp;
        }
        else
        {

            Context cont  = Mockito.mock(Context.class);
            return cont;   //prob dont need mock here!! lol!!
        }

    }





}
