package com.example.eoin_a.im_app20.Utils;

import android.content.Context;

import com.example.eoin_a.im_app20.R;
import com.example.eoin_a.im_app20.UtilsInt.ErrorCheckerInt;

import javax.inject.Inject;

/**
 * Created by eoin_a on 23/06/2015.
 *
 *  used to check textbox entries when registering
 *   and logging in.
 *
 */

public class ErrorChecker implements ErrorCheckerInt {


    private String email;
    private String password;
    private String warning;
    @Inject Context cont;


    @Inject
    public ErrorChecker()
    {

    }

    public void setEmail(String emailin)
    {
        email = emailin;
    }

    public void setPassword(String passwordin)
    {
        password = passwordin;
    }

    public ErrorChecker(String emailin, String passwordin)
    {
        email = emailin;
        password = passwordin;

    }


    @Override
    public boolean emailValid() {

        if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+"))
        {
            warning += cont.getString(R.string.warn2) + "\n";
            return false;
        }

        return true;
    }

    @Override
    public boolean passwordValid() {

        if(password.length() <= 8  &&  password.matches("[a-zA-Z0-9]+"))
        {
            warning += cont.getString(R.string.warn1) + "\n";
            return false;

        }

         return true;
    }

    @Override
    public String getWarning() {
        return "hellothere!!!";
    }
}
