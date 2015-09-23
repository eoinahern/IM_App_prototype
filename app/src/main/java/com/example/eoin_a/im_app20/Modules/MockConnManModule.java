package com.example.eoin_a.im_app20.Modules;

import com.example.eoin_a.im_app20.Components.PerModel;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eoin_a on 21/09/2015.
 */

@Module
public class MockConnManModule {

    public MockConnManModule()
    {

    }

    @PerModel
    @Provides
    public AbstractXMPPConnection getMockConn()
    {

        //create mock with required returns OR
        // od it in the tests.

        return Mockito.mock(AbstractXMPPConnection.class);
    }

    @PerModel
    @Provides
    public XMPPTCPConnectionConfiguration getMockConfig()
    {
            return Mockito.mock(XMPPTCPConnectionConfiguration.class);
    }


    @PerModel
    @Provides
    public AccountManager getMockAccountnMan()
    {
        return Mockito.mock(AccountManager.class);
    }








}
