package com.example.eoin_a.im_app20.Presenters;

import android.util.Log;

import com.example.eoin_a.im_app20.Models.RegisterModel;
import com.example.eoin_a.im_app20.ModelsInt.RegisterModelInt;
import com.example.eoin_a.im_app20.PresentersInt.RegisterPresenterInt;
import com.example.eoin_a.im_app20.ViewsInt.RegistrationViewInt;

/**
 * Created by eoin_a on 22/06/2015.
 */
public class RegisterPresenter  implements RegisterPresenterInt{

    //acts as intermediary between Model and View

    private RegisterModelInt regmodel;
    private RegistrationViewInt regview;

    public RegisterPresenter(RegistrationViewInt regviewin)
   {
        regview = regviewin;
        regmodel = new RegisterModel(this);
   }

    @Override
    public String getError() {
        return regmodel.getWarningStr();
    }

    @Override
    public boolean relayRegisterUser(String email, String password, String phoneno) {
       return  regmodel.registerDevice(email, password);
    }

    @Override
    public void updateUIProgress() {
        regview.updateProgress();
    }


}
