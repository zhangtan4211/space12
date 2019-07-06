package com.example.lianxi.utile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;

/**
 * <p>文件描述：<p>
 * <p>作者：染<p>
 * <p>创建时间：2019/6/15<p>
 * <p>更改时间：2019/6/15<p>
 */
public class Utiles {

    private static Utiles utiles = new Utiles();

    private Utiles(){
    }
    public static Utiles getInstance(){
        return utiles;
    }

    public  interface CrakWork{
        void data(String s);
    }

    public  interface CrakImg{
        void img(Bitmap s);
    }
    public  String BandData(String s){
         HttpURLConnection urlConnection=null;
        try {
            URL url = new URL(s);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == urlConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String temp="";
                StringBuffer stringBuffer = new StringBuffer();

                while ((temp=bufferedReader.readLine())!=null){
                    stringBuffer.append(temp);
                }
                bufferedReader.close();
                return  stringBuffer.toString();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        urlConnection.disconnect();
        return "";
    }


    public  void initData(String s,final CrakWork crakWork){
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return BandData(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                crakWork.data(s);
            }
        }.execute(s);
    }

    //图片
    public  Bitmap WorkImg(String s){
        Log.e("WorkImg",s);
        Bitmap bitmap=null;
        HttpURLConnection urlConnection=null;
        try {
            URL url = new URL(s);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == urlConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();

                bitmap= BitmapFactory.decodeStream(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
    }
    urlConnection.disconnect();
        return bitmap;
    }
    public void Img( String s, final CrakImg crakImg){
        new AsyncTask<String, Integer, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                return WorkImg(strings[0]);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                crakImg.img(bitmap);
            }
        }.execute(s);
    }
}
