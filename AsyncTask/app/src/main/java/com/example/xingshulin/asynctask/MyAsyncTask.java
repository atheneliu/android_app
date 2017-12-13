package com.example.xingshulin.asynctask;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by xingshulin on 2017/10/28.
 */

//Void指明返回值必须为空类型
public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
    @Override
    //第一步 初始化进度
    protected void onPreExecute() {
        Log.d("asyncTask", "onPreExecute");
        super.onPreExecute();
    }

    @Override
    //继承后必须实现的方法
    //第二步 真正执行异步操作
    protected Void doInBackground(Void... voids) {
        Log.d("asyncTask", "doInBackground");
        publishProgress();
        return null;
    }

    @Override
    //第三步 返回异步操作的结果
    protected void onPostExecute(Void aVoid) {
        Log.d("asyncTask", "onPostExecute");
        super.onPostExecute(aVoid);
    }

    @Override
    //获取进度，更新进度条，需在doInBackground中调用
    protected void onProgressUpdate(Void... values) {
        Log.d("asyncTask", "onProgressUpdate");
        super.onProgressUpdate(values);
    }
}
