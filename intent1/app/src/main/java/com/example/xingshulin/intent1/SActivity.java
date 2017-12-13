package com.example.xingshulin.intent1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

/**
 * Created by xingshulin on 2017/10/16.
 * 点击button回传数据
 */

public class SActivity extends Activity{
    private Button bt;
    private String content = "你好";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sactivity);
        /*
            回传给第一页面是一个Intent对象
        * */
        String text = this.getIntent().getStringExtra("text");

        final User obj = new User();
        obj.setName("Serializable");
        obj.setAge("22");

        new AlertDialog.Builder(this)
                .setTitle("标题")
                .setMessage(text)
                .setPositiveButton("确定", null)
                .show();

        bt = (Button) findViewById(R.id.button1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // 此时不用跳转到具体页面，所以不用指定具体参数
            Intent data = new Intent();
            // 和java相同 存储的都是键值对
            data.putExtra("obj",obj);
            setResult(2,data);
            //结束当前页面 自然回到了第一个页面
            finish();
            }
        });
    }
}
