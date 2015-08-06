package com.example.eoin_a.im_app20.Utils;

import com.example.eoin_a.im_app20.UtilsInt.ConnectionManagerInt;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by eoin_a on 21/07/2015.
 */
public class ConnectionManager implements ConnectionManagerInt {


    private AbstractXMPPConnection conn1;
    private XMPPTCPConnectionConfiguration configbuilder;
    private int PORT = 5222;
    private String HOST = "54.68.155.8";
    private String ACCOUNT ="admin";
    private String PASS = "hellothere123";

    @Inject
    public ConnectionManager()
    {
        //initialize

        /*configbuilder = XMPPTCPConnectionConfiguration.builder()
                .setHost(HOST)
                .setPort(PORT)
                .setUsernameAndPassword(ACCOUNT, PASS)
                .build();*/

        //need to provide xmpp service name???

        /*conn1 = new XMPPTCPConnection(configbuilder);*/
    }




    @Override
    public boolean connect()
    {

        //connect to openfire server

        try {
            conn1.connect();
        } catch (SmackException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XMPPException e) {
            e.printStackTrace();
            return false;
        }


        return true;


    }


}
