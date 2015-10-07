package com.example.eoin_a.im_app20.BroadcastRecievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import com.example.eoin_a.im_app20.MyApplication;
import com.example.eoin_a.im_app20.Services.ChatListenerService;

/**
 * Created by eoin_a on 02/10/2015.
 */
public class ConnectionBroadcastReceiver extends BroadcastReceiver {





    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("Broadcast", "action : " + intent.getAction());



        if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION))
        {
            ConnectivityManager conman = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkinfo =  conman.getActiveNetworkInfo();



            if(networkinfo == null)
            {
                return;
            }
            else if(networkinfo.getState() == NetworkInfo.State.CONNECTED)
            {
                    Log.d("network","network connected");
            }
            else if(networkinfo.getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED)
            {
                    Log.d("network", "network disconnected");
            }
            else
                return;

        }

        /*if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            NetworkInfo networkInfo =
                    intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (networkInfo.isConnected()) {

                // Todo: fix this. this action is never caught!!

                context.startService(new Intent(context, ChatListenerService.class));
                Toast.makeText(context, "service started!!", Toast.LENGTH_LONG).show();
                Log.d("network state", "Wifi is connected: " + String.valueOf(networkInfo));

            }
        } else if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

            ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = conman.getActiveNetworkInfo();

            if(networkInfo == null)
            {
                context.stopService(new Intent(context, ChatListenerService.class));
                Log.d("no network", "no network");
                return;
            }

        }*/
    }

}
