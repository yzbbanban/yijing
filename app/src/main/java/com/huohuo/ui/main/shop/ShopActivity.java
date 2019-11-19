package com.huohuo.ui.main.shop;

import android.content.Intent;
import android.os.Bundle;
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
import com.huohuo.R;
import com.huohuo.dao.table.ShopDetail;
import com.huohuo.mvp.contract.home.MallListContract;
import com.huohuo.mvp.model.bean.MallList;
import com.huohuo.mvp.presenter.home.MallListPresenter;
import com.huohuo.ui.adapter.ShopAdapter;
import com.huohuo.ui.dialog.MyPayDialog;

import java.util.BitSet;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private MallListPresenter mallListPresenter;

    ShopAdapter shopAdapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
