package com.dian.commonlib.utils;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 软键盘操作
 * Created by dian on 2018/10/8.
 */

public class KeyboardUtil {

    /**
     * 根据传入控件的坐标和用户的焦点坐标，判断是否隐藏键盘，如果点击的位置在控件内，则不隐藏键盘
     *
     * @param etView 控件etView EditText控件
     * @return 是否隐藏
     */
    public static void hideKeyboard(boolean hideKeyboard, View etView, Activity activity) {
        //判断当前键盘是否在输入。如果在输入则不隐藏
        IBinder token = etView.getWindowToken();
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        LogUtil.d("isActive" + inputMethodManager.isActive());
        LogUtil.d("isAcceptingText" + inputMethodManager.isAcceptingText());
        if (inputMethodManager.isActive()) {
            if (hideKeyboard) {
                // 隐藏键盘
                inputMethodManager.hideSoftInputFromWindow(token,
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }

    }

    /**
     * 根据传入控件的坐标和用户的焦点坐标，判断是否隐藏键盘，如果点击的位置在控件内，则不隐藏键盘
     *
     * @param etView  控件etView EditText控件
     * @param content 控件content  点击此view不需要消失
     * @param event   焦点位置
     * @return 是否隐藏
     */
    public static boolean isHideKeyboard(MotionEvent event, View etView, View content, Activity activity) {
        try {
            if (etView != null && etView instanceof EditText) {
                if (content == null)
                    content = etView;
                int[] location = {0, 0};
                content.getLocationInWindow(location);
                int left = location[0],
                        top = location[1],
                        right = left + content.getWidth(),
//                        right = DeviceUtils.deviceWidth(activity),
                        bootom = top + content.getHeight();
                // 判断焦点位置坐标是否在空间内，如果位置在控件外，则隐藏键盘
                if (event.getRawX() < left || event.getRawX() > right
                        || event.getRawY() < top || event.getRawY() > bootom) {
                    // 隐藏键盘
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
