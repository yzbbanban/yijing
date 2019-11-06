package com.dian.commonlib.utils.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;

import com.dian.commonlib.R;

/**
 * Created by kennysun on 2019/8/29.
 */

public class MyBottomSheetDialog extends BottomSheetDialog {
    public MyBottomSheetDialog(@NonNull Context context) {
        super(context, R.style.TransparentBottomSheetStyle);
    }
}
