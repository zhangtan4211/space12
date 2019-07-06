package com.example.day20190624zhoukao.adapter;

import android.content.Context;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day20190624zhoukao.R;
import com.example.day20190624zhoukao.bean.Bean;

import java.util.List;

public class MBaseAdapter extends BaseAdapter {
    private List<Bean.DataBean.NewsBean> list;
    private Context context;

    public MBaseAdapter(List<Bean.DataBean.NewsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertview==null){
            convertview = View.inflate(context, R.layout.news_item, null);
            holder = new ViewHolder();
            holder.img_view=convertview.findViewById(R.id.img_view);
            holder.tv_title = convertview.findViewById(R.id.tv_title);
            holder.tv_description = convertview.findViewById(R.id.tv_description);
            convertview.setTag(holder);


        }else {
            holder = (ViewHolder) convertview.getTag();
        }
        Glide.with(context).load(list.get(i).getImageUrl()).into(holder.img_view);
        holder.tv_title.setText(list.get(i).getTitle());
        holder.tv_description.setText(list.get(i).getPublishAt());

        return convertview;
    }




    class ViewHolder{
        private ImageView img_view;
        private TextView tv_title;
        private  TextView tv_description;
    }
}
