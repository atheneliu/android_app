package com.example.xingshulin.okhttp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTvResult;
    private ProgressBar mProgressbar;
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = (TextView) findViewById(R.id.id_tv_result);
        mProgressbar = (ProgressBar) findViewById(R.id.pg);
    }

    public void doDownload(View view){
        Request.Builder builder = new Request.Builder();
        Request request = builder
                .get()
                .url("http://photocdn.sohu.com/20151130/mp45268889_1448850642738_12.gif")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.e("onFailure:" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                L.e("onResponse:");

                final long total = response.body().contentLength();
                long sum = 0L;

                InputStream is = response.body().byteStream();

                int len = 0;

                File file = new File(MainActivity.this.getExternalCacheDir().toString()+"/test.png");

                if(!file.exists()){
                    file.createNewFile();
                }
                Log.d("path", "onResponse: "+file.getAbsolutePath());
                byte[] buf = new byte[90000];
                FileOutputStream fos = new FileOutputStream(file);

                while ( (len = is.read(buf)) != -1){
                    fos.write(buf, 0, len);
                    sum += len;
                    L.e(len+"/"+total);
                    final long finalSum = sum;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgressbar.setMax((int) total);
                            mTvResult.setText((int)(finalSum/(float)total*100)+"%");
                            mProgressbar.setProgress((int) finalSum);
                        }
                    });
                }

                fos.flush();
                fos.close();
                is.close();

                L.e("download success");
            }
        });
    }

    public void doGet(View view) {
        // 1.拿到okHttpClient对象

        //2.构造request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("https://www.baidu.com/").build();

        //3.将Request封装为Call
        Call call = okHttpClient.newCall(request);

        //4.执行call
        // 1.直接调用 Response是调用后的信息
//        try {
//            Response response = call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //2.异步操作
        call.enqueue(new Callback() {
            //仍然在子线程中，不能进行UI操作 -- 支持大文件操作
            @Override
            public void onFailure(Call call, IOException e) {
                L.e("onFailure:" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                L.e("onResponse:");
                final String res = response.body().string();
                L.e(res);
                // 大文件可以在子线程中创建buffer流
                InputStream is = response.body().byteStream();
                // 通过handler去执行
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvResult.setText(res);
                    }
                });
            }
        });

    }
}
