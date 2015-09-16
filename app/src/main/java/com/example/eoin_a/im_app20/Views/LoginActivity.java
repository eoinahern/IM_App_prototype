package com.example.eoin_a.im_app20.Views;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eoin_a.im_app20.Components.DaggerLoginComponent;
import com.example.eoin_a.im_app20.Modules.LoginModule;
import com.example.eoin_a.im_app20.PresentersInt.LoginPresenterInt;
import com.example.eoin_a.im_app20.R;
import com.example.eoin_a.im_app20.Utils.AppState;
import com.example.eoin_a.im_app20.ViewsInt.LoginViewInt;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginViewInt {

    private Button loginbtn;
    private EditText emailtxt;
    private EditText passwordtxt;
    private CheckBox chkbox;
    private ProgressDialog progdialog;
    private Intent intent;
    @Inject LoginPresenterInt loginpresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginbtn = (Button) findViewById(R.id.buttonlogin);
        emailtxt = (EditText) findViewById(R.id.edtxtemaillogin);
        passwordtxt = (EditText) findViewById(R.id.edtxtpasslogin);
        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relayLoginUser();
            }
        });

    }


    @Override
    public void relayLoginUser()
    {
        showLoading();
        loginpresenter.LoginDevice(emailtxt.getText().toString(), passwordtxt.getText().toString());
    }

    @Override
    public void showLoading()
    {
        //dialog
        progdialog = ProgressDialog.show(LoginActivity.this, "Logging in!", "Please wait ..." );
    }

    @Override
    public void hideLoading() {
        //hide dialog
        progdialog.dismiss();
    }

    @Override
    public void LoginComplete() {


        hideLoading();   //hide dialog

        if(loginpresenter.getError().isEmpty())
        {
           //open main activity

            Log.d("login complete!!", "login complete hooray");
            intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Log.d("login failed!!", "login failed waa");
            Toast.makeText(getApplicationContext(), loginpresenter.getError(),
                    Toast.LENGTH_LONG).show();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
