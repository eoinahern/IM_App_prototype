package com.example.eoin_a.im_app20.Components;

import com.example.eoin_a.im_app20.Modules.RegModule;
import com.example.eoin_a.im_app20.PresentersInt.RegisterPresenterInt;
import com.example.eoin_a.im_app20.Views.RegistrationActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by eoin_a on 07/07/2015.
 */
@PerModel
@Component(dependencies = {appComponent.class}, modules ={RegModule.class})
public interface RegComponent{

    /* can only use appComponents provision method objs
    when using dependencies in Dagger 2
     */

    void inject(RegistrationActivity regactivity);
    RegisterPresenterInt getPres();

}