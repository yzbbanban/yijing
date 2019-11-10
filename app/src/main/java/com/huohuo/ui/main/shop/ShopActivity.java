package com.huohuo.ui.main.shop;

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
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.MyDialog;
import com.huohuo.R;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.dao.table.ShopDetail;
import com.huohuo.ui.adapter.ShopAdapter;
import com.huohuo.ui.dialog.MyPayDialog;
import com.huohuo.ui.dialog.MyQrDialog;
import com.huohuo.ui.main.activi.YjActivity;
import com.huohuo.ui.user.MobileCodeActivty;
import com.huohuo.ui.web.HuoHuoWebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopActivity extends BaseLoadActivity {
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
    ShopAdapter shopAdapter;

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("积分商城");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(view -> {
            finish();
        });
        List<ShopDetail> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            ShopDetail shopDetail = new ShopDetail();
            shopDetail.setId(1 + i);
            shopDetail.setPrice("15" + i);
            shopDetail.setUrl("rggytyh" + i);
            shopDetail.setName("awfgeounho" + i);
            list.add(shopDetail);
        }

        shopAdapter = new ShopAdapter(R.layout.item_shop, list);
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
                Log.i(TAG, "onItemChildClick: " + list.get(position).getPrice());
                if (view.getId() == R.id.acbBtnPay) {
                    ToastUtil.show(ShopActivity.this, "子类的：" + list.get(position).getPrice());
                    ShopDetail pay = list.get(position);
                    new MyPayDialog(ShopActivity.this).setPayId(pay.getName(), pay.getId()).show();

                }
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
}
