package com.huohuo.ui.main.finance;

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
import com.huohuo.dao.table.ScoreYj;
import com.huohuo.ui.adapter.OutsideAdapter;
import com.huohuo.ui.adapter.ScoreYjAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ScoreFragment extends BaseFragment {

    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.rv_outside)
    RecyclerView rvOutside;
    Unbinder unbinder;

    private ScoreYjAdapter scoreYjAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_score;
    }

    @Override
    public void initViewAndData() {
        List<ScoreYj> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            ScoreYj scoreYj = new ScoreYj();
            scoreYj.setIndex(1 + i);
            scoreYj.setRemark(i + "awrfe");
            scoreYj.setTime("rggytyh" + i);
            scoreYj.setPhotoUrl("");
            list.add(scoreYj);
        }

        scoreYjAdapter = new ScoreYjAdapter(R.layout.item_score, list);
        rvOutside.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvOutside.setAdapter(scoreYjAdapter);
        scoreYjAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
