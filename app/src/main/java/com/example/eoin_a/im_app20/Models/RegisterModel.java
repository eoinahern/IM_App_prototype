package com.example.eoin_a.im_app20.Models;

import android.content.Context;
import android.os.*;
import android.os.Process;
import android.util.Log;

//import com.example.eoin_a.im_app20.Components.DaggerRegisterComponent;
import com.example.eoin_a.im_app20.Components.appComponent;
import com.example.eoin_a.im_app20.ModelsInt.RegisterModelInt;
import com.example.eoin_a.im_app20.MyApplication;
import com.example.eoin_a.im_app20.PresentersInt.RegisterPresenterInt;
import com.example.eoin_a.im_app20.Utils.AppState;
import com.example.eoin_a.im_app20.Utils.ConnectionManager;
import com.example.eoin_a.im_app20.Utils.ErrorChecker;

import javax.inject.Inject;

/**
 * Created by eoin_a on 23/06/2015.
 * class used to poll server and register new user.
 */
public class RegisterModel implements RegisterModelInt {

    @Inject AppState appstate;
    @Inject ErrorChecker errchecker;
    @Inject ConnectionManager connmanager;
    private Thread registerthread;
    private RegisterPresenterInt regpresenter;
    private Handler reghandler;
    private String warningstr;




    public RegisterModel(RegisterPresenterInt regpresin)
    {
        reghandler = new Handler();
        regpresenter = regpresin;
        appComponent component =  MyApplication.component();
        component.inject(this);
        warningstr = "";
    }



    @Override
    public boolean registerDevice(final String email, final String password) {  //prob dont need to return bool


        errchecker.setEmail(email);
        errchecker.setPassword(password);

        if(!checkErrors())
        {
            regpresenter.updateUIProgress();
            return false;
        }

        registerthread = new Thread(new Runnable() {

            @Override
            public void run()
            {
                android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


               if (!connmanager.connect())
               {
                   warningstr = connmanager.getError();
                   return;
               }

                if(!connmanager.registerDevice(email,password))
                {
                    warningstr = connmanager.getError();

                    return;
                }



                reghandler.post(new Runnable() {
                    @Override
                    public void run() {
                        regpresenter.updateUIProgress();
                    }
                });


                appstate.setRegistered(true);
            }
        });
        registerthread.start();
        Log.d("thread finished", "thread finished");
        return false;
    }


    private  boolean  checkErrors() {  //need to make error checking a bit more generic!!!

        errchecker.clearWarning();

        if(!errchecker.emailValid()  || !errchecker.passwordValid()) {
            warningstr = errchecker.getWarning();
            return false;
        }

        return true;
    }

    @Override
    public String getWarningStr() {
        return warningstr;
    }


}
