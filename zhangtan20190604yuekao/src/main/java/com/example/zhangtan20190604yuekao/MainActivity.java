package com.example.zhangtan20190604yuekao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText ed_name;
    private EditText ed_pwd;
    private CheckBox ck;
    private Button bt_login;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //控件
        ed_name = findViewById(R.id.ed_name);
        ed_pwd = findViewById(R.id.ed_pwd);
        ck = findViewById(R.id.ck);
        bt_login = findViewById(R.id.bt_login);
        //获取sp对象
        sp = getSharedPreferences("cogfig",MODE_PRIVATE);
        boolean isCheck = sp.getBoolean("isCheck", false);
        ck.setChecked(isCheck);
        //判断是否勾选了自动登录
        if (isCheck){
            String name = sp.getString("name", "");
            String pwd = sp.getString("pwd","");
            ed_name.setText(name);
            ed_pwd.setText(pwd);

        }
        //点击按钮进行登录传值
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入框的值
                String name = ed_name.getText().toString();
                String pwd = ed_pwd.getText().toString();
                //判断是否为空
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(MainActivity.this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    //获取编辑器对象
                    SharedPreferences.Editor edit = sp.edit();

                    if (ck.isChecked()){
                        edit.putString("name",name)
                                .putString("pwd",pwd)
                                .putBoolean("isCheck",false);

                    }else {
                        edit.clear();
                    }
                    edit.commit();
                    //登录
                    startActivity(new Intent(MainActivity.this,Main2Activity.class));
                    finish();

                }


            }
        });





    }
}

























