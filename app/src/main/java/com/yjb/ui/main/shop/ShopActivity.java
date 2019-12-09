package com.yjb.ui.main.shop;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.glide.GlideEngine;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.mvp.contract.home.ExchangePayContract;
import com.yjb.mvp.contract.home.MallListContract;
import com.yjb.mvp.model.bean.MallList;
import com.yjb.mvp.presenter.home.ExchangePayPresenter;
import com.yjb.mvp.presenter.home.MallListPresenter;
import com.yjb.scan.FinishListener;
import com.yjb.ui.adapter.ShopAdapter;
import com.yjb.ui.dialog.MyPayDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;

import java.util.*;

public class ShopActivity extends BaseLoadActivity implements MallListContract.View, ExchangePayContract.View {
    private static final String TAG = "ShopActivity";

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ivPhoto)
    AppCompatImageView ivPhoto;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.acbBtnRecord)
    AppCompatButton acbBtnRecord;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private MallListPresenter mallListPresenter;

    private ExchangePayPresenter exchangePayPresenter;

    ShopAdapter shopAdapter;

    int page = 1;
    int pageSize = 10;

    private int type;

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("积分商城");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(view -> {
            finish();
        });

        mallListPresenter = new MallListPresenter();
        exchangePayPresenter = new ExchangePayPresenter();
        mallListPresenter.attachView(this, this);
        exchangePayPresenter.attachView(this, this);

        mallListPresenter.getList(AppUtil.getToken(), "1", "10", AppUtil.getUser());
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                type = 1;
                mallListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, AppUtil.getUser());
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                type = 2;
                mallListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, AppUtil.getUser());
            }
        });
    }

    @OnClick({R.id.acbBtnRecord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.acbBtnRecord:
                startActivity(new Intent(this, ShopRecordActivity.class));
                break;
        }
    }

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop;
    }

    private List<MallList.ListBean> listBean = new ArrayList<>();

    @Override
    public void getMallListSuccess(MallList mallList) {
        refreshLayout.finishRefresh();//结束刷新
        refreshLayout.finishLoadMore();//结束加载
        if (type == 1) {
            listBean = new ArrayList<>();
        }
        type = 1;
        if (mallList.getList() == null || mallList.getList().size() == 0) {
            page--;
            ToastUtil.show(this, "没有数据了");
            return;
        }

        listBean.addAll(mallList.getList());
        tvName.setText("" + mallList.getUser_nickname());
        tvNumber.setText("" + mallList.getUser_score());
        GlideEngine.loadRound(ivPhoto, "" + AppUtil.getImage());
        shopAdapter = new ShopAdapter(R.layout.item_shop, listBean);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(shopAdapter);

        shopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });

        shopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.i(TAG, "onItemChildClick: " + listBean.get(position).getStatus());
                if (view.getId() == R.id.acbBtnPay) {
                    MallList.ListBean pay = listBean.get(position);
                    new MyPayDialog(ShopActivity.this, exchangePayPresenter)
                            .setPayId("兑换" + pay.getName() + "消耗" + pay.getIntegral() + "积分", pay.getId(), "" + pay.getIntegral()).show();

                }
            }
        });
    }

    @Override
    public void getPaySuccess(String msg) {
        ToastUtil.show(this, "兑换成功");
    }

    @Override
    public void onError(Object msg, int code) {
//        ToastUtil.showLong(this, "" + msg);
        displayFrameworkBugMessageAndExit("" + msg);
    }

    private void displayFrameworkBugMessageAndExit(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.button_ok, null);
        builder.show();
    }

}
