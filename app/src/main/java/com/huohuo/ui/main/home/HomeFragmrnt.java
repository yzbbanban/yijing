package com.huohuo.ui.main.home;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseRefreshFragment;
import com.dian.commonlib.glide.GlideEngine;
import com.dian.commonlib.utils.DeviceUtil;
import com.dian.commonlib.utils.RxPermissionsCallbackUtil;
import com.dian.commonlib.utils.RxPermissionsUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.huohuo.R;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.mvp.model.bean.HomeRecomBean;
import com.huohuo.mvp.model.bean.ModuleBean;
import com.huohuo.mvp.model.bean.ModuleItemBean;
import com.huohuo.ui.adapter.HomeModuleAdapter;
import com.huohuo.ui.adapter.HomeRecomAdapter;
import com.huohuo.ui.asset.AllAssetActvity;
import com.huohuo.ui.main.MainActivity;
import com.huohuo.ui.main.msg.FriendActivity;
import com.huohuo.ui.scan.CaptureActivity;
import com.huohuo.ui.widget.NumberTextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kennysun on 2019/8/8.
 */

public class HomeFragmrnt extends BaseRefreshFragment {
    @BindView(R.id.ivHomeAssetBg)
    ImageView ivHomeAssetBg;

    MainActivity mainActivity;
    @BindView(R.id.tvTotalAssetTitle)
    TextView tvTotalAssetTitle;
    @BindView(R.id.tvTotalAsset)
    NumberTextView tvTotalAsset;
    @BindView(R.id.ivSafe)
    ImageView ivSafe;
    @BindView(R.id.tvSafe)
    TextView tvSafe;
    @BindView(R.id.rvModule)
    RecyclerView rvModule;
    @BindView(R.id.ivNoticeBg)
    ImageView ivNoticeBg;
    @BindView(R.id.ivLaba)
    ImageView ivLaba;
    @BindView(R.id.ivNext)
    ImageView ivNext;
    @BindView(R.id.ivBanner)
    ImageView ivBanner;
    @BindView(R.id.tvRecommend)
    TextView tvRecommend;
    @BindView(R.id.rvRecommend)
    RecyclerView rvRecommend;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private boolean isVisibleToUser;
    private boolean isLoad = false;
    private boolean isCreated = false;

    private HomeModuleAdapter homeModuleAdapter;
    private HomeRecomAdapter homeRecomAdapter;
    private List<ModuleItemBean> moduleBeans;
    private List<HomeRecomBean> recomBeans;

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public SmartRefreshLayout getSmartRefreshView() {
        return smartRefreshLayout;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        isCreated = true;
        lazyLoad();
    }

    @Override
    public boolean canLoadMore() {
        return true;
    }

    @Override
    public boolean setFooter() {
        return false;
    }

    public void lazyLoad() {
        if (isVisibleToUser && isCreated) {
            mainActivity = (MainActivity) getActivity();
            mainActivity.updateToolBar(R.string.app_name);
            setHasOptionsMenu(true);
            initAssetBgSize();
            initBannerSize();
            initMyModule();//初始化自选Module
            initRecom();//初始化推荐
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
            if (!isLoad) {
                //todo 加载数据
            }
            isLoad = true;
        }
    }

    /**
     * 初始化推荐列表
     */
    private void initRecom() {
        rvRecommend.setLayoutManager(new GridLayoutManager(mainActivity, 2, LinearLayoutManager.VERTICAL, false));
        List<HomeRecomBean> list = new ArrayList<>();
        list.add(new HomeRecomBean());
        list.add(new HomeRecomBean());
        list.add(new HomeRecomBean());
        list.add(new HomeRecomBean());
        homeRecomAdapter = new HomeRecomAdapter(R.layout.item_home_recommend, list);
        rvRecommend.setAdapter(homeRecomAdapter);
    }

    /**
     * 初始化自选Module
     */
    private void initMyModule() {
        rvModule.setLayoutManager(new GridLayoutManager(mainActivity, 4, LinearLayoutManager.VERTICAL, false));
        moduleBeans = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            moduleBeans.add(new ModuleItemBean());
        }
        homeModuleAdapter = new HomeModuleAdapter(R.layout.item_home_module, moduleBeans);
        rvModule.setAdapter(homeModuleAdapter);
        homeModuleAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<ModuleBean> data = adapter.getData();
            if (position == data.size() - 1) {
                //添加更多
                startActivity(new Intent(getActivity(), ModuleEditActivity.class));
            }
        });
    }

    /**
     * /初始化banner图片大小
     */
    private void initBannerSize() {
        int deviceWidth = DeviceUtil.getDeviceWidth();
        deviceWidth = deviceWidth - DensityUtil.dp2px(30);
        int h = (int) (deviceWidth / HuoHuoConstants.BANNER_RATE);
        ViewGroup.LayoutParams layoutParams = ivBanner.getLayoutParams();
        layoutParams.width = deviceWidth;
        layoutParams.height = h;
        ivBanner.setLayoutParams(layoutParams);
        GlideEngine.loadRadio(ivBanner, R.drawable.temp, 20);
    }

    /**
     * 初始化顶部背景大小
     */
    private void initAssetBgSize() {
        int deviceWidth = DeviceUtil.getDeviceWidth();
        int h = (int) (deviceWidth / HuoHuoConstants.ASSET_BG_RATE);
        ViewGroup.LayoutParams layoutParams = ivHomeAssetBg.getLayoutParams();
        layoutParams.width = deviceWidth;
        layoutParams.height = h;
        ivHomeAssetBg.setLayoutParams(layoutParams);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        lazyLoad();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_home_toolbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contacts:
                startActivity(new Intent(getBaseActivity(), FriendActivity.class));
                break;
            case R.id.create_group_chat:
                break;
            case R.id.message_add_friend:
                break;
            case R.id.message_saoyisao:
                new RxPermissionsUtil(getBaseActivity())
                        .getChoosePicPermission(new RxPermissionsCallbackUtil(getBaseActivity(), "") {
                            @Override
                            public void allAllow() {
                                startActivity(new Intent(getBaseActivity(), CaptureActivity.class));
                            }
                        });
                break;
            case R.id.message_shoubi:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void retry() {

    }


    @Override
    public void refresh() {
        smartRefreshLayout.finishLoadMoreWithNoMoreData();
    }

    @Override
    protected void loadMore() {

    }


    @OnClick({R.id.tvTotalAsset, R.id.ivNoticeBg, R.id.ivBanner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivNoticeBg:
                break;
            case R.id.ivBanner:
                break;
            case R.id.tvTotalAsset:
                //总资产
                startActivity(new Intent(getBaseActivity(),AllAssetActvity.class));
                break;
        }
    }
}
