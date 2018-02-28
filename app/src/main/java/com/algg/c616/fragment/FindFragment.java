package com.algg.c616.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.algg.c616.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by 光光 on 2017/11/13.
 */

public class FindFragment extends Fragment {

    private ImageView djs;
    private TextView timeCount;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private Handler mHandler = new Handler();//全局handler
    private int ii = 0;//时间差

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO 加载我的布局文件
        return inflater.inflate(R.layout.fragment_find, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calendar c = Calendar.getInstance(); //获取当前的时间
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        timeCount = (TextView) getActivity().findViewById(R.id.showcount);
        djs = (ImageView) getActivity().findViewById(R.id.s101);
        djs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new TimeCount()).start();//开启线程
            }
        });
        String beginTime = "2018-02-15 20:00:00";//为了符合日期格式yyyy-MM-dd HH:mm:ss 把秒钟加上
        ii = getTimeInterval(beginTime);//获取时间差
    }

    class TimeCount implements Runnable {
        @Override
        public void run() {
            while (ii > 0)//整个倒计时执行的循环
            {
                ii--;
                mHandler.post(new Runnable() //通过它在UI主线程中修改显示的剩余时间
                {
                    public void run() {
                        timeCount.setText(getInterval(ii));//显示剩余时间
                    }
                });
                try {
                    Thread.sleep(1000);//线程休眠一秒钟     这个就是倒计时的间隔时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //下面是倒计时结束逻辑
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    timeCount.setText("狗年大吉。");//一轮倒计时结束  修改剩余时间为一分钟
                }
            });
        }
    }

    /**
     * 获取两个日期的时间差
     */
    public static int getTimeInterval(String data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int interval = 0;
        try {
            Date currentTime = new Date();//获取现在的时间
            Date beginTime = dateFormat.parse(data);
            interval = (int) ((beginTime.getTime() - currentTime.getTime()) / (1000));//时间差 单位秒
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return interval;
    }

    /**
     * 设定显示文字
     */
    public static String getInterval(int interval) {
        String txt = null;
        if (interval >= 0) {
            long day = interval / (24 * 3600);//天
            long hour = interval % (24 * 3600) / 3600;//小时
            long minute = interval % 3600 / 60;//分钟
            long second = interval % 60;//秒

            txt = day + "天" + hour + "时" + minute + "分" + second + "秒";
        }
        return txt;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate(Long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);

        return dateString;
    }
}
