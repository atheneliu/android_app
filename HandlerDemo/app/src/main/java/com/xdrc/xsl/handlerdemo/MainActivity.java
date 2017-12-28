package com.xdrc.xsl.handlerdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

  private static final int UPDATE_LABEL = 0;
  private String TAG = "MainActivity";
  Handler handler;
  @BindView(R.id.tv_label) TextView labelTv;
  @BindView(R.id.tv_progress) TextView progressTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    handler = new Handler() {
      public void handleMessage(Message msg) {
        switch (msg.what) {
          case UPDATE_LABEL: {
            labelTv.setText((String) msg.obj);
            break;
          }
        }
      }
    };
  }

  // 直接调用耗时方法可能会导致 ANR
  @OnClick(R.id.btn_anr)
  public void anr() {
    Log.i(TAG, "call anr");
    asyncFunc();
    Log.i(TAG, "after call anr");
  }

  // 可以在一个新的线程中调用耗时操作
  @OnClick(R.id.btn_new_thread)
  public void newThread() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        Log.i(TAG, "call newThread");
        asyncFunc();
        Log.i(TAG, "after call newThread");
      }
    }).start();
  }

  // 但是不能在非 UI 线程中更新 UI
  @OnClick(R.id.btn_thread_update_ui)
  void newThreadWithUpdateUI() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        Log.i(TAG, "call newThreadWithUpdateUI");
        asyncFuncUpdateUI("label from newThreadWithUpdateUI");
        Log.i(TAG, "after call newThreadWithUpdateUI");
      }
    }).start();
  }

  @OnClick(R.id.btn_handler_update_ui)
  void handlerUpdateUI() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        Log.i(TAG, "call handlerUpdateUI");
        asyncFuncWithHandler("msg from handlerUpdateUI");
        Log.i(TAG, "after call handlerUpdateUI");
      }
    }).start();
  }

  @OnClick(R.id.btn_send_msg)
  void sendMsg() {
  }

  @OnClick(R.id.btn_async_task)
  void runAsyncTask() {
    (new MyAsyncTask(new MyAsyncTask.UpdateProgress() {
      @Override
      public void update(Integer progress) {
        progressTv.setText(String.valueOf(progress));
      }
    })).execute();
  }

  private void asyncFuncWithHandler(String label) {
    try {
      Thread.sleep(12000);
      Message msg = new Message();
      msg.what = UPDATE_LABEL;
      msg.obj = label;
      handler.sendMessage(msg);
    } catch (InterruptedException e) {
      e.printStackTrace();
      Log.e(TAG, "sleep error");
    }
  }

  private void asyncFunc() {
    try {
      Thread.sleep(12000);
    } catch (InterruptedException e) {
      e.printStackTrace();
      Log.e(TAG, "sleep error");
    }
  }

  private void asyncFuncUpdateUI(String label) {
    try {
      Thread.sleep(12000);
      labelTv.setText(label);
    } catch (InterruptedException e) {
      e.printStackTrace();
      Log.e(TAG, "sleep error");
    }
  }
}
