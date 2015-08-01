package com.example.eoin_a.im_app20.Views;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.eoin_a.im_app20.R;

public class IntroActivity extends AppCompatActivity {



    private TextView tv;
    private int donecounter;
    private ProgressBar progbar;
    private class IntroHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg)
        {
            tv.setText(msg.arg1);
        }

    }
    //@Inject AppState state;
    private  Handler handl;
    private IntroHandler introhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        tv = (TextView) findViewById(R.id.textView);
        progbar = (ProgressBar)  findViewById(R.id.progressBar2);
        progbar.setMax(20);
        donecounter = 0;
        introhandler = new IntroHandler();
        handl = new Handler();
        startShit();

    }


    public void startShit()
    {
       Thread thread =  new Thread(new Runnable() {
            @Override
            public void run() {

                //might have to do this with progressbar
                while(donecounter++ < 20)
                {

                    handl.post(new Runnable() {
                        @Override
                        public void run() {
                            progbar.setProgress(donecounter);
                        }
                    });

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                handl.post(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        
                        statReg();
                    }
                });


            }
        });

        thread.start();

    }

    private void statReg() {

        Intent intent = new Intent(IntroActivity.this,RegistrationActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
