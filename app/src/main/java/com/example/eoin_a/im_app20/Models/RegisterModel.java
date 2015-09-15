package com.example.eoin_a.im_app20.Models;

import android.content.Context;
import android.os.*;
import android.os.Process;
import android.util.Log;

//import com.example.eoin_a.im_app20.Components.DaggerRegisterComponent;
import com.example.eoin_a.im_app20.Components.DaggerconnComponent;
import com.example.eoin_a.im_app20.Components.appComponent;
import com.example.eoin_a.im_app20.ModelsInt.RegisterModelInt;
import com.example.eoin_a.im_app20.Modules.ConnModule;
import com.example.eoin_a.im_app20.MyApplication;
import com.example.eoin_a.im_app20.PresentersInt.RegisterPresenterInt;
import com.example.eoin_a.im_app20.Utils.AppState;
import com.example.eoin_a.im_app20.Utils.ConnectionManager;
import com.example.eoin_a.im_app20.Utils.ErrorChecker;
import com.example.eoin_a.im_app20.Utils.TimedTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

/**
 * Created by eoin_a on 23/06/2015.
 * class used to poll server and register new user.
 */
public class RegisterModel implements RegisterModelInt {

    @Inject AppState appstate;
    @Inject ErrorChecker errchecker;
    @Inject ConnectionManager connmanager;
    @Inject ExecutorService exservice;
    private Thread registerthread;
    private RegisterPresenterInt regpresenter;
    private Handler reghandler;
    private String warningstr;
    private ExecutorService executorService;
    private Timer timer;


    public RegisterModel(RegisterPresenterInt regpresin)
    {
        reghandler = new Handler();
        regpresenter = regpresin;
        DaggerconnComponent.builder().connModule(new ConnModule())
                .appComponent(MyApplication.component()).build().inject(this);
        warningstr = "";

    }



    @Override
    public void registerDevice(final String email, final String password, final String phoneno) {

        //relatively messy looking at present. id like to refactor this!!


        /*errchecker.setEmail(email);
        errchecker.setPassword(password);
        errchecker.setPhoneNo(phoneno);


        if(!checkErrors())
        {
            regpresenter.updateUIProgress();
            return;
        }*/





            final Future<Boolean> future = exservice.submit(new Callable() {


                @Override
                public Boolean call() throws Exception {

                    android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);


                    if (!connmanager.connect()) {
                        warningstr = connmanager.getError();
                        updateUI();
                        return false;
                    }

                    Map<String, String> extparam = new HashMap<String, String>();
                    extparam.put("PhoneNo", phoneno);


                    if (!connmanager.registerDevice(email, password, extparam)) {
                        warningstr = connmanager.getError();
                        updateUI();
                        return false;
                    }

                    updateUI();
                    return true;
                }

                private void updateUI() {

                    reghandler.post(new Runnable() {
                        @Override
                        public void run() {
                            regpresenter.updateUIProgress();
                        }
                    });

                }

            });

        exservice.shutdown();

        registerthread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                  boolean status =  executorService.awaitTermination(10,TimeUnit.SECONDS);

                   // boolean status = future.get(10, TimeUnit.SECONDS);
                    if(!status)
                        throw new InterruptedException();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    warningstr = "Thread Interrupted!!!!";
                    return;
                }  /*catch (ExecutionException e) {
                    e.printStackTrace();
                    warningstr = "Server call timed out!";
                    return;
                } catch (TimeoutException e) {
                    e.printStackTrace();
                    warningstr = "Server call timed out!";
                    return;
                }*/ catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        registerthread.start();
        exservice.shutdownNow();

        Log.d("thread finished", "thread finished");

    }



    private  boolean  checkErrors() {  //need to make error checking a bit more generic!!!

        errchecker.clearWarning();

        boolean emailval = errchecker.emailValid();
        boolean passval = errchecker.passwordValid();
        boolean phoneval = errchecker.phonevalid();

        if(!emailval  || !passval || phoneval) {
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
