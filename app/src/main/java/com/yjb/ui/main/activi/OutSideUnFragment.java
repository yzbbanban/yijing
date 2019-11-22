package com.yjb.ui.main.activi;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseFragment;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yjb.R;
import com.yjb.mvp.contract.home.YjAcListContract;
import com.yjb.mvp.model.bean.ActivityList;
import com.yjb.mvp.model.bean.YjActivityDetail;
import com.yjb.mvp.presenter.home.AcListPresenter;
import com.yjb.ui.adapter.OutsideAdapter;
import com.yjb.ui.main.mine.AcDetailActivity;

import java.util.List;

import butterknife.BindView;

public class OutSideUnFragment extends BaseFragment implements YjAcListContract.View {

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
        //已结束
        acListPresenter = new AcListPresenter();
        acListPresenter.attachView(this, getBaseActivity());
        acListPresenter.getList(AppUtil.getToken(), "1", "10", "3");
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                acListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, "3");
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                acListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, "3");
            }
        });
    }


    @Override
    public void getAcYjListSuccess(ActivityList activityList) {
        refreshLayout.finishRefresh();//结束刷新
        refreshLayout.finishLoadMore();//结束加载
        if (activityList.getList() == null
                || activityList.getList().size() == 0) {
            page--;
            ToastUtil.show(getBaseActivity(), "没有数据了");
            return;
        }

        List<ActivityList.ListBean> list = activityList.getList();
        outsideAdapter = new OutsideAdapter(R.layout.item_outside, list);
        rvOutside.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        rvOutside.setAdapter(outsideAdapter);
        outsideAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getBaseActivity(), AcDetailActivity.class);
                intent.putExtra("type", 4);
                intent.putExtra("AC_MY_DETAIL", list.get(position));
                startActivity(intent);

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
