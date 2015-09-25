package com.example.eoin_a.im_app20.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.util.Log;

import com.example.eoin_a.im_app20.Entitys.Contact;
import com.example.eoin_a.im_app20.Fragments.ContactsFrag;
import com.example.eoin_a.im_app20.Fragments.ConversationFrag;

import java.util.ArrayList;

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

            //test contacts, and adapter!!
            Contact cont1 = new Contact("", "eoins ahern", "05399292");
            Contact cont2 = new Contact("", "doooo murphy", "05399292");


            ArrayList<Contact> conts = new ArrayList<>();

            conts.add(cont1);
            conts.add(cont2);


            contListAdapter mylistadt = new contListAdapter(conts);


            ListFragment frag = new ContactsFrag();
            frag.setListAdapter(mylistadt);

            fragment = frag;

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
