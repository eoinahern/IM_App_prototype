package com.example.eoin_a.im_app20.Views;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eoin_a.im_app20.Components.DaggerLoginComponent;
import com.example.eoin_a.im_app20.Modules.LoginModule;
import com.example.eoin_a.im_app20.PresentersInt.LoginPresenterInt;
import com.example.eoin_a.im_app20.R;
import com.example.eoin_a.im_app20.Utils.AppState;
import com.example.eoin_a.im_app20.ViewsInt.LoginViewInt;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity  implements LoginViewInt {

    private Button loginbtn;
    private EditText emailtxt;
    private EditText passwordtxt;
    @Inject LoginPresenterInt loginpresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginbtn = (Button) findViewById(R.id.buttonlogin);
        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);

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
