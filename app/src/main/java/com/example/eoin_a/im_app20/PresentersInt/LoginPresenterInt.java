package com.example.eoin_a.im_app20.PresentersInt;

/**
 * Created by eoin_a on 01/08/2015.
 */
public interface LoginPresenterInt  {

    void LoginDevice(String emailin, String passin);
    void LoginComplete();
    String getError();
    boolean getLoggedIn();


}
