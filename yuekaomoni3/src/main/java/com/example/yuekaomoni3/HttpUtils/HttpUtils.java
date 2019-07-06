package com.example.yuekaomoni3.HttpUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {

    //网络状态
    public static  boolean isNetWork(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null){
            return networkInfo.isAvailable();
        }
        return false;
    }


    //网络请求
    public static String HttpData(String s){
        try {
            URL url = new URL(s);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            int code = httpURLConnection.getResponseCode();
            if (code==httpURLConnection.HTTP_OK){
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String str = "";
                while ((str=reader.readLine())!=null){
                    builder.append(str);
                }
                reader.close();
                return builder.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
