package com.dian.commonlib.utils;

import android.text.TextUtils;

import com.dian.commonlib.lang.MultiLanguageUtil;

import java.math.BigDecimal;

/**
 * Created by dianer on 2018/9/5.
 */

public class BigDecimalUtil {

    /**
     * bigdecimal转字符串
     *
     * @param bigDecimal
     * @param empty
     * @return
     */
    public static String getString(BigDecimal bigDecimal, String empty) {
        String str = "";
        if (TextUtils.isEmpty(empty)) {
            empty = "0";
        }
        if (bigDecimal == null) {
            str = empty;
            return str;
        }
        if (bigDecimal.compareTo(new BigDecimal("0")) == 0) {
            str = empty;
            return str;
        }
        str = bigDecimal.stripTrailingZeros().toPlainString();
        return str;
    }

    public static String getString(BigDecimal bigDecimal, String empty, int jingdu) {
        String str = "";
        if (TextUtils.isEmpty(empty)) {
            empty = "0";
        }
        if (bigDecimal == null) {
            str = empty;
            return str;
        }
        if (bigDecimal.compareTo(new BigDecimal("0")) == 0) {
            str = empty;
            return str;
        }
        if (jingdu == 0){
            str = bigDecimal.toBigInteger().toString();
        }else{
            str = bigDecimal.setScale(jingdu, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        }

        return str;
    }

    /**
     * 获取指定精度的数字
     *
     * @param accuracy 精度
     * @param str      需要调整的数字
     * @return
     */
    public static String getAccuracyNum(int accuracy, String str) {
        if (TextUtils.isEmpty(str))
            return "";
        if (str.contains(".")) {
            int i = str.indexOf('.');
            int i1 = str.length() - i - 1;
            if (i1 > accuracy) {
                //如果小数位数大于设置精度，则截断
                return str.substring(0, i + accuracy + 1);
            }
        }
        return str;
    }

    /**
     * 获取当前法币单位
     *
     * @return
     */
    public static String getFaBiLable() {
        int selectedLanguage = MultiLanguageUtil.getInstance().getLanguageType();
        switch (selectedLanguage) {
            case MultiLanguageUtil.LANGUAGE_KO:
            case MultiLanguageUtil.LANGUAGE_EN:
                return "$";
            default:
                return "￥";
        }
    }
}
