package com.example.yuekaomoni2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShowActivity extends AppCompatActivity {

    private WebView web_view;
    private String urls="http://news.cctv.com/2019/07/01/ARTIhiYZ9A89l02zXl9Lj3uu190701.shtml?spm=C96370.PsikHJQ1ICOX.EKuioXvJBBSD.1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();

        web_view.loadUrl(urls);
        web_view.setWebViewClient(new WebViewClient());
    }

    private void initView() {
        web_view = (WebView) findViewById(R.id.web_view);
    }
}
