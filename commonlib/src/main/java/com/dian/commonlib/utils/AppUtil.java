package com.dian.commonlib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.dian.commonlib.app.Constants;

/**
 * APP相关工具类
 * Created by kennysun on 2019/8/6.
 */

public class AppUtil {

    /**
     * 返回版本名称
     * @param context
     * @return
     */
    public static String getVersionName(Context context){
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        }catch (Exception e){
            return "";
        }
    }

    public static String getToken(){
        return MmkvUtil.decodeString(Constants.TOKEN,"");
    }

    public static void setToken(String token){
        MmkvUtil.encodeString(Constants.TOKEN, token);
    }
}
