package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar=(Toolbar)findViewById(R.id.toolbar);


        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        Button button= (Button) findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, MainActivity.class);
                startActivity(i);
            }
        });
        TextView textView= (TextView) findViewById(R.id.link_signup);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(login.this,signup.class);
                startActivity(j);
            }
        });
    }


}
