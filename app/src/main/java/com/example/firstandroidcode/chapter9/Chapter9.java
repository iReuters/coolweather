package com.example.firstandroidcode.chapter9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstandroidcode.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Chapter9 extends AppCompatActivity implements View.OnClickListener {

    private static final String mTAG = "Chapter9";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter9);
        findViewById(R.id.chapter9_btn1).setOnClickListener(this);
        findViewById(R.id.chapter9_btn2).setOnClickListener(this);
        findViewById(R.id.chapter9_btn3).setOnClickListener(this);
        textView = findViewById(R.id.chapter9_tv1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chapter9_btn1:
                Intent intent = new Intent(Chapter9.this, Chapter9_1.class);
                startActivity(intent);
                break;
            case R.id.chapter9_btn2:
                sendRequestWithHttpURLConnection();
                break;
            case R.id.chapter9_btn3:
                sendResquestWithOkHttp();
                break;
                default:
                    break;
        }

    }

    private void sendRequestWithHttpURLConnection() {
        Log.d(mTAG, "sendRequestWithHttpURLConnection: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
               /* HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());
                    Log.d(mTAG, response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
*/

               try {
                   OkHttpClient client = new OkHttpClient();
                   Request request = new Request.Builder()
                           .url("http://www.baidu.com")
                           .build();
                   Response response = client.newCall(request).execute();
                   String responseData = response.body().string();
                   showResponse(responseData);
               } catch (Exception e) {
                   e.printStackTrace();
               }
            }
        }) .start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(response);
            }
        });

    }
    private void sendResquestWithOkHttp() {
        new Thread(){
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2/get_data.xml")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private void parseXMLWithPull(String xmlData) {

    }
}
