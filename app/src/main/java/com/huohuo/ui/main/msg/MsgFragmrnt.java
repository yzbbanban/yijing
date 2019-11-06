package com.huohuo.ui.main.msg;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.dian.commonlib.base.BaseFragment;
import com.dian.commonlib.utils.DeviceUtil;
import com.dian.commonlib.utils.widget.SearchEditText;
import com.huohuo.R;
import com.huohuo.dao.table.ChatList;
import com.huohuo.ui.adapter.MsgListAdapter;
import com.huohuo.ui.main.MainActivity;
import com.huohuo.ui.scan.CaptureActivity;
import com.huohuo.ui.widget.banner.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by kennysun on 2019/8/8.
 */

public class MsgFragmrnt extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.banner)
    BannerViewPager banner;
    private MainActivity mainActivity;
    private boolean isVisibleToUser;
    private boolean isLoad = false;
    private boolean isCreated = false;
    private MsgListAdapter msgListAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_msg;
    }

    @Override
    public void initViewAndData() {
        isCreated = true;
        lazyLoad();
    }

    public void lazyLoad() {
        if (isVisibleToUser && isCreated) {
            mainActivity = (MainActivity) getActivity();
            mainActivity.updateToolBar(R.string.yj_new);
            setHasOptionsMenu(true);
            initBanner();
            recyclerview.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
            List<ChatList> lists = new ArrayList<>();
            lists.add(new ChatList());
            lists.add(new ChatList());
            lists.add(new ChatList());
            lists.add(new ChatList());
            lists.add(new ChatList());
            msgListAdapter = new MsgListAdapter(R.layout.item_chat, lists);
            recyclerview.setAdapter(msgListAdapter);
            if (!isLoad) {
                //todo 加载数据
            }
            isLoad = true;
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
        menu.clear();
        inflater.inflate(R.menu.menu_msg_toolbar, menu);
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
                startActivity(new Intent(getBaseActivity(), CaptureActivity.class));
                break;
            case R.id.message_shoubi:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
