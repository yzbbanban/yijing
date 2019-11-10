package com.huohuo.ui.main.shop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.dao.table.ShopRecordDetail;
import com.huohuo.ui.adapter.ShopRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShopRecordActivity extends BaseLoadActivity {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rvRecord)
    RecyclerView rvRecord;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    ShopRecordAdapter shopAdapter;

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("兑换记录");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(view -> {
            finish();
        });

        List<ShopRecordDetail> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            ShopRecordDetail shopDetail = new ShopRecordDetail();
            shopDetail.setId(1 + i);
            shopDetail.setPrice("15" + i);
            shopDetail.setUrl("rggytyh" + i);
            shopDetail.setTime("2019-11-2 9:" + i);
            shopDetail.setStatus(1);
            shopDetail.setName("金龙鱼调和油"+i);
            list.add(shopDetail);
        }

        shopAdapter = new ShopRecordAdapter(R.layout.item_shop_record, list);
        rvRecord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvRecord.setAdapter(shopAdapter);

        shopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


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

}
