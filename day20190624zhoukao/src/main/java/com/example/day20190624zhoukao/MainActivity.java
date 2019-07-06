package com.example.day20190624zhoukao;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.day20190624zhoukao.adapter.MyAdapter;
import com.example.day20190624zhoukao.fragment.fragment_1;
import com.example.day20190624zhoukao.fragment.fragment_2;
import com.example.day20190624zhoukao.fragment.fragment_3;
import com.example.day20190624zhoukao.fragment.fragment_4;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ViewPager vp;
    private RadioGroup rg;
    private ArrayList<Fragment> list;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //创建集合
        list = new ArrayList();
        //加载页面
        initData();
        //适配器
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(),list);
        vp.setAdapter(myAdapter);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Toast.makeText(MainActivity.this, "侧拉菜单打开了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Toast.makeText(MainActivity.this, "侧拉菜单关闭了", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        //换图像
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,101);
            }
        });

    }


    //回传头像
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101&&data!=null){
            Uri data1 = data.getData();
            Glide.with(MainActivity.this)
                    .load(data1).apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(iv);
        }
    }

    private void initData() {
        list.add(new fragment_1());
        list.add(new fragment_2());
        list.add(new fragment_3());
        list.add(new fragment_4());
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.draw);
        vp = findViewById(R.id.vp);
        rg = findViewById(R.id.rg);
        iv = findViewById(R.id.iv);
    }
}
