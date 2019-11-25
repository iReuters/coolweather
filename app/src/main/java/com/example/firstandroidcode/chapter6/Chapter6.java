package com.example.firstandroidcode.chapter6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstandroidcode.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Chapter6 extends AppCompatActivity implements View.OnClickListener {

    private EditText editText1;
    private EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter6);
        findViewById(R.id.chapter6_btn1).setOnClickListener(this);
        findViewById(R.id.chapter6_btn2).setOnClickListener(this);
        findViewById(R.id.chapter6_btn3).setOnClickListener(this);
        findViewById(R.id.chapter6_btn4).setOnClickListener(this);
        editText1 = findViewById(R.id.chapter6_et1);
        editText2 = findViewById(R.id.chapter6_et2);
    }
    public void save(String inputText) {

        FileOutputStream fileOutputStream;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = openFileOutput("data", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(inputText);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public String load() {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader = null;
        StringBuilder content = new StringBuilder();
        String line = "";


        try {
            fileInputStream = openFileInput("data");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chapter6_btn1:
                String inputText = editText1.getText().toString();
                save(inputText);
                Toast.makeText(Chapter6.this,inputText + " 保存成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chapter6_btn2:
                String outputText;
                outputText = load();
                editText2.setText(outputText);
                Toast.makeText(Chapter6.this,outputText + " 读取成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chapter6_btn3:
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("data", editText1.getText().toString());
                editor.apply();
                break;
            case R.id.chapter6_btn4:
                SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                String data = preferences.getString("data", "数据不存在");
                Toast.makeText(Chapter6.this, data, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
