package com.androidbelieve.drawerwithswipetabs;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidbelieve.drawerwithswipetabs.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class chatlist extends ActionBarActivity {


    String URL = "http://fractals.in/ioschat/chat.php";
    ListView listView;
    EditText editText;
    Button button;
    TextView textView;
    ImageView imageView;
    String chatText;
    ListViewAdapter adapter;
    JSONParser parser;
    JSONObject json;
    String senduser, recieveuser;
    boolean item1IsChacked = true,
            Item2IsChecked = false;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> chatList = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parser = new JSONParser();
        listView = (ListView)findViewById(R.id.chat_listView);
        editText = (EditText)findViewById(R.id.chat_editText);
        button = (Button)findViewById(R.id.chat_button);

        new ChatListShowAsyncTask().execute();

        // MyTimerTask myTask = new MyTimerTask();
        // Timer myTimer = new Timer();

        // myTimer.schedule(myTask, 3000, 1500);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                chatText = editText.getText().toString();
                if(!chatText.equals("")){
                    new SendChatAsyncTask(getApplicationContext(), chatText).execute();
                    editText.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // new CheckChatAsyncTask().execute();
    }
	/*class MyTimerTask extends TimerTask {
		  public void run() {
			  new ChatListShowAsyncTask().execute();
		  }
		}*/

    class SendChatAsyncTask extends AsyncTask<Void,String, String>
    {
        Context context;
        String chatText;
        int success = 0;
        public SendChatAsyncTask(Context applicationContext, String chatText) {
            this.context = applicationContext;
            this.chatText = chatText;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            try{

                if(item1IsChacked == true)
                {
                    List<NameValuePair> params1 = new ArrayList<NameValuePair>();
                    params1.add(new BasicNameValuePair("tag", "chatmsg"));
                    params1.add(new BasicNameValuePair("touserid", "7"));
                    params1.add(new BasicNameValuePair("fromuserid", "43"));
                    params1.add(new BasicNameValuePair("message", chatText));
                    Log.i("Parameter", params1.toString());
                    try {
                        json = parser.makeHttpRequest(URL, "POST", params1);
                        Log.i("Json", json.toString());
                        success = json.getInt("success");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else if(Item2IsChecked == true)
                {
                    List<NameValuePair> params1 = new ArrayList<NameValuePair>();
                    params1.add(new BasicNameValuePair("tag", "chatmsg"));
                    params1.add(new BasicNameValuePair("touserid", "43"));
                    params1.add(new BasicNameValuePair("fromuserid", "7"));
                    params1.add(new BasicNameValuePair("message", chatText));
                    Log.i("Parameter", params1.toString());
                    try {
                        json = parser.makeHttpRequest(URL, "POST", params1);
                        Log.i("Json", json.toString());
                        success = json.getInt("success");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                //JSONArray jsonArray = json.getJSONArray()

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            if(success == 1)
            {
                try {
                    new ChatListShowAsyncTask().execute();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.selecta:
                item.setChecked(true);
                item1IsChacked = true;
                Item2IsChecked = false;
                Log.i("Status", "Sender"+item1IsChacked);
                Log.i("Status", "Reciever"+Item2IsChecked);
                return true;
            case R.id.selectb:
                item.setChecked(true);
                item1IsChacked = false;
                Item2IsChecked = true;
                Log.i("Status", "Sender"+item1IsChacked);
                Log.i("Status", "Reciever"+Item2IsChecked);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    class ChatListShowAsyncTask extends AsyncTask<Void, String, String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            chatList.clear();
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                Log.i("Start", "ok");/*
            	if(item1IsChacked == true)
            	{*/
                Log.i("lavel", "item1");
                List<NameValuePair> params1 = new ArrayList<NameValuePair>();
                params1.add(new BasicNameValuePair("tag", "oldmess"));
                params1.add(new BasicNameValuePair("touserid", "43"));
                params1.add(new BasicNameValuePair("fromuserid", "7"));
                try {
                    Log.i("Parameter", params1.toString());
                    json = parser.makeHttpRequest(URL, "POST", params1);
                    Log.i("Json", json.toString());
                    JSONArray jsonArray = json.getJSONArray("msgdetails");
                    for (int i = 0;i < jsonArray.length();i++)
                    {
                        String message = jsonArray.getJSONObject(i).getString("message");
                        String id = jsonArray.getJSONObject(i).getString("id");
                        String time = jsonArray.getJSONObject(i).getString("time");
                        String from_userid = jsonArray.getJSONObject(i).getString("from_userid");
                        String to_userid = jsonArray.getJSONObject(i).getString("to_userid");
                        String align = jsonArray.getJSONObject(i).getString("align");
                        String date = jsonArray.getJSONObject(i).getString("date");

                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("message", message);
                        map.put("id", id);
                        map.put("time", time);
                        map.put("from_userid", from_userid);
                        map.put("to_userid", to_userid);
                        map.put("align", align);
                        map.put("date", message);

                        chatList.add(map);

                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            	/*}else if(Item2IsChecked == true){
                	Log.i("lavel", "item2");
            		List<NameValuePair> params1 = new ArrayList<NameValuePair>();
                    params1.add(new BasicNameValuePair("tag", "oldmess"));
                    params1.add(new BasicNameValuePair("touserid", "43"));
                    params1.add(new BasicNameValuePair("fromuserid", "7"));
                    try {
                        Log.i("Parameter", params1.toString());
                        json = parser.makeHttpRequest(URL, "POST", params1);
                        Log.i("Json", json.toString());
                        JSONArray jsonArray = json.getJSONArray("msgdetails");
                        for (int i = 0;i < jsonArray.length();i++)
                        {
                            String message = jsonArray.getJSONObject(i).getString("message");
                            String id = jsonArray.getJSONObject(i).getString("id");
                            String time = jsonArray.getJSONObject(i).getString("time");
                            String from_userid = jsonArray.getJSONObject(i).getString("from_userid");
                            String to_userid = jsonArray.getJSONObject(i).getString("to_userid");
                            String align = jsonArray.getJSONObject(i).getString("align");
                            String date = jsonArray.getJSONObject(i).getString("date");

                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("message", message);
                            map.put("id", id);
                            map.put("time", time);
                            map.put("from_userid", from_userid);
                            map.put("to_userid", to_userid);
                            map.put("align", align);
                            map.put("date", message);

                            chatList.add(map);

                        }

                    }catch(Exception e){
                        e.printStackTrace();
                    }
            	}*/

                /*List<NameValuePair> params1 = new ArrayList<NameValuePair>();
                params1.add(new BasicNameValuePair("tag", "oldmess"));
                params1.add(new BasicNameValuePair("touserid", "34"));
                params1.add(new BasicNameValuePair("fromuserid", "12"));
                try {
                    json = parser.makeHttpRequest(URL, "POST", params1);
                    Log.i("Json", json.toString());
                    JSONArray jsonArray = json.getJSONArray("msgdetails");
                    for (int i = 0;i < jsonArray.length();i++)
                    {
                        String message = jsonArray.getJSONObject(i).getString("message");
                        String id = jsonArray.getJSONObject(i).getString("id");
                        String time = jsonArray.getJSONObject(i).getString("time");
                        String from_userid = jsonArray.getJSONObject(i).getString("from_userid");
                        String to_userid = jsonArray.getJSONObject(i).getString("to_userid");
                        String align = jsonArray.getJSONObject(i).getString("align");
                        String date = jsonArray.getJSONObject(i).getString("date");

                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("message", message);
                        map.put("id", id);
                        map.put("time", time);
                        map.put("from_userid", from_userid);
                        map.put("to_userid", to_userid);
                        map.put("align", align);
                        map.put("date", message);

                        chatList.add(map);

                    }

                }catch(Exception e){
                    e.printStackTrace();
                }*/

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                adapter = new ListViewAdapter(getApplicationContext(), chatText);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }catch(Exception e){

            }


        }
    }

    class CheckChatAsyncTask extends AsyncTask<Void, String, String>
    {
        String sender, reciever;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                if(item1IsChacked == true)
                {
                    List<NameValuePair> params1 = new ArrayList<NameValuePair>();
                    params1.add(new BasicNameValuePair("tag", "tousermsg"));
                    params1.add(new BasicNameValuePair("touserid", "7"));
                    params1.add(new BasicNameValuePair("fromuserid", "43"));
                    params1.add(new BasicNameValuePair("messageid", 0+""));
                    try {
                        Log.i("Parameter", params1.toString());
                        json = parser.makeHttpRequest(URL, "POST", params1);
                        Log.i("Json", json.toString());
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }else if(Item2IsChecked == true)
                {

                    List<NameValuePair> params1 = new ArrayList<NameValuePair>();
                    params1.add(new BasicNameValuePair("tag", "tousermsg"));
                    params1.add(new BasicNameValuePair("touserid", "43"));
                    params1.add(new BasicNameValuePair("fromuserid", "7"));
                    params1.add(new BasicNameValuePair("messageid", 0+""));
                    try {
                        Log.i("Parameter", params1.toString());
                        json = parser.makeHttpRequest(URL, "POST", params1);
                        Log.i("Json", json.toString());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    class ListViewAdapter extends BaseAdapter
    {
        Context context;
        String text;
        public ListViewAdapter(Context context, String text)
        {
            this.text = text;
        }

        @Override
        public int getCount() {
            return chatList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int pos = position;
            if(chatList.get(position).get("align").equals("0"))
            {

                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.list_item_message_left,null);
                textView = (TextView)convertView.findViewById(R.id.txtMsg);
                textView.setText(chatList.get(position).get("message"));
                //imageView = (ImageView)convertView.findViewById(R.id.imgMsg);
                //imageView.setImageResource(R.drawable.client);
            }else{

                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.list_item_message_right,null);
                textView = (TextView)convertView.findViewById(R.id.txtMsg);
                textView.setText(chatList.get(position).get("message"));
                //imageView = (ImageView)convertView.findViewById(R.id.imgMsg);
                // imageView.setImageResource(R.drawable.client);
            }
            return convertView;
        }
    }


}
