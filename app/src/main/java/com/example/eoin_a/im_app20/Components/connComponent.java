package com.example.eoin_a.im_app20.Components;

import com.example.eoin_a.im_app20.Models.LoginModel;
import com.example.eoin_a.im_app20.Modules.AppModule;
import com.example.eoin_a.im_app20.Modules.ConnModule;
import com.example.eoin_a.im_app20.Utils.ConnectionManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by eoin_a on 05/08/2015.
 */
@Singleton
@Component( modules = {ConnModule.class})
public interface connComponent {

    ConnectionManager manager();

}
