package com.example.day20190624zhoukao.HttpUtils;


import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//工具类
public class HttpUtils {


   private static HttpUtils httpUtils=new HttpUtils();

    private HttpUtils() {
    }
    public static HttpUtils getInstance() {
        return httpUtils;
    }
    public interface CallBackData{
       void data(String s);
    }

   public String initData(String s){
       HttpURLConnection connection=null;
       try {
           URL url=new URL(s);
         connection= (HttpURLConnection) url.openConnection();
           int responseCode = connection.getResponseCode();
           if (responseCode==200){
               InputStream inputStream = connection.getInputStream();
               BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
               String str="";
               StringBuilder stringBuilder=new StringBuilder();
               while ((str=reader.readLine())!=null){
                   stringBuilder.append(str);
               }
               reader.close();
               return stringBuilder.toString();
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       connection.disconnect();
       return"";
   }
   public void getData(String s, final CallBackData callBackData){
       new AsyncTask<String, Integer, String>() {
           @Override
           protected String doInBackground(String... strings) {
               return initData(strings[0]);
           }

           @Override
           protected void onPostExecute(String s) {
               super.onPostExecute(s);
               callBackData.data(s);
           }
       }.execute(s);

   }


}
