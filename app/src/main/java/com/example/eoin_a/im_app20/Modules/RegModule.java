package com.example.eoin_a.im_app20.Modules;

import com.example.eoin_a.im_app20.Presenters.RegisterPresenter;
import com.example.eoin_a.im_app20.PresentersInt.RegisterPresenterInt;
import com.example.eoin_a.im_app20.Views.RegistrationActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eoin_a on 07/07/2015.
 */
@Module
public class RegModule {

    private RegistrationActivity activ;

 public RegModule(RegistrationActivity activityin)
 {
     activ = activityin;
 }


@Singleton
@Provides RegisterPresenterInt getPres()
{
    return new RegisterPresenter(activ);
}



}
