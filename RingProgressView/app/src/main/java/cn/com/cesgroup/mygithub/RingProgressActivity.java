package cn.com.cesgroup.mygithub;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class RingProgressActivity extends Activity {

    private RingProgressView ringProgressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_progress);
        ringProgressView = (RingProgressView) findViewById(R.id.ringProgress);
    }
}
