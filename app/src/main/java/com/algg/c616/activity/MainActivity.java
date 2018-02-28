package com.algg.c616.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.algg.c616.MessageEvent_user;
import com.algg.c616.MyDBHelper;
import com.algg.c616.R;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;


public class MainActivity extends AppCompatActivity {
    //获取控件
    Button register, loginBtn, unlogin;
    EditText userName, passWord;
    //数据库
    private MyDBHelper dbHelper;
    //定义一个静态变量储存用户名（组长用）
    public static String USER = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 设置界面沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_main);

        //加载控件
        initView();
        //创建数据库
        dbHelper = new MyDBHelper(this, "UserStore.db", null, 1);
    }

    //TODO 加载控件
    private void initView() {
        //获取控件并加监听
        register = findViewById(R.id.toRegister);
        register.setOnClickListener(clickListener);
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(clickListener);
        unlogin = findViewById(R.id.unlogin);
        unlogin.setOnClickListener(clickListener);
        //两个输入框
        userName = (EditText) findViewById(R.id.userName0);
        passWord = (EditText) findViewById(R.id.passWord0);
    }

    //TODO 所有按钮监听器
    View.OnClickListener clickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        //登陆
                        case R.id.loginBtn:
                            toLogin();
                            break;
                        //无法登陆
                        case R.id.unlogin:
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this, UnLoginActivity.class);
                            startActivity(intent);
                            break;
                        //注册
                        case R.id.toRegister:
                            intent = new Intent();
                            intent.setClass(MainActivity.this, RegisterActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            };

    //TODO 登陆方法
    private void toLogin() {
        //保存用户名密码
        String username = userName.getText().toString();
        String password = passWord.getText().toString();
        //验证用户名密码是否一致
        if (login(username, password)) {
            USER = username;
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "登陆成功!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(MainActivity.this, "登陆失败!用户名或密码错误~", Toast.LENGTH_SHORT).show();
        }
    }

    //TODO 调用数据库验证用户名密码
    public boolean login(String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "**select * from userData where userName= ? and passWord= ? ";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    //TODO 设置再按一次退出效果
    long lastPressTime = 0;

    public void onBackPressed() {
        if (new Date().getTime() - lastPressTime < 2000) {
            finish();//结束程序
        } else {
            lastPressTime = new Date().getTime();//重置lastPressTime
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
    }
}