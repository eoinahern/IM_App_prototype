package com.example.eoin_a.im_app20.UtilsInt;

import java.util.Map;

/**
 * Created by eoin_a on 21/07/2015.
 */
public interface ConnectionManagerInt {

    boolean createConnect();
    boolean registerDevice(String email, String password, Map<String, String> extraparam);
    boolean loginDevice(String email, String password);
    String getError();
    void resetError();
    void closeConn();

}
