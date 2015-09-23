package com.example.eoin_a.im_app20.Components;

import com.example.eoin_a.im_app20.Modules.MockConnManModule;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;

import dagger.Component;

/**
 * Created by eoin_a on 21/09/2015.
 */

@PerModel
@Component(dependencies ={appComponent.class}, modules = {MockConnManModule.class})
public interface MockConnManComponent {


    AbstractXMPPConnection getMockConn();
    XMPPTCPConnectionConfiguration getMockConfig();
    AccountManager getMockAccountMan();

}
