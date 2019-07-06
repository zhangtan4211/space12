package com.example.yuekaomoni;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.yuekaomoni.adapter.mViewPager;
import com.example.yuekaomoni.fragment.Fragment_B;
import com.example.yuekaomoni.fragment.fragment_A;
import com.example.yuekaomoni.fragment.fragment_C;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
    private DrawerLayout draw;
    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    private ImageView iv;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //控件
        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);
        draw = findViewById(R.id.draw);
        iv = findViewById(R.id.iv);
        img=findViewById(R.id.img);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.openDrawer(Gravity.LEFT);
            }
        });

        titles.add("风景");
        titles.add("美女");
        titles.add("动漫卡通");
        titles.add("娱乐明星");
        titles.add("萌宠");

        fragments.add(new fragment_A());
        fragments.add(new Fragment_B());
        fragments.add(new fragment_C());
        fragments.add(new fragment_C());
        fragments.add(new fragment_C());

        mViewPager mViewPager = new mViewPager(getSupportFragmentManager(), fragments, titles);
        vp.setAdapter(mViewPager);
        tab.setupWithViewPager(vp);

        //换头像
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 101);
            }
        });

    }

    //回传头像 , 把头像设置上去
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && data != null) {
            Uri data1 = data.getData();
            Glide.with(MainActivity.this)
                    .load(data1).apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(iv);

        }
    }


}
