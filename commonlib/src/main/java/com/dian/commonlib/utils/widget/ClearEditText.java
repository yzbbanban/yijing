package com.dian.commonlib.utils.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.dian.commonlib.R;


/**
 * Created by kennysun on 2019/8/7.
 */

public class ClearEditText extends CompoundClickEditText {
    /**
     * TextView四周drawable的序号。
     * 0 left,  1 top, 2 right, 3 bottom
     */
    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_clear, 0);
        setOnDrawableRightListener((v, right) -> setText(""));
    }
}

