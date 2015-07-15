package com.example.eoin_a.im_app20.UtilsInt;

/**
 * Created by eoin_a on 23/06/2015.
 */
public interface AppStateInt {

     Boolean isLoggedIn();
     Boolean isRegistered();

     void setLoggedIn(boolean loggedin);
     void setRegistered(boolean registered);
     Boolean checkprefs();

}
