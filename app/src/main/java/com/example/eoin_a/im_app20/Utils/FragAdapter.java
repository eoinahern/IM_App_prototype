package com.example.eoin_a.im_app20.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.eoin_a.im_app20.Fragments.ContactsFrag;
import com.example.eoin_a.im_app20.Fragments.ConversationFrag;

/**
 * Created by eoin_a on 23/09/2015.
 */
public class FragAdapter extends FragmentPagerAdapter {

    private final int count = 2;

    public FragAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        Log.d("FragAdapter", "getItem called!");


        if(position == 0) {
            fragment = new ContactsFrag();
        }
        else {
            fragment = new ConversationFrag();
        }


        return fragment;

    }

    @Override
    public int getCount() {
        return count;
    }
}
