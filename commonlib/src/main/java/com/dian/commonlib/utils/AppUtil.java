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
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getToken() {
        return MmkvUtil.decodeString(Constants.TOKEN, "");
    }

    public static String getNickName() {
        return MmkvUtil.decodeString(Constants.NICK_NAME, "");
    }

    public static String getImage() {
        return MmkvUtil.decodeString(Constants.IMAGE, "");
    }


    public static String getUser() {
        return MmkvUtil.decodeString(Constants.USER, "");
    }

    public static String getTeamId() {
        return MmkvUtil.decodeString(Constants.TEAM_ID, "");
    }

    public static void setToken(String token) {
        MmkvUtil.encodeString(Constants.TOKEN, token);
    }

    public static void setUser(String user) {
        MmkvUtil.encodeString(Constants.USER, user);
    }

    public static void setTeamId(String user) {
        MmkvUtil.encodeString(Constants.TEAM_ID, user);
    }

    public static void setNickName(String name) {
        MmkvUtil.encodeString(Constants.NICK_NAME, name);
    }

    public static void setImage(String image) {
        MmkvUtil.encodeString(Constants.IMAGE, image);
    }


}
