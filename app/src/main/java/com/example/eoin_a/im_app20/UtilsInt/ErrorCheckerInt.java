package com.example.eoin_a.im_app20.UtilsInt;

/**
 * Created by eoin_a on 23/06/2015.
 */
public interface ErrorCheckerInt {


     boolean emailValid();
     boolean passwordValid();
     boolean phonevalid();
     String getWarning();
     void  clearWarning();




}
