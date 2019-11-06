package com.huohuo.app;

import com.dian.commonlib.app.App;
import com.dian.commonlib.app.Constants;
import com.dian.commonlib.lang.MultiLanguageUtil;
import com.dian.commonlib.utils.MmkvUtil;
import com.huohuo.dao.manager.DaoManager;
import com.huohuo.im.IMWebSocket;

/**
 * Created by kennysun on 2019/8/27.
 */

public class HuoHuoApp extends App {
    /**
     * 清空用户信息
     */
    @Override
    public void clearUser() {
        MmkvUtil.clear(Constants.TOKEN,
                Constants.USER_ID,
                Constants.USER_SAFE_SET,
                Constants.USER_PRIVACY_SET,
                Constants.USER_INFO,
                Constants.USER_COMMON_SET,
                Constants.USER_ACCOUNT
        );
        //关闭socket
        IMWebSocket.getImWebSocket().close();
        //关闭数据库
        DaoManager.getDaoManager().closeConnection();
    }

    /**
     * 登录过期
     */
    @Override
    public void loginExpire() {
        //to login
        clearUser();
    }
}
