package com.example.eoin_a.im_app20.Presenters;

import com.example.eoin_a.im_app20.Models.LoginModel;
import com.example.eoin_a.im_app20.ModelsInt.LoginModelInt;
import com.example.eoin_a.im_app20.PresentersInt.LoginPresenterInt;
import com.example.eoin_a.im_app20.ViewsInt.LoginViewInt;

/**
 * Created by eoin_a on 01/08/2015.
 */
public class LoginPresenter implements LoginPresenterInt {

    private LoginModelInt loginmodel;
    private LoginViewInt loginview;

    public LoginPresenter(LoginViewInt loginviewin)
    {
        loginview = loginviewin;
        loginmodel = new LoginModel(this);
    }




    @Override
    public void LoginDevice(String emailin, String passin) {





    }
}