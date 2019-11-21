package com.huohuo.ui.main.finance;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.dao.table.YiFcDetail;
import com.huohuo.mvp.model.bean.FengcaiList;
import com.huohuo.ui.adapter.YiFcDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class YiFcDetailActivity extends BaseLoadActivity {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvYiFcTitle)
    TextView tvYiFcTitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    YiFcDetailAdapter yiFcDetailAdapter;
    @BindView(R.id.tvRight)
    TextView tvRight;

    FengcaiList.ListBean listBean;

    @Override
    public void retry() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        listBean = (FengcaiList.ListBean) intent.getSerializableExtra("FENGCAI_LIST");
    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_yi_fc_detail;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvYiFcTitle.setText(listBean.getTitle());

        List<FengcaiList.ListBean.FengcaiSetBean> list = listBean.getFengcai_set();

        yiFcDetailAdapter = new YiFcDetailAdapter(R.layout.item_yi_fc_detail, list);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(yiFcDetailAdapter);
        yiFcDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });
    }

}
