package com.example.xingshulin.asynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by xingshulin on 2017/10/28.
 */

public class ImageTest extends Activity {

    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private static String URL =
            "http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        mImageView = findViewById(R.id.image);
        mProgressBar = findViewById(R.id.progressbar);
        //初始化一个asyncTask实例，并调用--则就创建了一个异步线程 参数作为doInBackground的参数
        new MyAsyncTask().execute(URL);
    }

    //参数类型：url类型 进度值类型 返回值类型
    class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        //注意 因为此处doInBackground的返回值类型 要和这里的参数进行对应
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mProgressBar.setVisibility(View.GONE);
            mImageView.setImageBitmap(bitmap);
        }

        //访问网络 获取网络图片
        @Override
        //参数为可变数组 可以传递多个值 用下标取值
        protected Bitmap doInBackground(String... params) {
            String url = params[0]; //取出对应URL
            Bitmap bitmap = null;
            URLConnection connection; //定义网络连接对象
            InputStream is; //定义输入流
            //将url所对应的图片转换成了一个 bitMap对象  -- 异步操作
            try {
                connection = new URL(url).openConnection(); //获取网络连接对象
                is = connection.getInputStream(); //获取输入流
                BufferedInputStream bis = new BufferedInputStream(is); //包装成buffer
                Thread.sleep(3000);
                bitmap = BitmapFactory.decodeStream(bis); //将输入流解析成一个bitmap
                is.close();
                bis.close(); //关闭输入流
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}
