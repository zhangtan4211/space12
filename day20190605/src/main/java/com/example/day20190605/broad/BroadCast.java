package com.example.day20190605.broad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "我是张坦,我收到广播啦", Toast.LENGTH_SHORT).show();
    }
}
