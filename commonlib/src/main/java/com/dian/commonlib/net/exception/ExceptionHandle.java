package com.dian.commonlib.net.exception;

import com.dian.commonlib.R;
import com.dian.commonlib.utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * Created by kennysun on 2019/8/7.
 */

public class ExceptionHandle {
    public static int errorCode = ErrorStatus.UNKNOWN_ERROR;
    public static Object errorMsg = R.string.request_fail;

    public static Object handleException(Throwable e) {
        LogUtil.d("ExceptionHandle:handleException:e=" + e);
        e.printStackTrace();
        if (e instanceof SocketTimeoutException) {//网络超时
            errorMsg = R.string.network_connection_exception;
            errorCode = ErrorStatus.NETWORK_ERROR;
        } else if (e instanceof ConnectException) { //均视为网络错误
            errorMsg = R.string.net_not_connected;
            errorCode = ErrorStatus.NETWORK_ERROR;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
        ) {   //均视为解析错误
            errorMsg = R.string.data_parsing_exception;
            errorCode = ErrorStatus.UNKNOWN_ERROR;
        } else if (e instanceof ApiException) {//服务器返回的错误信息
            errorMsg = ((ApiException) e).msg;
            errorCode = ((ApiException) e).code;
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            String errorBodyStr = null;
            try {
                errorBodyStr = httpException.response().errorBody().string();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (errorBodyStr.isEmpty()) {
                int code = httpException.response().code();
                String message = httpException.response().message();
                if (code == 500) {
                    errorMsg = R.string.server_error;
                    errorCode = ErrorStatus.SERVER_ERROR;
                } else {
                    errorMsg = R.string.not_know_error;
                    errorCode = ErrorStatus.UNKNOWN_ERROR;
                }
            } else {
                try {
                    ErrorBody errorBody = new Gson().fromJson(errorBodyStr, ErrorBody.class);
                    errorMsg = errorBody.message;
                    errorCode = errorBody.code;
                } catch (Exception el) {
                    errorMsg = R.string.not_know_error;
                    errorCode = ErrorStatus.UNKNOWN_ERROR;
                }

            }
        } else if (e instanceof UnknownHostException) {
            errorMsg = R.string.network_connection_exception;
            errorCode = ErrorStatus.NETWORK_ERROR;
        } else if (e instanceof IllegalArgumentException) {
            errorMsg = R.string.parameter_error;
            errorCode = ErrorStatus.SERVER_ERROR;
        } else {//未知错误
            errorMsg = R.string.not_know_error;
            errorCode = ErrorStatus.UNKNOWN_ERROR;
        }
        return errorMsg;
    }
}
