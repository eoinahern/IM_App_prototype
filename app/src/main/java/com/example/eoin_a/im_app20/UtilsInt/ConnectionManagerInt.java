package com.example.eoin_a.im_app20.UtilsInt;

/**
 * Created by eoin_a on 21/07/2015.
 */
public interface ConnectionManagerInt {

    String connect();
    boolean  registerDevice(String email, String password);
    boolean loginDevice(String email, String password);
    void closeConn();

}
