package com.dian.commonlib.base;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.dian.commonlib.utils.widget.MyBottomSheetDialog;

import butterknife.ButterKnife;

/**
 * Created by kennysun on 2019/8/7.
 */

public abstract class BaseBottomDialog extends MyBottomSheetDialog {
    private BaseActivity mActivity;

    public abstract int getView();

    public abstract void initViewAndData();

    public BaseBottomDialog(Context context) {
        super(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int view = getView();
        if (view > 0) {
            View inflate = View.inflate(getContext(), view, null);
            setContentView(inflate);
            ButterKnife.bind(this, inflate);
            initViewAndData();
        }
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }


}






