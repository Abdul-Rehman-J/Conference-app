
package com.androidbelieve.drawerwithswipetabs;

        import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ratan on 7/29/2015.
 */
public class fragment_C extends android.support.v4.app.Fragment {

    private MyAdapter myAdapter;
    private ArrayList<Contacts> arrayList; // this is your contacts array

    public fragment_C() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_c, container, false);
        ListView mylistView = (ListView) mainView.findViewById(R.id.listview1);

        prepareContactList();

        myAdapter = new MyAdapter(arrayList, getActivity());
        mylistView.setAdapter(myAdapter);

        return mainView;

    }


    @TargetApi(Build.VERSION_CODES.M)
    public void prepareContactList() {
        arrayList = new ArrayList<>();

        Cursor phones = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {


            //Read Contact Name
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            //Read Phone Number
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


            Contacts mycontact = new Contacts(name, phoneNumber);

            arrayList.add(mycontact);
        }
        phones.close();

    }

}