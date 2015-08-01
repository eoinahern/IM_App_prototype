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
import com.example.eoin_a.im_app20.Utils.ErrorChecker;

import javax.inject.Inject;

/**
 * Created by eoin_a on 23/06/2015.
 * class used to poll server and register new user.
 */
public class RegisterModel implements RegisterModelInt {

    @Inject AppState appstate;
    @Inject ErrorChecker errchecker;
    private Thread registerthread;
    private RegisterPresenterInt regpresenter;
    private Handler reghandler;
    private int progress;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

            //upload data to the server

            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            reghandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progress = 100;
                                    regpresenter.updateUIProgress(progress);
                                    Log.d("prog update 2 :!!", String.valueOf(progress));
                                }
                            });



            appstate.setRegistered(true);
        }
    };


    public RegisterModel(RegisterPresenterInt regpresin)
    {
        reghandler = new Handler();
        regpresenter = regpresin;
        appComponent component =  MyApplication.component();
        component.inject(this);
        progress = 0;
    }



    @Override
    public boolean registerDevice(String email, String password) {


        errchecker.setEmail(email);
        errchecker.setPassword(password);

        registerthread = new Thread(runnable);
        registerthread.start();

        //dont join thread as it forces
        //UI thread to wait for registerthread to complete

        Log.d("thread finished", "thread finished");
        return false;
    }


    private  boolean  checkErrors() {

        //if error returned from server. pass back to presenter

        if(!errchecker.emailValid()  || !errchecker.passwordValid())
            return false;

        return true;
    }

    @Override
    public String getWarningStr() {


        if(!checkErrors())
        {
            return errchecker.getWarning();
        }

        return "";

    }
}
