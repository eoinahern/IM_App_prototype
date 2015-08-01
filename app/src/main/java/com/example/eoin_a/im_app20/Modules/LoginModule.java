package com.example.eoin_a.im_app20.Modules;

import com.example.eoin_a.im_app20.Presenters.LoginPresenter;
import com.example.eoin_a.im_app20.PresentersInt.LoginPresenterInt;
import com.example.eoin_a.im_app20.Views.LoginActivity;
import com.example.eoin_a.im_app20.ViewsInt.LoginViewInt;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eoin_a on 01/08/2015.
 */
@Module
public class LoginModule {


    private LoginViewInt loginact;

    public LoginModule(LoginViewInt loginactin)
    {
      loginact =  loginactin;
    }

 @Singleton
 @Provides LoginPresenterInt getLoginPres()
 {
     return new LoginPresenter(loginact);
 }








}
