package com.yjb.ui.main.shop;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.yjb.R;
import com.yjb.mvp.contract.home.MallListContract;
import com.yjb.mvp.model.bean.MallList;
import com.yjb.mvp.presenter.home.MallListPresenter;
import com.yjb.ui.adapter.ShopAdapter;
import com.yjb.ui.dialog.MyPayDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopActivity extends BaseLoadActivity implements MallListContract.View {
    private static final String TAG = "ShopActivity";

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
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

    ShopAdapter shopAdapter;

    int page = 1;
    int pageSize = 10;

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("积分商城");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(view -> {
            finish();
        });

        mallListPresenter = new MallListPresenter();
        mallListPresenter.attachView(this, this);
        mallListPresenter.getList(AppUtil.getToken(), "1", "10", AppUtil.getUser());
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                mallListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize, AppUtil.getUser());
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
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

    @Override
    public void getMallListSuccess(MallList mallList) {
        refreshLayout.finishRefresh();//结束刷新
        refreshLayout.finishLoadMore();//结束加载
        if (mallList.getList() == null || mallList.getList().size() == 0) {
            page--;
            ToastUtil.show(this, "没有数据了");
            return;
        }
        tvName.setText("" + mallList.getUser_nickname());
        tvNumber.setText("" + mallList.getUser_score());
        shopAdapter = new ShopAdapter(R.layout.item_shop, mallList.getList());
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
                Log.i(TAG, "onItemChildClick: " + mallList.getList().get(position).getStatus());
                if (view.getId() == R.id.acbBtnPay) {
                    MallList.ListBean pay = mallList.getList().get(position);
                    new MyPayDialog(ShopActivity.this).setPayId(pay.getName(), pay.getId()).show();

                }
            }
        });
    }

}
