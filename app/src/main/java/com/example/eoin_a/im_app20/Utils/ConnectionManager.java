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
    private String HOST = "52.25.73.200";
    private String ACCOUNT ="admin";
    private String PASS = "hellothere123";
    private String error = "";
    @Inject Context cont;
    @Inject AppState appstate;
    @Inject AccountManager accman;
    private Map<String, String> attrmap;


    @Inject
    public ConnectionManager()
    {
        //initialize
        configbuilder = XMPPTCPConnectionConfiguration.builder()
                .setHost(HOST)
                .setPort(PORT)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled) //as this is a prototype SSL is disabled
                .setServiceName(HOST)
                .setUsernameAndPassword(ACCOUNT, PASS)
                .build();


        conn1 = new XMPPTCPConnection(configbuilder);
         DaggerconnmanComponent.builder().connManModule(new ConnManModule(conn1))
                .appComponent(MyApplication.component()).
                        build().inject(this);

    }


    @Override
    public boolean connect()
    {
        //connect to openfire server

        try {
            conn1.connect();
        } catch (SmackException e) {
            e.printStackTrace();
            error += cont.getResources().getResourceName(R.string.error_conn);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            error += cont.getResources().getResourceName(R.string.error_conn);
            return false;
        } catch (XMPPException e) {
            e.printStackTrace();
            error += cont.getResources().getResourceName(R.string.error_conn);
            return false;
        }


        return true;
    }


    @Override
    public boolean loginDevice(String email, String password)
    {





        return false;
    }

    @Override
    public boolean registerDevice(String email, String password, Map<String, String> extraparam)
    {
        try {
            if(accman.supportsAccountCreation())
                 accman.createAccount(email,password, extraparam);
            else
            {
                error +=  cont.getResources().getResourceName(R.string.regfail);
                return false;
            }


        } catch (SmackException.NoResponseException e) {
            e.printStackTrace();
            error += cont.getResources().getResourceName(R.string.regfail);
            return false;
        } catch (XMPPException.XMPPErrorException e) {
            e.printStackTrace();
            error += cont.getResources().getResourceName(R.string.regfail);
            return false;
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
            error += cont.getResources().getResourceName(R.string.regfail);
            return false;
        }

        appstate.setRegistered(true);

        return true;
    }


    @Override
    public String getError()
    {
        return error;
    }

    @Override
    public void resetError()
    {
        error = "";
    }


    @Override
    public void closeConn()
    {
        conn1.disconnect();
    }

}
