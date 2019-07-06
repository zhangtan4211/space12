package com.example.zhangtan20190604yuekao.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.zhangtan20190604yuekao.R;

import java.sql.Connection;

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        MediaPlayer mediaPlayer = MediaPlayer.create(MyService.this, R.raw.a);
        mediaPlayer.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        MyBind myBind = new MyBind();
        return myBind;
    }

    public class MyBind extends Binder{

    }


}
