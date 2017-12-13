package com.example.xingshulin.okhttp;

import android.util.Log;

/**
 * Created by xingshulin on 2017/10/28.
 */

//用于打印错误信息，可根据Tag直接
public class L {
    private static final String TAG = "Immoc_okhttp";
    private static boolean debug = true;

    public static void e(String msg) {
        if (debug) {
            Log.e(TAG, msg);
        }
    }
}
