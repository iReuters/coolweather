package com.example.firstandroidcode.chapter2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.firstandroidcode.R;

import javax.xml.transform.Result;

public class Chapter2_1 extends AppCompatActivity {

    private static final String mTAG = "Chapter2_1";
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter2_1);
        editText =findViewById(R.id.chapter2_1_et1);
        findViewById(R.id.chapter2_1_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String returnData = editText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("data_return",returnData);
                setResult(RESULT_OK,intent);
                Log.d(mTAG, "onClick: " + returnData);
                finish();
            }
        });
    }
}
