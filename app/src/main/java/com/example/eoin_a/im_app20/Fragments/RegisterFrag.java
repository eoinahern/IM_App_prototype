package com.example.eoin_a.im_app20.Fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eoin_a.im_app20.R;

/**
 * Created by eoin_a on 20/07/2015.
 */
public class RegisterFrag  extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        View v = inflater.inflate(R.layout.reg_loading_layout, container);
        return v;
    }
}
