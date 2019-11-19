package com.huohuo.ui.main.msg;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseFragment;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.DeviceUtil;
import com.huohuo.R;
import com.huohuo.dao.table.NewsData;
import com.huohuo.mvp.contract.home.NewsListContract;
import com.huohuo.mvp.model.bean.NewsList;
import com.huohuo.mvp.presenter.home.NewsListPresenter;
import com.huohuo.ui.adapter.MsgListAdapter;
import com.huohuo.ui.scan.CaptureActivity;
import com.huohuo.ui.widget.banner.BannerViewPager;
import com.huohuo.dao.table.ChatList;
import com.huohuo.ui.main.MainActivity;

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

    @Override
    public int getLayout() {
        return R.layout.fragment_msg;
    }

    @Override
    public void initViewAndData() {
        isCreated = true;
        newsListPresenter = new NewsListPresenter();
        newsListPresenter.attachView(this, getBaseActivity());
        lazyLoad();
    }

    public void lazyLoad() {
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

        List<String> list = new ArrayList<>();
        list.add("http://img0.imgtn.bdimg.com/it/u=1352823040,1166166164&fm=27&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=2293177440,3125900197&fm=27&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=3967183915,4078698000&fm=27&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=3184221534,2238244948&fm=27&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=1794621527,1964098559&fm=27&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=1243617734,335916716&fm=27&gp=0.jpg");

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

    @Override
    public void getNewsListSuccess(NewsList newsList) {
        msgListAdapter = new MsgListAdapter(R.layout.item_chat, newsList.getList());
        recyclerview.setAdapter(msgListAdapter);
        if (!isLoad) {
            //todo 加载数据
        }
        isLoad = true;
        msgListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsList.ListBean news = newsList.getList().get(position);
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
