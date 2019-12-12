package com.example.firstandroidcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.firstandroidcode.chapter10.Chapter10;
import com.example.firstandroidcode.chapter11.Chapter11;
import com.example.firstandroidcode.chapter12.Chapter12;
import com.example.firstandroidcode.chapter13.Chapter13;
import com.example.firstandroidcode.chapter2.Chapter2;
import com.example.firstandroidcode.chapter3.Chapter3;
import com.example.firstandroidcode.chapter5.Chapter5;
import com.example.firstandroidcode.chapter6.Chapter6;
import com.example.firstandroidcode.chapter7.Chapter7;
import com.example.firstandroidcode.chapter8.Chapter8;
import com.example.firstandroidcode.chapter9.Chapter9;

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
        findViewById(R.id.main_btn6).setOnClickListener(this);
        findViewById(R.id.main_btn7).setOnClickListener(this);
        findViewById(R.id.main_btn8).setOnClickListener(this);
        findViewById(R.id.main_btn9).setOnClickListener(this);
        findViewById(R.id.main_btn10).setOnClickListener(this);
        findViewById(R.id.main_btn11).setOnClickListener(this);
        findViewById(R.id.main_btn12).setOnClickListener(this);
        findViewById(R.id.main_btn13).setOnClickListener(this);
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
            case R.id.main_btn6:
                Intent intent5 = new Intent(MainActivity.this, Chapter6.class);
                startActivity(intent5);
                break;
            case R.id.main_btn7:
                Intent intent6 = new Intent(MainActivity.this, Chapter7.class);
                startActivity(intent6);
                break;
            case R.id.main_btn8:
                Intent intent7 = new Intent(MainActivity.this, Chapter8.class);
                startActivity(intent7);
                break;
            case R.id.main_btn9:
                Intent intent8 = new Intent(MainActivity.this, Chapter9.class);
                startActivity(intent8);
                break;
            case R.id.main_btn10:
                Intent intent9 = new Intent(MainActivity.this, Chapter10.class);
                startActivity(intent9);
                break;
            case R.id.main_btn11:
                Intent intent10 = new Intent(MainActivity.this, Chapter11.class);
                startActivity(intent10);
                break;
            case R.id.main_btn12:
                Intent intent11 = new Intent(MainActivity.this, Chapter12.class);
                startActivity(intent11);
                break;
            case R.id.main_btn13:
                Intent intent12 = new Intent(MainActivity.this, Chapter13.class);
                startActivity(intent12);
                break;
                default:
                    break;
        }
    }
}
