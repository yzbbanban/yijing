package com.yjb.ui.main.shop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.glide.GlideEngine;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.mvp.contract.home.ExchangeListContract;
import com.yjb.mvp.model.bean.ExchangeList;
import com.yjb.mvp.presenter.home.ExListPresenter;
import com.yjb.ui.adapter.ShopRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShopRecordActivity extends BaseLoadActivity implements ExchangeListContract.View {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rvRecord)
    RecyclerView rvRecord;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    ShopRecordAdapter shopAdapter;

    int page = 1;
    int pageSize = 10;
    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;
    @BindView(R.id.tvNickName)
    TextView tvNickName;
    @BindView(R.id.tvScore)
    TextView tvScore;

    private ExListPresenter exListPresenter;

    private int type;

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("兑换记录");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(view -> {
            finish();
        });

        exListPresenter = new ExListPresenter();
        exListPresenter.attachView(this, this);
        exListPresenter.getList(AppUtil.getToken(), "1", "10", AppUtil.getUser());

        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                type = 1;
                exListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, AppUtil.getUser());
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                type = 2;
                exListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, AppUtil.getUser());
            }
        });

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_record;
    }


    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    private List<ExchangeList.ListBean> listBean = new ArrayList<>();

    @Override
    public void getExListSuccess(ExchangeList exchangeList) {
        refreshLayout.finishRefresh();//结束刷新
        refreshLayout.finishLoadMore();//结束加载
        if (exchangeList.getList() == null || exchangeList.getList().size() == 0) {
            page--;
            ToastUtil.show(this, "没有数据了");
            return;
        }
        if (type == 1) {
            listBean = new ArrayList<>();
        }
        listBean.addAll(exchangeList.getList());
        tvNickName.setText("" + exchangeList.getUser_nickname());
        tvScore.setText("" + exchangeList.getUser_score());
        GlideEngine.load(ivPhoto, BuildConfig.API_IMG_HOST + AppUtil.getImage());
        shopAdapter = new ShopRecordAdapter(R.layout.item_shop_record, listBean);
        rvRecord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvRecord.setAdapter(shopAdapter);

        shopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });
    }

}
