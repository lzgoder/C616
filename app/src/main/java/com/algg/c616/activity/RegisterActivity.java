package com.algg.c616.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.algg.c616.R;
import com.algg.c616.MyDBHelper;

public class RegisterActivity extends AppCompatActivity {
    //控件
    Button rett, regiest;
    EditText nickName, userName, passWord, passWord2;
    //数据库
    private MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        dbHelper = new MyDBHelper(this, "UserStore.db", null, 1);
        initView();
    }

    //TODO 加载布局并加监听
    private void initView() {
        rett = findViewById(R.id.ret);
        rett.setOnClickListener(clickListener);
        regiest = findViewById(R.id.register);
        regiest.setOnClickListener(clickListener);
        nickName = (EditText) findViewById(R.id.nickName);
        userName = (EditText) findViewById(R.id.userName);
        passWord = (EditText) findViewById(R.id.passWord);
        passWord2 = findViewById(R.id.passWord2);
    }

    //TODO 所有按钮监听器
    View.OnClickListener clickListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        //注册按钮
                        case R.id.register:
                            add();
                            break;
                        //取消按钮
                        case R.id.ret:
                            finish();
                            break;
                    }
                }
            };

    //TODO    注册方法
    public void add() {
        //获取用户名输入的数据
        String nickname = nickName.getText().toString();
        String username = userName.getText().toString();
        String password = passWord.getText().toString();
        String password2 = passWord2.getText().toString();
        //判断用户输入两个密码是否一致和非空判断等
        if (password2.equals(password) && nickName != null) {
            //检查用户名是否存在
            if (CheckIsDataAlreadyInDBorNot(username)) {
                Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
            } else {
                //用户名不存在，可以注册，执行注册方法
                if (register(username, password, nickname)) {
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        } else {
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        }

    }

    //检验用户名是否已存在
    public boolean CheckIsDataAlreadyInDBorNot(String value) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String Query = "Select * from userData where userName = ? ";
        Cursor cursor = db.rawQuery(Query, new String[]{value});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    //向数据库插入数据  即注册方法
    public boolean register(String username, String password, String nickname) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userName", username);
        values.put("passWord", password);
        values.put("nickName", nickname);
        db.insert("userData", null, values);
        db.close();
        return true;
    }
}

