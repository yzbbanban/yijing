package com.yjb.ui.asset;

import com.dian.commonlib.base.BaseRefreshFragment;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.yjb.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by kennysun on 2019/9/5.
 */

public class YunWalletFragment extends BaseRefreshFragment {
    public static YunWalletFragment getYunWalletFragment() {
        YunWalletFragment yunWalletFragment = new YunWalletFragment();
        return yunWalletFragment;
    }

    @Override
    protected void retry() {

    }

    @Override
    protected MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_yun_wallet;
    }

    @Override
    public SmartRefreshLayout getSmartRefreshView() {
        return null;
    }

    @Override
    public void refresh() {

    }

    @Override
    protected void loadMore() {

    }
}
