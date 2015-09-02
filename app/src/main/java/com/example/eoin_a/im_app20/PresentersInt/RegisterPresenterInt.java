package com.example.eoin_a.im_app20.PresentersInt;

/**
 * Created by eoin_a on 22/06/2015.
 */
public interface RegisterPresenterInt {

    String getError();
    boolean relayRegisterUser(String email, String password, String phoneno);
    void updateUIProgress();



}
