package com.huohuo.ui.main.finance;

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
import com.huohuo.R;
import com.huohuo.mvp.contract.home.YjFcListContract;
import com.huohuo.mvp.model.bean.FengcaiList;
import com.huohuo.mvp.presenter.home.FcListPresenter;
import com.huohuo.ui.adapter.YiFcModuleAdapter;
import com.huohuo.ui.main.shop.ShopActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class YiFcActivity extends BaseLoadActivity implements YjFcListContract.View {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private YiFcModuleAdapter yiFcModuleAdapter;

    private FcListPresenter fcListPresenter;

    int page = 1;
    int pageSize = 10;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yi_fc;
    }

    @Override
    public void retry() {

    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("义警风采");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initModule();
    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }


    private void initModule() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        fcListPresenter = new FcListPresenter();
        fcListPresenter.attachView(this, this);
        fcListPresenter.getList(AppUtil.getToken(), "1", "10");
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                fcListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                fcListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize);
            }
        });
    }


    @Override
    public void getYjFcListSuccess(FengcaiList fengcaiList) {
        refreshLayout.finishRefresh();//结束刷新
        refreshLayout.finishLoadMore();//结束加载
        if (fengcaiList.getList() == null || fengcaiList.getList().size() == 0) {
            page--;
            ToastUtil.show(this, "没有数据了");
            return;
        }
        yiFcModuleAdapter = new YiFcModuleAdapter(R.layout.item_yifc_module, fengcaiList.getList());
        recyclerview.setAdapter(yiFcModuleAdapter);
        yiFcModuleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(YiFcActivity.this, YiFcDetailActivity.class);
                intent.putExtra("FENGCAI_LIST", fengcaiList.getList().get(position));
                startActivity(intent);
            }
        });
    }
}
