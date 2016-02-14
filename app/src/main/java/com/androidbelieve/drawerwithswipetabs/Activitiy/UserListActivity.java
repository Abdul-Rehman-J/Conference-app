package com.androidbelieve.drawerwithswipetabs.Activitiy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TOSHIBA on 14.2.2016. Şubat
 * Dont worry !
 */
public class UserListActivity extends CustomActivity {

    private ArrayList<ParseUser> userList;
    private ParseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();  // TODO: i will create an layout for this

        updateUserStatus(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updateUserStatus(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserList();
    }

    private void updateUserStatus(boolean online) {
        currentUser.put("online", online);
        currentUser.saveEventually();
    }

    private void loadUserList() {

        ParseUser selectedUser = ParseUser.getCurrentUser();

        if (selectedUser == null) {
            Toast.makeText(UserListActivity.this, "Please Login!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(UserListActivity.this, LoginActivity.class));
        } else {
            final ProgressDialog progressDialog = ProgressDialog.show(this, "Loading User..", "Please Wait! ");
            ParseUser.getQuery().whereNotEqualTo("username", currentUser.getUsername()).findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> objects, ParseException e) {
                    progressDialog.dismiss();
                    if (objects != null) {
                        if (objects.size() == 0) {
                            Toast.makeText(UserListActivity.this, "There is no user for chatting!", Toast.LENGTH_LONG).show();
                        }
                        userList = new ArrayList<ParseUser>(objects);
//                        ListView usetListView = findViewById();


                    }
                }
            });

        }
    }
}
