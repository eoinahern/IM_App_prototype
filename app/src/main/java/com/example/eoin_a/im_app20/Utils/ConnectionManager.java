package com.example.eoin_a.im_app20.Utils;

import android.content.Context;

import com.example.eoin_a.im_app20.Components.appComponent;
import com.example.eoin_a.im_app20.MyApplication;
import com.example.eoin_a.im_app20.R;
import com.example.eoin_a.im_app20.UtilsInt.ConnectionManagerInt;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
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
    private String HOST = "52.10.150.92";
    private String ACCOUNT ="admin";
    private String PASS = "hellothere123";
    private String error = "";
    @Inject Context cont;



    @Inject
    public ConnectionManager()
    {
        //initialize

        appComponent component =  MyApplication.component();
        component.inject(this);

        configbuilder = XMPPTCPConnectionConfiguration.builder()
                .setHost(HOST)
                .setPort(PORT)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled) //as this is a prototype SSL is disabled
                .setServiceName(HOST)
                .setUsernameAndPassword(ACCOUNT, PASS)
                .build();

        //need to provide xmpp service name???

        conn1 = new XMPPTCPConnection(configbuilder);
    }




    @Override
    public String connect()
    {

        //connect to openfire server

        try {
            conn1.connect();
        } catch (SmackException e) {
            e.printStackTrace();
            error += cont.getResources().getResourceName(R.string.error_conn);
        } catch (IOException e) {
            e.printStackTrace();
            error += cont.getResources().getResourceName(R.string.error_conn);
        } catch (XMPPException e) {
            e.printStackTrace();
            error += cont.getResources().getResourceName(R.string.error_conn);
        }


        return error;


    }



    public boolean loginDevice()
    {


        return false;
    }


    public boolean registerDevice()
    {


        return false;
    }

}
