package com.dian.commonlib.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.dian.commonlib.base.BaseActivity;
import com.dian.commonlib.lang.MultiLanguageUtil;
import com.dian.commonlib.utils.DeviceUtil;
import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.MmkvUtil;
import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;

/**
 * Created by kennysun on 2019/8/7.
 */

public abstract class App extends Application {
    public ArrayList<Activity> activitys = new ArrayList();
    public static App mApp;
    public static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        gson = new Gson();
        //初始化日志打印工具
        LogUtil.initLogger("HUOHUO");

        //初始化数据持久化工具Mmkv
        MMKV.initialize(this);

        //初始化设备管理工具类
        DeviceUtil.init(this);

        //初始化语言
        MultiLanguageUtil.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 退出app
     */
    public void exit() {
        removeAllActivity();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void addActivity(Activity activity) {
        activitys.add(activity);
    }

    public void removeActivity(Activity activity) {
        activitys.remove(activity);
    }

    public void removeAllActivity() {
        if (activitys != null && !activitys.isEmpty()) {
            Activity activity = null;
            while (activitys.size() > 0) {
                activity = activitys.get(activitys.size() - 1);
                activitys.remove(activity);
                try {
                    if (activity != null && !activity.isFinishing()) {
                        activity.finish();
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public void removeOtherActivity(Activity activityOther) {
        if (activitys != null && !activitys.isEmpty()) {
            Activity activity = null;
            int time = activitys.size();
            while (time > 1) {
                activity = activitys.get(time - 1);
                if (!activity.equals(activityOther)) {
                    activitys.remove(activity);
                    try {
                        if (!activity.isFinishing()) {
                            activity.finish();
                        }
                    } catch (Exception ignored) {
                    }
                }
                time--;
            }
        }
    }

    public abstract void clearUser();

    public abstract void loginExpire();


}
