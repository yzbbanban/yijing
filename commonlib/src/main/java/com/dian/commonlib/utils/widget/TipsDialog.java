package com.dian.commonlib.utils.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.dian.commonlib.R;

/**
 * Created by kennysun on 2019/8/30.
 */

public class TipsDialog {
    public AlertDialog showTipsDialog(Context context, Object msg, String sure, String cancle, DialogInterface.OnClickListener sureListener, DialogInterface.OnClickListener cancleListener) {
        String message;
        String mSure;
        String mCancle;
        if (msg instanceof Integer) {
            message = context.getResources().getString((Integer) msg);
        } else {
            message = (String) msg;
        }
        if (TextUtils.isEmpty(sure)) {
            mSure = context.getResources().getString(R.string.sure);
        } else {
            mSure = sure;
        }
        if (TextUtils.isEmpty(cancle)) {
            mCancle = context.getResources().getString(R.string.cancle);
        } else {
            mCancle = cancle;
        }

        if (cancleListener == null) {
            cancleListener = (dialog, which) -> dialog.dismiss();
        }

        if (sureListener == null) {
            sureListener = (dialog, which) -> dialog.dismiss();
        }

        AlertDialog show = new MyDialog.Builder(context).setMessage(message)
                .setPositiveButton(mSure, sureListener)
                .setNegativeButton(mCancle, cancleListener)
                .show();
        return show;
    }
}
