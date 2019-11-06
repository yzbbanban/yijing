package com.dian.commonlib.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kennysun on 2019/9/3.
 */

public class ValidateUtil {
    //        public static String LOGIN_PWD_REG = "^(?=.*)[\\da-zA-Z]{8,20}$";//最低要求--同时存在字母和数字
    public static String LOGIN_PWD_REG = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$";//最低要求--同时存在字母和数字
    public static String HANZI = "[\u4e00-\u9fa5]";//判断是否是汉字
    public static String LETTER = "[a-zA-Z]";//判断是否是字母
    //判断是否是链接
    public static String urlPattern =
            "((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)";

    public static boolean isUrl(String string) {
        Pattern pattern = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(string);
        return m.matches();
    }


    public static boolean loginPwdReg(String s) {
        Pattern pattern = Pattern.compile(LOGIN_PWD_REG);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    /**
     * 判断是否是汉字
     *
     * @param s
     * @return
     */
    public static boolean isHanzi(char s) {
        Pattern pattern = Pattern.compile(HANZI);
        Matcher matcher = pattern.matcher(String.valueOf(s));
        return matcher.matches();
    }

    /**
     * 判断是否是字母
     *
     * @param s
     * @return
     */
    public static boolean isLetter(char s) {
        Pattern pattern = Pattern.compile(LETTER);
        Matcher matcher = pattern.matcher(s + "");
        return matcher.matches();
    }

    /**
     * 获取首字母
     *
     * @param name
     * @return
     */
    public static String getFirstChar(String name) {
        if (ValidateUtil.isHanzi(name.charAt(0))) {//汉字
            String[] strings = PinyinHelper.toHanyuPinyinStringArray(name.charAt(0));
            String string = strings[0]; //可能有多音字，默认取第一个汉字的拼音
            return String.valueOf(string.charAt(0)).toUpperCase();
        } else if (ValidateUtil.isLetter(name.charAt(0))) {//字母
            return String.valueOf(name.charAt(0)).toUpperCase();
        } else {//特殊符号
            return String.valueOf("☆");
        }
    }
}
