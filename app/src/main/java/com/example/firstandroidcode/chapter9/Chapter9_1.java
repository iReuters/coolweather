package com.example.firstandroidcode.chapter9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.firstandroidcode.R;

public class Chapter9_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter9_1);
        final WebView webView = findViewById(R.id.chapter9_wv1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    if(url.startsWith("baiduboxapp://")){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        Toast.makeText(Chapter9_1.this, url,Toast.LENGTH_SHORT ).show();
                        startActivity(intent);
                        return true;


                    }
                } catch (Exception e) {
                    return false;
                }
                webView.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("http://www.baidu.com");
    }
}
