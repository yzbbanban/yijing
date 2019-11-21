package com.huohuo.ui.main.activi;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseFragment;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.mvp.contract.home.YjAcListContract;
import com.huohuo.mvp.model.bean.ActivityList;
import com.huohuo.mvp.model.bean.YjActivityDetail;
import com.huohuo.mvp.presenter.home.AcListPresenter;
import com.huohuo.ui.adapter.OutsideAdapter;
import com.huohuo.ui.main.shop.ShopActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class OutSideFragment extends BaseFragment implements YjAcListContract.View {

    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.rv_outside)
    RecyclerView rvOutside;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private OutsideAdapter outsideAdapter;

    private AcListPresenter acListPresenter;


    int page = 1;
    int pageSize = 10;

    @Override
    public int getLayout() {
        return R.layout.fragment_out_side;
    }

    @Override
    public void initViewAndData() {
        Bundle bundle = getArguments();
        String type = bundle.getString("type");
        acListPresenter = new AcListPresenter();
        acListPresenter.attachView(this, getActivity());
        acListPresenter.getList(AppUtil.getToken(), "1", "10", type);
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                acListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, AppUtil.getUser());
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                acListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, AppUtil.getUser());
            }
        });
    }


    @Override
    public void getAcYjListSuccess(ActivityList activityList) {
        refreshLayout.finishRefresh();//结束刷新
        refreshLayout.finishLoadMore();//结束加载
        if (activityList.getYjActivityDetails() == null
                || activityList.getYjActivityDetails().size() == 0) {
            page--;
            ToastUtil.show(getBaseActivity(), "没有数据了");
            return;
        }

        List<YjActivityDetail> list = activityList.getYjActivityDetails();
        Integer total = activityList.getTotal();
        outsideAdapter = new OutsideAdapter(R.layout.item_outside, list);
        rvOutside.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvOutside.setAdapter(outsideAdapter);
        outsideAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void onError(Object msg, int code) {

    }

    @Override
    public void onComplete() {

    }
}
