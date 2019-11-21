package com.yjb.im;

import com.dian.commonlib.app.Constants;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.DeviceUtil;
import com.dian.commonlib.utils.LogUtil;
import com.yjb.BuildConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * Created by kennysun on 2019/8/7.
 */

public class IMWebSocket {
    public enum SOCKET_STATE {
        NOT_OPEN,
        WAITINT,
        OPENING,
        OPENED,
        CLOSING,
        CLOSED,
        FAIL,
        NOT_NET
    }

    public static String TAG = "IMWebSocket";
    public static String BASE_SOCKET_URL = BuildConfig.API_SOCKET_HOST;
    public static int CHANGE_ACCOUNT = 4001;
    public static volatile SOCKET_STATE state = SOCKET_STATE.NOT_OPEN;
    public static Request mRequest;
    public static OkHttpClient mClient;
    public static WebSocket mWebSocket;
    public static IMWebSocketListener mListener;
    public static ExecutorService mExecutorService;
    public static IMWebSocket imWebSocket;

    public IMWebSocket setmListener(IMWebSocketListener mListener) {
        IMWebSocket.mListener = mListener;
        return imWebSocket;
    }

    public static synchronized IMWebSocket getImWebSocket() {
        synchronized (IMWebSocket.class) {
            if (imWebSocket == null) {
                imWebSocket = new IMWebSocket();
            }
            if (mExecutorService == null) {
                mExecutorService = Executors.newSingleThreadExecutor();
            }
            return imWebSocket;
        }
    }


    public void init() {
        if (state == SOCKET_STATE.OPENING || state == SOCKET_STATE.OPENED)
            return;
        state = SOCKET_STATE.OPENING;
        String token = AppUtil.getToken();
        String deviceId = DeviceUtil.getDeviceId();
        String socketUrl = BASE_SOCKET_URL + "?token=" + token + "&deviceId=" + deviceId;
        LogUtil.d("$TAG:init:weburl=$socketUrl");
        mRequest = new Request.Builder()
                .url(socketUrl)
                .build();
        mClient = new OkHttpClient.Builder()
                .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        mWebSocket = mClient.newWebSocket(mRequest, mListener);
        mClient.dispatcher().executorService().shutdown();
    }

    public void send(String msg) {
        mWebSocket.send(msg);
    }

    public void close() {
        mWebSocket.close(CHANGE_ACCOUNT, "切换账号");
    }

    private RetryThread retryThread = new RetryThread();

    public void retry() {
        mExecutorService.execute(retryThread);
    }

    class RetryThread implements Runnable {

        @Override
        public void run() {
            state = SOCKET_STATE.WAITINT;
            Observable.timer(5, TimeUnit.SECONDS)
                    .subscribe(aLong -> {
                        if (!(state == SOCKET_STATE.OPENING || state == SOCKET_STATE.OPENED))
                            IMWebSocket.getImWebSocket().init();
                    }, throwable -> {
                        LogUtil.d("${IMWebSocket.TAG}:RetryThread:t=$it");
                        if (!(state == SOCKET_STATE.OPENING || state == SOCKET_STATE.OPENED))
                            IMWebSocket.getImWebSocket().init();
                    });
        }
    }
}
