package com.example.eoin_a.im_app20.Components;

import android.content.Context;

import com.example.eoin_a.im_app20.Modules.AppModule;
import com.example.eoin_a.im_app20.Modules.ConnManModule;
import com.example.eoin_a.im_app20.Utils.ConnectionManager;
import org.jivesoftware.smackx.iqregister.AccountManager;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by eoin_a on 24/08/2015.
 */

@Singleton
@Component( modules = {ConnManModule.class})
public interface connmanComponent {

    //void inject(ConnectionManager connmanager);
    AccountManager getAccountManager();
}
