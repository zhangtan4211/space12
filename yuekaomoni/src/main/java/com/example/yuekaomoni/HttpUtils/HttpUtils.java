package com.example.yuekaomoni.HttpUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.yuekaomoni.MainActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {

    //判断当前网络状况
    public static boolean isNetWork(Context context){
        if (context!=null){
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info!=null){
                Toast.makeText(context, "当前有网!!!", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(context, "当前没有网!!!", Toast.LENGTH_SHORT).show();
            }
        }
        return false;

    }

    //网络请求
    public static String HttpData(String s){
        try {
            URL url = new URL(s);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            int code = connection.getResponseCode();
            if (code==HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String str = "";
                while ((str=reader.readLine())!=null){
                    builder.append(str);
                }
                reader.close();
                return builder.toString();

            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

           return null;
    }

    //异步处

}
