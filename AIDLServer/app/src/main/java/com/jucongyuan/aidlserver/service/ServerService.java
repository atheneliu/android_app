package com.jucongyuan.aidlserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.jucongyuan.IConnect;
import com.jucongyuan.Student;

public class ServerService extends Service {

    private int reslut;
    private int count;

    IConnect.Stub stub = new IConnect.Stub() {
        // 修改部分
        @Override
        public Student getStr(Student s) throws RemoteException {
            s.setresult(reslut);
            s.setcount(count);
            return s;
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        reslut = intent.getIntExtra("multi",0);
        count = intent.getIntExtra("count",0);
        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

}
