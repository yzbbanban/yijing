package com.yjb.ui.scan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.yjb.R;
import com.yjb.app.HuoHuoConstants;
import com.yjb.mvp.model.bean.CoinAddressBean;
import com.yjb.mvp.model.bean.ScanBean;
import com.yjb.mvp.presenter.sys.ScanPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 集中处理扫描后的结果
 * Created by kennysun on 2019/9/4.
 */

public class QRHandleUtil {
    private Activity activity;
    private String pagetype;//转币页面点击扫一扫进来的，需要返回转币页面而不是开启转币页面
    private String coinId;
    private String needYZAddress;//需要向后台验证的字符串
    private AlertDialog showShibieFail;
    private int type;//二维码类别
    private ScanPresenter scanPresenter;
    List<CoinAddressBean> resultList;//识别出来的地址列表

    public QRHandleUtil(Activity activity, ScanPresenter scanPresenter, String pagetype, String coinId) {
        this.activity = activity;
        this.scanPresenter = scanPresenter;
        this.pagetype = pagetype;
        this.coinId = coinId;
    }

    //集中处理分析二维码结果后得逻辑
    public void handQrResult(String text) {
        LogUtil.d("text===" + text);
        needYZAddress = text;
        Log.e("aaa", "str==" + text);

        Intent intent = new Intent();
        intent.putExtra(HuoHuoConstants.MEMO, text);
        activity.setResult(20000, intent);
        activity.finish();

    }


    public void showShibieFail(String string) {
//        if (activity instanceof ViewImagesActivity) {
//            //聊天查看大图，长按识别
//            if (type == 1 || type == 0) {//充币地址
//                string = activity.getResources().getString(R.string.find_chongbi_address);
//            }
//        }
//        String s = string + "：\n" + needYZAddress;
//        View inflate = View.inflate(activity, R.layout.layout_scan_result, null);
//        LinkAndEmojiTextView textView = inflate.findViewById(R.id.tvText);
//        textView.setText(needYZAddress);
//        textView.setLinkClick(new MyURLSpan.OnUrlClickListener() {
//            @Override
//            public void onUrlClick(View view, String url) {
//                Intent intent = new Intent(activity, MyWebViewActivity.class);
//                intent.putExtra(Constants.WEBCLOSE, true);
//                intent.putExtra(Constants.WEBTITLE, "");
//                intent.putExtra(Constants.WEBURL, url);
//                activity.startActivity(intent);
//                if (showShibieFail != null) {
//                    showShibieFail.dismiss();
//                }
//
//                if (activity instanceof ScanActivity) {
//                    activity.finish();
//                }
//            }
//        });//打开链接
//        if (activity instanceof ViewImagesActivity) {
//            AlertDialog.Builder builder = new MyDialog.Builder(activity)
//                    .setTitle(string)
//                    .setView(inflate);
//            if (type == 1 || type == 0) {//地址
//                builder.setNegativeButton(R.string.copy, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //复制
//                        ClipboarUtils.copy(activity, needYZAddress);
//                    }
//                }).setPositiveButton(R.string.zhuanzhang, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(activity, ScanCoinActivity.class);
//                        intent.putExtra(MyType.COINADDRESS, (Serializable) resultList);
//                        activity.startActivity(intent);
//                        activity.finish();
//
//                    }
//                });
//            } else {
//                builder
//                        .setPositiveButton(R.string.copy, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                                if (activity instanceof ScanActivity) {
//                                    activity.finish();
//                                }
//                                //复制
//                                ClipboarUtils.copy(activity, needYZAddress);
//                            }
//                        });
//            }
//            showShibieFail = builder.show();
//        } else {
//            showShibieFail = new MyDialog.Builder(activity)
//                    .setTitle(string)
//                    .setCancelable(false)
//                    .setView(inflate)
//                    .setPositiveButton(R.string.iknow, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                            if (activity instanceof ScanActivity) {
//                                activity.finish();
//                            }
//                        }
//                    })
//                    .show();
//        }
    }


    /**
     * 直接扫描
     * 接口验证扫描字符串回来
     *
     * @param scanData
     */
    public void yanzhengResultNormal(ScanBean scanData) {
//        type = scanData.getType();
//        if (type == 2) {//跳转内部链接
//            if (activity instanceof ViewImagesActivity) {
//                showShibieFail(activity.getResources().getString(R.string.shibie_fail));
//            } else {
//                Intent intent = new Intent(activity, HuoHuoWebViewActivity.class);
//                intent.putExtra(Constants.WEBCLOSE, true);
//                intent.putExtra(Constants.WEBTITLE, R.string.web_default_title);
//                intent.putExtra(Constants.WEBURL, needYZAddress);
//                activity.startActivity(intent);
//                activity.finish();
//            }
//        } else if (type == -2) {//加好友
//            getJiamiUserid(scanData.getUserId());
//        } else {
//            List<CoinAddressBean> assetWalletTransferVOList = scanData.getAssetWalletTransferVOList();
//            if (assetWalletTransferVOList != null && assetWalletTransferVOList.size() > 0) {//能识别出来的地址
//                //去重
//                resultList = new ArrayList<>();
//                Set<String> h = new HashSet<>();
//                for (CoinAddressBean coinAddress : assetWalletTransferVOList) {
//                    boolean add = h.add(coinAddress.getEnName());
//                    if (add) {
//                        coinAddress.setType(type);
//                        resultList.add(coinAddress);
//                    }
//                }
//                if (activity instanceof ViewImagesActivity) {
//                    showShibieFail(activity.getResources().getString(R.string.shibie_fail));
//                } else {
//                    Intent intent = new Intent(activity, ScanCoinActivity.class);
//                    intent.putExtra(HuoHuoConstants.COINADDRESS, (Serializable) resultList);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }
//            } else {//不能识别出来的地址
//                showShibieFail(activity.getResources().getString(R.string.shibie_fail));
//            }
//        }
    }

