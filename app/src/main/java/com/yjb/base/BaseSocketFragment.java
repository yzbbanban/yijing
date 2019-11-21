package com.yjb.base;

import android.content.Intent;

import com.dian.commonlib.app.Constants;
import com.dian.commonlib.base.BaseLoadFragment;
import com.yjb.app.HuoHuoConstants;
import com.yjb.im.IMWebSocket;
import com.yjb.im.SocketService;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by kennysun on 2019/8/7.
 */

abstract public class BaseSocketFragment extends BaseLoadFragment {

   public abstract void noNet();

    public abstract void socketUnconnected();

    @Override
    public boolean canRegisterEventbus() {
        return true;
    }

    /**
     * 订阅socket状态
     */
    @Subscribe
    void socketState(IMWebSocket.SOCKET_STATE state) {
        switch (state) {
            case NOT_NET://没网
                noNet();
                break;
            case NOT_OPEN:
            case CLOSED:
            case FAIL:
                socketUnconnected();//没连接
                break;
            case OPENED:
                socketRety();//重新连接成功
                break;
            default:
                break;
        }
    }

    /**
     * 订阅网络状态
     */
    @Subscribe
    void netState(Constants.NET_STATE state) {
        switch (state) {
            case DISCONNECT://没网重连
                socketRety();
                noNet();
                break;
            default:
                break;
        }
    }

    /**
     * 重连
     */
    private void socketRety() {
        Intent intent = new Intent(getBaseActivity(), SocketService.class);
        intent.putExtra(HuoHuoConstants.SOCKET_TYPE, HuoHuoConstants.SOCKET_RETY);
        getBaseActivity().startService(intent);
    }
}
