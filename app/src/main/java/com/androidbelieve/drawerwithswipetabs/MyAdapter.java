package com.androidbelieve.drawerwithswipetabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aspire on 1/24/2016.
 */
public class MyAdapter extends BaseAdapter {

    private ArrayList<Contacts> contactses;
    public final Context activity;
    private LayoutInflater layoutInflater;

    public MyAdapter(ArrayList<Contacts> contactses, Context activity){
        this.activity = activity;
        this.contactses = contactses;
    }


    @Override
    public int getCount() {
        return contactses.size();
    }

    @Override
    public Object getItem(int position) {
        return contactses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        Contacts selectedContact = contactses.get(position);
        convertView = layoutInflater.inflate(R.layout.custom_contact_cell, null);

        TextView name = (TextView) convertView.findViewById(R.id.test);
        TextView name1 = (TextView) convertView.findViewById(R.id.test1);

        name.setText(selectedContact.getName());
        name1.setText(selectedContact.getPhone());

        return convertView;
    }
}
