package com.example.xingshulin.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;

/**
 * Created by xingshulin on 2017/10/28.
 */

public class ProgressBarTest extends Activity{
    private ProgressBar mProgressbar;
    private MyAsyncTask mTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        mProgressbar = (ProgressBar) findViewById(R.id.pg);
        mTask = new MyAsyncTask();
        mTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // As不为空 且处于runing的状态时  在组件onPause时取消AS
        if (mTask != null &&
                mTask.getStatus() == AsyncTask.Status.RUNNING) {
            //参数的含义 -- cancel掉之后 是否继续执行线程
            // cancel方法只是将对应的As标记为cancel状态，并不能真正的停止执行-- 解决 循环判断状态,终止操作
            mTask.cancel(true);
        }
    }

    class MyAsyncTask extends AsyncTask <Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            //模拟进度更新
            for (int i = 0; i < 100; i++) {
                if (isCancelled()) {
                    break;
                }
                //将进度传递到UI上
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()) {
                return;
            }
            // 获取进度更新值
            mProgressbar.setProgress(values[0]);
        }
    }
}
