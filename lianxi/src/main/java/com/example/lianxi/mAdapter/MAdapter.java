package com.example.lianxi.mAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lianxi.R;
import com.example.lianxi.bean.MyBean;
import com.example.lianxi.utile.Utiles;

import java.util.ArrayList;

/**
 * <p>文件描述：<p>
 * <p>作者：染<p>
 * <p>创建时间：2019/6/15<p>
 * <p>更改时间：2019/6/15<p>
 */
public class MAdapter extends BaseAdapter {

    private ArrayList<MyBean.ResultBean> list;
    private  Utiles utiles = Utiles.getInstance();
    private Context context;


    public MAdapter(ArrayList<MyBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)){
            case 0:
                final  ViewHolder holder;
                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = View.inflate(context,R.layout.list,null);
                    holder.iv = convertView.findViewById(R.id.iv);
                    holder.tv_name = convertView.findViewById(R.id.tv_name);
                    convertView.setTag(holder);
                }else {
                    holder = (ViewHolder)  convertView.getTag();
                }
                    utiles.Img(list.get(position).getMasterPic(), new Utiles.CrakImg() {
                        @Override
                        public void img(Bitmap s) {
                            holder.iv.setImageBitmap(s);
                        }
                    });
                holder.tv_name.setText(list.get(position).getCommodityName());
                break;

            case 1:
                final  ViewHolder1 holder1;
                if (convertView == null) {
                    holder1 = new ViewHolder1();
                    convertView = View.inflate(context,R.layout.view,null);
                    holder1.iv_iv = convertView.findViewById(R.id.iv_iv);
                    holder1.tv_tv = convertView.findViewById(R.id.tv_tv);
                    convertView.setTag(holder1);
                }else {
                    holder1 = (ViewHolder1)  convertView.getTag();
                }
                holder1.tv_tv.setText(list.get(position).getCommodityName());
                utiles.Img(list.get(position).getMasterPic(), new Utiles.CrakImg() {
                    @Override
                    public void img(Bitmap s) {
                        holder1.iv_iv.setImageBitmap(s);
                    }
                });
                break;
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class ViewHolder{
        ImageView iv;
        TextView tv_name;
    }
    class ViewHolder1{
        TextView tv_tv;
        ImageView iv_iv;
    }
}
