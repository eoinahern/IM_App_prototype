package com.example.eoin_a.im_app20.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.eoin_a.im_app20.BroadcastRecievers.ConnectionBroadcastReceiver;
import com.example.eoin_a.im_app20.Components.DaggerconnComponent;
import com.example.eoin_a.im_app20.Modules.ConnModule;
import com.example.eoin_a.im_app20.MyApplication;
import com.example.eoin_a.im_app20.Utils.ConnectionManager;

import javax.inject.Inject;

/**
 * Created by eoin_a on 02/10/2015.
 */
public class ChatListenerService extends Service {



    private Thread incomingmessagethread;
    private Handler messagehandler;
    @Inject ConnectionManager connmanager;

    @Override
    public void onCreate()
    {


        DaggerconnComponent.builder().connModule(new ConnModule()).appComponent(MyApplication.component()).
                build().inject(this);

        Log.d("service created", "service created");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {

        incomingmessagethread = new Thread(new Runnable() {


            @Override
            public void run() {
                //listenout of incoming message!!!
                connmanager.listenForIncomingChat(ChatListenerService.this);

            }
        });


        incomingmessagethread.start();



        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy()
    {

        if(incomingmessagethread != null)
            incomingmessagethread.interrupt();

        super.onDestroy();
    }
}
