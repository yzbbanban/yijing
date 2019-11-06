package com.dian.commonlib.net;

import android.content.Context;

import com.dian.commonlib.lang.MultiLanguageUtil;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.DateFormatUtil;
import com.dian.commonlib.utils.DeviceUtil;
import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.NetworkUtil;
import com.dian.commonlib.utils.SignUtil;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by kennysun on 2019/8/6.
 */

public class HostInterceptor implements Interceptor {
    //app版本号
    private String appVersionCode = "";
    //渠道名称
    private String channel = "Demo";
    //设备id
    private String deviceId = "";
    //语言
    private String lang = "zh";

    private String newUrl = "";

    private Context mContext;

    private static HostInterceptor hostInterceptor;

    public static HostInterceptor getHostInterceptor(Context context) {
        if (hostInterceptor == null) {
            return new HostInterceptor(context);
        }
        return hostInterceptor;
    }

    public HostInterceptor(Context context) {
        this.mContext = context;
        appVersionCode = AppUtil.getVersionName(mContext);
        deviceId = DeviceUtil.getDeviceId();
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //先检查网络
        if (!NetworkUtil.isConnected(mContext)) {
            throw new ConnectException("网络未连接");
        }

        Request request = chain.request();
        if (!newUrl.isEmpty()) {
            request = request.newBuilder().url(newUrl).build();
        }
        //发起请求时间
        long localCurrentTime = System.currentTimeMillis();

        int selectedLanguage = MultiLanguageUtil.getInstance().getLanguageType();
        switch (selectedLanguage) {
            case MultiLanguageUtil.LANGUAGE_CHINESE_SIMPLIFIED:
                lang = "zh";
                break;
            case MultiLanguageUtil.LANGUAGE_CHINESE_TRADITIONAL_TW:
                lang = "tw";
                break;
            case MultiLanguageUtil.LANGUAGE_CHINESE_TRADITIONAL_HK:
                lang = "hk";
                break;
            case MultiLanguageUtil.LANGUAGE_EN:
                lang = "en";
                break;
            case MultiLanguageUtil.LANGUAGE_KO:
                lang = "ko";
                break;
        }

//        //加密
        Long serverCurrentTime = DateFormatUtil.getMyServiceTime();
        String sign = "";
        String requestUrl = request.url().toString();
        int i = requestUrl.indexOf("?");
        if (i > -1) {
            requestUrl = requestUrl.substring(i + 1, requestUrl.length());
            //有参数的情况
            Map<String, String> map = new HashMap<>();
            String[] split = requestUrl.split("&");
            for (String str : split) {
                String[] split1 = str.split("=");
                if (split1.length > 1) {
                    String value = URLDecoder.decode(split1[1], Charset.forName("UTF-8").name());
                    LogUtil.d("value===>" + value);
                    map.put(split1[0], value);
                } else {
                    map.put(split1[0], "");
                }
            }
            try {
                sign = SignUtil.getSignature(map, serverCurrentTime);
                LogUtil.d("requestUrl==>" + sign);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            //没有参数
            try {
                sign = SignUtil.getSignature(null, serverCurrentTime);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        Request newRequest = request.newBuilder()
                .header("appVersion", appVersionCode)
                .header("from", "Android")
                .header("lang", lang)
                .header("Content-Type", "application/json")
                .header("timestamp", serverCurrentTime + "")
                .header("deviceId", deviceId)
                .header("Authorization", AppUtil.getToken())
                .header("channel", channel)
                .header("sign", sign)
                .build();

        LogUtil.d(
                String.format(
                        "请求方式：%s\t\n\n请求地址：%s\t\n\n请求头：\t\n%s\t\n请求体：%s",
                        request.method(),
                        request.url(),
                        newRequest.headers(),
                        request.body()
                )
        );

        Response response = chain.proceed(newRequest);
        long timestamp2 = System.currentTimeMillis();
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        LogUtil.d("请求地址：" + request.url());
        LogUtil.d("请求响应：" + responseBody.string());
        LogUtil.d("耗时：" + (timestamp2 - localCurrentTime) + "毫秒");
        return response;
    }
}
