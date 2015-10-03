package com.example.eoin_a.im_app20.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by eoin_a on 02/10/2015.
 */
public class ChatListenerService extends Service {



    @Override
    public void onCreate()
    {




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
        return super.onStartCommand(intent, flags, startId);
    }
}
