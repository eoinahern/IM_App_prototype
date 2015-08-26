package com.example.eoin_a.im_app20.Models;

import android.os.*;
import android.os.Process;

import com.example.eoin_a.im_app20.Components.DaggerconnComponent;
import com.example.eoin_a.im_app20.Components.appComponent;
import com.example.eoin_a.im_app20.ModelsInt.LoginModelInt;
import com.example.eoin_a.im_app20.Modules.ConnModule;
import com.example.eoin_a.im_app20.MyApplication;
import com.example.eoin_a.im_app20.PresentersInt.LoginPresenterInt;
import com.example.eoin_a.im_app20.Utils.AppState;
import com.example.eoin_a.im_app20.Utils.ConnectionManager;
import com.example.eoin_a.im_app20.Utils.ErrorChecker;
import com.example.eoin_a.im_app20.UtilsInt.ConnectionManagerInt;

import javax.inject.Inject;

/**
 * Created by eoin_a on 01/08/2015.
 */
public class LoginModel implements LoginModelInt {

    private LoginPresenterInt loginpresenter;
    private Handler loginhandler;
    private String result;

    @Inject AppState appstate;
    @Inject ErrorChecker errcheck;
    @Inject ConnectionManager conmanager;


    public LoginModel(LoginPresenterInt loginpresin)
    {
        loginpresenter = loginpresin;
        loginhandler = new Handler();

        appComponent component =  MyApplication.component();
        component.inject(this);

    }




    @Override
    public boolean login(String emailin, String passin) {


         new Thread(new Runnable() {

            @Override
            public void run() {

                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

               result = conmanager.connect();

                //check login against server.
                //if logged in go to main screen


                loginhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginpresenter.LoginComplete(result);
                    }
                });

            }
        }).start();



        return false;
    }
}
