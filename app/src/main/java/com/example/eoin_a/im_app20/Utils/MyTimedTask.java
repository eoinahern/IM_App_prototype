package com.example.eoin_a.im_app20.Utils;

import java.util.TimerTask;
import java.util.concurrent.TimeoutException;

/**
 * Created by eoin_a on 14/09/2015.
 */
public class MyTimedTask extends TimerTask {

    private Thread passedthread;
    private String err;

    public MyTimedTask(Thread passedth)
    {
        passedthread = passedth;
        err = "";
    }


    @Override
    public void run() {

        if(passedthread.isAlive() && passedthread != null)
        {
                passedthread.interrupt();
        }

    }


    public String getErrorString()
    {
        return err;
    }

}
