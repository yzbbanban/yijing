package com.dian.commonlib.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dian.commonlib.R;
import com.dian.commonlib.app.Constants;
import com.dian.commonlib.glide.GlideEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.util.List;

/**
 * Created by kennysun on 2019/9/4.
 */

public class MyMatisseUtil {
    public static void matisse(Activity activity, int forResult, int count,boolean capture) {
        RxPermissionsUtil permissionsUtil = new RxPermissionsUtil(activity);
        permissionsUtil.getChoosePicPermission(new RxPermissionsCallbackUtil(activity,"") {
            @Override
            public void allAllow() {
                Matisse.from(activity)
                        .choose(MimeType.ofImage(),false)
                        .countable(true)
                        .capture(capture)  //是否可以拍照
                        .maxSelectable(count)
                        .captureStrategy(//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                                new CaptureStrategy(true, Constants.PIC_PATH))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .originalEnable(true)
                        .maxOriginalSize(10)
                        .forResult(forResult);
            }
        });
    }
}
