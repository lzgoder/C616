package com.algg.c616.activity;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.algg.c616.MessageEvent;
import com.algg.c616.R;
import com.algg.c616.fragment.FindFragment;
import com.algg.c616.fragment.HomeFragment;
import com.algg.c616.fragment.MeFragment;
import com.algg.c616.fragment.TeamFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    // 底部菜单4个RelativeLayout
    private RelativeLayout R_home, R_find, R_team, R_my;

    // 底部菜单4个ImageView
    private ImageView h1, h2,h616, h4, h5;

    // 底部菜单4个菜单标题
    private TextView h1_2, h2_2, h4_2, h5_2;
    //中间内容区域
    private ViewPager viewPager;

    private ArrayList<Fragment> fragmentList;
    private View inflate;
    //顶部弹出按钮
    private LinearLayout mone, gg;

    private LinearLayout bg_home3;

    private Dialog dialog;
    boolean bl = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_home);
        initView();
        initViewPager();

    }

    long lastPressTime = 0;

    public void onBackPressed() {
        if (new Date().getTime() - lastPressTime < 2000) {
            finish();//结束程序
        } else {
            lastPressTime = new Date().getTime();//重置lastPressTime
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        }
    }

    public void initView() {

        // 底部菜单4个Relativelayout
        this.R_home = (RelativeLayout) findViewById(R.id.h1);
        this.R_find = (RelativeLayout) findViewById(R.id.h2);
        this.R_team = (RelativeLayout) findViewById(R.id.h4);
        this.R_my = (RelativeLayout) findViewById(R.id.h5);
        h616=   (ImageView) findViewById(R.id.home_66166);

        bg_home3=findViewById(R.id.home_3);

        // 底部菜单4个ImageView
        this.h1 = (ImageView) findViewById(R.id.h1_1);
        this.h2 = (ImageView) findViewById(R.id.h2_1);
        this.h4 = (ImageView) findViewById(R.id.h4_1);
        this.h5 = (ImageView) findViewById(R.id.h5_1);


        // 底部菜单4个菜单标题
        this.h1_2 = (TextView) findViewById(R.id.h1_2);
        this.h2_2 = (TextView) findViewById(R.id.h2_2);
        this.h4_2 = (TextView) findViewById(R.id.h4_2);
        this.h5_2 = (TextView) findViewById(R.id.h5_2);
        R_home.setOnClickListener(this);
        R_find.setOnClickListener(this);
        R_team.setOnClickListener(this);
        R_my.setOnClickListener(this);


    }

    public void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.vp_content);
        fragmentList = new ArrayList<Fragment>();

        HomeFragment homeFragment = new HomeFragment();
        FindFragment findFragment = new FindFragment();
        TeamFragment teamFragment = new TeamFragment();
        MeFragment myFragment = new MeFragment();

        fragmentList.add(homeFragment);
        fragmentList.add(findFragment);
        fragmentList.add(teamFragment);
        fragmentList.add(myFragment);


        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), fragmentList));

        viewPager.setCurrentItem(0);

        //设置viewPager生命周期
        viewPager.setOffscreenPageLimit(4);
        //TODO 设置ViewPager适配器
        viewPager.setOnPageChangeListener(new MyPageListener());

    }

    class MyPageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int index = viewPager.getCurrentItem();
            restartBotton();
            switch (index) {
                case 0:
                    h1.setImageResource(R.mipmap.ic_11);
                    h1_2.setTextColor(0xff1296db);
                    break;
                case 1:
                    h2.setImageResource(R.mipmap.ic_2);
                    h2_2.setTextColor(0xff1296db);
                    break;
                case 2:
                    h4.setImageResource(R.mipmap.ic_4);
                    h4_2.setTextColor(0xff1296db);
                    break;
                case 3:
                    h5.setImageResource(R.mipmap.ic_5);
                    h5_2.setTextColor(0xff1296db);
                    break;
                default:
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class PageAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragments;

        public PageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    private void restartBotton() {
        // ImageView置为白色
        h1.setImageResource(R.mipmap.ic_1);
        h2.setImageResource(R.mipmap.ic_2_2);
        h4.setImageResource(R.mipmap.ic_4_4);
        h5.setImageResource(R.mipmap.ic_5_5);
        // TextView置为白色
        h1_2.setTextColor(0xffffffff);
        h2_2.setTextColor(0xffffffff);
        h4_2.setTextColor(0xffffffff);
        h5_2.setTextColor(0xffffffff);
    }

    @Override
    public void onClick(View v) {
        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为白色，然后根据点击着色
        restartBotton();
        // ImageView和TetxView置为蓝色，页面随之跳转
        switch (v.getId()) {
            case R.id.h1:
                h1.setImageResource(R.mipmap.ic_11);
                h1_2.setTextColor(0xff1296db);
                viewPager.setCurrentItem(0);
                break;
            case R.id.h2:
                h2.setImageResource(R.mipmap.ic_2);
                h2_2.setTextColor(0xff1296db);
                viewPager.setCurrentItem(1);
                break;
            case R.id.h4:
                h4.setImageResource(R.mipmap.ic_4);
                h4_2.setTextColor(0xff1296db);
                viewPager.setCurrentItem(2);
                break;
            case R.id.h5:
                h5.setImageResource(R.mipmap.ic_5);
                h5_2.setTextColor(0xff1296db);
                viewPager.setCurrentItem(3);
                break;
            case R.id.mone:

                if (bl) {//没有被点击过
                    viewPager.setBackgroundResource(R.drawable.bg_home_2_3);
                    bg_home3.setBackgroundResource(R.drawable.bg_home3_3);
                    mone.setBackgroundResource(R.drawable.black);
                    gg.setBackgroundResource(R.drawable.black);
                    EventBus.getDefault().post(new MessageEvent("关灯"));
                    Toast.makeText(this, "夜间模式已开启", Toast.LENGTH_SHORT).show();
                    bl = false;
                } else {//已经点击过了
                    viewPager.setBackgroundResource(R.drawable.bg_home2);
                    bg_home3.setBackgroundResource(R.drawable.bg_home3_1);
                    mone.setBackgroundResource(R.drawable.white);
                    gg.setBackgroundResource(R.drawable.white);
                    EventBus.getDefault().post(new MessageEvent("开灯"));
                    Toast.makeText(this, "已退出夜间模式", Toast.LENGTH_SHORT).show();
                    bl = true;
                }
                setButtonStyle();
                break;
            case R.id.gg:
                //跳转到QQ个人信息页
                String url = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin=1319837080&card_type=person&source=qrcode";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                Toast.makeText(this, "可以点十个赞哦~么么哒~", Toast.LENGTH_SHORT).show();
                setButtonStyle();
                break;
            case  R.id.home_6616:
                dialog.dismiss();
            default:
                break;
        }
    }
    public void show66166(View view) {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.home_66166_layout, null);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 600;//设置Dialog距离底部的距离

//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    public void show(View view) {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        //初始化控件
        mone = (LinearLayout) inflate.findViewById(R.id.mone);
        gg = (LinearLayout) inflate.findViewById(R.id.gg);
        mone.setOnClickListener(this);
        gg.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.TOP);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 200;//设置Dialog距离底部的距离
        lp.x = 150;

