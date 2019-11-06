package com.dian.commonlib.lang;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import com.dian.commonlib.utils.MmkvUtil;

import java.util.Locale;

/**
 * 多语言切换管理类
 * Created by kennysun on 2019/8/6.
 */

public class MultiLanguageUtil {
    public static final int LANGUAGE_FOLLOW_SYSTEM = 0;//跟随系统
    public static final int LANGUAGE_EN = 1; //英文
    public static final int LANGUAGE_CHINESE_SIMPLIFIED = 2;//简体
    public static final int LANGUAGE_CHINESE_TRADITIONAL_HK = 3;//香港繁体
    public static final int LANGUAGE_CHINESE_TRADITIONAL_TW = 4; //台湾繁体
    public static final int LANGUAGE_KO = 5;//韩文

    private static final String SAVE_LANGUAGE = "save_language";
    private Context mContext;
    private static MultiLanguageUtil instance;

    public static void init(Context mContext) {
        if (instance == null) {
            synchronized (MultiLanguageUtil.class) {
                if (instance == null) {
                    instance = new MultiLanguageUtil(mContext);
                }
            }
        }
    }

    public static MultiLanguageUtil getInstance() {
        if (instance == null) {
            throw new IllegalStateException("You must be init MultiLanguageUtil first");
        }
        return instance;
    }

    private MultiLanguageUtil(Context context) {
        this.mContext = context;
    }

    /**
     * 获取选择的语言
     * 默认返回简体中文
     *
     * @return
     */
    public Locale getLanguageLocale() {
        int languageType = MmkvUtil.decodeInt(SAVE_LANGUAGE, LANGUAGE_CHINESE_SIMPLIFIED);
        if (languageType == LANGUAGE_FOLLOW_SYSTEM) {
            return getSysLocale();
        } else if (languageType == LANGUAGE_EN) {
            return Locale.ENGLISH;
        } else if (languageType == LANGUAGE_CHINESE_SIMPLIFIED) {
            return Locale.CHINA;
        } else if (languageType == LANGUAGE_CHINESE_TRADITIONAL_HK) {
            return Locale.TAIWAN;
        } else if (languageType == LANGUAGE_CHINESE_TRADITIONAL_TW) {
            return Locale.TAIWAN;
        } else if (languageType == LANGUAGE_KO) {
            return Locale.KOREA;
        } else {
            return Locale.SIMPLIFIED_CHINESE;
        }
    }

    /**
     * 获取系统语言
     *
     * @return
     */
    public Locale getSysLocale() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }


    /**
     * 更新语言
     */
    public void updateLanguage(int languageType) {
        MmkvUtil.encodeInt(SAVE_LANGUAGE, languageType);
    }

    /**
     * 获取到用户保存的语言类型
     *
     * @return
     */
    public int getLanguageType() {
        int languageType = MmkvUtil.decodeInt(SAVE_LANGUAGE, LANGUAGE_CHINESE_SIMPLIFIED);
        return languageType;
    }

    /**
     * baseactivity中attachBaseContext方法调用
     *
     * @param context
     * @return
     */
    public Context attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createConfigurationResources(context);
        } else {
            setConfiguration();
            return context;
        }
    }

    /**
     * 设置语言
     */
    public void setConfiguration() {
        Locale targetLocale = getLanguageLocale();
        Configuration configuration = mContext.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(targetLocale);
        } else {
            configuration.locale = targetLocale;
        }
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);//语言更换生效的代码!
    }

    /**
     * 设置语言
     */
    @TargetApi(Build.VERSION_CODES.N)
    private Context createConfigurationResources(Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = getLanguageLocale();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }
}
