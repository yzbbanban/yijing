package com.huohuo.ui.main.activi;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseFragment;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.mvp.contract.home.YjAcListContract;
import com.huohuo.mvp.model.bean.ActivityList;
import com.huohuo.mvp.model.bean.YjActivityDetail;
import com.huohuo.mvp.presenter.home.AcListPresenter;
import com.huohuo.ui.adapter.OutsideAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class OutSideFragment extends BaseFragment implements YjAcListContract.View {

    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.rv_outside)
    RecyclerView rvOutside;
    Unbinder unbinder;

    private OutsideAdapter outsideAdapter;

    private AcListPresenter acListPresenter;

    @Override
    public int getLayout() {
        return R.layout.fragment_out_side;
    }

    @Override
    public void initViewAndData() {
        Bundle bundle = getArguments();
        String type = bundle.getString("type");
        acListPresenter = new AcListPresenter();
        acListPresenter.attachView(this, getActivity());
        acListPresenter.getList(AppUtil.getToken(), "1", "10", type);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getAcYjListSuccess(ActivityList activityList) {
        List<YjActivityDetail> list = activityList.getYjActivityDetails();
        Integer total = activityList.getTotal();
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
    public void showLoading(boolean show) {

    }

    @Override
    public void onError(Object msg, int code) {

    }

    @Override
    public void onComplete() {

    }
}
