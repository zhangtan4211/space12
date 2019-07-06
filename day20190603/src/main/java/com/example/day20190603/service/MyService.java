package com.example.day20190603.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.day20190603.R;


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
