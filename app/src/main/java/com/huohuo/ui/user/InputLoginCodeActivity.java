package com.huohuo.ui.user;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.dian.commonlib.base.BaseLoadActivity;
import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.MmkvUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.dian.commonlib.utils.widget.IdentifyingCodeView;
import com.dian.commonlib.utils.widget.MultipleStatusView;
import com.dian.commonlib.utils.widget.MyDialog;
import com.geetest.sdk.Bind.GT3GeetestBindListener;
import com.geetest.sdk.Bind.GT3GeetestUtilsBind;
import com.geetest.sdk.GTCallBack;
import com.google.gson.Gson;
import com.huohuo.BuildConfig;
import com.huohuo.R;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.mvp.contract.user.InputLoginCodeContract;
import com.huohuo.mvp.model.bean.JiYanData;
import com.huohuo.mvp.presenter.user.InputLoginCodePresenter;
import com.huohuo.ui.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by kennysun on 2019/8/28.
 */

public class InputLoginCodeActivity extends BaseLoadActivity implements InputLoginCodeContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.codeView)
    IdentifyingCodeView codeView;
    @BindView(R.id.acbBtn)
    Button acbBtn;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private String phone;
    private String phoneCode;
    private InputLoginCodePresenter inputLoginCodePresenter;

    String loginType;
    //极验
    /**
     * api1，需替换成自己的服务器URL
     */
    private static final String captchaURL = BuildConfig.API_HOST + "v1/verifyCode/init";
    /**
     * api2，需替换成自己的服务器URL
     */
    private static final String validateURL = BuildConfig.API_HOST + "v1/verifyCode/verify";
    private GT3GeetestUtilsBind gt3GeetestUtils;


    private String textContent;
    private String gestureLock;

    @Override
    public void retry() {

    }

    @Override
    public MultipleStatusView getMultipleStatusView() {
        return multipleStatusView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_input_login_code;
    }

    @Override
    public void initViewAndData() {
        super.initViewAndData();
        setToolbarConfig(toolbar, R.string.input_code);
        inputLoginCodePresenter = new InputLoginCodePresenter();
        inputLoginCodePresenter.attachView(this, this);
        loginType = getIntent().getStringExtra(HuoHuoConstants.TYPE);
        gestureLock = getIntent().getStringExtra(HuoHuoConstants.GESTURE_LOCK);
        phone = getIntent().getStringExtra(HuoHuoConstants.PHONE);
        phoneCode = getIntent().getStringExtra(HuoHuoConstants.COUNTRY_CODE);
        codeView.setInputCompleteListener(new IdentifyingCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                chageBtnState(codeView.getTextContent());
            }

            @Override
            public void deleteContent() {
                chageBtnState(codeView.getTextContent());
            }
        });
        initGeeTest();      //极验初始化
    }

    /**
     * 极验初始化
     */
    public void initGeeTest() {
        gt3GeetestUtils = new GT3GeetestUtilsBind(this);
        // 设置debug模式，开代理抓包可使用，默认关闭，TODO 生产环境务必设置为false
        gt3GeetestUtils.setDebug(false);
        // 设置加载webview超时时间，单位毫秒，默认15000，仅且webview加载静态文件超时，不包括之前的http请求
        gt3GeetestUtils.setTimeout(15000);
        // 设置webview请求超时(用户点选或滑动完成，前端请求后端接口)，单位毫秒，默认10000
        gt3GeetestUtils.setWebviewTimeout(10000);
    }

    private void chageBtnState(String content) {
        if (TextUtils.isEmpty(content) || content.length() < 6) {
            acbBtn.setEnabled(false);
        } else {
            acbBtn.setEnabled(true);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);      //自动弹出键盘
    }

    @OnClick(R.id.acbBtn)
    public void onViewClicked() {
        inputLoginCodePresenter.login(phoneCode, phone, codeView.getTextContent());
    }

    @Override
    public void loginSuccess() {
        ToastUtil.show(this, R.string.login_success);
        if (HuoHuoConstants.LoginType.FORGET_GETTURE_LOCK.name().equals(loginType)) {
            MmkvUtil.encodeBoolean(gestureLock, false);
        } else if (HuoHuoConstants.LoginType.FINGERPRINT_ERROR_MORE.name().equals(loginType)) {      //指纹密码错误过多
            // TODO: 2018\10\19 0019 暂时保留
        } else if (HuoHuoConstants.LoginType.THIRD_PAY.name().equals(loginType)) {                   //支付
            Intent intent = new Intent();
            intent.putExtra(HuoHuoConstants.TYPE, HuoHuoConstants.LoginType.THIRD_PAY.name());
            setResult(RESULT_OK, intent);
            finish();
            return;
        }
        startActivity(new Intent(this, MainActivity.class));
    }


    @Override
    public void geeTest() {
        // 开启LoadDialog 第二个参数为lang（语言，如果为null则为系统语言）
        gt3GeetestUtils.showLoadingDialog(this, null);
        // 设置是否可以点击Dialog灰色区域关闭验证码
        gt3GeetestUtils.setDialogTouch(false);
        inputLoginCodePresenter.jiYanApi1();
    }

    @Override
    public void codeError() {
        new MyDialog.Builder(this)
                .setMessage(R.string.code_error)
                .setPositiveButton(R.string.sure, (dialog, which) -> {
                    dialog.dismiss();
                    codeView.clearAllText();
                }).show();
    }

    /**
     * 第一次调用 api1
     *
     * @param data
     */
    @Override
    public void jiYanApi1(JiYanData data) {
        data.setNew_captcha(true);
        Gson gson = new Gson();
        String s = gson.toJson(data);
        try {
            JSONObject jsonObject = new JSONObject(s);
            continueVerify(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jiYanApi2() {
        gt3GeetestUtils.gt3TestFinish();
        inputLoginCodePresenter.login(phoneCode, phone, textContent);
    }


    /**
     * 开始验证 TODO 自定义api1及自定义api2示例
     */
    private void continueVerify(JSONObject parmas) {
        gt3GeetestUtils.gtSetApi1Json(parmas);
        gt3GeetestUtils.getGeetest(InputLoginCodeActivity.this, captchaURL, validateURL, null, new GT3GeetestBindListener() {

            /**
             * @param num 1: 点击验证码的关闭按钮, 2: 点击屏幕关闭验证码, 3: 点击返回键关闭验证码
             */
            @Override
            public void gt3CloseDialog(int num) {
                LogUtil.d("gt3CloseDialog-->num: " + num);
            }

            /**
             * 为API1接口添加数据，数据拼接在URL后，API1接口默认get请求
             */
            @Override
            public Map<String, String> gt3CaptchaApi1() {
                LogUtil.d("gt3CaptchaApi1");
                Map<String, String> map = new HashMap<String, String>();
                map.put("time", "" + System.currentTimeMillis());
                return map;
            }

            /**
             * api1接口返回数据
             */
            @Override
            public void gt3FirstResult(JSONObject jsonObject) {
                LogUtil.d("gt3FirstResult-->" + jsonObject);
            }


            /**
             * 准备完成，即将弹出验证码
             */
            @Override
            public void gt3DialogReady() {
                LogUtil.d("gt3DialogReady");
            }

            /**
             * 数据统计，从开启验证到成功加载验证码结束，具体解释详见GitHub文档
             */
            @Override
            public void gt3GeetestStatisticsJson(JSONObject jsonObject) {
                LogUtil.d("gt3GeetestStatisticsJson-->" + jsonObject);
            }

            /**
             * 返回是否自定义api2，true为自定义api2
             * false： gt3GetDialogResult(String result)，返回api2需要参数
             * true： gt3GetDialogResult(boolean a, String result)，返回api2需要的参数
             */
            @Override
            public boolean gt3SetIsCustom() {
                LogUtil.d("gt3SetIsCustom");
                return true;
            }

            /**
             * 用户滑动或点选完成后触发，gt3SetIsCustom配置为false才走此接口
             *
             * @param result api2接口需要参数
             */
            @Override
            public void gt3GetDialogResult(String result) {
                LogUtil.d("gt3GetDialogResult-->" + result);
            }

            /**
             * 用户滑动或点选完成后触发，gt3SetIsCustom配置为true才走此接口
             *
             * @param status 验证是否成功
             * @param result api2接口需要参数
             */
            @Override
            public void gt3GetDialogResult(boolean status, String result) {
                LogUtil.d("gt3GetDialogResult-->status: " + status + "result: " + result);
                if (status) {
                    try {
                        // 1.取出该接口返回的三个参数用于自定义二次验证
                        JSONObject jsonObject = new JSONObject(result);
                        String challenge = jsonObject.getString("geetest_challenge");
                        String validate = jsonObject.getString("geetest_validate");
                        String seccode = jsonObject.getString("geetest_seccode");
                        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), seccode);
                        inputLoginCodePresenter.jiYanApi2(challenge, validate, phone, phoneCode, requestBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    gt3GeetestUtils.gt3TestClose();
                }
            }

            /**
             * 为API2接口添加数据，数据拼接在URL后，API2接口默认get请求
             * 默认已有数据：geetest_challenge，geetest_validate，geetest_seccode
             * TODO 注意： 切勿重复添加以上数据
             */
            @Override
            public Map<String, String> gt3SecondResult() {
                LogUtil.d("gt3SecondResult");
                Map<String, String> map = new HashMap<String, String>();
                map.put("test", "test");
                return map;
            }

            /**
             * api2完成回调，判断是否验证成功，且成功调用gt3TestFinish，失败调用gt3TestClose
             *
             * @param result api2接口返回数据
             */
            @Override
            public void gt3DialogSuccessResult(String result) {
                LogUtil.d("gt3DialogSuccessResult-->" + result);
                if (!TextUtils.isEmpty(result)) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String status = jsonObject.getString("status");
                        if ("success".equals(status)) {
                            gt3GeetestUtils.gt3TestFinish();
                            // 设置loading消失回调
                            gt3GeetestUtils.setGtCallBack(new GTCallBack() {
                                @Override
                                public void onCallBack() {
                                    // 跳转其他页面操作等
                                }
                            });
                        } else {
                            gt3GeetestUtils.gt3TestClose();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    gt3GeetestUtils.gt3TestClose();
                }
            }

            /**
             * @param error 返回错误码，具体解释见GitHub文档
             */
            @Override
            public void gt3DialogOnError(String error) {
                LogUtil.d("gt3DialogOnError-->" + error);
            }
        });
    }

}
