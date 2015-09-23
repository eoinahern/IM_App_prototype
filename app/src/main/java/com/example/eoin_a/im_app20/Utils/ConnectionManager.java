package com.example.eoin_a.im_app20.Utils;

import android.content.Context;
import android.util.Log;

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


    @Inject AbstractXMPPConnection conn1;
    @Inject XMPPTCPConnectionConfiguration configbuilder;
    private String error = "";
    @Inject Context cont;
    @Inject AppState appstate;
    @Inject AccountManager accman;
    private Map<String, String> attrmap;


    @Inject
    public ConnectionManager()
    {

        //may need to pass boolean to inject mocks!!!

         DaggerconnmanComponent.builder().connManModule(new ConnManModule())
                .appComponent(MyApplication.component()).
                        build().inject(this);
    }



    @Override
    public  boolean createConnect()
    {
        //connect to openfire server

        try {
            if(!conn1.isConnected())
                conn1.connect();

        } catch (SmackException e) {
            e.printStackTrace();
            error += cont.getResources().getString(R.string.error_conn);
            Log.d("SmackException thrown", "SmackException connect");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            error += cont.getResources().getString(R.string.error_conn);
            Log.d("IOException thrown", "IOException connect");
            return false;
        } catch (XMPPException e) {
            e.printStackTrace();
            error += cont.getResources().getString(R.string.error_conn);
            Log.d("XMPPException thrown", "XMPPException connect");
            return false;
        }

        Log.d("connection created", "connection created");
        return true;
    }


    @Override
    public boolean loginDevice(String email, String password)
    {

        //login using smack api

        resetError();
        return false;
    }

    @Override
    public boolean registerDevice(String email, String password, Map<String, String> extraparam)
    {



        try {
            //if(accman.supportsAccountCreation())
                 accman.sensitiveOperationOverInsecureConnection(true);
                 accman.createAccount(email,password, extraparam);

          /* else
            {
                error +=  cont.getResources().getString(R.string.regfail).toString();
                Log.d("Error Account Creation", "Not supported acc creation");
                return false;
            }*/


        } catch (SmackException.NoResponseException e) {
            e.printStackTrace();
            error += cont.getResources().getString(R.string.regfail);
            Log.d("SmackException thrown", "SmackException");
            return false;
        } catch (XMPPException.XMPPErrorException e) {
            e.printStackTrace();
            error += cont.getResources().getString(R.string.regfail);
            Log.d("XMPPException thrown", "XMPPException");
            return false;
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
            Log.d("Not Connected!", "Not ConnectedException");
            error += cont.getResources().getString(R.string.regfail);
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
        if(conn1 != null)
        conn1.disconnect();
    }

}
