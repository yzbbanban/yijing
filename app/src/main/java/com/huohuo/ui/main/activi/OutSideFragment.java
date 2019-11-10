package com.huohuo.ui.main.activi;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseFragment;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.dao.table.OutsideDetail;
import com.huohuo.ui.adapter.OutsideAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import java.util.*;

public class OutSideFragment extends BaseFragment {

    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.rv_outside)
    RecyclerView rvOutside;
    Unbinder unbinder;

    private OutsideAdapter outsideAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_out_side;
    }

    @Override
    public void initViewAndData() {
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
        rvOutside.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvOutside.setAdapter(outsideAdapter);
        outsideAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
