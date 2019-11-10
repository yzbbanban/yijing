package com.huohuo.ui.main.msg;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.quicksidebar.QuickSideBarTipsView;
import com.bigkoo.quicksidebar.QuickSideBarView;
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.MmkvUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.SearchEditText;
import com.huohuo.R;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.ui.adapter.FriendHeadAdapter;
import com.huohuo.dao.table.Friend;
import com.huohuo.mvp.contract.msg.FriendContract;
import com.huohuo.mvp.presenter.msg.FriendPersenter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by kennysun on 2019/9/3.
 */

public class FriendActivity extends BaseLoadActivity implements FriendContract.View, OnQuickSideBarTouchListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etSearch)
    SearchEditText etSearch;
    @BindView(R.id.ivNewFriendIcon)
    ImageView ivNewFriendIcon;
    @BindView(R.id.clNewFriend)
    ConstraintLayout clNewFriend;
    @BindView(R.id.llNewFriend)
    LinearLayout llNewFriend;
    @BindView(R.id.clGroup)
    ConstraintLayout clGroup;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.quickSideBarTipsView)
    QuickSideBarTipsView quickSideBarTipsView;
    @BindView(R.id.quickSideBarView)
    QuickSideBarView quickSideBarView;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private FriendPersenter contractsPersenter;
    private FriendHeadAdapter friendAdapter;
    private String type;//页面类型

    HashMap<String, Integer> letters = new HashMap<>();
    private Badge badge;

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_contracts;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        setToolbarConfig(toolbar, R.string.contracts);
        etSearch.setFocusableInTouchMode(false);
        etSearch.setFocusable(false);
        etSearch.setHint(R.string.search_friend);
        contractsPersenter = new FriendPersenter();
        contractsPersenter.attachView(this, this);

        type = getIntent().getStringExtra(HuoHuoConstants.TYPE);
        if (HuoHuoConstants.ZHUAN_ZHANG.equals(type)) {
            etSearch.setVisibility(View.GONE);
            clNewFriend.setVisibility(View.GONE);
            clGroup.setVisibility(View.GONE);
        } else {
            etSearch.setVisibility(View.VISIBLE);
            clGroup.setVisibility(View.VISIBLE);
            clNewFriend.setVisibility(View.VISIBLE);
        }

        badge = new QBadgeView(this)
                .setBadgeBackgroundColor(getResources().getColor(R.color.colorAccent))
                .setGravityOffset(6f, 0f, true)
                .bindTarget(ivNewFriendIcon)
                .setBadgeTextSize(9, true)
                .setOnDragStateChangedListener((dragState, badge1, targetView) -> {
                    if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
                        ToastUtil.show(this, "移除了,parentPosition=");
                    }
                });

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        friendAdapter = new FriendHeadAdapter();
        friendAdapter.setOnItemClickListener(friend -> {
            Intent intent = new Intent();
            if (HuoHuoConstants.ZHUAN_ZHANG.equals(type)) {//转账
//                    intent.putExtra(MyType.FRIEND, friend);
                intent.putExtra(HuoHuoConstants.NAME, TextUtils.isEmpty(friend.getFriendRemark()) ? friend.getNickName() : friend.getFriendRemark());
                intent.putExtra(HuoHuoConstants.FRIENDID, friend.getFriendUid());
                setResult(RESULT_OK, intent);
                finish();
            } else {//个人信息页面
                intent.setClass(FriendActivity.this, FriendInfoActivity.class);
                intent.putExtra(HuoHuoConstants.USER_ID, friend.getFriendUid() + "");
                intent.putExtra(HuoHuoConstants.FRIEND_REMARK, friend.getFriendRemark());
                intent.putExtra(HuoHuoConstants.NAME, friend.getNickName());
                intent.putExtra(HuoHuoConstants.USE_NO_DISTURB, friend.getUseNoDisturb());
                intent.putExtra(HuoHuoConstants.AVATAR, friend.getHeadImage());
                startActivity(intent);
            }
        });

        //设置监听
        quickSideBarView.setOnQuickSideBarTouchListener(this);

        contractsPersenter.loadFriend();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取是否有新好友申请
        boolean newApplyInfo = MmkvUtil.decodeBoolean(HuoHuoConstants.CONTRACT_NEW_APPLY, false);
        if (newApplyInfo) {
            badge.setBadgeNumber(1);
            badge.setBadgeTextColor(getResources().getColor(R.color.colorAccent));
        } else {
            badge.setBadgeNumber(0);
        }
    }

    @OnClick({R.id.etSearch, R.id.clNewFriend, R.id.clGroup})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.etSearch:
                intent.setClass(FriendActivity.this, SearchFriendActivity.class);
                intent.putExtra(HuoHuoConstants.TYPE, type);
                startActivity(intent);
                break;
            case R.id.clNewFriend:
                intent.setClass(FriendActivity.this, NewFriendActivity.class);
                startActivity(intent);
                break;
            case R.id.clGroup:
                intent.setClass(FriendActivity.this, GroupActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void friend(List<Friend> friends, List<String> customLetters, HashMap<String, Integer> letters) {
        if (friends == null || friends.size() == 0) {
            return;
        }
        this.letters = letters;

        quickSideBarView.setLetters(customLetters);
        friendAdapter.setDatas(friends);
        recyclerView.setAdapter(friendAdapter);

        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(friendAdapter);
        recyclerView.addItemDecoration(headersDecor);
    }

    @Override
    public void onLetterChanged(String letter, int position, float y) {
        quickSideBarTipsView.setText(letter, position, y);
        //有此key则获取位置并滚动到该位置
        if (letters.containsKey(letter)) {
            recyclerView.scrollToPosition(letters.get(letter));
        }
    }

    @Override
    public void onLetterTouching(boolean touching) {
        //可以自己加入动画效果渐显渐隐
        quickSideBarTipsView.setVisibility(touching ? View.VISIBLE : View.INVISIBLE);
    }
}
