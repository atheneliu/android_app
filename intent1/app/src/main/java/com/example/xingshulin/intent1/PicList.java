package com.example.xingshulin.intent1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by xingshulin on 2017/10/17.
 */

public class PicList extends Activity{
    private ListView lv;
    private ArrayAdapter<String>arr_adapter;
    private SimpleAdapter simp_adapter;
    private List<Map<String, Object>> dataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview2);
        lv = (ListView) findViewById(R.id.listView2);
        // 1.新建一个数据适配器
        // ArrayAdapter(上下文、每一项的布局文件、数据源)
        // 2.适配器加载数据源
        String[]arr_data = {"msdsdd","sdasdsd","dssdasd","sadasdsd"};
        arr_adapter = new ArrayAdapter<String>(this, R.layout.listviewitem, arr_data);
        // 视图加载适配器
//        lv.setAdapter(arr_adapter);

        //simpleAdapter()
        /*
        * 参数：
        * data: 数据源（List<? extends Map<String, ?>> data）一个map所组成的List集合
        *       每一个Map都会对应ListView列表中的一行
        *       每一个Map(键-值对)中的键必须包含所有在from中所指定的键
        * resource: 列表项的布局文件ID
        * from: Map中的键名
        * to: 绑定数据视图中的ID,与form成对应关系
        * */
        dataList = new ArrayList<Map<String, Object>>();
//      //先写to在写from, to就是视图中的ID
        simp_adapter = new SimpleAdapter(this, getData(), R.layout.listviewitem2, new String[]{"pic","text"}, new int[]{R.id.pic,R.id.demo});
        lv.setAdapter(simp_adapter);

        //事件监听:

    }

    private List<Map<String,Object>> getData(){

        for (int i = 0; i<20; i++){
            Map<String,Object>map = new HashMap<String,Object>();
            map.put("pic", R.mipmap.ic_launcher);
            map.put("text",i);
            dataList.add(map);
        }
        return dataList;

    }

}
