package com.example.eoin_a.im_app20.Utils;

import com.example.eoin_a.im_app20.UtilsInt.ConnectionManagerInt;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

/**
 * Created by eoin_a on 21/07/2015.
 */
public class ConnectionManager implements ConnectionManagerInt {


    private AbstractXMPPConnection conn1;
    private XMPPTCPConnectionConfiguration configbuilder;
    private int PORT = 5222;
    private String HOST = "52.27.135.145";
    private String ACCOUNT ="admin";


    public ConnectionManager()
    {
        //initialize
        configbuilder = XMPPTCPConnectionConfiguration.builder()
                .setHost(HOST)
                .setPort(PORT)
                .setUsernameAndPassword(ACCOUNT, "testaccount")
                .build();







    }

    public void connect()
    {

        //return type stanza need to parse this???
        //this is all exploratory at the moment!


    }


}
