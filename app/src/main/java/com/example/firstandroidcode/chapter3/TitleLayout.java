package com.example.firstandroidcode.chapter3;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.firstandroidcode.R;

public class TitleLayout extends LinearLayout implements View.OnClickListener {
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        findViewById(R.id.title_btn1).setOnClickListener(this);
        findViewById(R.id.title_btn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_btn1:
                ((Activity)getContext()).finish();
                break;
            case R.id.title_btn2:
                Toast.makeText(getContext(),"Edit",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
