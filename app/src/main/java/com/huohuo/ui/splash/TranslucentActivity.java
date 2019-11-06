package com.huohuo.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kennysun on 2019/8/8.
 */

public class TranslucentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.isTaskRoot()) {//如果应用是按home键回退到后台的情况，再点击应用icon图标进入应用，则不开启启动页(部分手机问题)
            finish();
            return;
        }
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }
}
