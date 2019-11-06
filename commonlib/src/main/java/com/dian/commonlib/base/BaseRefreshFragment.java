package com.dian.commonlib.base;

import android.support.annotation.NonNull;

import com.dian.commonlib.R;
import com.dian.commonlib.utils.DateFormatUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.header.MaterialHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * Created by kennysun on 2019/8/7.
 */

abstract public class BaseRefreshFragment extends BaseLoadFragment {
    private SmartRefreshLayout mSmartRefreshLayout;

    public abstract SmartRefreshLayout getSmartRefreshView();

    public abstract void refresh();

    protected abstract void loadMore();

    public boolean canLoadMore() {
        return false;
    }

    public boolean setHeader() {
        return true;
    }

    public boolean setFooter() {
        return true;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        mSmartRefreshLayout = getSmartRefreshView();
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.setPrimaryColorsId(R.color.colorPrimary, R.color.colorAccent);
            if (setHeader())
                mSmartRefreshLayout.setRefreshHeader(new MaterialHeader(getBaseActivity()));
            if (setFooter())
                mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(getBaseActivity()).setDrawableSize(20f));
            mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    loadMore();
                }

                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    refresh();
                }
            });
            mSmartRefreshLayout.setEnableLoadMore(canLoadMore());
        }
    }

    @Override
    public void onComplete() {
        super.onComplete();
        mSmartRefreshLayout.finishLoadMore();
        mSmartRefreshLayout.finishRefresh();
    }
}
