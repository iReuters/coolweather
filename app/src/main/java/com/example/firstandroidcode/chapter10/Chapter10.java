package com.example.firstandroidcode.chapter10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstandroidcode.DownloadService;
import com.example.firstandroidcode.MyService;
import com.example.firstandroidcode.R;

public class Chapter10 extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private static final int UPDATE_TEXT = 1;
    private MyService.DownloadBinder downloadBinder;
    private DownloadService.DownloadBinder mDownloadBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter10);
        findViewById(R.id.chapter10_btn1).setOnClickListener(this);
        findViewById(R.id.chapter10_btn2).setOnClickListener(this);
        findViewById(R.id.chapter10_btn3).setOnClickListener(this);
        findViewById(R.id.chapter10_btn4).setOnClickListener(this);
        findViewById(R.id.chapter10_btn5).setOnClickListener(this);
        findViewById(R.id.chapter10_btn6).setOnClickListener(this);
        findViewById(R.id.chapter10_btn7).setOnClickListener(this);
        findViewById(R.id.chapter10_btn8).setOnClickListener(this);
        textView = findViewById(R.id.chapter10_tv1);
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        Log.d("Chapter10", "onCreate: 1");
        bindService(intent, connection, BIND_AUTO_CREATE);
        Log.d("Chapter10", "onCreate: 2");
        if (ContextCompat.checkSelfPermission(Chapter10.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Chapter10.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

    }

    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    textView.setText("Nice to meet you!");
                    break;
            }
        }
    };

   /*private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };*/

   private ServiceConnection connection = new ServiceConnection() {
       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {
           mDownloadBinder = (DownloadService.DownloadBinder) service;
           Log.d("Chapter10", "onServiceConnected: ");
       }

       @Override
       public void onServiceDisconnected(ComponentName name) {
           Log.d("Chapter10", "onServiceDisconnected: I have been killed");
       }
   };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chapter10_btn1:
                new Thread(){
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }.start();
                break;
            case R.id.chapter10_btn2:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.chapter10_btn3:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.chapter10_btn4:
                //Intent bindIntent = new Intent(this, MyService.class);
                //bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.chapter10_btn5:
               // unbindService(connection);
                break;
            case R.id.chapter10_btn6:
                String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                Log.d("Chapter10", "onClick: " + url.lastIndexOf("/"));
                mDownloadBinder.startDownload(url);
                break;
            case R.id.chapter10_btn7:
                mDownloadBinder.pauseDownload();
                break;
            case R.id.chapter10_btn8:
                mDownloadBinder.cancelDownload();
                break;

                default:
                    break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "拒绝授权将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:
                    break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
