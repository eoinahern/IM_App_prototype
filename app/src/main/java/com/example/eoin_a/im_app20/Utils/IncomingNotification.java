package com.example.eoin_a.im_app20.Utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.eoin_a.im_app20.MyApplication;
import com.example.eoin_a.im_app20.R;
import com.example.eoin_a.im_app20.Views.ChatActivity;
import com.example.eoin_a.im_app20.Views.IntroActivity;
import com.example.eoin_a.im_app20.Views.MainActivity;

/**
 * Created by eoin_a on 06/10/2015.
 */
public class IncomingNotification extends Notification {

    private Context cont;
    private PendingIntent pintent;
    private Intent chatintent;
    private NotificationManager notificationman;

    public IncomingNotification()
    {
        cont = MyApplication.getInst();
        notificationman = (NotificationManager) cont.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void showNotification(Context sentcontext)
    {
        chatintent = new Intent(sentcontext, ChatActivity.class);
        pintent = PendingIntent.getActivity(sentcontext,(int) System.currentTimeMillis(),chatintent,0);

        Notification notification = new Notification.Builder(sentcontext)
                .setContentTitle("Notification")
                .setContentText("notification text")
                .setSmallIcon(R.drawable.user_icon)
                .setAutoCancel(true)
                .addAction(R.drawable.user_icon, "Reply", pintent).build();

                //Todo: change deprecated method!!


        notificationman.notify(0, notification);
    }
}
