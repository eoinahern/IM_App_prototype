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
import com.example.eoin_a.im_app20.Utils.MyTimedTask;
import com.example.eoin_a.im_app20.UtilsInt.ConnectionManagerInt;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
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
    @Inject AppState appstate;
    @Inject ErrorChecker errcheck;
    @Inject ConnectionManager conmanager;
    @Inject ExecutorService exservice;


    public LoginModel(LoginPresenterInt loginpresin)
    {
        loginpresenter = loginpresin;
        loginhandler = new Handler();
        warningstr = "";

        DaggerconnComponent.builder().connModule(new ConnModule()).appComponent(MyApplication.component()).
                build().inject(this);

    }


    @Override
    public boolean login(final String emailin, final String passin) {


        //may use timetask method for timeout to see which method of timeout
        //I prefer.


        errcheck.setEmail(emailin);
        errcheck.setPassword(passin);

        if(!checkErrors())
        {
            loginpresenter.LoginComplete();
            return false;
        }


       Future<Boolean> future = exservice.submit(new Callable() {
           @Override
           public Boolean call() throws Exception {


               Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

               try {
                   Thread.sleep(7000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               if( !conmanager.connect())
               {
                   warningstr = conmanager.getError();
                   callPresenter();
                   warningstr = "Login failed!!!";
                   return false;
               }

               if(!conmanager.loginDevice(emailin, passin))
               {
                   warningstr = conmanager.getError();
                   callPresenter();
                   warningstr = "login failed!!";
                   return false;
               }

               callPresenter();
               return true;
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

        try
        {
           boolean state = future.get(10, TimeUnit.SECONDS);
           if(!state) { throw new InterruptedException();}


        } catch (InterruptedException   |  ExecutionException  | TimeoutException  e ) {
            e.printStackTrace();
            warningstr = "Login failed!!";
            return false;
        }

        return true;
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
