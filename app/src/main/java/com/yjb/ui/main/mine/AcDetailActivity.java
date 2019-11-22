package com.yjb.ui.main.mine;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.glide.GlideEngine;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.RxPermissionsCallbackUtil;
import com.dian.commonlib.utils.RxPermissionsUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.app.HuoHuoConstants;
import com.yjb.mvp.contract.home.AcSignOutContract;
import com.yjb.mvp.contract.home.AcSignUpContract;
import com.yjb.mvp.model.bean.AcMyList;
import com.yjb.mvp.model.bean.ActivityList;
import com.yjb.mvp.presenter.home.AcSIgnOutPresenter;
import com.yjb.mvp.presenter.home.AcSIgnUpPresenter;
import com.yjb.ui.main.msg.AddFriendActivity;
import com.yjb.ui.scan.CaptureActivity;

import butterknife.BindView;

public class AcDetailActivity extends BaseLoadActivity implements AcSignUpContract.View, AcSignOutContract.View {

    private static final String TAG = "AcDetailActivity";
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.btRight)
    Button btRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tcAcDetailTitle)
    TextView tcAcDetailTitle;
    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.tcAcDetailIntroduce)
    TextView tcAcDetailIntroduce;
    @BindView(R.id.tcAcDetailType)
    TextView tcAcDetailType;
    @BindView(R.id.tcAcDetailCount)
    TextView tcAcDetailCount;
    @BindView(R.id.tcAcDetailTime)
    TextView tcAcDetailTime;
    @BindView(R.id.tcAcDetailAddress)
    TextView tcAcDetailAddress;
    @BindView(R.id.tcAcDetailOwner)
    TextView tcAcDetailOwner;
    @BindView(R.id.tcAcDetailPhone)
    TextView tcAcDetailPhone;
    @BindView(R.id.tcAcDetailRemark)
    TextView tcAcDetailRemark;

    private Integer type;

    private AcSIgnUpPresenter acSIgnUpPresenter;
    private AcSIgnOutPresenter acSIgnOutPresenter;

    private String acid;
    private String teamId;

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        tvTitle.setText("活动详情");
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        acSIgnUpPresenter = new AcSIgnUpPresenter();
        acSIgnOutPresenter = new AcSIgnOutPresenter();
        acSIgnUpPresenter.attachView(this, this);
        acSIgnOutPresenter.attachView(this, this);


        Log.i(TAG, "onNewIntent: " + type);
        type = getIntent().getIntExtra("type", 1);
        switch (type) {
            case 1:
                AcMyList.ListBean listBean = (AcMyList.ListBean) getIntent().getSerializableExtra("AC_MY_DETAIL");
                GlideEngine.load(ivAvatar, BuildConfig.API_IMG_HOST + listBean.getCoverimage());
                tcAcDetailTitle.setText("" + listBean.getTitle());
                tcAcDetailIntroduce.setText("" + listBean.getContent());
                tcAcDetailType.setText("" + listBean.getStatus());
                tcAcDetailCount.setText("" + listBean.getActivityno());
                tcAcDetailTime.setText("" + listBean.getActivitystarttime());
                tcAcDetailAddress.setText("" + listBean.getAddress());
                tcAcDetailOwner.setText("" + listBean.getUser_id());
                tcAcDetailPhone.setText("" + listBean.getStatus_text());
                tcAcDetailRemark.setText("" + listBean.getRequirementdata());
                acid = "" + listBean.getActivity_id();
                break;
            case 2:
                ActivityList.ListBean bean = (ActivityList.ListBean) getIntent().getSerializableExtra("AC_MY_DETAIL");
                acid = "" + bean.getId();
                teamId = bean.getRequirementdata();
                btRight.setText("报名");
                btRight.setVisibility(View.VISIBLE);
                btRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //检查是否有资格
                        String tId = AppUtil.getTeamId();
                        if (tId == null || !teamId.contains(tId)) {
                            ToastUtil.show(AcDetailActivity.this, "无权限，不是义警");
                            return;
                        }
                        ToastUtil.show(AcDetailActivity.this, "报名中。。。稍等");
                        acSIgnUpPresenter.getList(AppUtil.getToken(), acid, AppUtil.getUser(), tId);
                    }
                });
                GlideEngine.load(ivAvatar, BuildConfig.API_IMG_HOST + bean.getCoverimage());
                tcAcDetailTitle.setText("" + bean.getTitle());
                tcAcDetailIntroduce.setText("" + bean.getContent());
                tcAcDetailType.setText("" + bean.getUserid_text());
                tcAcDetailCount.setText("" + bean.getActivityno());
                tcAcDetailTime.setText("" + bean.getActivitystarttime_text());
                tcAcDetailAddress.setText("" + bean.getAddress());
                tcAcDetailOwner.setText("" + bean.getUserid_text().getNickname());
                tcAcDetailPhone.setText("" + bean.getUserid_text().getMobile());
                tcAcDetailRemark.setText("" + bean.getRequirementdata_text());
                break;
            case 3:
                bean = (ActivityList.ListBean) getIntent().getSerializableExtra("AC_MY_DETAIL");
                acid = "" + bean.getActivitytype_id();
                teamId = bean.getRequirementdata();
                btRight.setText("扫一扫");
                btRight.setVisibility(View.VISIBLE);
                btRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new RxPermissionsUtil(AcDetailActivity.this)
                                .getChoosePicPermission(new RxPermissionsCallbackUtil(AcDetailActivity.this, "") {
                                    @Override
                                    public void allAllow() {
                                        startActivityForResult(new Intent(AcDetailActivity.this, CaptureActivity.class), 20000);
                                    }
                                });
                    }
                });
                GlideEngine.load(ivAvatar, BuildConfig.API_IMG_HOST + bean.getCoverimage());
                tcAcDetailTitle.setText("" + bean.getTitle());
                tcAcDetailIntroduce.setText("" + bean.getContent());
                tcAcDetailType.setText("" + bean.getUserid_text());
                tcAcDetailCount.setText("" + bean.getActivityno());
                tcAcDetailTime.setText("" + bean.getActivitystarttime_text());
                tcAcDetailAddress.setText("" + bean.getAddress());
                tcAcDetailOwner.setText("" + bean.getUserid_text().getNickname());
                tcAcDetailPhone.setText("" + bean.getUserid_text().getMobile());
                tcAcDetailRemark.setText("" + bean.getRequirementdata_text());
                break;
            case 4:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult requestCode: " + requestCode);
        Log.i(TAG, "onActivityResult resultCode: " + resultCode);
        String memo = data.getExtras().getString(HuoHuoConstants.MEMO);
        Log.i(TAG, "onActivityResult: " + memo);
        ToastUtil.show(this, "读取信息为：" + memo);
        acSIgnOutPresenter.getList(AppUtil.getToken(), memo, acid);

    }


    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ac_detail;
    }

    @Override
    public void getAcSignOutSuccess(String msg) {
        ToastUtil.show(AcDetailActivity.this, msg);
    }

    @Override
    public void getAcSignUpSuccess(String msg) {
        ToastUtil.show(AcDetailActivity.this, "报名完成");
    }

    @Override
    public void onError(Object msg, int code) {
        ToastUtil.show(AcDetailActivity.this, "" + msg);
    }
}
