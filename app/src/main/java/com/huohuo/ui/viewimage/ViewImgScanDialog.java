package com.huohuo.ui.viewimage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.dian.commonlib.base.BaseBottomDialog;
import com.dian.commonlib.utils.ImgFileUtil;
import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.google.zxing.Result;
import com.huohuo.R;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.mvp.contract.sys.ScanContract;
import com.huohuo.mvp.presenter.sys.ScanPresenter;
import com.huohuo.scan.decode.DecodeImg;
import com.huohuo.ui.scan.QRHandleUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kennysun on 2019/9/4.
 */

public class ViewImgScanDialog extends BaseBottomDialog implements ScanContract.View {
    @BindView(R.id.tvSave)
    TextView tvSave;
    @BindView(R.id.tvShibieQr)
    TextView tvShibieQr;
    @BindView(R.id.tvCancel)
    TextView tvCancel;
    private String image;
    private QRHandleUtil qrHandleUtil;
    private ScanPresenter scanPresenter;
    private Result qrResult;

    public ViewImgScanDialog(Context context) {
        super(context);
    }

    public ViewImgScanDialog setImage(String image) {
        this.image = image;
        return this;
    }

    @Override
    public int getView() {
        return R.layout.dialog_viewimg_scan;
    }

    @Override
    public void initViewAndData() {
        scanPresenter = new ScanPresenter();
        scanPresenter.attachView(this,getBaseActivity());
        qrHandleUtil = new QRHandleUtil(getBaseActivity(), scanPresenter, "", "");
        shibieImgQr(tvShibieQr);
    }

    @OnClick({R.id.tvSave, R.id.tvShibieQr, R.id.tvCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSave:
                saveImg();
                dismiss();
                break;
            case R.id.tvShibieQr:
                //有二维码
                String text = qrResult.getText();
                qrHandleUtil.handQrResult(text);
                dismiss();
                break;
            case R.id.tvCancel:
                dismiss();
                break;
        }
    }


    //识别图中二维码
    private void shibieImgQr(final TextView tvShibieQr) {
        Observable.create((ObservableOnSubscribe<Result>) e -> {
            //通过gilde下载得到file文件,这里需要注意android.permission.INTERNET权限
            File file = Glide.with(getBaseActivity())
                    .load(image)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();

            //解析图片
            Result result = DecodeImg.decodeBarcodeRGB(file.getPath(), true);
            if (result == null) {
                result = DecodeImg.parseCode(file.getPath());
            }
            e.onNext(result);
            e.onComplete();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(qrResult -> {
                    if (qrResult != null) {
                        ViewImgScanDialog.this.qrResult = qrResult;
                        tvShibieQr.setVisibility(View.VISIBLE);
                    }
                }, throwable -> {
                });
    }

    //保存图片
    private void saveImg() {
        Observable.create((ObservableOnSubscribe<File>) e -> {
            //通过gilde下载得到file文件,这里需要注意android.permission.INTERNET权限
            File file = Glide.with(getBaseActivity())
                    .load(image)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            //获取文件类型
            String mimeType = ImgFileUtil.getMimeType(file.getPath());
            String imgType = ".jpg";
            if (!TextUtils.isEmpty(mimeType)) {
                LogUtil.d("name===" + mimeType);
                if (mimeType.contains("/")) {
                    String[] split = mimeType.split("/");
                    imgType = "." + split[1];
                }
            }

            //获取到下载得到的图片，进行本地保存
            File pictureFolder = Environment.getExternalStorageDirectory();
            //第二个参数为你想要保存的目录名称
            File appDir = new File(pictureFolder, HuoHuoConstants.SAVE_IMG_PATH);
            if (!appDir.exists()) {
                appDir.mkdirs();
            }
            String fileName = System.currentTimeMillis() + "Demo" + imgType;
            File destFile = new File(appDir, fileName);
            //把gilde下载得到图片复制到定义好的目录中去
            ImgFileUtil.copy(file, destFile);
            e.onNext(destFile);
            e.onComplete();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(destFile -> {
                    // 最后通知图库更新
                    getBaseActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.fromFile(new File(destFile.getPath()))));
                    ToastUtil.show(getContext(), R.string.save_success);
                }, throwable -> ToastUtil.show(getContext(), R.string.save_fail));
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


//    /**
//     * 接口验证扫描字符串回来
//     *
//     * @param scanData
//     */
//    @Override
//    public void getCoinAddressType(ScanData scanData) {
//        if (scanData != null) {
//            qrHandleUtil.yanzhengResultNormal(scanData);
//        }
//    }
//
//    /**
//     * 转币专用验证地址
//     * 接口验证扫描字符串回来
//     *
//     * @param scanData
//     */
//    @Override
//    public void getTransSend(ScanData scanData) {
//        if (scanData != null) {
//            qrHandleUtil.yanzhengResultZhuanbi(scanData);
//        }
//
//    }
//
//    /**
//     * 识别地址错误
//     */
//    @Override
//    public void getAddressError() {
//        qrHandleUtil.yanzhengResultError();
//    }
//
//    @Override
//    public void loding() {
//
//    }
//
//    @Override
//    public void complete() {
//
//    }
//
//    @Override
//    public void getJiamiUserid(String data) {
//        qrHandleUtil.getJiamiUserid(data);
//    }

}
