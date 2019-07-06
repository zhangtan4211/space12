package com.example.day20190605;

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
    private Button bt_login;
    private SharedPreferences sp;
    private CheckBox ck;

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
        sp = getSharedPreferences("config",MODE_PRIVATE);
        final boolean isCheck = sp.getBoolean("isCheck", false);
        ck.setChecked(isCheck);
        //判断是否勾选了密码
        if (isCheck){
            String name = sp.getString("name", "");
            String pwd = sp.getString("pwd", "");
            ed_name.setText(name);
            ed_pwd.setText(pwd);

        }
        //点击按钮登录 并且存值
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入框的数据
                String name = ed_name.getText().toString();
                String pwd = ed_pwd.getText().toString();
                //非空验证
                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(MainActivity.this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();

                }else {
                    //获取编辑器的对象
                    SharedPreferences.Editor edit = sp.edit();
                    //判断是否勾选,进行存值
                    if (ck.isChecked()){
                        edit.putString("name",name)
                                .putString("pwd",pwd)
                                .putBoolean("isCheck",true);

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
