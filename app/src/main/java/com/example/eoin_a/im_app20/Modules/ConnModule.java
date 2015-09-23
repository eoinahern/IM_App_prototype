package com.example.eoin_a.im_app20.Modules;

import com.example.eoin_a.im_app20.Components.PerModel;
import com.example.eoin_a.im_app20.Utils.ConnectionManager;
import com.example.eoin_a.im_app20.UtilsInt.ConnectionManagerInt;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eoin_a on 05/08/2015.
 */

@Module
public class ConnModule {

    public ConnModule()
    {
        //pass bolean in.
        //then use in Connection manager to set injected mocks!!
    }

@PerModel
@Provides ConnectionManager getConnectionManager()
{
    return new ConnectionManager();
}








}
