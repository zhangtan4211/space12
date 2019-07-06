package com.example.day20190628.HttpUtils;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {
    //单例模式
    private static Utils utils = new Utils();

    private Utils() {

    }


    public static Utils getInstance(){
        return utils;
    }

    public interface callBackData{
        Void data(String s);
    }

    //网络请求,转换流
    public String initData(String s){
        try {
            URL url = new URL(s);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200){
                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String str = "";
                StringBuilder builder = new StringBuilder();
                while ((str = reader.readLine())!=null){
                    builder.append(str);

                }
                reader.close();
                return builder.toString();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
           return "";
    }


}
