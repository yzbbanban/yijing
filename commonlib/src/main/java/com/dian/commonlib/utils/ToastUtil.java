package com.dian.commonlib.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by dian on 2019/2/22.
 */

public class ToastUtil {
    public static Toast toast;

    public static void show(Context context, Object text) {
        if (context == null) {
            return;
        }
        String msg = "";
        if (text instanceof String) {
            msg = (String) text;
        } else if (text instanceof Integer) {
            msg = context.getResources().getString((int) text);
        }
        if (toast == null)
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        else
            toast.setText(msg);
        toast.show();
    }

    public static void showLong(Context context, Object text) {
        if (context == null) {
            return;
        }
        String msg = "";
        if (text instanceof String) {
            msg = (String) text;
        } else if (text instanceof Integer) {
            msg = context.getResources().getString((int) text);
        }
        if (toast == null)
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        else
            toast.setText(msg);
        toast.show();
    }

}
