package com.example.eoin_a.im_app20.Models;

import com.example.eoin_a.im_app20.ModelsInt.LoginModelInt;
import com.example.eoin_a.im_app20.PresentersInt.LoginPresenterInt;

/**
 * Created by eoin_a on 01/08/2015.
 */
public class LoginModel implements LoginModelInt {

    private LoginPresenterInt loginpresenter;
    private Thread loginthread;

    public LoginModel(LoginPresenterInt loginpresin)
    {
        loginpresenter = loginpresin;
    }



    public void loginDevice(String email, String password)
    {
        //login device with pass



    }


    @Override
    public boolean login(String emailin, String passin) {
        return false;
    }
}
