package com.yjb.ui.main.msg;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import com.bigkoo.quicksidebar.QuickSideBarTipsView;
import com.bigkoo.quicksidebar.QuickSideBarView;
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener;
import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.OnTextChangeListener;
import com.dian.commonlib.utils.widget.SearchEditText;
import com.yjb.R;
import com.yjb.ui.adapter.PhoneFriendHeadAdapter;
import com.yjb.dao.table.Friend;
import com.yjb.mvp.contract.msg.PhoneFriendContract;
import com.yjb.mvp.presenter.msg.PhoneFriendPresenter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by kennysun on 2019/9/3.
 */

public class PhoneFriendActivity extends BaseLoadActivity implements PhoneFriendContract.View, OnQuickSideBarTouchListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etSearch)
    SearchEditText etSearch;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.quickSideBarTipsView)
    QuickSideBarTipsView quickSideBarTipsView;
    @BindView(R.id.quickSideBarView)
    QuickSideBarView quickSideBarView;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private PhoneFriendHeadAdapter phoneFriendHeadAdapter;
    private PhoneFriendPresenter phoneFriendPresenter;

    private HashMap<String, Integer> letters = new HashMap<>();
    private String smsContent;
    private List<Friend> friendList;

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_phone_friend;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        setToolbarConfig(toolbar, R.string.phone_friend);
        phoneFriendPresenter = new PhoneFriendPresenter();
        phoneFriendPresenter.attachView(this, this);
        phoneFriendPresenter.loadPhoneFriend();//获取联系人列表
        phoneFriendPresenter.getSmsContent();//获取邀请短信内容

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        phoneFriendHeadAdapter = new PhoneFriendHeadAdapter();
        //设置监听
        quickSideBarView.setOnQuickSideBarTouchListener(this);

        recyclerView.setAdapter(phoneFriendHeadAdapter);
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(phoneFriendHeadAdapter);
        recyclerView.addItemDecoration(headersDecor);

        phoneFriendHeadAdapter.setAddFriendsClickListener(new PhoneFriendHeadAdapter.AddFriendsClickListener() {
            @Override
            public void addFriendsClick(boolean isRegister, int friendUid, int position) {
                if (!isRegister)     //未注册
                    sendSmsWithBody(friendList.get(position).getMobile(), smsContent);   //发送短信
                else {
                    phoneFriendPresenter.addFriend(String.valueOf(friendUid));
                }
            }
        });
        etSearch.addTextChangedListener(new OnTextChangeListener(){
            @Override
            public void afterTextChanged(Editable s) {
                searchPhoneFriend();
            }
        });
        etSearch.setOnSearchListener(() -> {
           searchPhoneFriend();
        });
    }

    private void searchPhoneFriend() {
        String searchStr = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(searchStr)) {
            sort(friendList);
        } else {

            if (friendList != null && friendList.size() > 0) {
                List<Friend> searchList = new ArrayList<>();
                for (Friend friend : friendList) {
                    if ((!TextUtils.isEmpty(friend.getRealName()) && friend.getRealName().contains(searchStr))
                            || (!TextUtils.isEmpty(friend.getNickName()) && friend.getNickName().contains(searchStr))
                            || (!TextUtils.isEmpty(friend.getMobile()) && friend.getMobile().contains(searchStr))) {
                        searchList.add(friend);
                    }
                }
                sort(searchList);
            }
        }
    }

    private void sort(List<Friend> searchList) {
        List<String> customLetters = new ArrayList<>();
        letters.clear();
        Collections.sort(searchList);
        int position = 0;
        for (Friend friend : searchList) {
            String letter = friend.getFirstChar();
            //如果没有这个key则加入并把位置也加入
            if (!letters.containsKey(letter)) {
                letters.put(letter, position);
                customLetters.add(letter);
            }
            position++;
        }
        quickSideBarView.setLetters(customLetters);
        phoneFriendHeadAdapter.setDatas(searchList);
    }


    @Override
    public void phoneFriend(List<Friend> list, List<String> customLetters, HashMap<String, Integer> letters) {
        this.letters = letters;
        friendList = list;
        quickSideBarView.setLetters(customLetters);
        phoneFriendHeadAdapter.setDatas(list);
    }

    @Override
    public void smsContent(String s) {
        smsContent = s;
    }

    @Override
    public void addFriendSendSuccess() {
        ToastUtil.show(this, R.string.add_friend_request);
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

    /**
     *  
     *  * 调用系统界面，给指定的号码发送短信，并附带短信内容 
     *  *  
     *  * @param context 
     *  * @param number 
     *  * @param body 
     *  
     */
    public void sendSmsWithBody(String number, String body) {
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("smsto:" + number));
        sendIntent.putExtra("sms_body", body);
        startActivity(sendIntent);
    }
}
