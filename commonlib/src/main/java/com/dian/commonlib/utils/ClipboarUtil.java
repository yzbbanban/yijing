package com.dian.commonlib.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

/**
 * 剪切板工具类
 * Created by kennysun on 2019/8/13.
 */

public class ClipboarUtil {

    private static final String TAG = ClipboarUtil.class.getSimpleName();

    private ClipboarUtil() {
    }

    public static CharSequence getText(Context context) {
        ClipboardManager clipboard = getManager(context);
        ClipData clip = clipboard.getPrimaryClip();
        return hasText(context) ? clip.getItemAt(0).coerceToText(context) : null;
    }

    public static void setText(CharSequence text, Context context) {
        if (text != null) {
            try {
                getManager(context).setPrimaryClip(ClipData.newPlainText(null, text));
            } catch (NullPointerException | IllegalStateException | SecurityException e) {
                // Have seen this in the wild, bizarrely
                Log.w(TAG, "Clipboard bug", e);
            }
        }
    }

    public static boolean hasText(Context context) {
        ClipboardManager clipboard = getManager(context);
        ClipData clip = clipboard.getPrimaryClip();
        return clip != null && clip.getItemCount() > 0;
    }

    private static ClipboardManager getManager(Context context) {
        return (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }

}
