package com.yjb.ui.main.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.yjb.mvp.contract.home.GetSignStautsContract;
import com.yjb.mvp.model.bean.AcMyList;
import com.yjb.mvp.model.bean.ActivityList;
import com.yjb.mvp.presenter.home.AcSIgnOutPresenter;
import com.yjb.mvp.presenter.home.AcSIgnUpPresenter;
import com.yjb.mvp.presenter.home.GetSIgnStatusPresenter;
import com.yjb.ui.main.msg.AddFriendActivity;
import com.yjb.ui.scan.CaptureActivity;

import butterknife.BindView;

public class AcDetailActivity extends BaseLoadActivity implements AcSignUpContract.View, AcSignOutContract.View, GetSignStautsContract.View {

    private static final String TAG = "AcDetailActivity";
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tcAcDetailTitle)
    TextView tcAcDetailTitle;
    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.wvAcDetailIntroduce)
    WebView wvAcDetailIntroduce;
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

    private GetSIgnStatusPresenter getSIgnStatusPresenter;

    private String acid;
    private String teamId;

    private String UN_SIGN_UP = "未签到";

    private String UN_ENTER = "未报名";

    private String SIGN_UP = "已签到";

    private String UN_SIGN_OUT = "未签退";

    private String SIGN_OUT = "已签退";

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
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setTextColor(getResources().getColor(R.color.colorAccent));
        acSIgnUpPresenter = new AcSIgnUpPresenter();
        acSIgnOutPresenter = new AcSIgnOutPresenter();
        getSIgnStatusPresenter = new GetSIgnStatusPresenter();
        acSIgnUpPresenter.attachView(this, this);
        acSIgnOutPresenter.attachView(this, this);
        getSIgnStatusPresenter.attachView(this, this);


        Log.i(TAG, "onNewIntent: " + type);
        type = getIntent().getIntExtra("type", 1);
        wvAcDetailIntroduce.setWebViewClient(new WebViewClient());


        switch (type) {
            case 1:
                AcMyList.ListBean listBean = (AcMyList.ListBean) getIntent().getSerializableExtra("AC_MY_DETAIL");
                GlideEngine.load(ivAvatar, "" + listBean.getCoverimage());
                tcAcDetailTitle.setText("" + listBean.getTitle());
                String ht = getHtmlData("" + listBean.getContent());
                wvAcDetailIntroduce.loadDataWithBaseURL(null, ht, "text/html", "UTF-8", null);
                tcAcDetailType.setText("" + tcAcDetailType.getText() + listBean.getRequirementdata());
                tcAcDetailCount.setText("" + tcAcDetailCount.getText() + listBean.getActivityno());
                tcAcDetailTime.setText("" + tcAcDetailTime.getText() + listBean.getActivitystarttime());
                tcAcDetailAddress.setText("" + tcAcDetailAddress.getText() + listBean.getAddress());
                tcAcDetailOwner.setText("" + tcAcDetailOwner.getText() + listBean.getUser_id());
                tcAcDetailPhone.setText("" + tcAcDetailPhone.getText() + listBean.getStatus_text());
                tcAcDetailRemark.setText("" + tcAcDetailRemark.getText() + "");
                acid = "" + listBean.getActivity_id();
                break;
            case 2:
                ActivityList.ListBean bean = (ActivityList.ListBean) getIntent().getSerializableExtra("AC_MY_DETAIL");
                acid = "" + bean.getId();
                teamId = bean.getRequirementdata();
                tvRight.setText("报名");
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setOnClickListener(new View.OnClickListener() {
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
                getDetail();
                break;
            case 3:
                bean = (ActivityList.ListBean) getIntent().getSerializableExtra("AC_MY_DETAIL");
                acid = "" + bean.getId();
                teamId = bean.getRequirementdata();
                tvRight.setText("扫一扫");
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendAc();
                    }
                });
                getDetail();
                break;
            case 4:
                bean = (ActivityList.ListBean) getIntent().getSerializableExtra("AC_MY_DETAIL");
                acid = "" + bean.getId();
                teamId = bean.getRequirementdata();
                tvRight.setText("扫一扫");
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendAc();
                    }
                });
                getDetail();
                break;
        }

    }

    private void getDetail() {
        ActivityList.ListBean bean;
        String ht;
        bean = (ActivityList.ListBean) getIntent().getSerializableExtra("AC_MY_DETAIL");
        acid = "" + bean.getId();
        teamId = bean.getRequirementdata();
        GlideEngine.load(ivAvatar, "" + bean.getCoverimage());
        tcAcDetailTitle.setText("" + bean.getTitle());
        ht = getHtmlData("" + bean.getContent());
        wvAcDetailIntroduce.loadDataWithBaseURL(null, ht, "text/html", "UTF-8", null);

        tcAcDetailType.setText("" + tcAcDetailType.getText() + bean.getRequirementdata_text());
        tcAcDetailCount.setText("" + tcAcDetailCount.getText() + bean.getActivityno());
        tcAcDetailTime.setText("" + tcAcDetailTime.getText() + bean.getActivitystarttime_text());
        tcAcDetailAddress.setText("" + tcAcDetailAddress.getText() + bean.getAddress());
        tcAcDetailOwner.setText("" + tcAcDetailOwner.getText() + bean.getUserid_text().getNickname());
        tcAcDetailPhone.setText("" + tcAcDetailPhone.getText() + bean.getUserid_text().getMobile());
        tcAcDetailRemark.setText("" + tcAcDetailRemark.getText() + "");
    }

    /**
     * 加载html标签
     *
     * @param bodyHTML
     * @return
     */
    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\">" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }


    private String memo = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult requestCode: " + requestCode);
        Log.i(TAG, "onActivityResult resultCode: " + resultCode);
        String memo = data.getExtras().getString(HuoHuoConstants.MEMO);
        Log.i(TAG, "onActivityResult: " + memo);
        ToastUtil.show(this, "读取信息为：" + memo);
        this.memo = memo;
        getSIgnStatusPresenter.getList(AppUtil.getToken(), acid, AppUtil.getUser());

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
        ToastUtil.show(AcDetailActivity.this, "" + msg);
        displayFrameworkBugMessageAndExit("1".equals(msg) ? "已签到" : "已签退");
    }

    @Override
    public void getAcSignUpSuccess(String msg) {
        ToastUtil.show(AcDetailActivity.this, "报名成功" + msg);
        displayFrameworkBugMessageAndExit("报名成功" + msg);
    }

    @Override
    public void onError(Object msg, int code) {
        ToastUtil.show(AcDetailActivity.this, "" + msg);
        displayFrameworkBugMessageAndExit("" + msg);
    }

    @Override
    public void getSignStatusSuccess(String msg) {

        String btcEn = "";
        String message = "";
        if (UN_SIGN_UP.equals(msg) || UN_ENTER.equals(msg)) {
            message = "是否确认签到";
            btcEn = "签到";
        } else if (SIGN_OUT.equals("msg")) {
            ToastUtil.show(this, "已签退");
            return;
        } else {
            message = "是否确认签退";
            btcEn = "签退";
        }
        //判读是否签到，显示对应 dialog
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(AcDetailActivity.this);
        builder.setTitle("系统提示");
        builder.setMessage(message);
        builder.setPositiveButton(btcEn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                acSIgnOutPresenter.getList(AppUtil.getToken(), memo, acid);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtil.show(AcDetailActivity.this, "已取消");
            }
        });
        builder.show();
    }

    private void sendAc() {

        new RxPermissionsUtil(AcDetailActivity.this)
                .getChoosePicPermission(new RxPermissionsCallbackUtil(AcDetailActivity.this, "") {
                    @Override
                    public void allAllow() {
                        startActivityForResult(new Intent(AcDetailActivity.this, CaptureActivity.class), 20000);
                    }
                });

    }

    private void displayFrameworkBugMessageAndExit(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.button_ok, null);
        builder.show();
    }
}
