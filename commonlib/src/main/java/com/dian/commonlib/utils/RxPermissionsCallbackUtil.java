package com.dian.commonlib.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.dian.commonlib.R;
import com.tbruyelle.rxpermissions2.Permission;

import io.reactivex.functions.Consumer;

/**
 * 权限管理
 * Created by dian on 2019/2/18.
 */

public abstract class RxPermissionsCallbackUtil implements Consumer<Permission> {
    private Activity activity;
    private String content;

//    public abstract void allAllow(boolean allow);
    public abstract void allAllow();

    public RxPermissionsCallbackUtil(Activity activity, String content) {
        this.activity = activity;
        if (TextUtils.isEmpty(content)){
            content = activity.getResources().getString(R.string.need_open_permissions);
        }
        this.content = content;
    }

    @Override
    public void accept(Permission permission) throws Exception {
        if (permission.granted) {//所有的都同意了
            allAllow();
        } else if (permission.shouldShowRequestPermissionRationale) {
            // At least one denied permission without ask never again
//            showToast("至少有一个权限点击了拒绝但没有点击再也不提示");
//            allAllow(false);
        } else {
            //至少有一个权限点击了拒绝且点击再也不提示
            // At least one denied permission with ask never again
            // Need to go to the settings
            //引导用户至设置页手动授权
            new AlertDialog.Builder(activity)
                    .setMessage(content)
                    .setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                    intent.setData(uri);
                    activity.startActivity(intent);
                }
            }).show();

        }
    }
}
