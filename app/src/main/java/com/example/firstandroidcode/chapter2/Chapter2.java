package com.example.firstandroidcode.chapter2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.firstandroidcode.R;

public class Chapter2 extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    private TextView textView;
   private static final String mTAG = "Chapter1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter2);
        textView = findViewById(R.id.chapter2_tv1);
        findViewById(R.id.chapter2_btn1).setOnClickListener(this);
        findViewById(R.id.chapter2_btn2).setOnClickListener(this);
        findViewById(R.id.chapter2_btn3).setOnClickListener(this);
        findViewById(R.id.chapter2_btn4).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                break;
            case R.id.add_remove:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chapter2_btn1:
                PopupMenu popupMenu = new PopupMenu(this,v);
                MenuInflater menuInflater = new MenuInflater(this);
                menuInflater.inflate(R.menu.main,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(this);
                popupMenu.show();
                break;
            case R.id.chapter2_btn2:
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                break;
            case R.id.chapter2_btn3:
                Intent intent1 = new Intent(Chapter2.this,Chapter2_1.class);
                startActivityForResult(intent1,1);
                break;
            case R.id.chapter2_btn4:
                Intent intent2 = new Intent(Chapter2.this,Chapter2_2.class);
                startActivityForResult(intent2,1);
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String getReturnData = null;
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK){
                    if (data != null) {
                        getReturnData = data.getStringExtra("data_return");
                        textView.setText(getReturnData);
                    }
                     else Log.d(mTAG, "onActivityResult: " + "data = null");
                }

                break;
                default:
        }
        Log.d(mTAG, "onActivityResult: " + getReturnData);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(mTAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(mTAG, "onStop: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(mTAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(mTAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(mTAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(mTAG, "onRestart: ");
    }
}
