package com.example.eoin_a.im_app20.Utils;

import android.util.Log;

import java.util.TimerTask;

/**
 * Created by eoin_a on 11/09/2015.
 */
public class TimedTask  extends Thread{

        private Thread  sentthread;
        private int m_time;

        public TimedTask(Thread sentthreadin)
        {
            sentthread = sentthreadin;
            m_time = 0;
        }

        @Override
        public void run()
        {
            while( m_time < 30)
            {

                try {
                    Thread.sleep(1000);
                    m_time++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.d("Interrupted","TimedTask interrupted");
                }
            }


            if(sentthread.isAlive() && sentthread != null)
                sentthread.interrupt();
        }
}


