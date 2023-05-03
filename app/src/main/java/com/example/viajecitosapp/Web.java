package com.example.viajecitosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web extends AppCompatActivity {

    private WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        wv1 =(WebView)findViewById(R.id.wv1);
        wv1.setWebViewClient(new WebViewClient());
        wv1.loadUrl(" https://youtu.be/jmnLE9TFJCk");
    }
}