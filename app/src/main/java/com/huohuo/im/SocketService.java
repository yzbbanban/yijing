package com.huohuo.im;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.dian.commonlib.utils.LogUtil;
import com.huohuo.dao.manager.FriendManager;
import com.huohuo.dao.manager.GroupManager;

/**
 * Created by kennysun on 2019/8/8.
 */

public class SocketService extends Service {
    private FriendManager friendManager;
    private GroupManager groupManager;

    public static void start(final Context context) {
        context.startService(new Intent(context, SocketService.class));
    }

    public static void stop(final Context context) {
        context.stopService(new Intent(context, SocketService.class));
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        friendManager = new FriendManager();
        groupManager = new GroupManager();




        //开启socket
        IMWebSocket.getImWebSocket().setmListener(listener).init();
    }

    /**
     * START_REDELIVER_INTENT 如果有未处理完的Intent，被杀后会重启，并在重启后发送所有Intent。stopSelf后释放保持的Intent。
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_REDELIVER_INTENT;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    IMWebSocketListener listener = new IMWebSocketListener() {
        @Override
        public void onMessage(String text) {
            LogUtil.d("SocketService onMessage===" + text);
        }
    };
}
