package com.dian.commonlib.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.dian.commonlib.R;
import com.dian.commonlib.app.App;
import com.dian.commonlib.lang.MultiLanguageUtil;
import com.dian.commonlib.utils.KeyboardUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by kennysun on 2019/8/7.
 */

abstract public class BaseActivity extends RxAppCompatActivity {
    public abstract int getLayoutId();

    public abstract void initViewAndData();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.mApp.activitys.add(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initViewAndData();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguageUtil.getInstance().attachBaseContext(newBase));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (canRegisterEventbus())
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (canRegisterEventbus())
            EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.mApp.activitys.remove(this);
    }


    /**
     * 子类是否可接受eventbus事件
     *
     * @return
     */
    public boolean canRegisterEventbus() {
        return false;
    }

    /**
     * 标题左边按钮点击事件
     */
    public void navigationOnClick() {
        finish();
    }

    /**
     * 配置toolbar
     *
     * @param toolbar
     * @param title
     * @param naIcon
     * @return
     */
    public Toolbar setToolbarConfig(Toolbar toolbar, int title, int naIcon) {
        if (naIcon != -1) {
            toolbar.setNavigationIcon(naIcon);
            toolbar.setContentInsetsAbsolute(0, 0);
            toolbar.setContentInsetStartWithNavigation(0);
        }
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> navigationOnClick());
        return toolbar;
    }

    public Toolbar setToolbarConfig(Toolbar toolbar, int title) {
        return setToolbarConfig(toolbar, title, R.drawable.ic_action_back);
    }

    public Toolbar setToolbarConfig(Toolbar toolbar, String title) {
        return setToolbarConfig(toolbar, title, R.drawable.ic_action_back);
    }

    public Toolbar setToolbarConfig(Toolbar toolbar, String title, int naIcon) {
        toolbar.setNavigationIcon(naIcon);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationOnClick();
            }
        });
        return toolbar;
    }

    /**
     * 给fragment分发
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    /**
     * 点击空白处隐藏键盘
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                boolean hideKeyboard = KeyboardUtil.isHideKeyboard(ev, getCurrentFocus(), getDontGoneView(), this);
                if (hideKeyboard) {
                    KeyboardUtil.hideKeyboard(hideKeyboard, getCurrentFocus(), this);
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 点击该view区域不隐藏键盘
     *
     * @return
     */
    public View getDontGoneView() {
        return null;
    }

    /**
     * 登录过期处理,默认跳转登录页面
     */
    protected void loginExpire() {
        App.mApp.loginExpire();
    }

    /**
     * 处理接口返回的错误信息--特殊处理
     * 5003:用户正在另一台设备登录
     * 401:登录过期或未登录
     *
     * @param code
     */
    public void handHttpCode(int code) {
        switch (code) {
            case 401:
            case 5003:
                loginExpire();
                break;
        }
    }
}

