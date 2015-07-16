package com.example.eoin_a.im_app20.Views;

import android.app.ProgressDialog;
import android.os.*;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.eoin_a.im_app20.Components.DaggerRegComponent;
import com.example.eoin_a.im_app20.Modules.RegModule;
import com.example.eoin_a.im_app20.Presenters.RegisterPresenter;
import com.example.eoin_a.im_app20.PresentersInt.RegisterPresenterInt;
import com.example.eoin_a.im_app20.R;
import com.example.eoin_a.im_app20.Utils.AppState;
import com.example.eoin_a.im_app20.ViewsInt.RegistrationViewInt;


import javax.inject.Inject;


public class RegistrationActivity extends AppCompatActivity implements RegistrationViewInt {

    private EditText emailedtxt;
    private EditText passedtxt;
    private EditText phoneedtxt;
    private Button registerbtn;
    private ProgressDialog progdialog;
    private String dialogstr = "Registering !!";
    Handler handler;



    @Inject RegisterPresenterInt rpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        emailedtxt = (EditText) findViewById(R.id.edtxtemail);
        passedtxt =(EditText) findViewById(R.id.edtxtpass);
        phoneedtxt = (EditText) findViewById(R.id.edtxtphone);
        registerbtn = (Button) findViewById(R.id.button);

        DaggerRegComponent.builder().regModule(new RegModule(this)).build().inject(this);


        handler = new Handler();

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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


    @Override
    public void registerUser() {

        //progdialog = ProgressDialog.show(RegistrationActivity.this, "SHIT", "SHIT");
        showProgress();

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        rpresenter.relayRegisterUser(emailedtxt.getText().toString(),passedtxt.getText().toString(),
                                phoneedtxt.getText().toString());
                    }
                });



            }
        });

        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean isRegistered()
    {
        //check if user is registered. get val from model.
        return false;
    }


    private void showProgress()
    {
       progdialog =  ProgressDialog.show(RegistrationActivity.this,"Loading...", dialogstr);
    }


    private void hideProgress()
    {
        Log.d("hide prog called", "hide prg called");
        progdialog.hide();
        progdialog.dismiss();

    }

    @Override
    public void getMessage() { //get error message

       String error  = rpresenter.getError();

        if(!error.equalsIgnoreCase(""))
        {
            //show error message.
            //undecided on how to display yet
        }

    }

    @Override
    public void updateProgress(int progress) {

        Log.d("increment progactivity:", String.valueOf(progress));
        progdialog.incrementProgressBy(progress);

        if(progress == 100)
            hideProgress();


    }
}
