package com.huohuo.ui.main.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.dao.table.OutsideDetail;
import com.huohuo.mvp.contract.home.MyAcListContract;
import com.huohuo.mvp.model.bean.AcMyList;
import com.huohuo.mvp.presenter.home.MyAcListPresenter;
import com.huohuo.ui.adapter.MyOutsideAdapter;
import com.huohuo.ui.adapter.OutsideAdapter;
import com.huohuo.ui.asset.AllAssetActvity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAcActivity extends BaseLoadActivity implements MyAcListContract.View {


    @BindView(R.id.rv_history_ac)
    RecyclerView rvHistoryAc;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private MyOutsideAdapter outsideAdapter;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private MyAcListPresenter myAcListPresenter;

    int page = 1;
    int pageSize = 10;

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_ac;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("我参与的活动");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        myAcListPresenter = new MyAcListPresenter();
        myAcListPresenter.attachView(this, this);

        myAcListPresenter.getList(AppUtil.getToken(), "1", "10", AppUtil.getUser());

        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                myAcListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, AppUtil.getUser());
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                myAcListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, AppUtil.getUser());
            }
        });
    }

    @Override
    public void getMyAcSuccess(AcMyList acMyList) {
        refreshLayout.finishRefresh();//结束刷新
        refreshLayout.finishLoadMore();//结束加载
        if (acMyList.getList() == null || acMyList.getList().size() == 0) {
            page--;
            ToastUtil.show(this, "没有数据了");
            return;
        }

        outsideAdapter = new MyOutsideAdapter(R.layout.item_outside, acMyList.getList());
        rvHistoryAc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvHistoryAc.setAdapter(outsideAdapter);
        outsideAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MyAcActivity.this, AcDetailActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("AC_MY_DETAIL", acMyList.getList().get(position));
                startActivity(intent);

            }
        });

    }
}
