package com.example.firstandroidcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.firstandroidcode.chapter2.Chapter2;
import com.example.firstandroidcode.chapter3.Chapter3;
import com.example.firstandroidcode.chapter5.Chapter5;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String mTAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(mTAG, this.toString());
        findViewById(R.id.main_btn1).setOnClickListener(this);
        findViewById(R.id.main_btn2).setOnClickListener(this);
        findViewById(R.id.main_btn3).setOnClickListener(this);
        findViewById(R.id.main_btn5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_btn1:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                break;
            case R.id.main_btn2:
                Intent intent1 = new Intent(MainActivity.this, Chapter2.class);
                startActivity(intent1);
                //finish();
                break;
            case R.id.main_btn3:
                Intent intent2 = new Intent(MainActivity.this, Chapter3.class);
                startActivity(intent2);
                break;
            case R.id.main_btn5:
                Intent intent4 = new Intent(MainActivity.this, Chapter5.class);
                startActivity(intent4);
                break;
                default:
                    break;
        }
    }
}
