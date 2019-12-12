package com.example.firstandroidcode.chapter13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.firstandroidcode.R;

public class Chapter13 extends AppCompatActivity implements View.OnClickListener {

    private static final String CANCEL_ALARM_TASK = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter13);
        findViewById(R.id.chapter13_btn1).setOnClickListener(this);
        findViewById(R.id.chapter13_btn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           case  R.id.chapter13_btn1:
               Intent intent = new Intent(this, LongRunningService.class);
               startService(intent);
                break;
            case R.id.chapter13_btn2:
                Intent intent1 = new Intent(this, LongRunningService.class);
                intent1.putExtra("Cancel Alarm Task", CANCEL_ALARM_TASK);
                Log.d("TAG", "onClick: STOP");

                stopService(intent1);
                break;


                default:
                    break;
        }

    }
}
