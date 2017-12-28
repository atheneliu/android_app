package com.xdrc.xsl.handlerdemo;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by zoushicheng on 2017/10/15.
 */

public class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
  private static final String TAG = "MyAsyncTask";
  UpdateProgress updater;
  int progress;
  public MyAsyncTask(UpdateProgress updater) {
    this.updater = updater;
  }

  @Override
  protected void onPreExecute() {
    Log.i(TAG, "async task start");
    progress = 0;
  }

  @Override
  protected Boolean doInBackground(Void... voids) {
    // 写真正的耗时操作
    try {
      while (progress < 100) {
        Thread.sleep(1000);
        progress += 10;
        publishProgress(progress);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
    Log.i(TAG, "onProgressUpdate");
    updater.update(values[0]);
  }

  @Override
  protected void onPostExecute(Boolean result) {
    Log.i(TAG, "async task finish " + String.valueOf(result));
  }

  interface UpdateProgress {
    void update(Integer progress);
  }
}
