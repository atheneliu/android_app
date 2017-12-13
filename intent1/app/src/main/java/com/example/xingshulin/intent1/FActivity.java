package com.example.xingshulin.intent1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by xingshulin on 2017/10/16.
 */

public class FActivity  extends Activity{
    // 在此使用组件时，需要先初始化
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;
    private Button bt6;
    private TextView tv;
    private Context mContext;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 11;
    private String phone = "10086";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factivity);
         /*
         * 通过点击btn1实现跳转，需在java中初始化一个btn
         * a.startActivity实现
         * 初始化Intent
         * */
        //直接在内部类中无法用this获取，需设置一个上下文变量
         mContext = this;
         tv = (TextView) findViewById(R.id.textView1);
         bt1 = (Button)findViewById(R.id.button1_first);
         bt2 = (Button)findViewById(R.id.button2_second);
         bt3 = (Button)findViewById(R.id.button3);
         bt4 = (Button)findViewById(R.id.button4);
         bt5 = (Button)findViewById(R.id.button5);
         bt6 = (Button)findViewById(R.id.button6);


        //  注册点击事件
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                第一个参数：上下文this
                第二个参数：目标文件
                * */
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                第一个参数：上下文this
                第二个参数：目标文件
                * */
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phone));

                if (ContextCompat.checkSelfPermission(FActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else {
                    try {
                        startActivity(intent);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        /*
          通过StartAcivityForResult
        * */
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,SActivity.class);
                intent.putExtra("text","hello");
                /*
                    第一个参数：Intent对象
                    第二个参数：请求的一个标识
                * */
                startActivityForResult(intent, 1);
            }
        });
        // ListView 纯文本列表
        bt4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MainList.class);
                startActivity(intent);
            }
        });
        // ListView 图文列表
        bt5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PicList.class);
                startActivity(intent);
            }
        });
        // 自定义日历
        bt6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SelfDate.class);
                startActivity(intent);
            }
        });
    }
    /*
        通过startActivityForResult跳转，接受返回数据的方法
        是其提供给我们的我们只需重写
        参数：
        requestCode：根据不同的请求标识，返回不同的结果---因为假如该页面有十个不同的跳转，则都会用这一个函数来处理，需要code来标识是哪一个页面
        resultCode：第二个页面返回的标准
        data： 第二个页面回传的数据
    * */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2){
//            String content = data.getStringExtra("data");
            User userObj = (User) data.getSerializableExtra("obj");
            tv.setText(userObj.getName());
            Log.i("log",userObj.getName());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }else{
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
