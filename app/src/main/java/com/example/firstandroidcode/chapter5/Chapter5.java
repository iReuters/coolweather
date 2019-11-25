package com.example.firstandroidcode.chapter5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firstandroidcode.MyReceiver;
import com.example.firstandroidcode.R;

public class Chapter5 extends AppCompatActivity implements View.OnClickListener {

    private static final String mTAG = "Chapter5";
    private IntentFilter intentFilter1;
    private IntentFilter intentFilter2;
    private NetworkChangeReceiver networkChangeReceiver;
    private MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter5);
        intentFilter1 = new IntentFilter();
        intentFilter1.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter1);
        findViewById(R.id.chapter5_btn1).setOnClickListener(this);
        findViewById(R.id.chapter5_btn2).setOnClickListener(this);
        intentFilter2 = new IntentFilter("aaa");
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver,intentFilter2);
    }

    @Override

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
        unregisterReceiver(myReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chapter5_btn1:
                Intent intent = new Intent("aaa");
                sendBroadcast(intent);
                Log.d(mTAG, "onClick: ");
                break;
        }
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context,"network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context,"network is unavailable", Toast.LENGTH_SHORT).show();
            }

        }
    }
}


