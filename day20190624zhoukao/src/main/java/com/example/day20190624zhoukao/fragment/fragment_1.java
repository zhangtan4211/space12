package com.example.day20190624zhoukao.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.day20190624zhoukao.HttpUtils.HttpUtils;
import com.example.day20190624zhoukao.R;
import com.example.day20190624zhoukao.adapter.MBaseAdapter;
import com.example.day20190624zhoukao.bean.Bean;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class fragment_1 extends Fragment {
    private HttpUtils httpUtils = HttpUtils.getInstance();
    private View inflate;
    private Banner bn;
    private PullToRefreshListView list_view;
    String s="http://blog.zhaoliang5156.cn/zixunnew/fengjing";
    private List<Bean.DataBean.NewsBean> news;
    private MBaseAdapter mBaseAdapter;
    private List<Bean.DataBean.NewsBean> newas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.frag_1, null);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bn = inflate.findViewById(R.id.bn);
        httpUtils.getData(s, new HttpUtils.CallBackData() {
            @Override
            public void data(String s) {
                Gson gson = new Gson();
                Bean bean = gson.fromJson(s, Bean.class);
                final List<Bean.DataBean.NewsBean> news = bean.getData().getNews();
                bn.setImages(news);
                bn.setDelayTime(2000);
                bn.isAutoPlay(true);
                bn.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Bean.DataBean.NewsBean news = (Bean.DataBean.NewsBean) path;
                        Log.i("NewsBean", news.getImageUrl());
                        Glide.with(context).load(news.getImageUrl()).into(imageView);
                    }
                }).start();
            }
        });


        list_view.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                AsyncTask<Void, Void, List<Bean.DataBean.NewsBean>> asyncTask = new AsyncTask<Void, Void, List<Bean.DataBean.NewsBean>>() {

                    @Override
                    protected List<Bean.DataBean.NewsBean> doInBackground(Void... voids) {
                        String s = httpUtils.initData(fragment_1.this.s);
                        Gson gson=new Gson();
                        Bean bean = gson.fromJson(s, Bean.class);
                        news = bean.getData().getNews();
                        return news;
                    }

                    @Override
                    protected void onPostExecute(List<Bean.DataBean.NewsBean> newsBeans) {
                        super.onPostExecute(newsBeans);
                        mBaseAdapter = new MBaseAdapter(news,getContext());
                        list_view.setAdapter(mBaseAdapter);
                    }
                }.execute();
                list_view.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                Toast.makeText(getContext(), "加载成功", Toast.LENGTH_SHORT).show();
                AsyncTask<Void, Void, List<Bean.DataBean.NewsBean>> asyncTask1 = new AsyncTask<Void, Void, List<Bean.DataBean.NewsBean>>() {

                    @Override
                    protected List<Bean.DataBean.NewsBean> doInBackground(Void... voids) {
                        String s = httpUtils.initData(fragment_1.this.s);
                        Gson gson=new Gson();
                        Bean bean = gson.fromJson(s, Bean.class);
                        newas = bean.getData().getNews();
                        return newas;
                    }

                    @Override
                    protected void onPostExecute(List<Bean.DataBean.NewsBean> newsBeans) {
                        super.onPostExecute(newsBeans);
                        news.addAll(newas);
                        mBaseAdapter.notifyDataSetChanged();
                    }
                }.execute();
                list_view.onRefreshComplete();
            }
        });
    }

    private void initView(View inflate) {
        list_view = (PullToRefreshListView) inflate.findViewById(R.id.list_view);
    }
}
