package com.example.yuekaomoni3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main2Activity extends AppCompatActivity {

    private WebView web_view;
    String s = "http://www.cctv.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //控件
        web_view = (WebView) findViewById(R.id.web_view);

        web_view.loadUrl(s);
        web_view.setWebViewClient(new WebViewClient());
    }
}
