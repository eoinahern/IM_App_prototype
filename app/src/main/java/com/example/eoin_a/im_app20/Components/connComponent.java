package com.example.eoin_a.im_app20.Components;

import com.example.eoin_a.im_app20.Models.LoginModel;
import com.example.eoin_a.im_app20.Models.RegisterModel;
import com.example.eoin_a.im_app20.Modules.AppModule;
import com.example.eoin_a.im_app20.Modules.ConnModule;
import com.example.eoin_a.im_app20.Utils.ConnectionManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by eoin_a on 05/08/2015.
 */
@PerModel
@Component(dependencies = {appComponent.class}, modules = {ConnModule.class})
public interface connComponent
{

    void inject(LoginModel loginmodel);
    void inject(RegisterModel regmodel);
    ConnectionManager manager();
}
