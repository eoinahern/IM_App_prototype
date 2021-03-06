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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


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
    private ExecutorService executorService;
    private HashMap<String, String> attrmap;

    public RegisterModel(RegisterPresenterInt regpresin)
    {
        reghandler = new Handler();
        regpresenter = regpresin;
        DaggerconnComponent.builder().connModule(new ConnModule())
                .appComponent(MyApplication.component()).build().inject(this);
        warningstr = "";

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        attrmap = new HashMap<>();  // as per smack 4.1.0 java docs
        attrmap.put("name","empty");
        attrmap.put("first", "empty");
        attrmap.put("last","empty");
        attrmap.put("city","empty");
        attrmap.put("state", "empty");
        attrmap.put("zip","empty");
        attrmap.put("url","empty");
        attrmap.put("date", date);
        attrmap.put("misc", "empty");
        attrmap.put("text", "empty");
        attrmap.put("remove", "no");  //not sure how remove flag work!

    }



    @Override
    public void registerDevice(final String email, final String password, final String phoneno) {


        //refactor!!


        /*errchecker.setEmail(email);
        errchecker.setPassword(password);
        errchecker.setPhoneNo(phoneno);

        if(!checkErrors())
        {
            regpresenter.updateUIProgress();
            return;
        }*/
        connmanager.resetError();

        ExecutorService exservice =  Executors.newSingleThreadExecutor();
            final Future<Boolean> future = exservice.submit(new Callable() {

                @Override
                public Boolean call() throws Exception {

                    android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

                    if (!connmanager.createConnect()) {
                        warningstr = connmanager.getError();
                        updateUI();
                        return false;
                    }

                    attrmap.put("phone",phoneno);
                    attrmap.put("email",email);


                    if (!connmanager.registerDevice(email, password, attrmap)) {
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

                  boolean status =  future.get();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.d("Exception","Interrupted Exception!!");
                    warningstr =" Nah!";
                    return;
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Exception", "Register Exception!!");
                    warningstr =" Nah!";
                    return;
                }
            }
        });

        registerthread.start();


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
