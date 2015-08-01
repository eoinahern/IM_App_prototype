package com.example.eoin_a.im_app20.Components;

import com.example.eoin_a.im_app20.Modules.LoginModule;
import com.example.eoin_a.im_app20.Views.LoginActivity;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by eoin_a on 01/08/2015.
 */

@Singleton
@Component(modules = {LoginModule.class})
public interface LoginComponent {
     void inject(LoginActivity loginact);
}
