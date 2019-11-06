package com.dian.commonlib.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by kennysun on 2019/8/6.
 */

public class SignUtil {
    public static String SECRET = "secret";

    /**
     * 签名生成算法
     *
     * @param paramMap  参数
     * @param timestamp 时间戳
     * @return 签名
     * @throw sources IOException
     */
    public static String getSignature(Map<String, String> paramMap, Long timestamp) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String secret = MmkvUtil.decodeString(SECRET, "");
        if (TextUtils.isEmpty(secret)) {
            return "";
        }
        // 遍历排序后的字典，将所有参数按"keyValue"格式拼接在一起
        StringBuilder baseStr = new StringBuilder();
        //在参数前添加秘钥
        baseStr.append(secret);
        // 先将参数以其参数名的字典序升序进行排序
        if (paramMap != null) {
            Map<String, String> sortedParams = new TreeMap<>(paramMap);
            Set<Map.Entry<String, String>> entries = sortedParams.entrySet();
            for (Map.Entry<String, String> param : entries) {
                //忽略空参数
                if (param.getValue() != null && !"".equals(param.getValue().trim())) {
                    baseStr.append(param.getKey()).append(param.getValue());
                }
            }
        }
        baseStr.append(timestamp);
        //在参数后添加秘钥
        baseStr.append(secret);
        // 使用MD5对待签名串求签
        LogUtil.d("secret===>" + baseStr);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(baseStr.toString().getBytes("UTF-8"));
        // 将MD5输出的二进制结果转换为小写的十六进制
        return toHexString(bytes);
    }

    /**
     * 将字节流转换成16进制字符串
     *
     * @param bytes 字节流
     * @return 16进制字符串
     */
    public static String toHexString(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }

    /**
     * 保存secret
     */
    public static void saveSecret(String secret) {
        MmkvUtil.encodeString(SECRET, secret);
    }
}
