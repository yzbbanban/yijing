package com.dian.commonlib.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 需要在application中初始化
 * Created by kennysun on 2019/8/6.
 */

public class DeviceUtil {
    private static int deviceWidth;
    private static int deviceHeight;
    private static String deviceId;
    private static String brand;
    private static String model;
    private static String androidVersion;
    private static int apiVersion;

    public static void init(Context context) {
        //设备尺寸相关
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        deviceHeight = displayMetrics.heightPixels;
        deviceWidth = displayMetrics.widthPixels;

        //设备基础信息相关
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        deviceId = EncryptUtil.encryptMD5ToString(androidId);

        //手机品牌
        brand = Build.BRAND;
        //手机型号
        model = Build.MODEL;
        //sdk版本&android手机版本
        apiVersion = Build.VERSION.SDK_INT;
        androidVersion = Build.VERSION.RELEASE;
    }

    public static int getDeviceWidth() {
        return deviceWidth;
    }

    public static int getDeviceHeight() {
        return deviceHeight;
    }

    public static String getDeviceId() {
        return deviceId;
    }

    public static String getBrand() {
        return brand;
    }

    public static String getModel() {
        return model;
    }

    public static String getAndroidVersion() {
        return androidVersion;
    }

    public static int getApiVersion() {
        return apiVersion;
    }

    /**
     * 判断设备是否root
     *
     * @return
     */
    public static boolean isRoot() {
        String binPath = "/system/bin/su";
        String xBinPath = "/system/xbin/su";
        if (new File(binPath).exists() && isExecutable(binPath))
            return true;
        if (new File(xBinPath).exists() && isExecutable(xBinPath))
            return true;
        return false;
    }

    private static boolean isExecutable(String filePath) {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("ls -l " + filePath);
            // 获取返回内容
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String str = in.readLine();
            if (str != null && str.length() >= 4) {
                char flag = str.charAt(3);
                if (flag == 's' || flag == 'x')
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
        return false;
    }

}
