package com.dian.commonlib.utils.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kennysun on 2019/8/9.
 */

public class CompoundClickTextView extends AppCompatTextView {
    private int LEFT = 0;
    private int RIGHT = 2;
    private OnDrawableListener onDrawableListener;
    private OnDrawableRightListener onDrawableRightListener;
    private OnDrawableLeftListener onDrawableLeftListener;
    public void setOnDrawableListener(OnDrawableListener onDrawableListener) {
        this.onDrawableListener = onDrawableListener;
    }

    public void setOnDrawableRightListener(OnDrawableRightListener onDrawableRightListener) {
        this.onDrawableRightListener = onDrawableRightListener;
    }

    public void setOnDrawableLeftListener(OnDrawableLeftListener onDrawableLeftListener) {
        this.onDrawableLeftListener = onDrawableLeftListener;
    }

    public CompoundClickTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this.mOnTouchListener);
    }


    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Drawable drawableLeft = getCompoundDrawables()[LEFT];
                    if (drawableLeft != null && event.getRawX() <= getLeft() + drawableLeft.getBounds().width()) {
                        if (onDrawableListener != null)
                            onDrawableListener.onLeft(v, drawableLeft);
                        if (onDrawableLeftListener != null)
                            onDrawableLeftListener.onLeft(v, drawableLeft);
                        return false;
                    }

                    Drawable drawableRight = getCompoundDrawables()[RIGHT];
                    if (drawableRight != null && event.getRawX() >= getRight() - drawableRight.getBounds().width()) {
                        if (onDrawableListener != null)
                            onDrawableListener.onRight(v, drawableRight);
                        if (onDrawableRightListener != null)
                            onDrawableRightListener.onRight(v, drawableRight);
                        return false;
                    }
                    break;
            }
            return false;
        }
    };
}
