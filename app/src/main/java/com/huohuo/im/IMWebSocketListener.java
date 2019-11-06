package com.huohuo.im;

import android.support.annotation.Nullable;

import com.dian.commonlib.utils.LogUtil;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by kennysun on 2019/8/7.
 */

public abstract class IMWebSocketListener extends WebSocketListener {
    public abstract void onMessage(String text);
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        LogUtil.d(IMWebSocket.TAG + ":onOpen:response=" + response);
        IMWebSocket.state = IMWebSocket.SOCKET_STATE.OPENED;
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        LogUtil.d(IMWebSocket.TAG + ":onMessage:onMessage=" + text);
        onMessage(text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        LogUtil.d(IMWebSocket.TAG + "onMessage:onMessage=" + bytes);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        LogUtil.d(IMWebSocket.TAG + "onClosing:reason=" + reason + ",code=" + code);
        IMWebSocket.state = IMWebSocket.SOCKET_STATE.CLOSING;
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        LogUtil.d(IMWebSocket.TAG+"onClosed:reason="+reason+",code="+code);
        IMWebSocket.state = IMWebSocket.SOCKET_STATE.CLOSED;
        if (code != IMWebSocket.CHANGE_ACCOUNT) {
            IMWebSocket.getImWebSocket().retry();
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
        LogUtil.d(IMWebSocket.TAG+"onClosed:response="+response+",Throwable="+t);
        IMWebSocket.state = IMWebSocket.SOCKET_STATE.FAIL;
        IMWebSocket.getImWebSocket().retry();
    }
}
