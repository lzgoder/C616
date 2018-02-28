package com.algg.c616.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.algg.c616.R;

public class HelpActivity extends AppCompatActivity {

    private Button gogogo,nonono;
    private EditText addc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_help);

        gogogo=findViewById(R.id.gogogo);
        nonono=findViewById(R.id.nonono);
        addc=findViewById(R.id.add_content);
        gogogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HelpActivity.this, "反馈成功~ThankYou~", Toast.LENGTH_SHORT).show();
            }
        });
        nonono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addc.setText("");
            }
        });

    }
}
