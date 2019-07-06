package com.example.yuekaomoni2.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yuekaomoni2.HttpUtils.HttpUtils;
import com.example.yuekaomoni2.R;
import com.example.yuekaomoni2.ShowActivity;
import com.example.yuekaomoni2.adapter.MBaseadapter;
import com.example.yuekaomoni2.bean.Bean;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class fragment_A extends Fragment {

    private Banner banner;
    private PullToRefreshListView list_pull;
    //接口
    String path = "http://blog.zhaoliang5156.cn/zixunnew/fengjing?page=1";
    private List<Bean.DataBean.NewsBean> news;
    private MBaseadapter mBaseadapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.frag_a, null);
        list_pull = (PullToRefreshListView)inflate.findViewById(R.id.list_pull);

        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AsyncTask<Void, Void, List<Bean.DataBean.NewsBean>> asyncTask = new AsyncTask<Void, Void, List<Bean.DataBean.NewsBean>>() {

            @Override
            protected List<Bean.DataBean.NewsBean> doInBackground(Void... voids) {

                String s = HttpUtils.HttpData(path);
                Gson gson = new Gson();
                Bean bean = gson.fromJson(s, Bean.class);
                news = bean.getData().getNews();
                return news;
            }

            @Override
            protected void onPostExecute(List<Bean.DataBean.NewsBean> newsBeans) {
                super.onPostExecute(newsBeans);
                banner.setImages(news);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Bean.DataBean.NewsBean beans = (Bean.DataBean.NewsBean) path;
                        Glide.with(getContext())
                                .load("http://blog.zhaoliang5156.cn/zixunnew/"+beans.getImageUrl())
                                .into(imageView);

                    }
                });
                banner.isAutoPlay(true);
                banner.setDelayTime(3000);
                banner.start();
            }
        }.execute();

        list_pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                AsyncTask<Void, Void, List<Bean.DataBean.NewsBean>> asyncTask = new AsyncTask<Void, Void, List<Bean.DataBean.NewsBean>>() {

                    @Override
                    protected List<Bean.DataBean.NewsBean> doInBackground(Void... voids) {
                        String s = HttpUtils.HttpData(path);
                        Gson gson = new Gson();
                        Bean bean = gson.fromJson(s, Bean.class);
                        bean.getData().getNews();
                        return news ;
                    }

                    @Override
                    protected void onPostExecute(List<Bean.DataBean.NewsBean> newsBeans) {
                        super.onPostExecute(newsBeans);
                        mBaseadapter = new MBaseadapter(news,getContext());
                        list_pull.setAdapter(mBaseadapter);
                    }
                }.execute();

                list_pull.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                Toast.makeText(getContext(), "加载成功", Toast.LENGTH_SHORT).show();
                AsyncTask<Void, Void, List<Bean.DataBean.NewsBean>> asyncTask = new AsyncTask<Void, Void, List<Bean.DataBean.NewsBean>>() {

                    private List<Bean.DataBean.NewsBean> newsa;

                    @Override
                    protected List<Bean.DataBean.NewsBean> doInBackground(Void... voids) {
                        String s = HttpUtils.HttpData(path);
                        Gson gson = new Gson();
                        Bean bean = gson.fromJson(s, Bean.class);
                        newsa = bean.getData().getNews();
                        return newsa;
                    }

                    @Override
                    protected void onPostExecute(List<Bean.DataBean.NewsBean> newsBeans) {
                        super.onPostExecute(newsBeans);
                        news.addAll(newsa);
                        mBaseadapter.notifyDataSetChanged();


                    }
                }.execute();
                list_pull.onRefreshComplete();
            }
        });

        //点击条目跳转到网页WebView
        list_pull.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), ShowActivity.class);
                startActivity(intent);
            }
        });

        View inflate = View.inflate(getContext(), R.layout.ban, null);
        //控件
        banner = (Banner)inflate.findViewById(R.id.banner);
        ListView refreshableView = list_pull.getRefreshableView();
        refreshableView.addHeaderView(inflate);
    }
}
