package com.example.yuekaomoni2;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.yuekaomoni2.HttpUtils.HttpUtils;
import com.example.yuekaomoni2.adapter.mViewPager;
import com.example.yuekaomoni2.fragment.fragment_A;
import com.example.yuekaomoni2.fragment.fragment_B;
import com.example.yuekaomoni2.fragment.fragment_C;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout draw;
    private ViewPager viewPager;
    private TabLayout tab;
    private ImageView iv;
    //创建集合
    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //控件
        draw = findViewById(R.id.draw);
        viewPager = findViewById(R.id.viewpager);
        tab = findViewById(R.id.tab);
        iv = findViewById(R.id.iv);

        //判断有无网络
        boolean netWork = HttpUtils.isNetWork(MainActivity.this);
        if(netWork!=false){
            Toast.makeText(this, "当前网络良好", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "当前无网络", Toast.LENGTH_SHORT).show();
        }

        //添加TabLayout选项卡
        titles.add("风景");
        titles.add("美女");
        titles.add("动漫卡通");
        titles.add("娱乐明星");
        titles.add("萌宠");

        //加载fragmen
        fragments.add(new fragment_A());
        fragments.add(new fragment_B());
        fragments.add(new fragment_C());
        fragments.add(new fragment_C());
        fragments.add(new fragment_C());

        //适配器
        mViewPager mViewPager = new mViewPager(getSupportFragmentManager(),fragments,titles);

        //设置适配器
        viewPager.setAdapter(mViewPager);

        //将ViewPager和TabLayout关联起来
        tab.setupWithViewPager(viewPager);

        //换头像
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,101);
            }
        });

    }

    //回传头像
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101 && data!=null){
            Uri data1 = data.getData();
            Glide.with(MainActivity.this)
                    .load(data1).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv);

        }

    }

    //点击左上角图片打开侧拉界面
    public void img(View view) {
        draw.openDrawer(Gravity.LEFT);
    }
}
