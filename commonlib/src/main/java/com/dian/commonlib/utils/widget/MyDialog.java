package com.dian.commonlib.utils.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

/**
 * Created by kennysun on 2019/8/28.
 */

public class MyDialog  extends AlertDialog {
    protected MyDialog(@NonNull Context context) {
        super(context);
    }

    protected MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }}
