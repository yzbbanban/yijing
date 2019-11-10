package com.huohuo.ui.main.msg;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.RxPermissionsCallbackUtil;
import com.dian.commonlib.utils.RxPermissionsUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.SearchEditText;
import com.huohuo.R;
import com.huohuo.ui.dialog.MyQrDialog;
import com.huohuo.ui.scan.CaptureActivity;
import com.huohuo.mvp.contract.msg.AddFriendContract;
import com.huohuo.mvp.presenter.msg.AddFriendPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kennysun on 2019/9/3.
 */

public class AddFriendActivity extends BaseLoadActivity implements AddFriendContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etSearch)
    SearchEditText etSearch;
    @BindView(R.id.tvMyQr)
    TextView tvMyQr;
    @BindView(R.id.clSaoYiSao)
    ConstraintLayout clSaoYiSao;
    @BindView(R.id.clPhoneFriend)
    ConstraintLayout clPhoneFriend;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;

    private AddFriendPresenter addFriendPresenter;
    private String jiaMiUserId;
    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_friend;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        setToolbarConfig(toolbar, R.string.add_friend);
        etSearch.setFocusableInTouchMode(false);
        etSearch.setFocusable(false);
        etSearch.setHint(R.string.phone_num);
        addFriendPresenter = new AddFriendPresenter();
        addFriendPresenter.attachView(this,this);
        addFriendPresenter.getJiamiUserId();
    }

    @OnClick({R.id.etSearch, R.id.tvMyQr, R.id.clSaoYiSao, R.id.clPhoneFriend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.etSearch:
                startActivity(new Intent(this, SearchUserActivity.class));
                break;
            case R.id.tvMyQr:
                if (!TextUtils.isEmpty(jiaMiUserId)){
                    new MyQrDialog(this).setJiaMiUserId(jiaMiUserId).show();
                }
                break;
            case R.id.clSaoYiSao:
                new RxPermissionsUtil(this)
                        .getChoosePicPermission(new RxPermissionsCallbackUtil(this,"") {
                            @Override
                            public void allAllow() {
                                startActivity(new Intent(AddFriendActivity.this, CaptureActivity.class));
                            }
                        });
                break;
            case R.id.clPhoneFriend:
                startActivity(new Intent(this, PhoneFriendActivity.class));
                break;
        }
    }

    @Override
    public void getJiamiUserId(String s) {
        jiaMiUserId = s;
    }
}
