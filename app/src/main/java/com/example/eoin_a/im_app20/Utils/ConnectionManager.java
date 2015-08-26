package com.example.eoin_a.im_app20.Utils;

import android.content.Context;

//import com.example.eoin_a.im_app20.Components.DaggerconnmanComponent;
import com.example.eoin_a.im_app20.Components.DaggerconnmanComponent;
import com.example.eoin_a.im_app20.Components.appComponent;
import com.example.eoin_a.im_app20.Components.connmanComponent;
import com.example.eoin_a.im_app20.Modules.AppModule;
import com.example.eoin_a.im_app20.Modules.ConnManModule;
import com.example.eoin_a.im_app20.MyApplication;
import com.example.eoin_a.im_app20.R;
import com.example.eoin_a.im_app20.UtilsInt.ConnectionManagerInt;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    private AccountManager accman;
    private Map<String, String> attrmap;


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


        conn1 = new XMPPTCPConnection(configbuilder);
        accman = DaggerconnmanComponent.builder()
                .connManModule(new ConnManModule(conn1)).build()
                .getAccountManager();
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


    @Override
    public boolean loginDevice(String email, String password)
    {


        return false;
    }

    @Override
    public boolean registerDevice(String email, String password)
    {

        try {

            if(accman.supportsAccountCreation())
                accman.createAccount(email,password);
            else
                return false;

        } catch (SmackException.NoResponseException e) {
            e.printStackTrace();
            return false;
        } catch (XMPPException.XMPPErrorException e) {
            e.printStackTrace();
            return false;
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
            return false;
        }

        //if device is registered. save apstate

        return true;
    }


    @Override
    public void closeConn()
    {
        conn1.disconnect();
    }

}
