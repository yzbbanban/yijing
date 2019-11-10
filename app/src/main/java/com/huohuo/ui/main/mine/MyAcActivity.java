package com.huohuo.ui.main.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.dao.table.OutsideDetail;
import com.huohuo.ui.adapter.OutsideAdapter;
import com.huohuo.ui.asset.AllAssetActvity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAcActivity extends BaseLoadActivity {


    @BindView(R.id.rv_history_ac)
    RecyclerView rvHistoryAc;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private OutsideAdapter outsideAdapter;

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
        List<OutsideDetail> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            OutsideDetail outsideDetail = new OutsideDetail();
            outsideDetail.setAddress("jimjim" + i);
            outsideDetail.setId(1 + i);
            outsideDetail.setLimitTime("adawa" + i);
            outsideDetail.setTime("rggytyh" + i);
            outsideDetail.setTitle("11月" + i + "日小区巡查检查");
            outsideDetail.setImage("");
            list.add(outsideDetail);
        }

        outsideAdapter = new OutsideAdapter(R.layout.item_outside, list);
        rvHistoryAc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvHistoryAc.setAdapter(outsideAdapter);
        outsideAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MyAcActivity.this, AcDetailActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);

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
