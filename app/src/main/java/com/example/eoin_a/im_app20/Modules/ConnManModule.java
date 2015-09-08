package com.example.eoin_a.im_app20.Modules;

import com.example.eoin_a.im_app20.Components.PerModel;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smackx.iqregister.AccountManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eoin_a on 24/08/2015.
 */

@Module
public class ConnManModule {

    private AbstractXMPPConnection conn;

    public ConnManModule(AbstractXMPPConnection connin)
    {
        conn = connin;
    }

@PerModel
@Provides AccountManager getAccountManager()
{
    return AccountManager.getInstance(conn);
}




}