    /**
     * 转币专用验证地址
     * 接口验证扫描字符串回来
     *
     * @param scanData
     */
    public void yanzhengResultZhuanbi(ScanBean scanData) {
//        int type = scanData.getType();
//        List<CoinAddress> assetWalletTransferVOList = scanData.getAssetWalletTransferVOList();
//        if (assetWalletTransferVOList != null && assetWalletTransferVOList.size() > 0) {//能识别出来的地址
//            //判断地址中有没有转账页面传过来的相同的coinid
//            boolean addressZZ = false;
//            CoinAddress coinAddressTemp = null;
//            for (CoinAddress coinAddress : assetWalletTransferVOList) {
//                coinAddress.setType(type);
//                if (String.valueOf(coinAddress.getCoinId()).equals(coinId)) {
//                    addressZZ = true;
//                    coinAddressTemp = coinAddress;
//                }
//            }
//            Intent intent = new Intent();
//            if (addressZZ) {
//                intent.putExtra(MyType.COINADDRESS, coinAddressTemp);
//            } else {
//                CoinAddress coinAddress = new CoinAddress();
//                coinAddress.setAddress(needYZAddress);
//                coinAddress.setType(-1);
//                intent.putExtra(MyType.COINADDRESS, coinAddress);
////                        showShibieFail(getResources().getString(R.string.address_not_match));
//            }
//            activity.setResult(activity.RESULT_OK, intent);
//            activity.finish();
//
//        } else {//不能识别出来的地址
//            CoinAddress coinAddress = new CoinAddress();
//            coinAddress.setAddress(needYZAddress);
//            coinAddress.setType(-1);
//            Intent intent = new Intent();
//            intent.putExtra(MyType.COINADDRESS, coinAddress);
//            activity.setResult(activity.RESULT_OK, intent);
//            activity.finish();
//        }
    }

    /**
     * 识别地址错误
     * 接口返回的识别失败
     */
    public void yanzhengResultError() {
//        if (MyType.ZHUAN_ZHANG.equals(pagetype)) {
//            //不能识别出来的地址
//            CoinAddress coinAddress = new CoinAddress();
//            coinAddress.setAddress(needYZAddress);
//            coinAddress.setType(-1);
//            Intent intent = new Intent();
//            intent.putExtra(MyType.COINADDRESS, coinAddress);
//            activity.setResult(activity.RESULT_OK, intent);
//            activity.finish();
//
//        } else {
//            showShibieFail(activity.getResources().getString(R.string.shibie_fail));
//        }
    }

    /**
     * 获取加密userid
     *
     * @param data
     */
    public void getJiamiUserid(String data) {
//        UserSourceImpl userSource = new UserSourceImpl();
//        UserInfo localUserInfo = userSource.getLocalUserInfo(activity);
//        UserAccount localUserAccount = userSource.getLocalUserAccount(activity);
//        if (localUserInfo != null && localUserAccount != null && String.valueOf(localUserInfo.getId()).equals(data)) {
//            Intent intent = new Intent(activity, MineInfoActivity.class);
//            intent.putExtra(MyType.HEAD, localUserInfo.getHeadImage());
//            intent.putExtra(MyType.NAME, localUserInfo.getNickName());
//            intent.putExtra(MyType.PHONE, localUserAccount.getMobile());
//            intent.putExtra(MyType.USER_ID, localUserInfo.getId());
//            activity.startActivity(intent);
//        } else {
//            Intent intent = new Intent(activity, FriendInfoActivity.class);
//            intent.putExtra(MyType.USER_ID, data);
//            activity.startActivity(intent);
//        }
//        activity.finish();
    }

    public void shibieImgQr(String albumImgPath) {
//        Observable.create(new ObservableOnSubscribe<Result>() {
//            @Override
//            public void subscribe(ObservableEmitter<Result> emitter) throws Exception {
//                //解析图片
//                Result result = DecodeImg.decodeBarcodeRGB(albumImgPath, true);
//                if (result == null) {
//                    result = DecodeImg.parseCode(albumImgPath);
//                }
//                emitter.onNext(result);
//            }
//        }).compose(SchedulerUtil.ioToMain())
//                .subscribe(new Consumer<Result>() {
//                    @Override
//                    public void accept(Result result) throws Exception {
//                        if (result == null) {
//                            ToastUtil.show(CaptureActivity.this, R.string.shibie_fail);
//                        } else {
//                            //识别图片二维码
//                            String text = result.getText();
//                            handQrResult(text);
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        ToastUtil.show(CaptureActivity.this, R.string.shibie_fail);
//                    }
//                });
    }
//

}
