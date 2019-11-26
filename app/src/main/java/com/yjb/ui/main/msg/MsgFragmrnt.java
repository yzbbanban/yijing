package com.yjb.ui.main.msg;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseFragment;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.DeviceUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.mvp.contract.home.NewsListContract;
import com.yjb.mvp.model.bean.NewsList;
import com.yjb.mvp.presenter.home.NewsListPresenter;
import com.yjb.ui.adapter.MsgListAdapter;
import com.yjb.ui.widget.banner.BannerViewPager;
import com.yjb.ui.main.MainActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by kennysun on 2019/8/8.
 */

public class MsgFragmrnt extends BaseFragment implements NewsListContract.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.banner)
    BannerViewPager banner;
    private MainActivity mainActivity;
    private boolean isVisibleToUser;
    private boolean isLoad = false;
    private boolean isCreated = false;
    private MsgListAdapter msgListAdapter;

    private NewsListPresenter newsListPresenter;

    private static final String NEWS = "news";

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    int page = 1;
    int pageSize = 10;

    private int type;

    @Override
    public int getLayout() {
        return R.layout.fragment_msg;
    }

    @Override
    public void initViewAndData() {
        isCreated = true;
        newsListPresenter = new NewsListPresenter();
        newsListPresenter.attachView(this, getBaseActivity());
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                type = 1;
                newsListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                type = 2;
                newsListPresenter.getList(AppUtil.getToken(), "" + page, "" + pageSize);
            }
        });
        lazyLoad();
    }

    public void lazyLoad() {
        type = 1;
        if (isVisibleToUser && isCreated) {
            mainActivity = (MainActivity) getActivity();
            mainActivity.updateToolBar(R.string.yj_new);
            mainActivity.updateToolBarColor(R.color.colorAccent);
            setHasOptionsMenu(true);
            initBanner();
            recyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
            newsListPresenter.getList(AppUtil.getToken(), "1", "10");
        }
    }

    private void initBanner() {
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        int deviceWidth = DeviceUtil.getDeviceWidth();
        int h = (int) (deviceWidth / 2.6);
        layoutParams.height = h;
        layoutParams.width = deviceWidth;
        banner.setLayoutParams(layoutParams);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        lazyLoad();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
//        inflater.inflate(R.menu.menu_msg_toolbar, menu);
    }

    private List<NewsList.ListBean> listBean = new ArrayList<>();

    @Override
    public void getNewsListSuccess(NewsList newsList) {

        if (page == 1) {
            List<String> list = new ArrayList<>();
            int len = newsList.getList().size();
            if (len > 3) {
                len = 3;
            }
            for (int i = 0; i < len; i++) {
                NewsList.ListBean news = newsList.getList().get(i);
                list.add(BuildConfig.API_IMG_HOST + news.getCoverimage());
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

        refreshLayout.finishRefresh();//结束刷新
        refreshLayout.finishLoadMore();//结束加载
        if (type == 1) {
            listBean = new ArrayList<>();
        }
        type = 1;
        if (newsList.getList() == null || newsList.getList().size() == 0) {
            page--;
            ToastUtil.show(getBaseActivity(), "没有数据了");
            return;
        }

        listBean.addAll(newsList.getList());
        msgListAdapter = new MsgListAdapter(R.layout.item_chat, listBean);
        recyclerview.setAdapter(msgListAdapter);
        if (!isLoad) {
            //todo 加载数据
        }
        isLoad = true;
        msgListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsList.ListBean news = listBean.get(position);
                Intent intent = new Intent(getBaseActivity(), NewsDetailActivity.class);
                intent.putExtra(NEWS, news);
                startActivity(intent);
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


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.contacts:
//                startActivity(new Intent(getBaseActivity(), FriendActivity.class));
//                break;
//            case R.id.create_group_chat:
//                break;
//            case R.id.message_add_friend:
//                break;
//            case R.id.message_saoyisao:
//                startActivity(new Intent(getBaseActivity(), CaptureActivity.class));
//                break;
//            case R.id.message_shoubi:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
