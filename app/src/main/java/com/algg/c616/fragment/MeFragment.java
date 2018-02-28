package com.algg.c616.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.algg.c616.MessageEvent;
import com.algg.c616.R;
import com.algg.c616.activity.HelpActivity;
import com.algg.c616.activity.MainActivity;
import com.algg.c616.activity.MyActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by 光光 on 2017/11/13.
 */

public class MeFragment extends Fragment {
    private RelativeLayout
            my_me, my_join, my_reds, my_help, my_upload, my_webs, my_setting;
    private Dialog dialog;
    private View inflate;
    private TextView userName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO 加载我的布局文件
        return inflater.inflate(R.layout.fragment_my, null);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        EventBus.getDefault().register(this);//注册观察者


        //RelativeLayout控件
        my_me = getActivity().findViewById(R.id.my_me);
        my_join = getActivity().findViewById(R.id.my_join);
        my_reds = getActivity().findViewById(R.id.my_red);
        my_help = getActivity().findViewById(R.id.my_help);
        my_upload = getActivity().findViewById(R.id.my_upload);
        my_webs = getActivity().findViewById(R.id.my_webs);
        my_setting = getActivity().findViewById(R.id.my_setting);

        userName = getActivity().findViewById(R.id.textView333);
        userName.setText(MainActivity.USER);
        my_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyActivity.class);
                startActivity(intent);
            }
        });

        my_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        my_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });

        my_reds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
                //填充对话框的布局
                inflate = LayoutInflater.from(getActivity()).inflate(R.layout.redpack_layout, null);

                dialog.setContentView(inflate);
                //获取当前Activity所在的窗体
                Window dialogWindow = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity(Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.y = 500;//设置Dialog距离底部的距离
                Toast.makeText(getActivity(), "截图保存一下吧~", Toast.LENGTH_SHORT).show();

//       将属性设置给窗体
                dialogWindow.setAttributes(lp);
                dialog.show();//显示对话框
            }
        });

        my_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin=598128528&card_type=group&source=qrcode";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

        my_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Thread.sleep(20);//线程休眠两秒钟
                    Toast.makeText(getActivity(), "已是最新版本", Toast.LENGTH_SHORT).show();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        my_webs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.community616.top");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);

            }
        });

    }
    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {

        if (messageEvent.getMessage() == "关灯") {

            my_me.setBackgroundResource(R.drawable.colormone);
            my_join.setBackgroundResource(R.drawable.colormone);
            my_reds.setBackgroundResource(R.drawable.colormone);
            my_help.setBackgroundResource(R.drawable.colormone);
            my_upload.setBackgroundResource(R.drawable.colormone);
            my_webs.setBackgroundResource(R.drawable.colormone);
            my_setting.setBackgroundResource(R.drawable.colormone);

        } else if (messageEvent.getMessage() == "开灯") {
            my_me.setBackgroundResource(R.drawable.coloranan);
            my_join.setBackgroundResource(R.drawable.coloranan);
            my_reds.setBackgroundResource(R.drawable.coloranan);
            my_help.setBackgroundResource(R.drawable.coloranan);
            my_upload.setBackgroundResource(R.drawable.coloranan);
            my_webs.setBackgroundResource(R.drawable.coloranan);
            my_setting.setBackgroundResource(R.drawable.coloranan);
        }

    }
}
