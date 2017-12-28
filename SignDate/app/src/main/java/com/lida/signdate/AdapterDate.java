package com.lida.signdate;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdapterDate extends BaseAdapter {

    private Context context;
    private List<Integer> days = new ArrayList<>();
    //日历数据

    public AdapterDate(Context context) {
        this.context = context;
        int maxDay = DateUtil.getCurrentMonthLastDay();//获取当月天数
        for (int i = 0; i < DateUtil.getFirstDayOfMonth() - 1; i++) {
            //DateUtil.getFirstDayOfMonth()获取当月第一天是星期几，星期日是第一天，依次类推
            days.add(0);
        }
        for (int i = 0; i < maxDay; i++) {
            days.add(i+1);
        }
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int i) {
        return days.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_gv,null);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv = view.findViewById(R.id.tvWeek);
        viewHolder.rlItem = view.findViewById(R.id.rlItem);
        viewHolder.ivStatus = view.findViewById(R.id.ivStatus);
        viewHolder.tv.setText(days.get(i)+"");
        if(days.get(i)==0){
            viewHolder.rlItem.setVisibility(View.GONE);
        }
        return view;
    }

    class ViewHolder{
        RelativeLayout rlItem;
        TextView tv;
        ImageView ivStatus;
    }
}
