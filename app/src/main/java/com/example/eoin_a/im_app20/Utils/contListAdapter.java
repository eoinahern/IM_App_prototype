package com.example.eoin_a.im_app20.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.eoin_a.im_app20.Entitys.Contact;
import com.example.eoin_a.im_app20.MyApplication;
import com.example.eoin_a.im_app20.R;

import java.util.ArrayList;

/**
 * Created by eoin_a on 25/09/2015.
 */
public class contListAdapter extends BaseAdapter {



    private ArrayList<Contact> contacts;
    private Contact contact;


    public contListAdapter(ArrayList<Contact> contactsin)
    {
        contacts = contactsin;
    }


    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
       return  contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewholder;
        contact = contacts.get(position);


        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) MyApplication.getInst().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.single_contact, null, false);

            viewholder = new ViewHolder();
            viewholder.name = (TextView) convertView.findViewById(R.id.cont_tv) ;
            convertView.setTag(viewholder);

        }
        else
        {
           viewholder = (ViewHolder) convertView.getTag();
        }


        viewholder.name.setText(contact.getName());
        return convertView;
    }



    private static class ViewHolder{

        private TextView name;

    }
}
