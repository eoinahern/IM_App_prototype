package com.example.eoin_a.im_app20.Views;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.eoin_a.im_app20.Components.DaggerRegComponent;
import com.example.eoin_a.im_app20.Components.DaggerappComponent;
import com.example.eoin_a.im_app20.Components.appComponent;
import com.example.eoin_a.im_app20.Fragments.RegisterFrag;
import com.example.eoin_a.im_app20.Modules.AppModule;
import com.example.eoin_a.im_app20.Modules.RegModule;
import com.example.eoin_a.im_app20.MyApplication;
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
    private boolean registerset;
    @Inject  RegisterPresenterInt rpresenter;
    @Inject AppState appstate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        emailedtxt = (EditText) findViewById(R.id.edtxtemail);
        passedtxt =(EditText) findViewById(R.id.edtxtpass);
        phoneedtxt = (EditText) findViewById(R.id.edtxtphone);
        registerbtn = (Button) findViewById(R.id.button);


        RegModule regmodule = new RegModule(this);

         DaggerRegComponent.builder().regModule(regmodule).appComponent(MyApplication.component()).build()
        .inject(this);

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

       showDialog();
        rpresenter.relayRegisterUser(emailedtxt.getText().toString(), passedtxt.getText().toString(),
                 phoneedtxt.getText().toString());

    }


    public void showDialog()
    {
        progdialog = ProgressDialog.show(RegistrationActivity.this, "Registering!", "Please wait ..." );
    }


    private void hideProgress()
    {
        Log.d("hide prog called", "hide prg called");
        progdialog.dismiss();
    }


    @Override
    public void getMessage() { //get error message

       String error  = rpresenter.getError();

        if(!error.equalsIgnoreCase(""))
        {
            Toast toast = Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, -50);
            toast.show();
        }
        else
        {
            startNextActivity();
        }
    }

    private void startNextActivity() {


        if(appstate.isLoggedIn())
        {
            Intent intent = new Intent(RegistrationActivity.this,IntroActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void updateProgress() {
        Log.d("increment progactivity:", "updated");
        hideProgress();
        getMessage();
    }


}
