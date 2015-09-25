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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

/**
 * Created by eoin_a on 01/08/2015.
 */
public class LoginModel implements LoginModelInt {

    private LoginPresenterInt loginpresenter;
    private Handler loginhandler;
    private String warningstr;
    private Thread loginthread;
    @Inject AppState appstate;
    @Inject ErrorChecker errcheck;
    @Inject ConnectionManager conmanager;

    public LoginModel(LoginPresenterInt loginpresin)
    {
        loginpresenter = loginpresin;
        loginhandler = new Handler();
        warningstr = "";

        DaggerconnComponent.builder().connModule(new ConnModule()).appComponent(MyApplication.component()).
                build().inject(this);

    }


    @Override
    public void login(final String emailin, final String passin) {


        errcheck.setEmail(emailin);
        errcheck.setPassword(passin);

        if(!checkErrors())
        {
            loginpresenter.LoginComplete();
            return;
        }

        ExecutorService exservice =  Executors.newSingleThreadExecutor();
       final Future future = exservice.submit(new Runnable() {
           @Override
           public  void run() {


               Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

               try {
                   Thread.sleep(7000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               if( !conmanager.createConnect())
               {
                   warningstr = conmanager.getError();
                   callPresenter();
                   warningstr = "Login failed!!!";
                   return;
               }

               if(!conmanager.loginDevice(emailin, passin))
               {
                   warningstr = conmanager.getError();
                   callPresenter();
                   warningstr = "login failed!!";
                   return;
               }

               callPresenter();
           }

           public void callPresenter()
           {

               loginhandler.post(new Runnable() {
                   @Override
                   public void run() {
                       loginpresenter.LoginComplete();

                   }
               });
           }
       });


        exservice.shutdown();
        loginthread = new Thread() {


            @Override
            public void run()
            {
                try
                {
                    future.get(10, TimeUnit.SECONDS);

                } catch (InterruptedException   |  ExecutionException  | TimeoutException  e ) {
                    e.printStackTrace();
                    warningstr = "Login failed!!";
                }
            }
        };

        loginthread.start();


    }

    private boolean checkErrors() {


        errcheck.clearWarning();

        boolean emailvaid = errcheck.emailValid();
        boolean passvalid = errcheck.passwordValid();

        if( !emailvaid || !passvalid)
        {
            warningstr = errcheck.getWarning();
            return false;
        }

        return true;
    }


    @Override
    public String getWarning()
    {
        return warningstr;
    }


    private void resetWarning()
    {
        warningstr = "";
    }





}
