package com.example.day20190603.fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.VpnService;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.day20190603.R;
import com.example.day20190603.adapter.MyAdapter;
import com.example.day20190603.bean.News;
import com.example.day20190603.bean.NewsBean;
import com.example.day20190603.helper.MyHelper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class fragment_1 extends Fragment {

    private ListView lv;
    private SharedPreferences sp;
    private SQLiteDatabase db;
    private String table = "news";
    private long insert;
    private  int flag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.frag_1,null);

        //控件
        lv = inflate.findViewById(R.id.lv);
        //获取sp对象
        sp = getActivity().getSharedPreferences("cogfig",Context.MODE_PRIVATE);
        boolean isInsert = sp.getBoolean("isInsert", true);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getActivity().sendBroadcast(new Intent("mycast"));
            }
        });


        //获取数据库对象;
        db = new MyHelper(getContext()).getWritableDatabase();
        if (isInsert){
            String json = readCard();
            Log.i("aaa","读取出来的集合: "+json);
            //解析json
            ArrayList<News> list = jsonParse(json);
            Log.i("aaa","解析出来的集合: "+list.toString());

            //添加,存入数据库
            for (int i=0;i<list.size();i++){
                ContentValues values = new ContentValues();
                values.put("title",list.get(i).getTitle());
                values.put("description",list.get(i).getDescription());
                insert = db.insert(table, null, values);


            }
            if (insert>0){
                sp.edit().putBoolean("isInsert",true).commit();

            }


        }
        //查询数据库
        final ArrayList<News> news =  selectTable();
        Log.i("aaa","查询出来的集合"+news.toString());
        //展示
        final MyAdapter myAdapter = new MyAdapter(getContext(),news);
        lv.setAdapter(myAdapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int flag = i;
                //对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("提示");
                builder.setMessage("您确定要删除么");

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int delete = db.delete(table, "title=?", new String[]{news.get(flag).getTitle()});
                        if (delete>0){
                            news.remove(flag);
                            myAdapter.notifyDataSetChanged();

                        }
                    }
                });

                builder.setNegativeButton("取消",null);
                builder.show();
                return true;
            }
        });


        return inflate;
    }

    private ArrayList<News> selectTable() {
        //查询数据的集合
        ArrayList<News> slist = new ArrayList();
        Cursor cursor = db.query(table, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            slist.add(new News(title,description));

        }
        //关闭资源
        cursor.close();
        return slist;
    }



    private ArrayList<News> jsonParse(String json) {
        Gson gson = new Gson();
        NewsBean newsBean = gson.fromJson(json, NewsBean.class);
         return  newsBean.getList().getArticles();

    }

    private String readCard() {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/mydata/newsList.json");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String str = "";
            StringBuilder builder = new StringBuilder();
            while ((str = reader.readLine())!=null){
                builder.append(str);

            }
            reader.close();
            return  builder.toString();



        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
