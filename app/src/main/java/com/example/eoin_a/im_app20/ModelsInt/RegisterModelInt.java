package com.example.eoin_a.im_app20.ModelsInt;

/**
 * Created by eoin_a on 23/06/2015.
 */
public interface RegisterModelInt {

    boolean registerDevice(String email, String password);
    String getWarningStr();
}
