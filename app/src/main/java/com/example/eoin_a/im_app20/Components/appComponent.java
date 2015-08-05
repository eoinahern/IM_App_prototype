package com.example.eoin_a.im_app20.Components;

import android.content.Context;

import com.example.eoin_a.im_app20.Models.LoginModel;
import com.example.eoin_a.im_app20.Models.RegisterModel;
import com.example.eoin_a.im_app20.Modules.AppModule;
import com.example.eoin_a.im_app20.MyApplication;
import com.example.eoin_a.im_app20.Views.IntroActivity;
import com.example.eoin_a.im_app20.Views.RegistrationActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by eoin_a on 25/06/2015.
 *
 */
@Singleton
@Component( modules = {AppModule.class})
public interface appComponent {

    void inject(RegisterModel model);
    void inject(IntroActivity introActivity);
    void inject(LoginModel loginmodel);

}
