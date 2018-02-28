package com.algg.c616.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.algg.c616.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 光光 on 2017/11/13.
 */

public class TeamFragment extends Fragment {
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO 加载我的布局文件
        return inflater.inflate(R.layout.fragment_team,null);
     }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView =(ListView)getActivity().findViewById(R.id.listview);

        SimpleAdapter adapter =new SimpleAdapter(getActivity(),setDate(),R.layout.item,new String[]{"name","sno","ps","age","lv","icon","icon2"},new int[]{
                R.id.team_name,R.id.team_no,R.id.team_ps,R.id.team_age,R.id.team_lv,R.id.team_icon,R.id.team_icon2 });

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view,int position,long id){
            //    listView.setSelected(true);
                HashMap map=(HashMap) parent.getItemAtPosition(position);
                String str =""+map.get("QQ");
                try {

                    int i = 0;

                        //TODO 解决如果QQ为自己的话无法跳转问题
                    String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + str;//uin是发送过去的qq号码
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    Toast.makeText(getActivity(), "与" + map.get("na") + "聊天中", Toast.LENGTH_LONG).show();
//
//                        String url0 = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin=" + str + "&card_type=person&source=qrcode";
//                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url0)));
                   } catch(Exception e){
                        e.printStackTrace();
                    }

            }
        } );
    }
    // TODO: 定义数据源
    private List<Map<String,Object>> setDate(){
        List<Map<String,Object>> list
                = new ArrayList<Map<String,Object>>();
        HashMap map = new HashMap<String,Object>();
        map.put("name","姓名：李中光");
        map.put("na","李中光");
        map.put("sno","学号：16180410604");
        map.put("ps","签名：光光会发光");
        map.put("age","年龄：22");
        map.put("lv",R.mipmap.ic_user_level_6);
        map.put("icon",R.mipmap.ic_lzg);
        map.put("icon2",R.mipmap.ic_user_gg);
        map.put("QQ","1319837080");
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","姓名：皮雪领");
        map.put("na","皮雪领");
        map.put("sno","学号：14110410610");
        map.put("ps","签名：皮皮傻皮皮");
        map.put("age","年龄：23");
        map.put("lv",R.mipmap.ic_user_level_5);
        map.put("icon",R.mipmap.ic_pipi);
        map.put("icon2",R.mipmap.ic_user_pipi);
        map.put("QQ","249396141");
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","姓名：王  乐");
        map.put("na","王乐");
        map.put("sno","学号：16180410601");
        map.put("ps","签名：乐子会傻笑");
        map.put("age","年龄：19");
        map.put("lv",R.mipmap.ic_user_level_2);
        map.put("icon",R.mipmap.ic_lezi);
        map.put("icon2",R.mipmap.ic_user_wl);
        map.put("QQ","1330866094");
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("name","姓名：林旭阳");
        map.put("na","林旭阳");
        map.put("sno","学号：16180410602");
        map.put("ps","签名：大胖真的胖");
        map.put("age","年龄：21");
        map.put("lv",R.mipmap.ic_user_level_0);
        map.put("icon",R.mipmap.ic_dapang);
        map.put("icon2",R.mipmap.ic_user_dapang);
        map.put("QQ","1925170284");
        list.add(map);
        return list;
    }
}

