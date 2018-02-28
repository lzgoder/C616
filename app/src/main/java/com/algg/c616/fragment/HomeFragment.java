package com.algg.c616.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.algg.c616.R;

import java.text.ParseException;


/**
 * Created by 光光 on 2017/11/13.
 */

public class HomeFragment extends Fragment {

    LinearLayout mu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO 加载首页布局文件
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mu = getActivity().findViewById(R.id.ll_module2);
        mu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                //参数是包名，类全限定名，注意直接用类名不行
                ComponentName cn = new ComponentName("com.example.myapplication666",
                        "com.example.myapplication666.MainActivity");
                intent.setComponent(cn);
                try {
                    startActivity(intent);
                    Toast.makeText(getActivity(), "即将跳转林旭阳的APP", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "还没下载这个APP~", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
