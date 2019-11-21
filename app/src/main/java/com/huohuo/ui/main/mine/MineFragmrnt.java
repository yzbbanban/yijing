package com.huohuo.ui.main.mine;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseFragment;
import com.dian.commonlib.utils.AppUtil;
import com.huohuo.R;
import com.huohuo.ui.adapter.MsgListAdapter;
import com.huohuo.ui.main.MainActivity;
import com.huohuo.ui.user.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by kennysun on 2019/8/8.
 */

public class MineFragmrnt extends BaseFragment {
    @BindView(R.id.ivAvatar)
    AppCompatImageView ivAvatar;
    @BindView(R.id.tvNickName)
    TextView tvNickName;
    @BindView(R.id.rbPingFen)
    AppCompatRatingBar rbPingFen;
    @BindView(R.id.sendSmsCode)
    AppCompatButton sendSmsCode;
    @BindView(R.id.ivJiFen)
    ImageView ivJiFen;
    @BindView(R.id.tvLCCount)
    TextView tvLCCount;
    @BindView(R.id.ivTime)
    ImageView ivTime;
    @BindView(R.id.tvYDCoinCount)
    TextView tvYDCoinCount;
    @BindView(R.id.clTop)
    ConstraintLayout clTop;
    @BindView(R.id.ivAccountSafeIcon)
    ImageView ivAccountSafeIcon;
    @BindView(R.id.tvAccountSafeTitle)
    TextView tvAccountSafeTitle;
    @BindView(R.id.ivAccountSafeNext)
    ImageView ivAccountSafeNext;
    @BindView(R.id.clActivity)
    ConstraintLayout clActivity;
    @BindView(R.id.ivHDIcon)
    ImageView ivHDIcon;
    @BindView(R.id.tvHDTitle)
    TextView tvHDTitle;
    @BindView(R.id.ivHDNext)
    ImageView ivHDNext;
    @BindView(R.id.clAboutUs)
    ConstraintLayout clAboutUs;
    @BindView(R.id.ivLangIcon)
    ImageView ivLangIcon;
    @BindView(R.id.tvLangTitle)
    TextView tvLangTitle;
    @BindView(R.id.ivLangNext)
    ImageView ivLangNext;
    @BindView(R.id.clLogOut)
    ConstraintLayout clLogOut;
    private MainActivity mainActivity;
    private boolean isVisibleToUser;
    private boolean isLoad = false;
    private boolean isCreated = false;
    private MsgListAdapter msgListAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViewAndData() {
        isCreated = true;
        lazyLoad();
    }

    @OnClick({R.id.clActivity, R.id.clAboutUs, R.id.clLogOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clActivity:
                startActivity(new Intent(getBaseActivity(), MyAcActivity.class));
                break;
            case R.id.clAboutUs:

                break;
            case R.id.clLogOut:
                AppUtil.setToken("");
                AppUtil.setUser("");
                SharedPreferences.Editor editor = getBaseActivity()
                        .getSharedPreferences("userinfo", getBaseActivity().MODE_PRIVATE)
                        .edit();
                editor.putString("token", null);
                editor.putString("user", null);
                editor.commit();
                startActivity(new Intent(getBaseActivity(), LoginActivity.class));
                break;
        }
    }

    public void lazyLoad() {
        if (isVisibleToUser && isCreated) {
            mainActivity = (MainActivity) getActivity();
            mainActivity.updateToolBar(R.string.grzx);
            setHasOptionsMenu(true);
            if (!isLoad) {
                //todo 加载数据
            }
            isLoad = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        lazyLoad();
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
//        inflater.inflate(R.menu.menu_mine_toolbar, menu);
//    }


}
