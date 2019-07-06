package com.example.day20190625;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText ed_path;
    private WebView wb;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //使用webView,进行一些初始化设置,解决跳转到游览器的问题
        webViewInit();
        //通过webViewSettings对webView进行初始化设置
        webViewSettingInit();
    }

    private void webViewSettingInit() {
        WebSettings settings = wb.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    private void webViewInit() {
        //在本页面内加载网页
        wb.setWebViewClient(new WebViewClient());
        //设置监听
        wb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pb.setVisibility(View.VISIBLE);
                pb.setProgress(newProgress);
                if (newProgress == 100){
                     pb.setVisibility(View.GONE);
                }

            }
        });
    }

    private void initView() {
        ed_path = findViewById(R.id.ed_path);
        pb = findViewById(R.id.pb);
        wb = findViewById(R.id.wb);
    }


    //点击事件,加载网址
    public void load(View view) {
        String trim = ed_path.getText().toString().trim();
        if (TextUtils.isEmpty(trim)){
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
        }
        //加载输入的网址
        wb.loadUrl("http://"+trim);
    }
}
