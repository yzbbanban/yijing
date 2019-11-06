package com.dian.commonlib.utils;

import android.content.SharedPreferences;

import com.tencent.mmkv.MMKV;

/**
 * Created by kennysun on 2019/8/6.
 */

public class MmkvUtil {

    public static void encodeInt(String key, int value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    public static int decodeInt(String key, int defaultValue) {
        return MMKV.defaultMMKV().decodeInt(key, defaultValue);
    }

    public static void encodeString(String key, String value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    public static String decodeString(String key, String defaultValue) {
        return MMKV.defaultMMKV().decodeString(key, defaultValue);
    }

    public static void encodeLong(String key, Long value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    public static Long decodeLong(String key, Long defaultValue) {
        return MMKV.defaultMMKV().decodeLong(key, defaultValue);
    }


    public static void encodeBoolean(String key, boolean value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    public static boolean contains(String key) {
        return MMKV.defaultMMKV().contains(key);
    }

    public static boolean decodeBoolean(String key, boolean defaultValue) {
        return MMKV.defaultMMKV().decodeBool(key, defaultValue);
    }

    public static void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        MMKV.defaultMMKV().registerOnSharedPreferenceChangeListener(listener);

    }

    public static void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        MMKV.defaultMMKV().unregisterOnSharedPreferenceChangeListener(listener);
    }

    public static void clear(String... arrays) {
        MMKV.defaultMMKV().removeValuesForKeys(arrays);
    }

}
