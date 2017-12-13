package com.example.xingshulin.progressbar2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

//@SuppressWarnings("deprecation")
//public abstract class MainActivity extends AppCompatActivity implements View.OnClickListener {
public  class  MainActivity extends AppCompatActivity {
    private ProgressBar progress;
    private Button add;
    private Button reduce;
    private Button reset;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //启用窗口特征，启用待进度和不带进度的进度条
//        requestWindowFeature(Window.FEATURE_PROGRESS);
//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
//
//        //显示两种进度条
//        setProgressBarVisibility(true);
//        setProgressBarIndeterminateVisibility(false);
//
//        //max=10000
//        setProgress(9999);

        init();
    }

    private void init() {
        progress=(ProgressBar)findViewById(R.id.horiz);
        add= (Button) findViewById(R.id.add_btn);
        reduce= (Button) findViewById(R.id.reduce_btn);
        reset= (Button) findViewById(R.id.reset_btn);
        text= (TextView) findViewById(R.id.textView);

        //初始化进度条信息
        //获取第一进度条的进度
        int first = progress.getProgress();
        //获取第二进度的进度条
        int second = progress.getSecondaryProgress();
        //获取进度条的最大进度
        int max = progress.getMax();

        Log.i("fisrt", String.valueOf(first));
        Log.i("second", String.valueOf(second));
        Log.i("max", String.valueOf(max));
        text.setText("第一进度百分比"+(int)(first/(float)max*100)+"%"+"第二进度百分比"+(int)(second/(float)max*100)+"%");
//        add.setOnClickListener(this);
//        reduce.setOnClickListener(this);
//        reset.setOnClickListener(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //增加第一进度和第二个进度10个刻度
                progress.incrementProgressBy(10);
                progress.incrementSecondaryProgressBy(10);
                text.setText("第一进度百分比"+(int)(progress.getProgress()/(float)progress.getMax()*100)+"%"+"第二进度百分比"+(int)(progress.getSecondaryProgress()/(float)progress.getMax()*100)+"%");
            }
        });
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //减少第一进度和第二进度10个刻度(负数为减少)
                progress.incrementProgressBy(-10);
                progress.incrementSecondaryProgressBy(-10);
                text.setText("第一进度百分比"+(int)(progress.getProgress()/(float)progress.getMax()*100)+"%"+"第二进度百分比"+(int)(progress.getSecondaryProgress()/(float)progress.getMax()*100)+"%");
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setProgress(50);
                progress.setSecondaryProgress(80);
                text.setText("第一进度百分比"+(int)(progress.getProgress()/(float)progress.getMax()*100)+"%"+"第二进度百分比"+(int)(progress.getSecondaryProgress()/(float)progress.getMax()*100)+"%");
            }
        });
    }

////    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.add_btn:{
//                //增加第一进度和第二个进度10个刻度
//                progress.incrementProgressBy(10);
//                progress.incrementSecondaryProgressBy(10);
//                break;
//            }
//            case R.id.reduce_btn:{
//                //减少第一进度和第二进度10个刻度(负数为减少)
//                progress.incrementProgressBy(-10);
//                progress.incrementSecondaryProgressBy(-10);
//                break;
//            }
//            case R.id.reset_btn:{
//                progress.setProgress(50);
//                progress.setSecondaryProgress(80);
//                break;
//            }
//        }
//    }
}
