package com.dian.commonlib.utils;

import com.dian.commonlib.app.App;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 需要在application中初始化
 * Created by kennysun on 2019/8/6.
 * 日志打印工具
 * 日志打印规则
 * 类名(类别名):方法名:参数=?,参数=?,...
 */

public class LogUtil {
    /**
     * 可在Application中设置
     */
    private static String TAG = "HUOHUO";
    public static Boolean IS_DEBUG = true;

    public static void initLogger(String tag) {
        TAG = tag;
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag(TAG)   //
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public static void d(Object any) {
        if (IS_DEBUG)
            Logger.d(any);
    }
}
