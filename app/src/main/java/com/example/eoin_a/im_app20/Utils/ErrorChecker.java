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
    private String phoneno;
    @Inject Context cont;


    @Inject
    public ErrorChecker()
    {
        warning = "";
    }

    public void setEmail(String emailin)
    {
        email = emailin;
    }

    public void setPassword(String passwordin)
    {
        password = passwordin;
    }


    public void setPhoneNo(String phonenoin)
    {
      phoneno =  phonenoin;
    }

    public ErrorChecker(String emailin, String passwordin, String phonenoin)
    {
        email = emailin;
        phoneno = phonenoin;
        password = passwordin;
        warning = "";

    }


    @Override
    public boolean emailValid() {



       /* if(!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+"))
        {
            warning += cont.getString(R.string.warn2) + "\n";
            return false;
        }*/

        return true;
    }

    @Override
    public boolean passwordValid() {

        //need to check for empty string also

        if(password.length() <= 8   || password.matches("[a-zA-Z0-9 ]+"))
        {
            warning += cont.getString(R.string.warn1) + "\n";
            return false;

        }

         return true;
    }

    @Override
    public boolean phonevalid() {



        if( !(phoneno.length() > 15)  ||  !checkAllDigits(phoneno))
        {
            warning += cont.getString(R.string.phonenowarning) + "\n";
            return false;
        }

        return true;
    }

    private boolean checkAllDigits(String phoneno)
    {
        for(int i = 0; i < phoneno.length(); i++)
        {
            if(!Character.isDigit(phoneno.charAt(i)))
            {
                    return false;
            }
        }

        return true;


    }


    @Override
    public void clearWarning()
    {
        warning = "";
    }

    @Override
    public String getWarning() {
        return warning;
    }
}
