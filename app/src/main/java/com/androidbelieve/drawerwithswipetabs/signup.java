package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class signup extends AppCompatActivity {
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        toolbar=(Toolbar)findViewById(R.id.toolbar);


        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        Button button= (Button) findViewById(R.id.btn_signup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });
        TextView textView= (TextView) findViewById(R.id.link_login);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signup.this, login.class);
                startActivity(i);
            }
        });

    }

}
