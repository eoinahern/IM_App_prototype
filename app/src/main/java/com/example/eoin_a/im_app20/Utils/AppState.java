package com.example.eoin_a.im_app20.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.eoin_a.im_app20.UtilsInt.AppStateInt;

import javax.inject.Inject;

/**
 * Created by eoin_a on 23/06/2015.
 */
public class AppState implements AppStateInt {


    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private static String shpfile = "appstate";
    private static String keyloggedin = "loggedin";
    private static String keyregistered = "registered";
    private Context cont;
    private boolean defval = false;



    //so we can inject Context into this and get it from
    //the appmodule which another component can depend on possibly
    //still trying figure out dagger.

    @Inject
    public AppState(Context contin)
    {
        cont = contin;
        prefs = cont.getSharedPreferences(shpfile, cont.MODE_PRIVATE);
        editor = prefs.edit();
    }



    @Override
    public Boolean isLoggedIn() {
        return  prefs.getBoolean(keyloggedin, defval);
    }

    @Override
    public Boolean isRegistered() {
        return prefs.getBoolean(keyregistered, defval);
    }


    @Override
    public void setLoggedIn(boolean loggedin) {

        editor.putBoolean(keyloggedin, loggedin);
        editor.commit();
    }

    @Override
    public void setRegistered(boolean registered) {


        editor.putBoolean(keyregistered, registered);
        editor.commit();
    }

    @Override
    public Boolean checkprefs() {

        return (!prefs.contains(keyregistered))?  false : true;
    }






}
