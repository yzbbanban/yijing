package com.dian.commonlib.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.dian.commonlib.app.Constants;
import com.dian.commonlib.utils.NetworkUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by kennysun on 2019/8/7.
 */

public class NetWorkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //检测API是不是小于21，因为到了API21之后getNetworkInfo(int networkType)方法被弃用
        boolean conn = NetworkUtil.isConnected(context);
        if (conn) {
            EventBus.getDefault().post(Constants.NET_STATE.CONNECT);
        } else {
            EventBus.getDefault().post(Constants.NET_STATE.DISCONNECT);
        }
    }
}
