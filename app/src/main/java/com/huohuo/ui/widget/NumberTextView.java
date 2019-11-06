package com.huohuo.ui.widget;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by kennysun on 2019/8/30.
 */

public class NumberTextView extends TextView {
    public NumberTextView(Context context) {
        super(context);
        init(context);
    }

    public NumberTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NumberTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        //得到AssetManager
        AssetManager mgr = context.getAssets();

        //根据路径得到Typeface
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/number.ttf");

        //设置字体
        setTypeface(tf);
    }

}
