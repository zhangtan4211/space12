package com.example.zhangtan20190604yuekao;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.zhangtan20190604yuekao.MyBroad.MyBroad;
import com.example.zhangtan20190604yuekao.adapter.FrAdapter;
import com.example.zhangtan20190604yuekao.fragment.fragment_1;
import com.example.zhangtan20190604yuekao.fragment.fragment_2;
import com.example.zhangtan20190604yuekao.fragment.fragment_3;
import com.example.zhangtan20190604yuekao.service.MyService;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ViewPager vp;
    private RadioGroup rg;
    private ArrayList<Fragment> list;
    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //注册广播
        registerReceiver(new MyBroad(),new IntentFilter("MyCast"));

        //service
        Intent intent = new Intent(Main2Activity.this, MyService.class);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                 MyService.MyBind myBind = (MyService.MyBind)iBinder;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        bindService(intent,connection,BIND_AUTO_CREATE);


        //控件
        vp = findViewById(R.id.vp);
        rg = findViewById(R.id.rg);

        list = new ArrayList<>();
        initData();
        //适配器
        FrAdapter frAdapter = new FrAdapter(getSupportFragmentManager(),list);
        vp.setAdapter(frAdapter);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    private void initData() {
        list.add(new fragment_1());
        list.add(new fragment_2());
        list.add(new fragment_3());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
