package com.yjb.ui.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dian.commonlib.base.BaseDialog;
import com.dian.commonlib.glide.GlideEngine;
import com.google.gson.Gson;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.app.HuoHuoConstants;
import com.yjb.mvp.model.bean.User;
import com.yjb.scan.encode.QrUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by kennysun on 2019/9/3.
 */

public class MyQrDialog extends BaseDialog {
    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.ivQr)
    ImageView ivQr;
    private String jiaMiUserId;
    private User.UserInfo userInfo;

    public MyQrDialog setJiaMiUserId(String jiaMiUserId) {
        this.jiaMiUserId = jiaMiUserId;
        return this;
    }

    public MyQrDialog(Context context) {
        super(context);
    }

    @Override
    public int getView() {
        return R.layout.dialog_my_qr;
    }

    @Override
    public void initViewAndData() {
        userInfo = User.getUser().getUserInfo();
        if (userInfo != null) {
            String headImage = userInfo.getHeadImage();
            GlideEngine.loadRound(ivAvatar, BuildConfig.API_IMG_HOST + headImage);
            tvName.setText(userInfo.getNickName());
        }
        //生成二维码
        if (!TextUtils.isEmpty(jiaMiUserId)) {
            Gson gson = new Gson();
            Map<String, String> map = new HashMap<>();
            map.put(HuoHuoConstants.BTW_TYPE, HuoHuoConstants.ADD_FRIEND);
            map.put(HuoHuoConstants.USER_ID, jiaMiUserId);
            String s = gson.toJson(map);
            Bitmap qrCodeWithLogo = QrUtil.createQRCode(s, 400, 400, null);
            ivQr.setImageBitmap(qrCodeWithLogo);
        }
    }
}
