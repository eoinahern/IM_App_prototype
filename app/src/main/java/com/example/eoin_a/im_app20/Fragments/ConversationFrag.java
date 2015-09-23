package com.example.eoin_a.im_app20.Fragments;



import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eoin_a.im_app20.R;

/**
 * Created by eoin_a on 22/09/2015.
 */
public class ConversationFrag extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedinstancestate)
    {
        return inflater.inflate(R.layout.conversation_layout, container, false);
    }
}
