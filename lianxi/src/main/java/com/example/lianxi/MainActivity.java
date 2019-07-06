package com.example.lianxi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.lianxi.bean.MyBean;
import com.example.lianxi.mAdapter.MAdapter;
import com.example.lianxi.utile.Utiles;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private XListView lv;
    private  Utiles utiles = Utiles.getInstance();
    private  int pag=1;
    private String url="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=%E6%9D%BF%E9%9E%8B&page=1&count=";
    private List<MyBean.ResultBean> list=new ArrayList<>();
    private MAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        lv.setPullLoadEnable(true);
        lv.setPullRefreshEnable(true);

        lv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                pag=1;

                initData();
                lv.stopRefresh();

            }

            @Override
            public void onLoadMore() {
                pag+=1;

                initData();
                lv.stopLoadMore();

            }
        });


    }
    private void initView() {
        lv = (XListView) findViewById(R.id.lv);

    }
    private void initData() {
        utiles.initData(url+pag, new Utiles.CrakWork() {
            @Override
            public void data(String s) {
                Log.e("initData",s);
                Gson gson = new Gson();
                MyBean myBean = gson.fromJson(s, MyBean.class);
                list = myBean.getResult();
                Log.e("TAG", "data: "+list.toString() );
                mAdapter = new MAdapter((ArrayList<MyBean.ResultBean>) list, MainActivity.this);
                lv.setAdapter(mAdapter);

            }
        });
    }
}
