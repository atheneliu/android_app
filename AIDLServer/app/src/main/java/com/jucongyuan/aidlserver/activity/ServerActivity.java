package com.jucongyuan.aidlserver.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jucongyuan.aidlserver.R;
import com.jucongyuan.aidlserver.service.ServerService;

public class ServerActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score[] = new int[5];
                int multi = 1;
                for (int i =0;i<score.length;i++){
                    score[i]=i+1;
                }
                for (int i =0;i<score.length;i++){
                    multi = multi*score[i];
                }
                Log.i("multi", String.valueOf(multi));
                Intent intent = new Intent(ServerActivity.this, ServerService.class);
                intent.putExtra("multi", multi);
                intent.putExtra("count", score.length);
                startService(intent);
            }
        });
    }
}
