package com.androidbelieve.drawerwithswipetabs.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by TOSHIBA on 14.2.2016. Şubat
 * Dont worry !
 */
public class UserListAdapter extends BaseAdapter {

    private static int USERLIST_ROW = 0; //

    private ArrayList<ParseUser> parseUsers;
    private Activity activity;

    public UserListAdapter(ArrayList<ParseUser> arrayList, Activity activity) {
        this.parseUsers = arrayList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return parseUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return parseUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {

        //TODO : i am gonna work in there for different layouts types
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View cell = convertView;
//        if (cell == null){
//            LayoutInflater layoutInflater =
//        }

        // TODO : i am gonna work in there
        return null;
    }
}
