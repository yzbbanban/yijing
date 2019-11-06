package com.dian.commonlib.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * Created by kennysun on 2019/8/6.
 */

public class RxPermissionsUtil {
    private RxPermissions rxPermissions;
    private Context context;

    public RxPermissionsUtil(Context context) {
        rxPermissions = new RxPermissions((FragmentActivity) context);
        this.context = context;
    }

    /**
     * 获取相机权限
     *
     * @param onPermissionsCallback
     */
    public void getChoosePicPermission(final RxPermissionsCallbackUtil onPermissionsCallback) {
        rxPermissions.requestEachCombined(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        if (onPermissionsCallback != null)
                            onPermissionsCallback.allAllow();
                    } else if (permission.shouldShowRequestPermissionRationale) {

                    } else {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        context.startActivity(intent);
                    }
                });
    }


    /**
     * 获取读写权限
     *
     * @param onPermissionsCallback
     */
    public void getStoragePermission(final RxPermissionsCallbackUtil onPermissionsCallback) {
        rxPermissions.requestEachCombined(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        if (onPermissionsCallback != null)
                            onPermissionsCallback.allAllow();
                    } else if (permission.shouldShowRequestPermissionRationale) {

                    } else {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        context.startActivity(intent);
                    }
                });
    }

    /**
     * 获取读写手机联系人权限
     *
     * @param onPermissionsCallback
     */
    public void getPhoneContract(final RxPermissionsCallbackUtil onPermissionsCallback) {
        rxPermissions.requestEachCombined(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        if (onPermissionsCallback != null)
                            onPermissionsCallback.allAllow();
                    } else if (permission.shouldShowRequestPermissionRationale) {

                    } else {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        context.startActivity(intent);
                    }
                });
    }
}