//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    public void setButtonStyle() {
     /* 最初设置的每次点击主页按钮都重置一次
     主页菜单栏按钮背景颜色然后根据这个get到的值再设定选中的按钮的颜色，
     后来加了新按钮后原来除非改原来逻辑，不然实现不了点击新按钮给选中的按钮赋值了，
     就想了个办法把给按钮改颜色流程提取出来，get到原来的选中的按钮再给把颜色赋回去，
*/
        int i = 0;
        i = viewPager.getCurrentItem();
        switch (i) {
            case 0:
                //     Toast.makeText(this,""+i,Toast.LENGTH_SHORT).show();
                h1.setImageResource(R.mipmap.ic_11);
                h1_2.setTextColor(0xff1296db);
                viewPager.setCurrentItem(0);
                break;
            case 1:
                h2.setImageResource(R.mipmap.ic_2);
                h2_2.setTextColor(0xff1296db);
                viewPager.setCurrentItem(1);
                break;
            case 2:
                h4.setImageResource(R.mipmap.ic_4);
                h4_2.setTextColor(0xff1296db);
                viewPager.setCurrentItem(2);
                break;
            case 3:
                h5.setImageResource(R.mipmap.ic_5);
                h5_2.setTextColor(0xff1296db);
                viewPager.setCurrentItem(3);
                break;
            default:
                break;
        }


    }
}
