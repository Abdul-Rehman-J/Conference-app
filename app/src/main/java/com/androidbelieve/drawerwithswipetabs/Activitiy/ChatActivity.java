package com.androidbelieve.drawerwithswipetabs.Activitiy;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;

import com.androidbelieve.drawerwithswipetabs.Adapter.ChatAdapter;
import com.androidbelieve.drawerwithswipetabs.Model.Conversation;
import com.androidbelieve.drawerwithswipetabs.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Handler;

/**
 * Created by TOSHIBA on 14.2.2016. Şubat
 * Dont worry !
 */
public class ChatActivity extends CustomActivity {

    private static Handler handler;
    private ArrayList<Conversation> conversationlist;
    private ChatAdapter chatAdapter;
    private EditText editText;
    private String receiver;  //
    private Date lastMessageDate;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        conversationlist = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.list);
        chatAdapter = new ChatAdapter();
        listView.setAdapter(chatAdapter);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setStackFromBottom(true);


    }
}
