package com.yjb.ui.main.finance;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseLoadFragment;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.DeviceUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.app.HuoHuoApp;
import com.yjb.mvp.contract.home.ArticleListContract;
import com.yjb.mvp.contract.home.HomeYjRankContract;
import com.yjb.mvp.model.bean.ArticleList;
import com.yjb.mvp.model.bean.HomeYjRank;
import com.yjb.mvp.model.bean.ModuleItemBean;
import com.yjb.mvp.presenter.home.ArticleListPresenter;
import com.yjb.mvp.presenter.home.HomeYjRankPresenter;
import com.yjb.ui.adapter.FinanceModuleAdapter;
import com.yjb.ui.user.LoginActivity;
import com.yjb.ui.widget.banner.BannerViewPager;
import com.yjb.mvp.model.bean.ModuleBean;
import com.yjb.ui.main.MainActivity;
import com.yjb.ui.main.activi.YjActivity;
import com.yjb.ui.main.shop.ShopActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by kennysun on 2019/8/8.
 */

public class FinanceFragmrnt extends BaseLoadFragment implements ArticleListContract.View, HomeYjRankContract.View {
    @BindView(R.id.banner)
    BannerViewPager banner;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.llPoliceActivity)
    LinearLayout llPoliceActivity;
    @BindView(R.id.llPoliceFC)
    LinearLayout llPoliceFC;
    @BindView(R.id.llBuy)
    LinearLayout llBuy;
    @BindView(R.id.llPoliceApply)
    LinearLayout llPoliceApply;
    Unbinder unbinder;
    private MainActivity mainActivity;
    private boolean isVisibleToUser;
    private boolean isLoad = false;
    private boolean isCreated = false;
    FinanceModuleAdapter financeModuleAdapter;

    private ArticleListPresenter articleListPresenter;

    private HomeYjRankPresenter homeYjRankPresenter;

    @Override
    public int getLayout() {
        return R.layout.fragment_finance;
    }

    @Override
    protected void retry() {

    }

    @Override
    protected MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        isCreated = true;
        lazyLoad();
    }

    @OnClick({R.id.llPoliceActivity, R.id.llPoliceFC, R.id.llBuy, R.id.llPoliceApply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llPoliceActivity:
                startActivity(new Intent(getBaseActivity(), YjActivity.class));
                break;
            case R.id.llPoliceFC:
                startActivity(new Intent(getBaseActivity(), YiFcActivity.class));
                break;
            case R.id.llBuy:
                startActivity(new Intent(getBaseActivity(), ShopActivity.class));
                break;
            case R.id.llPoliceApply:
                startActivity(new Intent(getBaseActivity(), YiApplyActivity.class));
                break;
        }
    }

    public void lazyLoad() {
        if (isVisibleToUser && isCreated) {
            mainActivity = (MainActivity) getActivity();
            mainActivity.updateToolBar(R.string.yhyj);
            setHasOptionsMenu(true);
            initBanner();
            if (!isLoad) {
                //todo 加载数据
            }
            isLoad = true;
        }
    }

    private void initModule(List<HomeYjRank> homeYjRank) {
        recyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        List<ModuleBean> list = new ArrayList<>();
        ModuleBean m1 = new ModuleBean();
        m1.setTitle("义警队伍");
        m1.setType(1);
        List<ModuleItemBean> mList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ModuleItemBean itemBean = new ModuleItemBean();
            itemBean.setIndex(i + 1);
            mList.add(itemBean);
        }
        m1.setModuleItems(mList);
        list.add(m1);
        ModuleBean m2 = new ModuleBean();
        m2.setTitle("义警排名");
        m2.setType(2);
        List<ModuleItemBean> mList2 = new ArrayList<>();

        if (homeYjRank != null) {
            for (int i = 0; i < homeYjRank.size(); i++) {
                HomeYjRank hy = homeYjRank.get(i);
                ModuleItemBean itemBean = new ModuleItemBean();
                itemBean.setIndex(i + 1);
                itemBean.setScore(hy.getScore());
                itemBean.setName(hy.getNickname());
                itemBean.setPhotoUrl(hy.getPhotoimage());
                itemBean.setRes(2);
                mList2.add(itemBean);
            }
        }

        m2.setModuleItems(mList2);
        list.add(m2);

        financeModuleAdapter = new FinanceModuleAdapter(R.layout.item_finance_module, list);
        recyclerview.setAdapter(financeModuleAdapter);
        financeModuleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getBaseActivity(), YiBestActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        intent = new Intent(getBaseActivity(), ScoreActivity.class);
                        startActivity(intent);
                        break;

                }

            }
        });

    }

    private void initBanner() {
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        int deviceWidth = DeviceUtil.getDeviceWidth();
        int h = (int) (deviceWidth / 2.6);
        layoutParams.height = h;
        layoutParams.width = deviceWidth;
        banner.setLayoutParams(layoutParams);
        articleListPresenter = new ArticleListPresenter();
        articleListPresenter.attachView(this, getBaseActivity());
        articleListPresenter.getList(AppUtil.getToken(), "1", "10");
        homeYjRankPresenter = new HomeYjRankPresenter();
        homeYjRankPresenter.attachView(this, getBaseActivity());
        homeYjRankPresenter.getList(AppUtil.getToken());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        lazyLoad();
    }

    @Override
    public void getAtListSuccess(ArticleList articleList) {
        List<String> list = new ArrayList<>();
        if (articleList.getList() == null || articleList.getList().size() == 0) {
            ToastUtil.show(getBaseActivity(), "没有数据了");
            return;
        }

        for (int i = 0, len = articleList.getList().size(); i < len; i++) {
            ArticleList.ListBean listBean = articleList.getList().get(i);
            list.add(BuildConfig.API_IMG_HOST + listBean.getCoverimage());
        }

        banner.initBanner(list, true)//关闭3D画廊效果
                .addPageMargin(-18, 20)//参数1page之间的间距,参数2中间item距离边界的间距
                .addPoint(6)//添加指示器
                .addStartTimer(5)//自动轮播5秒间隔
                .addPointBottom(6)
                .addRoundCorners(5)//圆角
                .finishConfig()//这句必须加
                .addBannerListener(new BannerViewPager.OnClickBannerListener() {
                    @Override
                    public void onBannerClick(int position) {
                        Log.i("test", "--------------00x2");
                    }
                });
    }

    @Override
    public void getRankSuccess(List<HomeYjRank> homeYjRank) {
        initModule(homeYjRank);
    }

    @Override
    public void onError(Object msg, int code) {
        if (code == 401) {
            ToastUtil.show(getBaseActivity(), "登录过期，请重新登录");
            Intent intent = new Intent(getBaseActivity(), LoginActivity.class);
            SharedPreferences.Editor editor = getBaseActivity().getSharedPreferences("userinfo",
                    getBaseActivity().MODE_PRIVATE).edit();
            editor.putString("token", null);
            editor.putString("user", null);
            editor.commit();
            startActivity(intent);
            getBaseActivity().finish();
        }
    }
}
