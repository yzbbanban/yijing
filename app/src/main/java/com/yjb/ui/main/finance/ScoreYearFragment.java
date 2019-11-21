package com.yjb.ui.main.finance;

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
import com.yjb.mvp.contract.home.RankListContract;
import com.yjb.mvp.model.bean.RankList;
import com.yjb.mvp.presenter.home.RankListPresenter;
import com.yjb.ui.adapter.ScoreYjAdapter;

import butterknife.BindView;

public class ScoreYearFragment extends BaseFragment implements RankListContract.View {

    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.rv_outside)
    RecyclerView rvOutside;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ScoreYjAdapter scoreYjAdapter;
    String type = "1";
    private RankListPresenter rankListPresenter;
    int page = 1;
    int pageSize = 10;

    @Override
    public int getLayout() {
        return R.layout.fragment_score;
    }

    @Override
    public void initViewAndData() {
        rankListPresenter = new RankListPresenter();
        rankListPresenter.attachView(this, getBaseActivity());
        rankListPresenter.getList(AppUtil.getToken(), "1", "10", 2);
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                rankListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, 2);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                rankListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, 2);
            }
        });
    }


    @Override
    public void getRankListSuccess(RankList msg) {
        refreshLayout.finishRefresh();//结束刷新
        refreshLayout.finishLoadMore();//结束加载
        if (msg.getList() == null || msg.getList().size() == 0) {
            page--;
            ToastUtil.show(getBaseActivity(), "没有数据了");
            return;
        }
        for (int i = 0, len = msg.getList().size(); i < len; i++) {
            msg.getList().get(i).setIndex(i + 1);
        }

        scoreYjAdapter = new ScoreYjAdapter(R.layout.item_score, msg.getList());
        rvOutside.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvOutside.setAdapter(scoreYjAdapter);
        scoreYjAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
