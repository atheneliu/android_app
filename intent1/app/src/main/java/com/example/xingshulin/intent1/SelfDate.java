package com.example.xingshulin.intent1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class SelfDate extends Activity {
    private ListView lv;
    private ArrayAdapter<String> arr_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selfdate);

        lv = (ListView) findViewById(R.id.datelisthead);
        // 1.新建一个数据适配器
        // ArrayAdapter(上下文、每一项的布局文件、数据源)
        // 2.适配器加载数据源
        String[]arr_data = {"一","二","三","四","五","六","七"};
        arr_adapter = new ArrayAdapter<String>(this, R.layout.datelistitem, arr_data);
        // 视图加载适配器
        lv.setAdapter(arr_adapter);
        Long time = getCurrentTime();
        Log.i("getCurrentTime", String.valueOf(time));
        Log.i("getCurrentMonthDay", String.valueOf(getCurrentMonthDay(time)));
        Log.i("getFirOfMonth", String.valueOf(getFirOfMonth(time)));
    }
    /**
     * 获取该月天数
     */
    public static int getCurrentMonthDay(long millSec) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millSec);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        int dateCount = calendar.get(Calendar.DATE);
        return dateCount;
    }
    /**
     * 获取当月第一天
     */
    public static long getFirOfMonth(long millSec) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millSec);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTimeInMillis();
    }
    /**
     * 获取当前时间戳
     */
    public static long getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }
    /**
     * 格式化到月份
     */
    public static String long2str(long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(millSec));
    }
}