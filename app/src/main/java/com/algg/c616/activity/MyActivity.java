package com.algg.c616.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.algg.c616.MessageEvent_user;
import com.algg.c616.MyDBHelper;
import com.algg.c616.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MyActivity extends AppCompatActivity {
    RelativeLayout rt;
    TextView username, password, ninkname, userID;
    MyDBHelper dbHelper;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_my);

     /*   Toast.makeText(MyActivity.this,"" +MainActivity.USER, Toast.LENGTH_SHORT).show();*/
        dbHelper = new MyDBHelper(this, "UserStore.db", null, 1);
        rt = findViewById(R.id.return616);
        username = findViewById(R.id.my_user_name);
        password = findViewById(R.id.my_user_password);
        ninkname = findViewById(R.id.my_user_nname);
        userID = findViewById(R.id.my_user_id);

        username.setText(MainActivity.USER);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select * from userData where userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{(String) username.getText()});
        if (cursor.moveToNext()) {
            password.setText(cursor.getString(2));
            userID.setText(cursor.getString(0));
            username.setText(cursor.getString(1));
            ninkname.setText(cursor.getString(3));
        }
        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
