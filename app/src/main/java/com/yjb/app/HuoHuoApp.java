package com.yjb.app;

import android.content.Context;

import com.dian.commonlib.app.App;
import com.dian.commonlib.app.Constants;
import com.dian.commonlib.utils.MmkvUtil;
import com.yjb.R;
import com.yjb.dao.manager.DaoManager;
import com.yjb.im.IMWebSocket;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

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
//        IMWebSocket.getImWebSocket().close();
        //关闭数据库
        DaoManager.getDaoManager().closeConnection();
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorAccent, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
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
