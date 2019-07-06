package com.example.zhangtan20190604yuekao.MyBroad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroad extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "我是张坦,我收到广播啦", Toast.LENGTH_SHORT).show();
    }
}
