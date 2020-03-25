package com.yjb.ui.main.mine;

import android.content.Intent;
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
import com.yjb.R;
import com.yjb.mvp.contract.home.MyAcListContract;
import com.yjb.mvp.model.bean.AcMyList;
import com.yjb.mvp.presenter.home.MyAcListPresenter;
import com.yjb.ui.adapter.MyOutsideAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.*;

import butterknife.BindView;

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
    private int type;

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
                type = 1;
                myAcListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, AppUtil.getUser());
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                type = 2;
                myAcListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, AppUtil.getUser());
            }
        });
    }

    private List<AcMyList.ListBean> listBean = new ArrayList<>();

    @Override
    public void getMyAcSuccess(AcMyList acMyList) {
        refreshLayout.finishRefresh();//结束刷新
        refreshLayout.finishLoadMore();//结束加载
        if (acMyList.getList() == null || acMyList.getList().size() == 0) {
            page--;
            ToastUtil.show(this, "没有数据了");
            return;
        }
        if (type == 1) {
            listBean = new ArrayList<>();
        }
        listBean.addAll(acMyList.getList());
        outsideAdapter = new MyOutsideAdapter(R.layout.item_outside, listBean);
        rvHistoryAc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvHistoryAc.setAdapter(outsideAdapter);
        outsideAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MyAcActivity.this, AcDetailActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("AC_MY_DETAIL", listBean.get(position));
                startActivity(intent);

            }
        });

    }
}
