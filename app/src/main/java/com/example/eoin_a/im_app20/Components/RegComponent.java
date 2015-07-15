package com.example.eoin_a.im_app20.Components;

import com.example.eoin_a.im_app20.Modules.RegModule;
import com.example.eoin_a.im_app20.Views.RegistrationActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by eoin_a on 07/07/2015.
 */
@Singleton
@Component(modules ={RegModule.class})
public interface RegComponent{

    void inject(RegistrationActivity activity);


}
