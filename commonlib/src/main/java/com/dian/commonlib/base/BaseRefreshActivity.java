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

abstract public class BaseRefreshActivity extends BaseLoadActivity {
    private SmartRefreshLayout mSmartRefreshLayout;

    abstract SmartRefreshLayout getSmartRefreshView();

    abstract void refresh();

    protected abstract void loadMore();

    public boolean canLoadMore() {
        return false;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        mSmartRefreshLayout = getSmartRefreshView();

        mSmartRefreshLayout.setPrimaryColorsId(R.color.colorPrimary, R.color.colorAccent);
        mSmartRefreshLayout.setRefreshHeader(new MaterialHeader(this));
        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(this).setDrawableSize(20f));
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

    @Override
    public void onComplete() {
        super.onComplete();
        mSmartRefreshLayout.finishLoadMore();
        mSmartRefreshLayout.finishRefresh();
    }
}
