package com.example.day20190605.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.day20190605.R;
import com.example.day20190605.bean.News;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<News> list;

    public MyAdapter(Context context, ArrayList<News> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertview==null){
            convertview = View.inflate(context, R.layout.news_item, null);
            holder = new ViewHolder();
            holder.tv_title = convertview.findViewById(R.id.tv_title);
            holder.tv_description = convertview.findViewById(R.id.tv_description);
            convertview.setTag(holder);


        }else {
            holder = (ViewHolder) convertview.getTag();
        }
        holder.tv_title.setText(list.get(i).getTitle());
        holder.tv_description.setText(list.get(i).getDescription());

        return convertview;
    }



    class ViewHolder{
        private TextView tv_title;
        private  TextView tv_description;
    }
}
