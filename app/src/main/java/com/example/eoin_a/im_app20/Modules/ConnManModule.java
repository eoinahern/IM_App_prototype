package com.example.eoin_a.im_app20.Modules;

import com.example.eoin_a.im_app20.Components.PerModel;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.mockito.Mockito;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eoin_a on 24/08/2015.
 */

@Module
public class ConnManModule {

    private AbstractXMPPConnection conn;
    private XMPPTCPConnectionConfiguration config;
    private AccountManager accman;
    private int PORT = 5222;
    private String HOST = "54.68.155.224";
    private String ACCOUNT ="admin";
    private String PASS = "hellothere123";

    public ConnManModule()
    {

     config = XMPPTCPConnectionConfiguration.builder()
                .setHost(HOST)
                .setPort(PORT)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setServiceName(HOST)
                .setUsernameAndPassword(ACCOUNT, PASS)
                .build();
         conn = new XMPPTCPConnection(config);
         conn.setPacketReplyTimeout(30000);
         accman = AccountManager.getInstance(conn);
    }


    //overload contructor to return mock obj's

    public ConnManModule(boolean mock)
    {
        conn = Mockito.mock(AbstractXMPPConnection.class);
        config = Mockito.mock(XMPPTCPConnectionConfiguration.class);
        accman = Mockito.mock(AccountManager.class);

    }


@PerModel
@Provides AbstractXMPPConnection getConnection()
{
    return conn;
}


@PerModel
@Provides XMPPTCPConnectionConfiguration getConfig()
{
    return config;
}




@PerModel
@Provides AccountManager getAccountManager()
{
    return accman;
}




}
